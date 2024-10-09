package com.limyel.haoyuan.cloud.mall.trade.service.mq;

import com.limyel.haoyuan.cloud.mall.product.dto.StockRecotdExist;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.cloud.mall.trade.entity.OrderEntity;
import com.limyel.haoyuan.cloud.mall.trade.service.OrderService;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.cloud.mall.product.api.StockRecordFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RocketMQTransactionListener
@RequiredArgsConstructor
public class OrderTxMsgListener implements RocketMQLocalTransactionListener {

    private final OrderService orderService;

    private final StringRedisTemplate redisTemplate;

    private final StockRecordFeignClient stockRecordFeignClient;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String orderSn = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        return doSubmitOrder(orderSn);
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String orderSn = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);

        // 回查超过三次，提交事务消息
        String key = "ORDER:CHECK-LOCAL-TX:" + orderSn;
        redisTemplate.opsForValue().increment(key);
        String value = redisTemplate.opsForValue().get(key);
        if (value != null && Integer.parseInt(value) > 3) {
            redisTemplate.delete(key);
            return RocketMQLocalTransactionState.COMMIT;
        }

        try {
            // 检查库存扣减情况
            List<StockRecotdExist> stockRecords = stockRecordFeignClient.checkExist(orderSn);
            // 没有库存流水记录，返回 UNKNOWN
            // 因为可能扣减库存 orderService.deductStock() 执行的时间过长，库存服务还没执行完，所以查不到流水，如果这时候就 ROLLBACK 会出问题
            if (stockRecords.isEmpty() || stockRecords.get(0).getSkuNum() != stockRecords.size()) {
                return RocketMQLocalTransactionState.UNKNOWN;
            }

            // 检查订单状态
            OrderEntity order = orderService.getBySn(orderSn);
            if (order == null) {
                if (stockRecords.get(0).getCreateTime().plusMinutes(2).isAfter(LocalDateTime.now())) {
                    return RocketMQLocalTransactionState.UNKNOWN;
                } else {
                    return RocketMQLocalTransactionState.COMMIT;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /**
     * 执行提交订单操作，扣减库存、创建订单
     * @param orderSn
     * @return
     */
    private RocketMQLocalTransactionState doSubmitOrder(String orderSn) {
        // 从 redis 中取回 OrderSubmitDTO
        String s = redisTemplate.opsForValue().get("ORDER:SUBMIT:" + orderSn);
        if (!StringUtils.hasText(s)) {
            throw new ServiceException("订单数据异常，请重新下单");
        }
        OrderSubmitDTO dto = JSONUtil.parseObject(s, OrderSubmitDTO.class);

        try {
            orderService.deductStock(dto.getOrderItems(), dto.getOrderSn());
        } catch (Exception e) {
            e.printStackTrace();
            // 如果扣减库存过程中出现库存不足、网络或者其他异常，直接提交半消息
            return RocketMQLocalTransactionState.COMMIT;
        }

        try {
            orderService.create(dto);
        } catch (Exception e) {
            // 如果创建订单的过程中出现异常，提交半消息
            return RocketMQLocalTransactionState.COMMIT;
        }
        // 扣减库存、创建订单成功，回滚半消息
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}

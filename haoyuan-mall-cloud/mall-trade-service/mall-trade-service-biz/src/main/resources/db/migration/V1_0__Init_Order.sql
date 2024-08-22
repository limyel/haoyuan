DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
    `total_amount` bigint NOT NULL DEFAULT 0 COMMENT '订单总额（分）',
    `total_quantity` int NOT NULL DEFAULT 0 COMMENT '商品总数',
    `status` int NOT NULL DEFAULT 101 COMMENT '订单状态，101-待支付，102-用户取消，103-系统取消, 201-已完成',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `coupon_id` bigint NULL DEFAULT NULL COMMENT '使用的优惠券',
    `coupon_amount` bigint NOT NULL DEFAULT 0 COMMENT '优惠券抵扣金额（分）',
    `payment_amount` bigint NOT NULL DEFAULT 0 COMMENT '应付总额（分）',
    `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
    `payment_method` int NULL DEFAULT NULL COMMENT '支付方式，0-积分，1-余额，2-积分+余额',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';


DROP TABLE IF EXISTS `trade_order_item`;
CREATE TABLE `trade_order_item` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `spu_id` bigint NOT NULL COMMENT '商品ID',
    `spu_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
    `pic_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型，0-一次性，1-订阅',
    `price` bigint NOT NULL COMMENT '商品单价（分）',
    `quantity` int NOT NULL COMMENT '商品数量',
    `total_amount` bigint NOT NULL DEFAULT 0 COMMENT '商品总价（分）',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单商品信息表';


DROP TABLE IF EXISTS `trade_user_spu`;
CREATE TABLE `trade_user_spu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `spu_id` bigint NOT NULL COMMENT '商品ID',
    `pic_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片',
    `spu_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型，0-一次性，1-订阅',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '数量',
    `subscribe_time` datetime NULL DEFAULT NULL COMMENT '订阅时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id_spu_id` (`user_id`, `spu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户商品表';

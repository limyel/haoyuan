package com.limyel.haoyuan.common.cloud.loadbalancer;

import com.limyel.haoyuan.common.cloud.constant.LoadBalancerConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultRequestContext;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestData;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class CanaryRule implements ReactorServiceInstanceLoadBalancer {

    private final String serviceId;

    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    private final AtomicInteger position;

    /**
     * 负载均衡策略选择服务器的入口方法
     * @param request
     * @return
     */
    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next()
                .map(serviceInstances -> processInstanceResponse(supplier, serviceInstances, request));
    }

    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
                                                              List<ServiceInstance> serviceInstances, Request request) {
        return getInstanceResponse(serviceInstances, request);
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
        if (CollectionUtils.isEmpty(instances)) {
            log.warn("Nacos 没有可用实例: {}.", serviceId);
            return new EmptyResponse();
        }

        // 从请求的 Header 中获取特定的流量打标值
        DefaultRequestContext context = (DefaultRequestContext) request.getContext();
        RequestData requestData = (RequestData) context.getClientRequest();
        HttpHeaders headers = requestData.getHeaders();
        String trafficVersion = headers.getFirst(LoadBalancerConstant.TRAFFIC_VERSION);

        if (!StringUtils.hasText(trafficVersion)) {
            List<ServiceInstance> noneCanaryInstances = instances.stream()
                    .filter(e -> !e.getMetadata().containsKey(LoadBalancerConstant.TRAFFIC_VERSION))
                    .toList();
            return getRoundRobinInstance(noneCanaryInstances);
        }

        List<ServiceInstance> canaryInstances = instances.stream().filter(e -> {
            String trafficVersionMetadata = e.getMetadata().get(LoadBalancerConstant.TRAFFIC_VERSION);
            return StringUtils.endsWithIgnoreCase(trafficVersionMetadata, trafficVersion);
        }).toList();
        return getRoundRobinInstance(canaryInstances);
    }

    private Response<ServiceInstance> getRoundRobinInstance(List<ServiceInstance> instances) {
        //
        if (instances.isEmpty()) {
            log.warn("没有可提供服务的可用节点: {}", serviceId);
            return new EmptyResponse();
        }

        int pos = Math.abs(this.position.incrementAndGet());
        ServiceInstance instance = instances.get(pos % instances.size());
        return new DefaultResponse(instance);
    }

}

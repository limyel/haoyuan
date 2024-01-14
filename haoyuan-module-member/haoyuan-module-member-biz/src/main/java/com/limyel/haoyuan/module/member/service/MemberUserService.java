package com.limyel.haoyuan.module.member.service;

import com.limyel.haoyuan.module.member.vo.UserVO;
import com.limyel.haoyuan.module.product.api.sku.ProductSkuFacade;
import com.limyel.haoyuan.module.product.api.sku.vo.SkuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductSkuFacade productSkuFacade;

    private static final String QUERY_PRODUCT_INFO_URL = "http://%s:%s/product/sku/%d";

    public UserVO get(String username) {
        UserVO result = new UserVO();
        result.setUsername(username);

        ServiceInstance serviceInstance = discoveryClient.getInstances("mall-product")
                .stream().findAny().get();

        SkuVO sku = productSkuFacade.get(2L);

        result.setSku(sku);
        return result;
    }


}

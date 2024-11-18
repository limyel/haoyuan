package com.limyel.cloud.product.service;

import com.limyel.cloud.product.dao.ProductDao;
import com.limyel.cloud.product.model.dto.product.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public int create(ProductDTO dto) {
        return 0;
    }

    public int delete(List<Long> ids) {
        return 0;
    }
    
    public int update(ProductDTO dto) {
        return 0;
    }

}

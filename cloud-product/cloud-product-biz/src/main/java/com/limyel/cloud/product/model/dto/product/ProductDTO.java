package com.limyel.cloud.product.model.dto.product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Long price;

    private String desc;

    private Integer stock;

    private Integer status;

}

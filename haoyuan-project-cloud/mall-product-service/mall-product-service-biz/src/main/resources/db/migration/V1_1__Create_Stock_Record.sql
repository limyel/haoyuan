DROP TABLE IF EXISTS `product_stock_record`;
CREATE TABLE `product_stock_record` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sku_id` bigint NOT NULL COMMENT 'SKU ID',
    `quantity` int NOT NULL COMMENT '商品数量',
    `sku_num` int NOT NULL COMMENT 'SKU 数量',
    `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SPU表';

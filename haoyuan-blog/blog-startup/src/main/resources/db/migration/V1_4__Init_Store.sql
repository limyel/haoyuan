CREATE TABLE `store_product` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `image` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `price` bigint NOT NULL COMMENT '价格（分）',
    `stock` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存数量',
    `locked_stock` int NOT NULL DEFAULT 0 COMMENT '库存锁定数量',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型，0-一次性，1-包月订阅',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-下架 1-正常',
    `member_level_id` bigint NULL DEFAULT NULL COMMENT '可购买的会员等级',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品';


CREATE TABLE `store_order` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
    `total_amount` bigint NOT NULL DEFAULT 0 COMMENT '订单总额（分）',
    `total_quantity` int NOT NULL DEFAULT 0 COMMENT '商品总数',
    `status` int NOT NULL DEFAULT 101 COMMENT '订单状态，101-待支付，102-用户取消，103-系统取消, 201-已完成',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `payment_amount` bigint NOT NULL DEFAULT 0 COMMENT '应付总额（分）',
    `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';


CREATE TABLE `store_order_item` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `price` bigint NOT NULL COMMENT '价格',
    `quantity` int NOT NULL COMMENT '数量',
    `total_amount` bigint NOT NULL DEFAULT 0 COMMENT '商品总价（分）',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单商品信息';


CREATE TABLE `store_user_product` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `quantity` int NOT NULL COMMENT '数量',
    `subscribe_time` datetime NULL DEFAULT NULL COMMENT '订阅时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id_product_id` (`user_id`, `product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户商品表';


CREATE TABLE `store_stock_rule` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id` bigint NOT NULL COMMENT '商品ID',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '更新类型，0-手动更新，1-定时定量更新，2-定时不定量更新',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '更新数量',
    `min_quantity` int NOT NULL DEFAULT 0 COMMENT '最少更新数量',
    `max_quantity` int NOT NULL DEFAULT 0 COMMENT '最多更新数量',
    `cron` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新 cron 表达式',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='库存更新规则表';

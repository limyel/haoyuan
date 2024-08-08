DROP TABLE IF EXISTS `product_spu`;
CREATE TABLE `product_spu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pic_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `price` bigint NOT NULL COMMENT '价格（分）',
    `stock` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '库存数量',
    `locked_stock` int NOT NULL DEFAULT 0 COMMENT '库存锁定数量',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型，0-一次性，1-订阅',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-下架 1-正常',
    `member_level_id` bigint NULL DEFAULT NULL COMMENT '可购买的会员等级',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品表';


DROP TABLE IF EXISTS `product_user_spu`;
CREATE TABLE `product_user_spu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `pic_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品图片',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型，0-一次性，1-订阅',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '数量',
    `subscribe_time` datetime NULL DEFAULT NULL COMMENT '订阅时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户商品表';


DROP TABLE IF EXISTS `product_stock_rule`;
CREATE TABLE `product_stock_rule` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `spu_id` bigint NOT NULL COMMENT '商品ID',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '更新类型，0-手动更新，1-定时定量更新，2-定时不定量更新',
    `quantity` int NOT NULL DEFAULT 0 COMMENT '更新数量',
    `min_quantity` int NOT NULL DEFAULT 0 COMMENT '最少更新数量',
    `max_quantity` int NOT NULL DEFAULT 0 COMMENT '最多更新数量',
    `cron` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '更新 cron 表达式',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0-关闭，1-开启',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='库存更新规则表';

CREATE TABLE `member_point` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `point` int NOT NULL DEFAULT 0 COMMENT '积分',
    `locked_point` int NOT NULL DEFAULT 0 COMMENT '锁定的积分',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单商品信息';


CREATE TABLE `member_point_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `point_id` bigint NOT NULL COMMENT '积分ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `type` tinyint NOT NULL COMMENT '变动类型，0-减少，1-增加',
    `changed_point` int NOT NULL DEFAULT 0 COMMENT '变动的积分',
    `reason` varchar(128) NOT NULL COMMENT '变动原因',
    `result` tinyint NOT NULL DEFAULT 0 COMMENT '变动结果，0-失败，1-成功',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单商品信息';



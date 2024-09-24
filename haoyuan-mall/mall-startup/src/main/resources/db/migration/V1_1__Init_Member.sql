DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `point` bigint NOT NULL DEFAULT 0 COMMENT '积分',
    `balance` bigint NOT NULL DEFAULT 0 COMMENT '余额',
    `locked_point` bigint NULL DEFAULT NULL COMMENT '锁定积分',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0-封禁，1-正常',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员用户表';

INSERT INTO `member_user` (id, user_id, point, balance, status) VALUES
    (1, 1, 2000, 0, 1),
    (2, 2, 8000, 3000, 1),
    (3, 3, 6000, 1500, 1),
    (4, 4, 6000, 1500, 1),
    (5, 5, 7000, 1500, 1),
    (6, 6, 6000, 1500, 1),
    (7, 7, 6000, 1500, 1),
    (8, 8, 6000, 1500, 1),
    (9, 9, 8000, 3000, 1),
    (10, 10, 6000, 1500, 1),
    (11, 11, 5000, 1500, 1),
    (12, 12, 5000, 1500, 1),
    (13, 12, 3000, 1000, 1),
    (14, 12, 5000, 1500, 1),
    (15, 12, 5000, 1500, 1),
    (16, 12, 1000, 500, 1),
    (17, 12, 1000, 500, 1),
    (18, 12, 1000, 500, 1);


DROP TABLE IF EXISTS `member_pay_log`;
CREATE TABLE `member_pay_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `type` tinyint NOT NULL COMMENT '变动类型，0-减少，1-增加',
    `changed_point` bigint NOT NULL DEFAULT 0 COMMENT '变动的积分',
    `changed_balance` bigint NOT NULL DEFAULT 0 COMMENT '变动的余额',
    `reason` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '变动原因',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='积分日志表';


DROP TABLE IF EXISTS `member_level`;
CREATE TABLE `member_level` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '等级名称',
    `point` bigint NOT NULL DEFAULT 0 COMMENT '需要的积分',
    `sort` int NOT NULL DEFAULT 0 COMMENT '展示顺序',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员等级表';

INSERT INTO `member_level` (id, name, point, sort) VALUES
    (1, '青铜', 0, 1),
    (2, '白银', 500, 2),
    (3, '黄金', 1500, 3),
    (4, '钻石', 3500, 4);
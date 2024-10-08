DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `point` bigint NOT NULL DEFAULT 0 COMMENT '积分',
    `balance` bigint NOT NULL DEFAULT 0 COMMENT '余额',
    `locked_point` bigint NULL DEFAULT NULL COMMENT '锁定积分',
    `blog_username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '博客管理员用户名',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0-封禁，1-正常',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员用户表';

INSERT INTO `member_user` (id, username, mobile, password, point, balance, status) VALUES
    (1, 'limyel', '13000000000', '{noop}123456', 2000, 0, 1),
    (2, 'liu_bei', '13000000000', '{noop}123456', 8000, 3000, 1),
    (3, 'guan_yu', '13000000000', '{noop}123456', 6000, 1500, 1),
    (4, 'zhang_fei', '13000000000', '{noop}123456', 6000, 1500, 1),
    (5, 'zhu_ge_liang', '13000000000', '{noop}123456', 7000, 1500, 1),
    (6, 'zhao_yun', '13000000000', '{noop}123456', 6000, 1500, 1),
    (7, 'ma_chao', '13000000000', '{noop}123456', 6000, 1500, 1),
    (8, 'huang_zhong', '13000000000', '{noop}123456', 6000, 1500, 1),
    (9, 'cao_cao', '13000000000', '{noop}123456', 8000, 3000, 1),
    (10, 'guo_jia', '13000000000', '{noop}123456', 6000, 1500, 1),
    (11, 'zhang_liao', '13000000000', '{noop}123456', 5000, 1500, 1),
    (12, 'le_jin', '13000000000', '{noop}123456', 5000, 1500, 1),
    (13, 'yu_jin', '13000000000', '{noop}123456', 3000, 1000, 1),
    (14, 'zhang_he', '13000000000', '{noop}123456', 5000, 1500, 1),
    (15, 'xu_huang', '13000000000', '{noop}123456', 5000, 1500, 1),
    (16, 'si_ma_yi', '13000000000', '{noop}123456', 1000, 500, 0),
    (17, 'si_ma_shi', '13000000000', '{noop}123456', 1000, 500, 0),
    (18, 'si_ma_zhao', '13000000000', '{noop}123456', 1000, 500, 0);



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


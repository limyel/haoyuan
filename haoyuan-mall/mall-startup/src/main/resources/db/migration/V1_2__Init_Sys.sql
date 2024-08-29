DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像',
    `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-禁用，1-正常',
    `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';

INSERT INTO `sys_user` (id, dept_id, username, email, mobile, avatar, password, status, remark) VALUES
    (1, null, 'admin', 'limyel@outlook.com', '13000000000', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (2, null, 'liu_bei', 'liu_bei@outlook.com', '13000000100', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (3, null, 'guan_yu', 'guan_yu@outlook.com', '13000000101', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (4, null, 'zhang_fei', 'zhang_fei@outlook.com', '13000000102', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (5, null, 'zhu_ge_liang', 'zhu_ge_liang@outlook.com', '13000000103', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (6, null, 'zhao_yun', 'zhao_yun@outlook.com', '13000000104', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (7, null, 'ma_chao', 'ma_chao@outlook.com', '13000000105', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (8, null, 'huang_zhong', 'huang_zhong@outlook.com', '13000000106', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (9, null, 'cao_cao', 'cao_cao@outlook.com', '13000000200', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (10, null, 'guo_jia', 'guo_jia@outlook.com', '13000000201', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (11, null, 'zhang_liao', 'zhang_liao@outlook.com', '13000000203', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (12, null, 'le_jin', 'le_jin@outlook.com', '13000000204', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (13, null, 'yu_jin', 'yu_jin@outlook.com', '13000000205', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (14, null, 'zhang_he', 'zhang_he@outlook.com', '13000000206', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (15, null, 'xu_huang', 'xu_huang@outlook.com', '13000000207', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 1, ''),
    (16, null, 'si_ma_yi', 'si_ma_yi@outlook.com', '13000000400', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 0, ''),
    (17, null, 'si_ma_shi', 'si_ma_shi@outlook.com', '13000000401', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 0, ''),
    (18, null, 'si_ma_zhao', 'si_ma_zhao@outlook.com', '13000000402', '', '$2a$10$AhVZZnoXZu3EVNBwdhJYOe61BAK6YFlA9pVzjg2RoqXk0AV7ZH1Iy', 0, '');


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
    `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-禁用，1-正常',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
    `sys_user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`sys_user_id`, `role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';


DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
    `pid` bigint NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '路由地址',
    `component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '组件路径',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '菜单类型，0-目录，1-菜单，2-按钮',
    `visible` bit(1) NOT NULL DEFAULT 1 COMMENT '是否显示',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-禁用，1-正常',
    `perms` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '#' COMMENT '图标',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';


DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';


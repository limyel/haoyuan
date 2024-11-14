DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `dept_id` bigint NOT NULL DEFAULT 0 COMMENT '部门ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `realname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
    `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像',
    `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-正常，1-停用',
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

INSERT INTO `sys_user` (id, dept_id, username, realname, email, mobile, avatar, password) VALUES
    (1, 0, 'super_admin', '超级管理员', 'super_admin@limyel.com', '13000000000', '', '{noop}123456'),
    (2, 100, 'admin', '管理员', 'admin@limyel.com', '13100000000', '', '{noop}123456'),
    (3, 100, 'employee', '员工', 'employee@limyel.com', '13200000000', '', '{noop}123456'),
    (4, 101, 'member_admin', '会员管理员', 'member_admin@limyel.com', '13300000000', '', '{noop}123456'),
    (5, 102, 'mall_admin', '商城管理员', 'mall_admin@limyel.com', '13400000000', '', '{noop}123456'),
    (6, 103, 'todo_admin', '待办管理员', 'todo_admin@limyel.com', '13500000000', '', '{noop}123456');


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
    `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-正常，1-停用',
    `type` int NOT NULL DEFAULT 1 COMMENT '角色类型，0-系统内置角色 1-用户自定义角色',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

INSERT INTO `role` (id, name, code, sort, type) VALUES
    (1, '超级管理员', 'super_admin', 0, 0),
    (2, '员工', 'employee', 1, 1),
    (3, '访客', 'guest', 2, 1),
    (4, '会员管理员', 'member_admin', 3, 1),
    (5, '商城管理员', 'mall_admin', 4, 1),
    (6, '待办管理员', 'todo_admin', 5, 1);


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
    `sys_user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`sys_user_id`, `role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';


DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
    `pid` bigint NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路由地址',
    `component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
    `type` tinyint NOT NULL DEFAULT 0 COMMENT '菜单类型，0-菜单，1-按钮',
    `visible` bit(1) NOT NULL DEFAULT 1 COMMENT '是否显示',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-正常，1-停用',
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

INSERT INTO `menu` (id, name, pid, sort, path, component, type, perms, icon) VALUES
    (1, '权限管理', 0, 0, NULL, NULL, 0, '', '#'),
    (100, '用户管理', 1, 0, 'sys/user', NULL, 0, '', '#'),
    (10000, '查看', 100, 0, NULL, NULL, 1, 'sys:user:page,sys:user:get', '#'),
    (10001, '新增', 100, 1, NULL, NULL, 1, 'sys:user:create', '#'),
    (10002, '修改', 100, 2, NULL, NULL, 1, 'sys:user:update', '#'),
    (10003, '删除', 100, 3, NULL, NULL, 1, 'sys:user:delete', '#'),
    (10004, '导出', 100, 4, NULL, NULL, 1, 'sys:user:export', '#'),
    (101, '部门管理', 1, 1, 'sys/dept', NULL, 0, '', '#'),
    (10100, '查看', 101, 0, NULL, NULL, 1, 'sys:dept:page,sys:dept:get', '#'),
    (10101, '新增', 101, 1, NULL, NULL, 1, 'sys:dept:create', '#'),
    (10102, '修改', 101, 2, NULL, NULL, 1, 'sys:dept:update', '#'),
    (10103, '删除', 101, 3, NULL, NULL, 1, 'sys:dept:delete', '#'),
    (102, '角色管理', 1, 2, 'sys/role', NULL, 0, '', '#'),
    (10200, '查看', 102, 0, NULL, NULL, 1, 'sys:role:page,sys:role:get', '#'),
    (10201, '新增', 102, 1, NULL, NULL, 1, 'sys:role:create', '#'),
    (10202, '修改', 102, 2, NULL, NULL, 1, 'sys:role:update', '#'),
    (10203, '删除', 102, 3, NULL, NULL, 1, 'sys:role:delete', '#'),
    (103, '菜单管理', 1, 3, 'sys/menu', NULL, 0, '', '#'),
    (10300, '查看', 103, 0, NULL, NULL, 1, 'sys:menu:list,sys:menu:get', '#'),
    (10301, '新增', 103, 1, NULL, NULL, 1, 'sys:menu:create', '#'),
    (10302, '修改', 103, 2, NULL, NULL, 1, 'sys:menu:update', '#'),
    (10303, '删除', 103, 3, NULL, NULL, 1, 'sys:menu:delete', '#'),

    (2, '系统设置', 0, 1, NULL, NULL, 0, '', '#'),
    (200, '参数管理', 2, 0, 'sys/params', NULL, 0, '', '#'),
    (20000, '查看', 200, 0, NULL, NULL, 1, 'sys:params:page,sys:params:get', '#'),
    (20001, '新增', 200, 1, NULL, NULL, 1, 'sys:params:create', '#'),
    (20002, '修改', 200, 2, NULL, NULL, 1, 'sys:params:update', '#'),
    (20003, '删除', 200, 3, NULL, NULL, 1, 'sys:params:delete', '#'),
    (20004, '导出', 200, 4, NULL, NULL, 1, 'sys:params:export', '#'),
    (201, '字典管理', 2, 1, 'sys/dict-type', NULL, 0, '', '#'),
    (20100, '查看', 201, 0, NULL, NULL, 1, 'sys:dict:page,sys:dict:get', '#'),
    (20101, '新增', 201, 1, NULL, NULL, 1, 'sys:dict:create', '#'),
    (20102, '修改', 201, 2, NULL, NULL, 1, 'sys:dict:update', '#'),
    (20103, '删除', 201, 3, NULL, NULL, 1, 'sys:dict:delete', '#'),

    (3, '日志管理', 0, 2, NULL, NULL, 0, '', '#'),
    (300, '登录日志', 3, 0, 'sys/log-login', NULL, 0, 'sys:log:login', '#'),
    (301, '操作日志', 3, 1, 'sys/log-operation', NULL, 0, 'sys:log:operation', '#'),
    (302, '异常日志', 3, 2, 'sys/log-error', NULL, 0, 'sys:log:error', '#'),

    (4, '会员管理', 0, 3, NULL, NULL, 0, '', '#'),
    (400, '会员用户', 4, 0, 'member/user', NULL, 0, '', '#'),

    (5, '商城管理', 0, 4, NULL, NULL, 0, '', '#'),

    (6, '待办管理', 0, 5, NULL, NULL, 0, '', '#');


DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';

INSERT INTO `role_menu` (role_id, menu_id) VALUES
    (1, 1),
    (1, 100), (1, 10000), (1, 10001), (1, 10002), (1, 10003), (1, 10004),
    (1, 101), (1, 10100), (1, 10101), (1, 10102), (1, 10103),
    (1, 102), (1, 10200), (1, 10201), (1, 10202), (1, 10203),
    (1, 103), (1, 10300), (1, 10301), (1, 10302), (1, 10303),
    (1, 2),
    (1, 200), (1, 20000), (1, 20001), (1, 20002), (1, 20003), (1, 20004),
    (1, 201), (1, 20100), (1, 20101), (1, 20102), (1, 20103),
    (1, 3),
    (1, 300), (1, 301), (1, 302),
    (1, 4),
    (1, 5),
    (1, 6),

    (4, 4),

    (5, 5),

    (6, 6);


DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `pid` bigint NOT NULL DEFAULT 0 COMMENT '上级 ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门名称',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `leader` bigint NOT NULL DEFAULT 0 COMMENT '负责人',
    `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0-正常，1-停用',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

INSERT INTO `dept` (id, pid, name, sort, leader, email, mobile) VALUES
    (1, 0, '总部', 0, 2, 'admin@limyel.com', '13100000000'),
    (100, 1, '系统研发部', 1, 2, 'admin@limyel.com', '13100000000'),
    (101, 1, '会员研发部', 2, 4, 'member_admin@limyel.com', '13300000000'),
    (102, 1, '商城研发部', 3, 5, 'mall_admin@limyel.com', '13400000000'),
    (103, 1, '待办研发部', 4, 6, 'todo_admin@limyel.com', '13500000000');


DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `dict_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
    `dict_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典类型',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型表';


DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `dict_type_id` bigint NOT NULL DEFAULT 0 COMMENT '字典类型 ID',
    `dict_label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典标签',
    `dict_value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典值表';


DROP TABLE IF EXISTS `params`;
CREATE TABLE `params` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `param_label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数标签',
    `param_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数编码',
    `param_value` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数值',
    `type` tinyint NOT NULL DEFAULT 1 COMMENT '参数类型，0-系统参数 1-非系统参数',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统参数表';


DROP TABLE IF EXISTS `log_operation`;
CREATE TABLE `log_operation` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块标题',
    `biz_type` int NOT NULL DEFAULT 0 COMMENT '业务类型，0-其他，1-新增，2-修改，3-删除',
    `method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求方法',
    `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求 URL',
    `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主机地址',
    `param` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求参数',
    `json_result` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '响应结果',
    `status` int NOT NULL DEFAULT 0 COMMENT '状态，0-正常，1-异常',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';


DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主机地址',
    `status` int NOT NULL DEFAULT 0 COMMENT '状态，0-成功 1-失败',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录日志表';


DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `type` int NOT NULL COMMENT '公告类型，1-通知 2-公告',
    `content` text NOT NULL COMMENT '内容',
    `status` int NOT NULL DEFAULT 0 COMMENT '状态，0-正常 1-关闭',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知公告表';


DROP TABLE IF EXISTS `oss`;
CREATE TABLE `oss` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `filename` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '文件名',
    `original_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '原名',
    `suffix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '后缀名',
    `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'URL 地址',
    `service_type` int NOT NULL COMMENT '服务商类型',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OSS 对象存储表';
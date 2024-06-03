CREATE TABLE `sys_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
    `post_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '岗位ID数组',
    `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
    `gender` tinyint NOT NULL DEFAULT 0 COMMENT '性别',
    `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像',
    `super_admin` bit(1) NOT NULL DEFAULT b'0' COMMENT '超级管理员，0：否，1：是',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '账号状态',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表';


CREATE TABLE `sys_user_role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户和角色关联表';


CREATE TABLE `sys_user_post` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户ID',
    `post_id` bigint NOT NULL DEFAULT 0 COMMENT '岗位ID',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户和岗位关联表';


CREATE TABLE `sys_role_menu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色ID',
    `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单ID',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色和菜单关联表';

INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100101);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100102);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100103);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100104);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100105);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100201);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100202);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100203);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100204);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100205);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100301);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100302);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100303);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100304);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100305);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100401);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100402);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100403);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100404);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100405);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100501);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100502);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100503);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100504);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100505);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100601);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100602);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100603);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100604);
INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (1, 100605);


CREATE TABLE `sys_role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
    `code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `data_scope` tinyint NOT NULL DEFAULT 1 COMMENT '数据范围',
    `data_scope_dept_ids` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
    `type` tinyint NOT NULL DEFAULT 2 COMMENT '类型',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表';

INSERT INTO `sys_role` (`id`, `name`, `code`, `sort`, `data_scope`, `data_scope_dept_ids`, `type`) VALUES (1, '管理员', 'admin', 1, 1, '', 1);


CREATE TABLE `sys_post` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '岗位表';


CREATE TABLE `sys_operate_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `user_type` tinyint NOT NULL DEFAULT 0 COMMENT '用户类型',
    `module` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模块标题',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作名',
    `type` bigint NOT NULL DEFAULT 0 COMMENT '操作分类',
    `content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作内容',
    `exts` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '拓展字段',
    `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求方法名',
    `request_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '请求地址',
    `user_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户 IP',
    `user_agent` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '浏览器 UA',
    `java_method` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'Java 方法名',
    `java_method_args` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'Java 方法的参数',
    `start_time` datetime NOT NULL COMMENT '操作时间',
    `duration` int NOT NULL COMMENT '执行时长',
    `result_code` int NOT NULL DEFAULT 0 COMMENT '结果码',
    `result_msg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果提示',
    `result_data` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果数据',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志记录';


CREATE TABLE `sys_menu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
    `permissions` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `type` tinyint NOT NULL DEFAULT 1 COMMENT '菜单类型',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `pid` bigint NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '路由地址',
    `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
    `component_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组件名',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '菜单状态',
    `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单权限表';

INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (10, '系统管理', '', 1, 1, 0, '/system', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1001, '用户管理', '', 2, 1, 10, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100101, '用户查询', 'sys:user:list', 3, 1, 1001, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100102, '用户详情', 'sys:user:get', 3, 2, 1001, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100103, '用户新增', 'sys:user:create', 3, 3, 1001, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100104, '用户修改', 'sys:user:update', 3, 4, 1001, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100105, '用户删除', 'sys:user:delete', 3, 5, 1001, '/system/user', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1002, '角色管理', '', 2, 2, 10, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100201, '角色查询', 'sys:menu:list', 3, 1, 1002, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100202, '角色详情', 'sys:menu:get', 3, 2, 1002, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100203, '角色新增', 'sys:user:create', 3, 3, 1002, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100204, '角色修改', 'sys:user:update', 3, 4, 1002, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100205, '角色删除', 'sys:user:delete', 3, 5, 1002, '/system/role', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1003, '菜单管理', '', 2, 3, 10, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100301, '菜单查询', 'sys:menu:list', 3, 1, 1003, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100302, '菜单详情', 'sys:menu:get', 3, 2, 1003, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100303, '菜单新增', 'sys:menu:create', 3, 3, 1003, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100304, '菜单修改', 'sys:menu:update', 3, 4, 1003, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100305, '菜单删除', 'sys:menu:delete', 3, 5, 1003, '/system/menu', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1004, '部门管理', '', 2, 4, 10, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100401, '部门查询', 'sys:dept:list', 3, 1, 1004, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100402, '部门详情', 'sys:dept:get', 3, 2, 1004, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100403, '部门新增', 'sys:dept:create', 3, 3, 1004, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100404, '部门修改', 'sys:dept:update', 3, 4, 1004, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100405, '部门删除', 'sys:dept:delete', 3, 5, 1004, '/system/dept', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1005, '岗位管理', '', 2, 5, 10, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100501, '岗位查询', 'sys:post:list', 3, 1, 1005, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100502, '岗位详情', 'sys:post:get', 3, 2, 1005, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100503, '岗位新增', 'sys:post:create', 3, 3, 1005, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100504, '岗位修改', 'sys:post:update', 3, 4, 1005, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100505, '岗位删除', 'sys:post:delete', 3, 5, 1005, '/system/post', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (1006, '字典管理', '', 2, 6, 10, '/system/dict', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100601, '字典查询', 'sys:dict:list', 3, 1, 1006, '/system/dict', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100602, '字典详情', 'sys:dict:get', 3, 2, 1006, '/system/dict', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100603, '字典新增', 'sys:dict:create', 3, 3, 1006, '/system/dict', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100604, '字典修改', 'sys:dict:update', 3, 4, 1006, '/system/dict', '', '', '');
INSERT INTO `sys_menu` (`id`, `name`, `permissions`, `type`, `sort`, `pid`, `path`, `icon`, `component`, `component_name`)
    VALUES (100605, '字典删除', 'sys:dict:delete', 3, 5, 1006, '/system/dict', '', '', '');


CREATE TABLE `sys_login_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `log_type` tinyint NOT NULL COMMENT '日志类型',
    `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户编号',
    `user_type` tinyint NOT NULL DEFAULT 0 COMMENT '用户类型',
    `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户账号',
    `result` tinyint NOT NULL COMMENT '登陆结果',
    `user_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户 IP',
    `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '浏览器 UA',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统访问记录';


CREATE TABLE `sys_dict_type` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典名称',
    `type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `dict_type`(`type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典类型表';


CREATE TABLE `sys_dict_data`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sort` int NOT NULL DEFAULT 0 COMMENT '字典排序',
    `label` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典标签',
    `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典键值',
    `dict_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
    `color_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '颜色类型',
    `css_class` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT 'css 样式',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典数据表';


CREATE TABLE `sys_dept`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
    `pid` bigint NOT NULL DEFAULT 0 COMMENT '上级部门ID',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `leader_id` bigint NOT NULL DEFAULT 0 COMMENT '负责人ID',
    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '联系电话',
    `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
    `status` tinyint NOT NULL DEFAULT 1 COMMENT '部门状态',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '部门表';


CREATE TABLE `sys_oauth2_access_token`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `user_type` tinyint NOT NULL COMMENT '用户类型',
    `access_token` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
    `refresh_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
    `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
    `expires_time` datetime NOT NULL COMMENT '过期时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
#     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_access_token`(`access_token` ASC) USING BTREE,
    INDEX `idx_refresh_token`(`refresh_token` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OAuth2 访问令牌';


CREATE TABLE `sys_param`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '编码',
    `value` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '参数值',
    `type` tinyint NOT NULL DEFAULT 2 COMMENT '类型，1：内置参数，2：自定义参数',
    `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统参数';


CREATE TABLE `sys_oss` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `url` varchar(512) NOT NULL COMMENT '文件地址',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'OSS 存储';


CREATE TABLE `sys_user_token` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id` bigint NOT NULL COMMENT '用户 ID',
    `token` varchar(256) NOT NULL COMMENT 'token',
    `expire_time` datetime NOT NULL COMMENT '过期时间',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户 token';

CREATE TABLE `project_project` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '项目地址',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-停止维护 1-维护',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目';

INSERT INTO `project_project` (id, name, summary, url, status) VALUES
    (1, 'haoyuan', '对一些后端技术的练手项目，以及各种造轮子。', 'https://github.com/limyel/haoyuan', 1),
    (2, 'blog-ui', '个人博客的前端页面。', 'https://github.com/limyel/blog-ui', 1),
    (3, 'mall-ui', '一个简单的商城前端页面。', 'https://github.com/limyel/mall-ui', 1),
    (4, 'mall-admin', '一个简单的管理系统前端页面。', 'https://github.com/limyel/mall-admin', 1),
    (5, 'sqlim', '出于学习的目的，基于 Java 实现的一个关系型数据库。', 'https://github.com/limyel/sqlim', 1);


CREATE TABLE `project_commit_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `project_id` bigint NOT NULL COMMENT '项目 ID',
    `commit_num` int NOT NULL COMMENT 'commit 次数',
    `commit_time` datetime DEFAULT NULL COMMENT '提交时间',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目提交日志';
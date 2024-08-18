CREATE TABLE `project_project` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '项目地址',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-停止维护 1-维护',
    `last_commit_time` datetime DEFAULT NULL COMMENT '最后提交时间',
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
    (2, 'haoyuan-ui', '对一些前端技术的练手项目。', 'https://github.com/limyel/haoyuan-ui', 1),
    (3, 'whatis-sql', '什么是 SQL？一个基于 Java 实现的简易关系型数据库。', 'https://github.com/limyel/whatis-sql', 1),
    (4, 'whatis-kv', '什么是 key-value？一个基于 Java 实现的简易键值对数据库。', 'https://github.com/limyel/whatis-kv', 1),
    (5, 'whatis-mq', '什么是 MQ？一个基于 Java 实现的简易消息队列。', 'https://github.com/limyel/whatis-mq', 1);
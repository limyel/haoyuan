CREATE TABLE `wiki_cover` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `slug` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'slug',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
    `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-草稿 1-发布',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='wiki 封面';


CREATE TABLE `wiki_catalog` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `wiki_id` bigint NOT NULL COMMENT 'wiki ID',
    `post_id` bigint NOT NULL COMMENT '文章 ID',
    `post_slug` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章 slug',
    `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `level` tinyint NOT NULL DEFAULT '1' COMMENT '目录层级',
    `pid` bigint NOT NULL DEFAULT '0' COMMENT '父目录 ID',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='wiki 目录';


ALTER TABLE `main_post` add column `type` tinyint NOT NULL DEFAULT '1' COMMENT '文章类型 - 1：普通文章，2：收录于知识库';

ALTER TABLE `main_post_content` ADD COLUMN `create_by` bigint NULL DEFAULT NULL COMMENT '创建者';
ALTER TABLE `main_post_content` ADD COLUMN `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
ALTER TABLE `main_post_content` ADD COLUMN `update_by` bigint NULL DEFAULT NULL COMMENT '更新者';
ALTER TABLE `main_post_content` ADD COLUMN `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
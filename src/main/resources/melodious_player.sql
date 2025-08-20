/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80040
 Source Host           : localhost:3306
 Source Schema         : melodious_player

 Target Server Type    : MySQL
 Target Server Version : 80040
 File Encoding         : 65001

 Date: 20/08/2025 14:16:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for home_item
-- ----------------------------
DROP TABLE IF EXISTS `home_item`;
CREATE TABLE `home_item`
(
    `id`             int                                                           NOT NULL AUTO_INCREMENT COMMENT '音乐ID',
    `type`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
    `title`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
    `artist_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家姓名',
    `description`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `poster_pic`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '海报图片',
    `thumbnail_pic`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
    `lyric`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌词',
    `url`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '音乐链接',
    `hd_url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '高品质音乐链接',
    `uhd_url`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超高品质音乐链接',
    `music_size`     float                                                         NULL DEFAULT NULL COMMENT '音乐大小',
    `hd_music_size`  float                                                         NULL DEFAULT NULL COMMENT '高品质音乐大小',
    `uhd_music_size` float                                                         NULL DEFAULT NULL COMMENT '超高品质音乐大小',
    `status`         int                                                           NULL DEFAULT NULL COMMENT '状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '首页在线音乐数据项'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_item
-- ----------------------------
INSERT INTO `home_item`
VALUES (1, 'audio/mpeg', '这个年纪', '齐一', '国语', '20250810125433000000801.jpeg', '20250810125538000000881.jpg',
        '20250810125625000000918.lrc', '20250810125712000000459.mp3', '20250810125712000000459.mp3',
        '20250810125712000000459.mp3', 10.9, 10.9, 10.9, 0);
INSERT INTO `home_item`
VALUES (2, 'audio/mpeg', '感谢你曾来过2017版', 'Ayo97; 阿涵',
        '这首歌讲述的是一对恋人，以前在一起很恩爱，随着相处时间，女孩因为种种原因离开了男孩，离别的痛苦然而又割舍不得，曾经一起的回忆历历在目，感谢他曾经的来过，虽然只是个过客，但也深深相爱过，最终两人没能走到一起走到最后。',
        '20250721013540000000631.jpg', '20250721013544000000747.jpg', '20250725010545000000044.lrc',
        '20250721013601000000893.mp3', '20250721013601000000893.mp3', '20250721013601000000893.mp3', 9.1, 9.1, 9.1, 0);
INSERT INTO `home_item`
VALUES (3, 'audio/mpeg', '我的天空', '南征北战',
        '《我的天空》是一首较有影响力的现象级励志歌曲，其目标受众是即将参加高考的学生。它自问世后一直人气颇高，被歌迷和网友广为传唱。',
        '20250721014709000000253.jpg', '20250721014712000000920.jpg', NULL, '20250721014736000000441.mp3',
        '20250721014736000000441.mp3', '20250721014736000000441.mp3', 9.2, 9.2, 9.2, 0);
INSERT INTO `home_item`
VALUES (4, 'audio/mpeg', '体面', '于文文',
        '在拿到《前任3：再见前任》剧本之后，于文文就产生了音乐创作的想法。随着电影《前任3：再见前任》越来越深入的拍摄，让于文文对于角色林佳产生了更多的情感共鸣，而沉浸在人物里的她，需要用音乐创作去宣泄情绪和表达态度。于是，于文文以“林佳”为创作灵感 ，为影片量身定制了该曲。',
        '20250721015434000000918.jpg', '20250721015438000000207.jpg', NULL, '20250721015459000000859.mp3',
        '20250721015459000000859.mp3', '20250721015459000000859.mp3', 10.8, 10.8, 10.8, 0);
INSERT INTO `home_item`
VALUES (5, 'audio/mpeg', '此生不换', '青鸟飞鱼',
        '《此生不换》是电视剧《仙剑奇侠传三》的插曲，创作者在歌词中写道“喝不完忘情泉不让你如烟”，以此暗喻着剧中的两位主人公徐长卿与紫萱在全剧的终章虽共饮忘情之水、试图斩断情丝，却终究难以割舍对彼此的深情眷恋，并希望表达出二人不愿让彼此刻骨铭心的爱恋如烟消散、徒留遗憾的情感。',
        '20250721020029000000862.jpg', '20250721020033000000191.jpg', NULL, '20250721020050000000183.mp3',
        '20250721020050000000183.mp3', '20250721020050000000183.mp3', 10.2, 10.2, 10.2, 0);
INSERT INTO `home_item`
VALUES (6, 'audio/mpeg', '就忘了吧（DJAh版）', '1K',
        '《就忘了吧》 是一首以都市情感为主题的歌曲，通过直白的叙事风格与反复的“忘了吧”核心句式，刻画了失恋后自我挣扎与释怀的心理过程。',
        '20250725110544000000126.jpg', '20250725110547000000464.jpg', NULL, '20250725110600000000970.mp3',
        '20250725110600000000970.mp3', '20250725110600000000970.mp3', 8.7, 8.7, 8.7, 0);
INSERT INTO `home_item`
VALUES (8, 'audio/mpeg', '曾经的你', '许巍',
        '《曾经的你》是一首对青春岁月的怀念之歌和无悔宣言，让我们更清楚的知道许巍是从哪里来的，他的过往其实和你我一样，\"有难过也有精彩\"。',
        '20250728012021000000159.jpeg', '20250728012025000000055.jpeg', '20250728012033000000195.lrc',
        '20250728012047000000986.mp3', '20250728012047000000986.mp3', '20250728012047000000986.mp3', 9.9, 9.9, 9.9, 0);

-- ----------------------------
-- Table structure for mv
-- ----------------------------
DROP TABLE IF EXISTS `mv`;
CREATE TABLE `mv`
(
    `id`                     int                                                           NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `title`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
    `description`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `artist_name`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家姓名',
    `poster_pic`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '海报',
    `thumbnail_pic`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
    `regdate`                datetime                                                      NULL DEFAULT NULL COMMENT '发行时间',
    `video_source_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频源类型名',
    `total_views`            int                                                           NULL DEFAULT NULL COMMENT '总浏览量',
    `total_pc_views`         int                                                           NULL DEFAULT NULL COMMENT 'PC端浏览量',
    `total_mobile_views`     int                                                           NULL DEFAULT NULL COMMENT '手机端浏览量',
    `total_comments`         int                                                           NULL DEFAULT NULL COMMENT '评论数',
    `url`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
    `hd_url`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '高清视频链接',
    `uhd_url`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超高清视频链接',
    `video_size`             float                                                         NULL DEFAULT NULL COMMENT '视频大小',
    `hd_video_size`          float                                                         NULL DEFAULT NULL COMMENT '高清视频大小',
    `uhd_video_size`         float                                                         NULL DEFAULT NULL COMMENT '超高清视频大小',
    `duration`               time                                                          NULL DEFAULT NULL COMMENT '时长',
    `status`                 int                                                           NULL DEFAULT NULL COMMENT '状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'MV'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv
-- ----------------------------
INSERT INTO `mv`
VALUES (1, '这个年纪',
        '人生已过三十载，而立之年的我，并没有走过很多的路，并没有爱过很多的人，并没有很清楚的记得这三十年过往的经历以及擦肩的过客。',
        '齐一', '20250811020919000000630.jpg', '20250811021031000000961.jpg', '2022-08-29 22:10:52', 'video/mp4', 100,
        200, 300, 0, '20250811021143000000254.mp4', '20250811021143000000254.mp4', '20250811021143000000254.mp4', 25.5,
        25.5, 25.5, '00:06:18', 0);
INSERT INTO `mv`
VALUES (2, '感谢你曾来过',
        '歌曲中充满了对过去美好时光的深情回忆，以及对那个曾经来过自己世界的人的深深思念。尽管对方已经离去，但那份记忆和思念却如影随形，难以割舍。',
        'Ayo97; 阿涵', '20250728125023000000210.jpg', '20250728125026000000764.jpg', '2025-07-28 12:50:29', 'video/mp4',
        NULL, NULL, NULL, NULL, '20250728125036000000227.mp4', '20250728125036000000227.mp4',
        '20250728125036000000227.mp4', 16.2, 16.2, 16.2, '00:04:05', 0);
INSERT INTO `mv`
VALUES (3, '我的天空', '歌曲普遍围绕\"挣脱束缚、追逐自由\"展开，通过意象化表达传递直面挫折、坚守梦想的信念。', '南征北战',
        '20250728010815000000581.jpg', '20250728010818000000940.jpg', '2025-07-28 13:08:22', 'video/mp4', NULL, NULL,
        NULL, NULL, '20250728010831000000224.mp4', '20250728010831000000224.mp4', '20250728010831000000224.mp4', 14, 14,
        14, '00:03:59', 0);
INSERT INTO `mv`
VALUES (4, '不为谁而作的歌',
        '林俊杰通过此曲传递对音乐与生命的思考，结合自身经历创作，旨在感谢那些曾在他低谷期给予帮助的陌生人及团队成员。',
        '林俊杰', '20250731103835000000579.jpg', '20250731103839000000724.jpg', '2025-07-31 10:38:43', 'video/mp4',
        NULL, NULL, NULL, NULL, '20250731104030000000489.mp4', '20250731104030000000489.mp4',
        '20250731104030000000489.mp4', 22.4, 22.4, 22.4, '00:04:22', 0);
INSERT INTO `mv`
VALUES (5, '体面',
        '《体面》是一首容易引起大众共鸣的情歌，歌曲旋律委婉动听，伤感且带着洒脱，韵味十足，道出了失恋者对逝去爱情感到遗憾心酸却不得已要洒脱放手的心声。',
        '于文文', '20250731105304000000063.jpg', '20250731105307000000305.jpg', '2025-07-31 10:53:09', 'video/mp4',
        NULL, NULL, NULL, NULL, '20250731105409000000231.mp4', '20250731105409000000231.mp4',
        '20250731105409000000231.mp4', 8.7, 8.7, 8.7, '00:04:39', 0);
INSERT INTO `mv`
VALUES (6, '此生不换',
        '《此生不换》的歌词宛如一幅细腻入微的画卷，以深情笔触缓缓勾勒出长卿与紫萱之间跨越三生三世的凄美爱恋。', '青鸟飞鱼',
        '20250731110119000000106.jpg', '20250731110122000000332.jpg', '2025-07-31 11:01:24', 'video/mp4', NULL, NULL,
        NULL, NULL, '20250731110221000000473.mp4', '20250731110221000000473.mp4', '20250731110221000000473.mp4', 23.1,
        23.1, 23.1, '00:04:16', 0);
INSERT INTO `mv`
VALUES (7, '就忘了吧', '《就忘了吧》 歌词以情感纠葛为主题，通过回忆与释怀交织的叙述，展现了现代情感关系中的挣扎与成长。',
        '1K', '20250731111057000000730.jpg', '20250731111101000000404.jpg', '2025-07-31 11:11:04', 'video/mp4', NULL,
        NULL, NULL, NULL, '20250731111204000000348.mp4', '20250731111204000000348.mp4', '20250731111204000000348.mp4',
        17.7, 17.7, 17.7, '00:03:45', 0);

-- ----------------------------
-- Table structure for mv_area
-- ----------------------------
DROP TABLE IF EXISTS `mv_area`;
CREATE TABLE `mv_area`
(
    `id`   int                                                           NOT NULL AUTO_INCREMENT COMMENT 'MV地区ID',
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区名',
    `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'MV区域'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv_area
-- ----------------------------
INSERT INTO `mv_area`
VALUES (1, '内地篇', 'ML');
INSERT INTO `mv_area`
VALUES (2, '韩国篇', 'KR');
INSERT INTO `mv_area`
VALUES (3, '港台篇', 'HT');
INSERT INTO `mv_area`
VALUES (4, '日本篇', 'JP');
INSERT INTO `mv_area`
VALUES (5, '欧美篇', 'US');

-- ----------------------------
-- Table structure for mv_area_code
-- ----------------------------
DROP TABLE IF EXISTS `mv_area_code`;
CREATE TABLE `mv_area_code`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT 'MV区域代码ID',
    `mv_id`      int NULL DEFAULT NULL COMMENT 'MVID',
    `mv_area_id` int NULL DEFAULT NULL COMMENT 'MV区域ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv_area_code
-- ----------------------------
INSERT INTO `mv_area_code`
VALUES (1, 1, 1);
INSERT INTO `mv_area_code`
VALUES (2, 2, 2);
INSERT INTO `mv_area_code`
VALUES (3, 3, 3);
INSERT INTO `mv_area_code`
VALUES (4, 4, 1);
INSERT INTO `mv_area_code`
VALUES (5, 5, 1);
INSERT INTO `mv_area_code`
VALUES (6, 6, 1);
INSERT INTO `mv_area_code`
VALUES (7, 7, 1);

-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play`
(
    `id`              int                                                           NOT NULL AUTO_INCREMENT COMMENT '音乐清单ID',
    `title`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
    `thumbnail_pic`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
    `video_count`     int                                                           NULL DEFAULT NULL COMMENT '视频数量',
    `description`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `category`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '种类',
    `status`          int                                                           NULL DEFAULT NULL COMMENT '状态',
    `total_views`     int                                                           NULL DEFAULT NULL COMMENT '浏览量',
    `total_favorites` int                                                           NULL DEFAULT NULL COMMENT '点赞量',
    `update_time`     datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    `created_time`    datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `integral`        int                                                           NULL DEFAULT NULL COMMENT '积分',
    `week_integral`   int                                                           NULL DEFAULT NULL COMMENT '周积分',
    `total_user`      int                                                           NULL DEFAULT NULL COMMENT '总积分',
    `rank`            int                                                           NULL DEFAULT NULL COMMENT '排名',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '音乐清单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play
-- ----------------------------
INSERT INTO `play`
VALUES (1, '经典歌曲', '20250812113504000000669.jpg', 4, '国语', '国语', 0, 3, 5, '2025-08-12 11:35:10',
        '2025-08-02 10:40:11', 3, 5, 8, 1);
INSERT INTO `play`
VALUES (3, '流行音乐', '20250809112046000000445.jpg', 3, '在这座不夜城中，每个人都是匆匆过客。', '', 0, 0, 0,
        '2025-08-09 11:20:06', '2025-08-09 11:22:05', 0, 0, 0, 0);
INSERT INTO `play`
VALUES (4, '蓝色孤本收藏家', '20250809010633000000461.jpeg', 3, '孤独，是那些深夜里的寂寞时光，与冷月为伴，秋水生凉。', '',
        0, 0, 0, '2025-08-09 13:06:14', '2025-08-09 13:07:39', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for play_mv
-- ----------------------------
DROP TABLE IF EXISTS `play_mv`;
CREATE TABLE `play_mv`
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '音乐清单MV主键ID',
    `play_id` int NULL DEFAULT NULL COMMENT '音乐清单ID',
    `mv_id`   int NULL DEFAULT NULL COMMENT 'MVID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play_mv
-- ----------------------------
INSERT INTO `play_mv`
VALUES (12, 3, 2);
INSERT INTO `play_mv`
VALUES (13, 3, 3);
INSERT INTO `play_mv`
VALUES (14, 3, 6);
INSERT INTO `play_mv`
VALUES (15, 4, 1);
INSERT INTO `play_mv`
VALUES (16, 4, 2);
INSERT INTO `play_mv`
VALUES (17, 4, 5);
INSERT INTO `play_mv`
VALUES (18, 1, 1);
INSERT INTO `play_mv`
VALUES (19, 1, 2);
INSERT INTO `play_mv`
VALUES (20, 1, 3);
INSERT INTO `play_mv`
VALUES (21, 1, 4);

-- ----------------------------
-- Table structure for play_user
-- ----------------------------
DROP TABLE IF EXISTS `play_user`;
CREATE TABLE `play_user`
(
    `id`      int    NOT NULL AUTO_INCREMENT COMMENT '音乐清单创作者主键ID',
    `play_id` int    NULL DEFAULT NULL COMMENT '音乐清单ID',
    `user_id` bigint NULL DEFAULT NULL COMMENT '创作者ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play_user
-- ----------------------------
INSERT INTO `play_user`
VALUES (1, 1, 1);
INSERT INTO `play_user`
VALUES (2, 3, 1);
INSERT INTO `play_user`
VALUES (3, 4, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '菜单名称',
    `icon`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `parent_id`   bigint                                                        NULL DEFAULT NULL COMMENT '父菜单ID',
    `order_num`   int                                                           NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `perms`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限标识',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 'system', 0, 1, '/sys', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31',
        '系统管理目录');
INSERT INTO `sys_menu`
VALUES (2, '业务管理', 'monitor', 0, 3, '/bsns', '', 'M', '', '2022-07-04 14:59:43', '2022-07-04 14:59:45',
        '业务管理目录');
INSERT INTO `sys_menu`
VALUES (3, '用户管理', 'user', 1, 1, '/sys/user', 'sys/user/index', 'C', 'system:user:list', '2022-07-04 15:20:51',
        '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu`
VALUES (4, '角色管理', 'peoples', 1, 2, '/sys/role', 'sys/role/index', 'C', 'system:role:list', '2022-07-04 15:23:35',
        '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu`
VALUES (5, '菜单管理', 'tree-table', 1, 3, '/sys/menu', 'sys/menu/index', 'C', 'system:menu:list',
        '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu`
VALUES (6, '部门管理', 'tree', 2, 1, '/bsns/department', 'bsns/Department', 'C', '', '2022-07-04 15:24:40',
        '2022-07-04 15:24:44', '部门管理菜单');
INSERT INTO `sys_menu`
VALUES (7, '岗位管理', 'post', 2, 2, '/bsns/post', 'bsns/Post', 'C', '', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '岗位管理菜单');
INSERT INTO `sys_menu`
VALUES (8, '用户新增', '#', 3, 2, '', '', 'F', 'system:user:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '添加用户按钮');
INSERT INTO `sys_menu`
VALUES (9, '用户修改', '#', 3, 3, '', '', 'F', 'system:user:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '修改用户按钮');
INSERT INTO `sys_menu`
VALUES (10, '用户删除', '#', 3, 4, '', '', 'F', 'system:user:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '删除用户按钮');
INSERT INTO `sys_menu`
VALUES (11, '分配角色', '#', 3, 5, '', '', 'F', 'system:user:role', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '分配角色按钮');
INSERT INTO `sys_menu`
VALUES (12, '重置密码', '#', 3, 6, '', '', 'F', 'system:user:resetPwd', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '重置密码按钮');
INSERT INTO `sys_menu`
VALUES (13, '角色新增', '#', 4, 2, '', '', 'F', 'system:role:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '添加用户按钮');
INSERT INTO `sys_menu`
VALUES (14, '角色修改', '#', 4, 3, '', '', 'F', 'system:role:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '修改用户按钮');
INSERT INTO `sys_menu`
VALUES (15, '角色删除', '#', 4, 4, '', NULL, 'F', 'system:role:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '删除用户按钮');
INSERT INTO `sys_menu`
VALUES (16, '分配权限', '#', 4, 5, '', '', 'F', 'system:role:menu', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '分配权限按钮');
INSERT INTO `sys_menu`
VALUES (17, '菜单新增', '#', 5, 2, '', NULL, 'F', 'system:menu:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '添加菜单按钮');
INSERT INTO `sys_menu`
VALUES (18, '菜单修改', '#', 5, 3, '', NULL, 'F', 'system:menu:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '修改菜单按钮');
INSERT INTO `sys_menu`
VALUES (19, '菜单删除', '#', 5, 4, '', NULL, 'F', 'system:menu:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '删除菜单按钮');
INSERT INTO `sys_menu`
VALUES (20, '用户查询', '#', 3, 1, '', NULL, 'F', 'system:user:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '用户查询按钮');
INSERT INTO `sys_menu`
VALUES (21, '角色查询', '#', 4, 1, '', NULL, 'F', 'system:role:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '角色查询按钮');
INSERT INTO `sys_menu`
VALUES (22, '菜单查询', '#', 5, 1, '', NULL, 'F', 'system:menu:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46',
        '菜单查询按钮');
INSERT INTO `sys_menu`
VALUES (33, '测速22', '122', 3, 3, '', '34', 'M', '33', '2022-08-19 03:11:20', '2022-08-18 19:11:33', NULL);
INSERT INTO `sys_menu`
VALUES (34, '数据管理', 'data', 0, 2, '/data', NULL, 'M', '', '2025-07-16 11:28:46', '2025-07-16 11:28:57',
        '数据管理目录');
INSERT INTO `sys_menu`
VALUES (37, '在线音乐', 'music', 34, 1, '/data/music', 'data/music/index', 'C', 'system:user:list',
        '2025-07-18 10:37:43', '2025-07-18 13:22:30', '在线音乐目录');
INSERT INTO `sys_menu`
VALUES (38, '在线MV', 'mv', 34, 2, '/data/mv', 'data/mv/index', 'C', 'system:user:list', '2025-07-25 10:33:55',
        '2025-07-25 10:34:32', '在线MV目录');
INSERT INTO `sys_menu`
VALUES (39, '在线悦单', 'list', 34, 3, '/data/list', 'data/list/index', 'C', 'system:user:list', '2025-08-01 11:16:03',
        '2025-08-01 11:19:56', '在线悦单目录');
INSERT INTO `sys_menu`
VALUES (40, '公告管理', 'notice', 1, 4, '/sys/notice', 'sys/notice/index', 'C', 'system:user:list',
        '2025-08-14 14:14:06', '2025-08-14 14:14:46', '公告管理目录');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `title`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题',
    `content`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
    `publish_time` datetime                                                      NULL DEFAULT NULL COMMENT '公告发布时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice`
VALUES (1, '新功能上线', '新增在线音乐管理功能', '2025-08-15 10:57:40');
INSERT INTO `sys_notice`
VALUES (2, '新功能上线-002', '新增在线MV管理功能', '2025-08-15 14:04:48');
INSERT INTO `sys_notice`
VALUES (3, '新功能上线-003', '新增在线悦单管理功能', '2025-08-15 14:28:03');
INSERT INTO `sys_notice`
VALUES (4, '新功能上线-004', '新增公告管理功能', '2025-08-15 14:28:40');
INSERT INTO `sys_notice`
VALUES (5, '新功能上线-005', '新增查看MV功能', '2025-08-15 14:44:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
    `name`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '角色名称',
    `code`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'admin', '2022-07-04 14:40:44', '2022-07-04 14:40:47', '拥有系统最高权限');
INSERT INTO `sys_role`
VALUES (2, '普通角色', 'common', '2022-07-04 14:41:56', '2022-07-04 14:41:58', '普通角色');
INSERT INTO `sys_role`
VALUES (3, '测试角色', 'test3', '2022-07-04 14:42:24', '2022-07-04 14:42:27', '测试角色');
INSERT INTO `sys_role`
VALUES (4, '2', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role`
VALUES (5, '3', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role`
VALUES (6, '4', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role`
VALUES (17, '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role`
VALUES (19, '测2', 'cc2', '2022-08-13 21:06:21', '2022-08-13 13:06:27', 'eewew2');
INSERT INTO `sys_role`
VALUES (20, 'ccc测试', 'test2', '2022-08-29 17:10:33', NULL, 'xxx');
INSERT INTO `sys_role`
VALUES (21, '今天测试角色', 'todytest', '2022-08-29 22:01:11', NULL, 'ccc');
INSERT INTO `sys_role`
VALUES (22, 'ttt测', 't11', '2025-07-06 19:08:30', '2025-07-06 11:09:41', 'tce测试');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '角色菜单主键ID',
    `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
    `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 390
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (15, 3, 2);
INSERT INTO `sys_role_menu`
VALUES (16, 3, 6);
INSERT INTO `sys_role_menu`
VALUES (17, 3, 7);
INSERT INTO `sys_role_menu`
VALUES (21, 7, 1);
INSERT INTO `sys_role_menu`
VALUES (22, 7, 2);
INSERT INTO `sys_role_menu`
VALUES (23, 7, 6);
INSERT INTO `sys_role_menu`
VALUES (24, 7, 7);
INSERT INTO `sys_role_menu`
VALUES (25, 6, 1);
INSERT INTO `sys_role_menu`
VALUES (26, 6, 3);
INSERT INTO `sys_role_menu`
VALUES (27, 6, 9);
INSERT INTO `sys_role_menu`
VALUES (28, 6, 10);
INSERT INTO `sys_role_menu`
VALUES (29, 19, 1);
INSERT INTO `sys_role_menu`
VALUES (30, 19, 3);
INSERT INTO `sys_role_menu`
VALUES (31, 19, 2);
INSERT INTO `sys_role_menu`
VALUES (32, 19, 6);
INSERT INTO `sys_role_menu`
VALUES (208, 20, 1);
INSERT INTO `sys_role_menu`
VALUES (209, 20, 3);
INSERT INTO `sys_role_menu`
VALUES (210, 20, 20);
INSERT INTO `sys_role_menu`
VALUES (211, 20, 8);
INSERT INTO `sys_role_menu`
VALUES (212, 20, 9);
INSERT INTO `sys_role_menu`
VALUES (213, 20, 33);
INSERT INTO `sys_role_menu`
VALUES (214, 20, 10);
INSERT INTO `sys_role_menu`
VALUES (215, 20, 11);
INSERT INTO `sys_role_menu`
VALUES (216, 20, 4);
INSERT INTO `sys_role_menu`
VALUES (217, 20, 21);
INSERT INTO `sys_role_menu`
VALUES (218, 20, 13);
INSERT INTO `sys_role_menu`
VALUES (219, 20, 5);
INSERT INTO `sys_role_menu`
VALUES (220, 20, 22);
INSERT INTO `sys_role_menu`
VALUES (221, 20, 17);
INSERT INTO `sys_role_menu`
VALUES (222, 20, 18);
INSERT INTO `sys_role_menu`
VALUES (223, 20, 2);
INSERT INTO `sys_role_menu`
VALUES (224, 20, 6);
INSERT INTO `sys_role_menu`
VALUES (225, 20, 7);
INSERT INTO `sys_role_menu`
VALUES (232, 21, 1);
INSERT INTO `sys_role_menu`
VALUES (233, 21, 9);
INSERT INTO `sys_role_menu`
VALUES (234, 21, 4);
INSERT INTO `sys_role_menu`
VALUES (235, 21, 21);
INSERT INTO `sys_role_menu`
VALUES (236, 21, 2);
INSERT INTO `sys_role_menu`
VALUES (237, 21, 6);
INSERT INTO `sys_role_menu`
VALUES (238, 21, 7);
INSERT INTO `sys_role_menu`
VALUES (251, 4, 1);
INSERT INTO `sys_role_menu`
VALUES (252, 4, 2);
INSERT INTO `sys_role_menu`
VALUES (253, 4, 6);
INSERT INTO `sys_role_menu`
VALUES (254, 4, 7);
INSERT INTO `sys_role_menu`
VALUES (279, 2, 1);
INSERT INTO `sys_role_menu`
VALUES (280, 2, 3);
INSERT INTO `sys_role_menu`
VALUES (281, 2, 4);
INSERT INTO `sys_role_menu`
VALUES (282, 2, 5);
INSERT INTO `sys_role_menu`
VALUES (283, 2, 34);
INSERT INTO `sys_role_menu`
VALUES (284, 2, 37);
INSERT INTO `sys_role_menu`
VALUES (285, 2, 2);
INSERT INTO `sys_role_menu`
VALUES (286, 2, 6);
INSERT INTO `sys_role_menu`
VALUES (287, 2, 7);
INSERT INTO `sys_role_menu`
VALUES (363, 1, 1);
INSERT INTO `sys_role_menu`
VALUES (364, 1, 3);
INSERT INTO `sys_role_menu`
VALUES (365, 1, 20);
INSERT INTO `sys_role_menu`
VALUES (366, 1, 8);
INSERT INTO `sys_role_menu`
VALUES (367, 1, 9);
INSERT INTO `sys_role_menu`
VALUES (368, 1, 10);
INSERT INTO `sys_role_menu`
VALUES (369, 1, 11);
INSERT INTO `sys_role_menu`
VALUES (370, 1, 12);
INSERT INTO `sys_role_menu`
VALUES (371, 1, 40);
INSERT INTO `sys_role_menu`
VALUES (372, 1, 4);
INSERT INTO `sys_role_menu`
VALUES (373, 1, 21);
INSERT INTO `sys_role_menu`
VALUES (374, 1, 13);
INSERT INTO `sys_role_menu`
VALUES (375, 1, 14);
INSERT INTO `sys_role_menu`
VALUES (376, 1, 15);
INSERT INTO `sys_role_menu`
VALUES (377, 1, 16);
INSERT INTO `sys_role_menu`
VALUES (378, 1, 5);
INSERT INTO `sys_role_menu`
VALUES (379, 1, 22);
INSERT INTO `sys_role_menu`
VALUES (380, 1, 17);
INSERT INTO `sys_role_menu`
VALUES (381, 1, 18);
INSERT INTO `sys_role_menu`
VALUES (382, 1, 19);
INSERT INTO `sys_role_menu`
VALUES (383, 1, 34);
INSERT INTO `sys_role_menu`
VALUES (384, 1, 37);
INSERT INTO `sys_role_menu`
VALUES (385, 1, 38);
INSERT INTO `sys_role_menu`
VALUES (386, 1, 39);
INSERT INTO `sys_role_menu`
VALUES (387, 1, 2);
INSERT INTO `sys_role_menu`
VALUES (388, 1, 6);
INSERT INTO `sys_role_menu`
VALUES (389, 1, 7);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'default.jpg' COMMENT '用户头像',
    `email`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '手机号码',
    `login_date`  datetime                                                      NULL DEFAULT NULL COMMENT '最后登录时间',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'java1234', '$2a$10$lJbJLsUFkA67J4Cvk6RFIu.Zd1hdTD1iUzTAAW0XQr2sPltS5aw3e', '20250630013543000000287.jpg',
        'caofeng4012@126.com', '18862857412', '2022-08-29 22:10:52', '0', '2022-06-09 08:47:52', '2025-06-30 13:39:09',
        '备注');
INSERT INTO `sys_user`
VALUES (2, 'common', '$2a$10$tiArwm0GxChyEP5k0JGzsOuzyY15IKA.ZTl8S2aj3haYlKAfpwfl.', '222.jpg', '', '',
        '2022-08-22 21:34:39', '0', '2022-07-05 11:22:47', NULL, NULL);
INSERT INTO `sys_user`
VALUES (3, 'test', '$2a$10$tiArwm0GxChyEP5k0JGzsOuzyY15IKA.ZTl8S2aj3haYlKAfpwfl.', '333.jpg', '', '',
        '2022-07-24 17:36:07', '0', '2022-07-15 11:23:09', NULL, NULL);
INSERT INTO `sys_user`
VALUES (4, '1', '$2a$10$lD0Fx7oMsFFmX9hVkmYy7eJteH8pBaXXro1X9DEMP5sbM.Z6Co55m', 'default.jpg', '', '', NULL, '1',
        '2022-07-20 11:23:33', NULL, NULL);
INSERT INTO `sys_user`
VALUES (5, '2', NULL, 'default.jpg', '', '', NULL, '1', '2022-07-23 11:24:20', NULL, NULL);
INSERT INTO `sys_user`
VALUES (15, 'fdsfs', '$2a$10$AQVcp4hQ7REc5o7ztVnI7eX.sJdcYy3d1x2jm5CfrcCoMZMPacfpi', 'default.jpg', 'fdfa4@qq.com',
        '18862851414', '2022-08-02 02:22:45', '1', '2022-08-02 02:21:24', '2022-08-01 18:23:16', 'fdfds4');
INSERT INTO `sys_user`
VALUES (28, 'sdfss2', '$2a$10$7aNJxwVmefI0XAk64vrzYuOqeeImYJUQnoBrtKP9pLTGTWO2CXQ/y', 'default.jpg', 'dfds3@qq.com',
        '18862857413', NULL, '1', '2022-08-07 00:42:46', '2022-08-06 16:43:04', 'ddd33');
INSERT INTO `sys_user`
VALUES (29, 'ccc', '$2a$10$7cbWeVwDWO9Hh3qbJrvTHOn0E/DLYXxnIZpxZei0jY4ChfQbJuhi.', '20220829080150000000341.jpg',
        '3242@qq.com', '18862584120', '2022-08-29 19:52:27', '0', '2022-08-29 17:04:58', NULL, 'xxx');
INSERT INTO `sys_user`
VALUES (30, 'ccc666', '$2a$10$Tmw5VCM/K2vb837AZDYHQOqE3gPiRZKevxLsh/ozndpTSjdwABqaK', '20220829100454000000771.jpg',
        'fdafds@qq.com', '18865259845', '2022-08-29 22:05:18', '0', '2022-08-29 22:00:39', NULL, 'ccc');
INSERT INTO `sys_user`
VALUES (31, '1', NULL, 'default.jpg', '', '', NULL, '0', '2023-06-14 11:24:36', NULL, NULL);
INSERT INTO `sys_user`
VALUES (32, '2', NULL, 'default.jpg', '', '', NULL, '0', '2024-04-18 11:24:51', NULL, NULL);
INSERT INTO `sys_user`
VALUES (40, 'common2', '$2a$10$25fGtgLQyUziPqLh/vroI.s3BiUFXZfVcc8KO1TyIZmKrK0lJOoLy', 'default.jpg', 'dfs2@qq.com',
        '15525462582', NULL, '1', '2025-07-03 20:43:58', '2025-07-05 11:16:31', 'xxx2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色主键ID',
    `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
    `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 2, 2);
INSERT INTO `sys_user_role`
VALUES (4, 1, 2);
INSERT INTO `sys_user_role`
VALUES (6, 3, 3);
INSERT INTO `sys_user_role`
VALUES (7, 3, 2);
INSERT INTO `sys_user_role`
VALUES (9, 4, 3);
INSERT INTO `sys_user_role`
VALUES (10, 5, 3);
INSERT INTO `sys_user_role`
VALUES (11, 15, 3);
INSERT INTO `sys_user_role`
VALUES (20, 29, 20);
INSERT INTO `sys_user_role`
VALUES (21, 30, 17);
INSERT INTO `sys_user_role`
VALUES (22, 30, 21);

SET FOREIGN_KEY_CHECKS = 1;

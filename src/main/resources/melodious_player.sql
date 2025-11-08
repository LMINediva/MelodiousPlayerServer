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

 Date: 02/11/2025 14:39:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for android_application
-- ----------------------------
DROP TABLE IF EXISTS `android_application`;
CREATE TABLE `android_application`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '安卓应用ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安卓应用名',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'default.png' COMMENT '安卓应用图标',
  `code` int NULL DEFAULT NULL COMMENT '安卓应用版本代码',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安卓应用版本号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安卓应用内容',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '安卓应用链接',
  `size` float NULL DEFAULT NULL COMMENT '安卓应用大小',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `force` int NULL DEFAULT NULL COMMENT '强制更新',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '安卓应用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of android_application
-- ----------------------------
INSERT INTO `android_application` VALUES (1, '悦听影音', 'default.png', 1, '1.0.0', '1、在线音乐、MV和歌单列表播放功能；\n2、用户登录、注册功能、退出登录等功能；\n3、用户添加音乐、MV和歌单列表的功能；\n4、本地音乐和视频的播放功能。', '20251022014724000000029.apk', 8.1, '2025-10-22 13:47:29', 0, 0);
INSERT INTO `android_application` VALUES (2, '悦听影音', 'default.png', 2, '2.1.3', '1、新增版本检查更新功能；\n2、美化界面UI，使界面UI更舒适；\n3、修复表单字符串超出限制的BUG。', '20251027013123000000705.apk', 10.2, '2025-10-25 14:28:22', 0, 0);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '反馈图片',
  `submission_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, '首页UI界面文字颜色不够美观，建议开发人员进行优化。', '20251029112216000000268.jpg', '2025-10-29 11:22:27');
INSERT INTO `feedback` VALUES (3, 'MVUI界面文字颜色不够美观，建议优化。', '20251029115212000000823.jpg', '2025-10-29 11:52:19');
INSERT INTO `feedback` VALUES (6, '添加音乐后，首页音乐列表未显示用户添加的音乐，要刷新后才显示，建议开发人员进行优化。', '20251102020415000000574.jpg', '2025-11-02 14:04:24');

-- ----------------------------
-- Table structure for feedback_user
-- ----------------------------
DROP TABLE IF EXISTS `feedback_user`;
CREATE TABLE `feedback_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '反馈创作者主键ID',
  `feedback_id` int NULL DEFAULT NULL COMMENT '反馈ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '创作者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback_user
-- ----------------------------
INSERT INTO `feedback_user` VALUES (1, 1, 1);
INSERT INTO `feedback_user` VALUES (2, 3, 1);
INSERT INTO `feedback_user` VALUES (5, 6, 45);

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '音乐ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `artist_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家姓名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `poster_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '海报图片',
  `thumbnail_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `lyric` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌词',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '音乐链接',
  `hd_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '高品质音乐链接',
  `uhd_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超高品质音乐链接',
  `music_size` float NULL DEFAULT NULL COMMENT '音乐大小',
  `hd_music_size` float NULL DEFAULT NULL COMMENT '高品质音乐大小',
  `uhd_music_size` float NULL DEFAULT NULL COMMENT '超高品质音乐大小',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页在线音乐数据项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (1, 'audio/mpeg', '这个年纪', '齐一', '国语', '20250810125433000000801.jpeg', '20250810125538000000881.jpg', '20250810125625000000918.lrc', '20250810125712000000459.mp3', '20250810125712000000459.mp3', '20250810125712000000459.mp3', 10.9, 10.9, 10.9, 0);
INSERT INTO `music` VALUES (2, 'audio/mpeg', '感谢你曾来过2017版', 'Ayo97; 阿涵', '这首歌讲述的是一对恋人，以前在一起很恩爱，随着相处时间，女孩因为种种原因离开了男孩，离别的痛苦然而又割舍不得，曾经一起的回忆历历在目，感谢他曾经的来过，虽然只是个过客，但也深深相爱过，最终两人没能走到一起走到最后。', '20250721013540000000631.jpg', '20250721013544000000747.jpg', '20250725010545000000044.lrc', '20250721013601000000893.mp3', '20250721013601000000893.mp3', '20250721013601000000893.mp3', 9.1, 9.1, 9.1, 0);
INSERT INTO `music` VALUES (3, 'audio/mpeg', '我的天空', '南征北战', '《我的天空》是一首较有影响力的现象级励志歌曲，其目标受众是即将参加高考的学生。它自问世后一直人气颇高，被歌迷和网友广为传唱。', '20250721014709000000253.jpg', '20250721014712000000920.jpg', NULL, '20250721014736000000441.mp3', '20250721014736000000441.mp3', '20250721014736000000441.mp3', 9.2, 9.2, 9.2, 0);
INSERT INTO `music` VALUES (4, 'audio/mpeg', '体面', '于文文', '在拿到《前任3：再见前任》剧本之后，于文文就产生了音乐创作的想法。随着电影《前任3：再见前任》越来越深入的拍摄，让于文文对于角色林佳产生了更多的情感共鸣，而沉浸在人物里的她，需要用音乐创作去宣泄情绪和表达态度。于是，于文文以“林佳”为创作灵感 ，为影片量身定制了该曲。', '20250721015434000000918.jpg', '20250721015438000000207.jpg', NULL, '20250721015459000000859.mp3', '20250721015459000000859.mp3', '20250721015459000000859.mp3', 10.8, 10.8, 10.8, 0);
INSERT INTO `music` VALUES (5, 'audio/mpeg', '此生不换', '青鸟飞鱼', '《此生不换》是电视剧《仙剑奇侠传三》的插曲，创作者在歌词中写道“喝不完忘情泉不让你如烟”，以此暗喻着剧中的两位主人公徐长卿与紫萱在全剧的终章虽共饮忘情之水、试图斩断情丝，却终究难以割舍对彼此的深情眷恋，并希望表达出二人不愿让彼此刻骨铭心的爱恋如烟消散、徒留遗憾的情感。', '20250721020029000000862.jpg', '20250721020033000000191.jpg', NULL, '20250721020050000000183.mp3', '20250721020050000000183.mp3', '20250721020050000000183.mp3', 10.2, 10.2, 10.2, 0);
INSERT INTO `music` VALUES (6, 'audio/mpeg', '就忘了吧（DJAh版）', '1K', '《就忘了吧》 是一首以都市情感为主题的歌曲，通过直白的叙事风格与反复的“忘了吧”核心句式，刻画了失恋后自我挣扎与释怀的心理过程。', '20250725110544000000126.jpg', '20250725110547000000464.jpg', NULL, '20250725110600000000970.mp3', '20250725110600000000970.mp3', '20250725110600000000970.mp3', 8.7, 8.7, 8.7, 0);
INSERT INTO `music` VALUES (8, 'audio/mpeg', '曾经的你', '许巍', '《曾经的你》是一首对青春岁月的怀念之歌和无悔宣言，让我们更清楚的知道许巍是从哪里来的，他的过往其实和你我一样，\"有难过也有精彩\"。', '20250728012021000000159.jpeg', '20250728012025000000055.jpeg', '20250728012033000000195.lrc', '20250728012047000000986.mp3', '20250728012047000000986.mp3', '20250728012047000000986.mp3', 9.9, 9.9, 9.9, 0);
INSERT INTO `music` VALUES (10, 'audio/mpeg', '世间美好与你环环相扣', '柏松', '世间美好与你环环相扣》这首歌，仿佛是一首描绘人间烟火的诗篇。它以温馨的半民谣风格，诉说着世间万物的美好与情感的细腻。', '20250915105458000000656.png', '20250915105504000000852.png', '20250915105629000000905.lrc', '20250915105653000000575.mp3', '20250915105653000000575.mp3', '20250915105653000000575.mp3', 7.4, 7.4, 7.4, 0);
INSERT INTO `music` VALUES (11, 'audio/mpeg', '尘土', '安朕宇', '歌曲以尘土为核心意象，通过第一人称独白构建单恋者甘愿消解自我存在的叙事视角。', '20250925110433000000041.jpg', '20250925110444000000825.jpg', '20250925110457000000308.lrc', '20250925110509000000872.mp3', '20250925110509000000872.mp3', '20250925110509000000872.mp3', 9, 9, 9, 0);
INSERT INTO `music` VALUES (12, 'audio/mpeg', '悬溺', '葛东琪', '歌曲采用双人合唱形式，围绕情感困局与自我抗争展开创作。', '20250925124948000000448.jpg', '20250925125000000000163.jpg', '20250925125015000000695.lrc', '20250925125036000000213.mp3', '20250925125036000000213.mp3', '20250925125036000000213.mp3', 7, 7, 7, 0);
INSERT INTO `music` VALUES (13, 'audio/mpeg', '夏天', '李玖哲', '歌曲以夏日为背景，通过风筝、日落、星空等意象描绘恋人间的甜蜜互动，传递携手冒险与相伴成长的温暖情感。', '20250925010021000000082.jpg', '20250925010030000000598.jpg', '20250925010048000000862.lrc', '20250925010104000000063.mp3', '20250925010104000000063.mp3', '20250925010104000000063.mp3', 3, 3, 3, 0);
INSERT INTO `music` VALUES (14, 'audio/mpeg', '纸短情长', '烟把儿乐队', '《纸短情长》伴奏优美，声音动人，烟把儿乐队以轻快俏皮的唱法将这首歌曲演绎了出来。', '20250925012341000000249.jpg', '20250925012350000000355.jpg', '20250925012400000000429.lrc', '20250925012413000000725.mp3', '20250925012413000000725.mp3', '20250925012413000000725.mp3', 4, 4, 4, 0);

-- ----------------------------
-- Table structure for music_user
-- ----------------------------
DROP TABLE IF EXISTS `music_user`;
CREATE TABLE `music_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '音乐创作者主键ID',
  `music_id` int NULL DEFAULT NULL COMMENT '音乐ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '创作者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_user
-- ----------------------------
INSERT INTO `music_user` VALUES (1, 1, 1);
INSERT INTO `music_user` VALUES (2, 2, 1);
INSERT INTO `music_user` VALUES (3, 3, 1);
INSERT INTO `music_user` VALUES (4, 4, 1);
INSERT INTO `music_user` VALUES (5, 5, 1);
INSERT INTO `music_user` VALUES (6, 6, 1);
INSERT INTO `music_user` VALUES (8, 8, 1);
INSERT INTO `music_user` VALUES (9, 10, 45);
INSERT INTO `music_user` VALUES (10, 11, 45);
INSERT INTO `music_user` VALUES (11, 12, 45);
INSERT INTO `music_user` VALUES (12, 13, 45);
INSERT INTO `music_user` VALUES (13, 14, 45);

-- ----------------------------
-- Table structure for mv
-- ----------------------------
DROP TABLE IF EXISTS `mv`;
CREATE TABLE `mv`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `artist_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家姓名',
  `poster_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '海报',
  `thumbnail_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `regdate` datetime NULL DEFAULT NULL COMMENT '发行时间',
  `video_source_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频源类型名',
  `total_views` int NULL DEFAULT NULL COMMENT '总浏览量',
  `total_pc_views` int NULL DEFAULT NULL COMMENT 'PC端浏览量',
  `total_mobile_views` int NULL DEFAULT NULL COMMENT '手机端浏览量',
  `total_comments` int NULL DEFAULT NULL COMMENT '评论数',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  `hd_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '高清视频链接',
  `uhd_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超高清视频链接',
  `video_size` float NULL DEFAULT NULL COMMENT '视频大小',
  `hd_video_size` float NULL DEFAULT NULL COMMENT '高清视频大小',
  `uhd_video_size` float NULL DEFAULT NULL COMMENT '超高清视频大小',
  `duration` time NULL DEFAULT NULL COMMENT '时长',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MV' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv
-- ----------------------------
INSERT INTO `mv` VALUES (1, '这个年纪', '人生已过三十载，而立之年的我，并没有走过很多的路，并没有爱过很多的人，并没有很清楚的记得这三十年过往的经历以及擦肩的过客。', '齐一', '20250811020919000000630.jpg', '20250811021031000000961.jpg', '2022-08-29 22:10:52', 'video/mp4', 100, 200, 300, 0, '20250811021143000000254.mp4', '20250811021143000000254.mp4', '20250811021143000000254.mp4', 25.5, 25.5, 25.5, '00:06:18', 0);
INSERT INTO `mv` VALUES (2, '感谢你曾来过', '歌曲中充满了对过去美好时光的深情回忆，以及对那个曾经来过自己世界的人的深深思念。尽管对方已经离去，但那份记忆和思念却如影随形，难以割舍。', 'Ayo97; 阿涵', '20250728125023000000210.jpg', '20250728125026000000764.jpg', '2025-07-28 12:50:29', 'video/mp4', 0, 0, 0, 0, '20250728125036000000227.mp4', '20250728125036000000227.mp4', '20250728125036000000227.mp4', 16.2, 16.2, 16.2, '00:04:05', 0);
INSERT INTO `mv` VALUES (3, '我的天空', '歌曲普遍围绕\"挣脱束缚、追逐自由\"展开，通过意象化表达传递直面挫折、坚守梦想的信念。', '南征北战', '20250728010815000000581.jpg', '20250728010818000000940.jpg', '2025-07-28 13:08:22', 'video/mp4', 0, 0, 0, 0, '20250728010831000000224.mp4', '20250728010831000000224.mp4', '20250728010831000000224.mp4', 14, 14, 14, '00:03:59', 0);
INSERT INTO `mv` VALUES (4, '不为谁而作的歌', '林俊杰通过此曲传递对音乐与生命的思考，结合自身经历创作，旨在感谢那些曾在他低谷期给予帮助的陌生人及团队成员。', '林俊杰', '20250731103835000000579.jpg', '20250731103839000000724.jpg', '2025-07-31 10:38:43', 'video/mp4', 0, 0, 0, 0, '20250731104030000000489.mp4', '20250731104030000000489.mp4', '20250731104030000000489.mp4', 22.4, 22.4, 22.4, '00:04:22', 0);
INSERT INTO `mv` VALUES (5, '体面', '《体面》是一首容易引起大众共鸣的情歌，歌曲旋律委婉动听，伤感且带着洒脱，韵味十足，道出了失恋者对逝去爱情感到遗憾心酸却不得已要洒脱放手的心声。', '于文文', '20250731105304000000063.jpg', '20250731105307000000305.jpg', '2025-07-31 10:53:09', 'video/mp4', 0, 0, 0, 0, '20250731105409000000231.mp4', '20250731105409000000231.mp4', '20250731105409000000231.mp4', 8.7, 8.7, 8.7, '00:04:39', 0);
INSERT INTO `mv` VALUES (6, '此生不换', '《此生不换》的歌词宛如一幅细腻入微的画卷，以深情笔触缓缓勾勒出长卿与紫萱之间跨越三生三世的凄美爱恋。', '青鸟飞鱼', '20250731110119000000106.jpg', '20250731110122000000332.jpg', '2025-07-31 11:01:24', 'video/mp4', 0, 0, 0, 0, '20250731110221000000473.mp4', '20250731110221000000473.mp4', '20250731110221000000473.mp4', 23.1, 23.1, 23.1, '00:04:16', 0);
INSERT INTO `mv` VALUES (7, '就忘了吧', '《就忘了吧》 歌词以情感纠葛为主题，通过回忆与释怀交织的叙述，展现了现代情感关系中的挣扎与成长。', '1K', '20250731111057000000730.jpg', '20250731111101000000404.jpg', '2025-07-31 11:11:04', 'video/mp4', 0, 0, 0, 0, '20250731111204000000348.mp4', '20250731111204000000348.mp4', '20250731111204000000348.mp4', 17.7, 17.7, 17.7, '00:03:45', 0);
INSERT INTO `mv` VALUES (9, '世间美好与你环环相扣', '《世间美好与你环环相扣》这首歌，不仅以温馨的半民谣风格吸引着听众，更在每一句歌词中融入了深情的寓意。', '柏松', '20250915113032000000962.jpeg', '20250915113037000000672.jpeg', '2025-09-15 11:28:39', 'video/mp4', 0, 0, 0, 0, '20250915113210000000631.mp4', '20250915113210000000631.mp4', '20250915113210000000631.mp4', 8.4, 8.4, 8.4, '00:02:32', 0);
INSERT INTO `mv` VALUES (10, '纸短情长', '歌曲以书信为载体，通过\"纸短情长啊，道不尽太多涟漪\"等意象化歌词，追忆逝去恋情中的遗憾与纠葛。', '烟把儿', '20251001113946000000095.jpg', '20251001114005000000922.jpg', '2025-10-01 11:40:54', 'video/mp4', 0, 0, 0, 0, '20251001114028000000687.mp4', '20251001114028000000687.mp4', '20251001114028000000687.mp4', 11, 11, 11, '00:02:54', 0);
INSERT INTO `mv` VALUES (11, 'Demons', '歌曲《Demons》描写了一个人从最初内心的挣扎到最终对自己诚实的全过程。', 'Imagine Dragons', '20251001121810000000436.jpg', '20251001121819000000911.jpg', '2025-10-01 12:19:08', 'video/mp4', 0, 0, 0, 0, '20251001121846000000425.mp4', '20251001121846000000425.mp4', '20251001121846000000425.mp4', 7, 7, 7, '00:03:09', 0);
INSERT INTO `mv` VALUES (12, 'Unstoppable', '歌曲以理性与情感的冲突为核心主题，通过主歌的内心独白与副歌的渐进旋律，刻画单恋中难以抑制的心动。', 'Sia', '20251001011730000000020.jpg', '20251001011756000000579.jpg', '2025-10-01 13:18:38', 'video/mp4', 0, 0, 0, 0, '20251001011810000000013.mp4', '20251001011810000000013.mp4', '20251001011810000000013.mp4', 10, 10, 10, '00:03:34', 0);
INSERT INTO `mv` VALUES (13, '春娇与志明', '歌曲讲述成长和爱情的故事，传递出对于成长的思考以及对未来的憧憬，鼓励听众们在爱情路上勇敢面对挑战，持续成长。', '街道办GDC、欧阳耀莹', '20251001023742000000829.jpeg', '20251001023749000000803.jpeg', '2025-10-01 14:38:01', 'video/mp4', 0, 0, 0, 0, '20251001023757000000615.mp4', '20251001023757000000615.mp4', '20251001023757000000615.mp4', 8.3, 8.3, 8.3, '00:03:23', 0);
INSERT INTO `mv` VALUES (14, '在你身边', '歌词通过描述相遇、相知、相恋到分离的全过程，刻画了主人公对逝去爱情的执着追求。', '盛哲', '20251015011725000000781.jpeg', '20251015011730000000689.jpeg', '2025-10-15 13:12:24', 'video/mp4', 0, 0, 0, 0, '20251015011738000000133.mp4', '20251015011738000000133.mp4', '20251015011738000000133.mp4', 17.9, 17.9, 17.9, '00:04:22', 0);

-- ----------------------------
-- Table structure for mv_area
-- ----------------------------
DROP TABLE IF EXISTS `mv_area`;
CREATE TABLE `mv_area`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'MV地区ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区名',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MV区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv_area
-- ----------------------------
INSERT INTO `mv_area` VALUES (1, '内地篇', 'ML');
INSERT INTO `mv_area` VALUES (2, '韩国篇', 'KR');
INSERT INTO `mv_area` VALUES (3, '港台篇', 'HT');
INSERT INTO `mv_area` VALUES (4, '日本篇', 'JP');
INSERT INTO `mv_area` VALUES (5, '欧美篇', 'US');

-- ----------------------------
-- Table structure for mv_area_code
-- ----------------------------
DROP TABLE IF EXISTS `mv_area_code`;
CREATE TABLE `mv_area_code`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'MV区域代码ID',
  `mv_id` int NULL DEFAULT NULL COMMENT 'MVID',
  `mv_area_id` int NULL DEFAULT NULL COMMENT 'MV区域ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv_area_code
-- ----------------------------
INSERT INTO `mv_area_code` VALUES (1, 1, 1);
INSERT INTO `mv_area_code` VALUES (2, 2, 2);
INSERT INTO `mv_area_code` VALUES (3, 3, 3);
INSERT INTO `mv_area_code` VALUES (4, 4, 1);
INSERT INTO `mv_area_code` VALUES (5, 5, 1);
INSERT INTO `mv_area_code` VALUES (6, 6, 1);
INSERT INTO `mv_area_code` VALUES (7, 7, 1);
INSERT INTO `mv_area_code` VALUES (9, 9, 1);
INSERT INTO `mv_area_code` VALUES (10, 10, 1);
INSERT INTO `mv_area_code` VALUES (11, 11, 5);
INSERT INTO `mv_area_code` VALUES (12, 12, 5);
INSERT INTO `mv_area_code` VALUES (13, 13, 1);
INSERT INTO `mv_area_code` VALUES (14, 14, 1);

-- ----------------------------
-- Table structure for mv_user
-- ----------------------------
DROP TABLE IF EXISTS `mv_user`;
CREATE TABLE `mv_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'MV创作者主键ID',
  `mv_id` int NULL DEFAULT NULL COMMENT 'MVID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '创作者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mv_user
-- ----------------------------
INSERT INTO `mv_user` VALUES (1, 1, 1);
INSERT INTO `mv_user` VALUES (2, 2, 1);
INSERT INTO `mv_user` VALUES (3, 3, 1);
INSERT INTO `mv_user` VALUES (4, 4, 1);
INSERT INTO `mv_user` VALUES (5, 5, 1);
INSERT INTO `mv_user` VALUES (6, 6, 1);
INSERT INTO `mv_user` VALUES (7, 7, 1);
INSERT INTO `mv_user` VALUES (8, 9, 45);
INSERT INTO `mv_user` VALUES (9, 10, 45);
INSERT INTO `mv_user` VALUES (10, 11, 45);
INSERT INTO `mv_user` VALUES (11, 12, 45);
INSERT INTO `mv_user` VALUES (12, 13, 1);
INSERT INTO `mv_user` VALUES (13, 14, 1);

-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '音乐清单ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `thumbnail_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `video_count` int NULL DEFAULT NULL COMMENT '视频数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '种类',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `total_views` int NULL DEFAULT NULL COMMENT '浏览量',
  `total_favorites` int NULL DEFAULT NULL COMMENT '点赞量',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `integral` int NULL DEFAULT NULL COMMENT '积分',
  `week_integral` int NULL DEFAULT NULL COMMENT '周积分',
  `total_user` int NULL DEFAULT NULL COMMENT '总积分',
  `rank` int NULL DEFAULT NULL COMMENT '排名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '音乐清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play
-- ----------------------------
INSERT INTO `play` VALUES (1, '经典歌曲', '20250812113504000000669.jpg', 4, '国语', '国语', 0, 3, 5, '2025-08-12 11:35:10', '2025-08-02 10:40:11', 3, 5, 8, 1);
INSERT INTO `play` VALUES (3, '流行音乐', '20250809112046000000445.jpg', 3, '在这座不夜城中，每个人都是匆匆过客。', '', 0, 0, 0, '2025-08-09 11:20:06', '2025-08-09 11:22:05', 0, 0, 0, 0);
INSERT INTO `play` VALUES (4, '蓝色孤本收藏家', '20250809010633000000461.jpeg', 3, '孤独，是那些深夜里的寂寞时光，与冷月为伴，秋水生凉。', '', 0, 0, 0, '2025-08-09 13:06:14', '2025-08-09 13:07:39', 0, 0, 0, 0);
INSERT INTO `play` VALUES (5, '没有答案的答案', '20251003020908000000308.jpeg', 3, '这世界上的许多事，就是没有答案的。答案在你心里，在你自己的选择。', '', 0, 0, 0, '2025-10-10 14:31:20', '2025-10-03 14:08:46', 0, 0, 0, 0);
INSERT INTO `play` VALUES (6, '孤单的人循环往复的听', '20251003023521000000483.jpeg', 3, '孤单不是缺失，而是灵魂的独立与自由。', '', 0, 0, 0, NULL, '2025-10-03 14:34:06', 0, 0, 0, 0);
INSERT INTO `play` VALUES (7, '于是我们感受', '20251004111334000000963.jpeg', 3, '于是我们感受，像淀浦河畔晨光中辨认左岸的旅人，以身体为罗盘丈量生活的流向。', '', 0, 0, 0, NULL, '2025-10-04 11:13:15', 0, 0, 0, 0);
INSERT INTO `play` VALUES (8, '最食人间烟火色', '20251004114555000000982.jpeg', 3, '人间烟火，是夕阳下的一抹炊烟，宁静而致远。', '', 0, 0, 0, NULL, '2025-10-04 11:42:17', 0, 0, 0, 0);
INSERT INTO `play` VALUES (9, '雨天思念', '20251011114046000000756.jpeg', 4, '雨滴连成线，思念涌成潮，雨天的心，格外柔软。', '', 0, 0, 0, NULL, '2025-10-11 11:37:42', 0, 0, 0, 0);
INSERT INTO `play` VALUES (10, '我的世界一直在下雨', '20251011020818000000268.jpeg', 3, '我的世界下雨了，滴滴答答的雨声，像是诉说着我的心事。愿这场雨能冲刷掉所有烦恼，还我一个清透的心灵。', '流行音乐', 0, 0, 0, NULL, '2025-10-11 14:05:13', 0, 0, 0, 0);
INSERT INTO `play` VALUES (11, '后来我们什么都有', '20251011024926000000341.jpg', 4, '岁月如歌，后来的我们，在每一个音符中都找到了自己的旋律，悠扬而深远。', '轻音乐', 0, 0, 0, NULL, '2025-10-11 14:51:33', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for play_mv
-- ----------------------------
DROP TABLE IF EXISTS `play_mv`;
CREATE TABLE `play_mv`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '音乐清单MV主键ID',
  `play_id` int NULL DEFAULT NULL COMMENT '音乐清单ID',
  `mv_id` int NULL DEFAULT NULL COMMENT 'MVID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play_mv
-- ----------------------------
INSERT INTO `play_mv` VALUES (12, 3, 2);
INSERT INTO `play_mv` VALUES (13, 3, 3);
INSERT INTO `play_mv` VALUES (14, 3, 6);
INSERT INTO `play_mv` VALUES (15, 4, 1);
INSERT INTO `play_mv` VALUES (16, 4, 2);
INSERT INTO `play_mv` VALUES (17, 4, 5);
INSERT INTO `play_mv` VALUES (18, 1, 1);
INSERT INTO `play_mv` VALUES (19, 1, 2);
INSERT INTO `play_mv` VALUES (20, 1, 3);
INSERT INTO `play_mv` VALUES (21, 1, 4);
INSERT INTO `play_mv` VALUES (23, 6, 2);
INSERT INTO `play_mv` VALUES (24, 6, 7);
INSERT INTO `play_mv` VALUES (25, 6, 13);
INSERT INTO `play_mv` VALUES (28, 7, 4);
INSERT INTO `play_mv` VALUES (29, 7, 9);
INSERT INTO `play_mv` VALUES (30, 7, 13);
INSERT INTO `play_mv` VALUES (40, 8, 9);
INSERT INTO `play_mv` VALUES (41, 8, 1);
INSERT INTO `play_mv` VALUES (42, 8, 13);
INSERT INTO `play_mv` VALUES (45, 5, 2);
INSERT INTO `play_mv` VALUES (46, 5, 13);
INSERT INTO `play_mv` VALUES (47, 5, 5);
INSERT INTO `play_mv` VALUES (48, 9, 1);
INSERT INTO `play_mv` VALUES (49, 9, 4);
INSERT INTO `play_mv` VALUES (50, 9, 6);
INSERT INTO `play_mv` VALUES (51, 9, 7);
INSERT INTO `play_mv` VALUES (52, 10, 2);
INSERT INTO `play_mv` VALUES (53, 10, 4);
INSERT INTO `play_mv` VALUES (54, 10, 5);
INSERT INTO `play_mv` VALUES (55, 11, 2);
INSERT INTO `play_mv` VALUES (56, 11, 4);
INSERT INTO `play_mv` VALUES (57, 11, 9);
INSERT INTO `play_mv` VALUES (58, 11, 10);

-- ----------------------------
-- Table structure for play_user
-- ----------------------------
DROP TABLE IF EXISTS `play_user`;
CREATE TABLE `play_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '音乐清单创作者主键ID',
  `play_id` int NULL DEFAULT NULL COMMENT '音乐清单ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '创作者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of play_user
-- ----------------------------
INSERT INTO `play_user` VALUES (1, 1, 1);
INSERT INTO `play_user` VALUES (2, 3, 1);
INSERT INTO `play_user` VALUES (3, 4, 1);
INSERT INTO `play_user` VALUES (4, 5, 1);
INSERT INTO `play_user` VALUES (5, 6, 1);
INSERT INTO `play_user` VALUES (6, 7, 1);
INSERT INTO `play_user` VALUES (7, 8, 1);
INSERT INTO `play_user` VALUES (8, 9, 1);
INSERT INTO `play_user` VALUES (9, 10, 1);
INSERT INTO `play_user` VALUES (10, 11, 45);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 'system', 0, 1, '/sys', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '系统管理目录');
INSERT INTO `sys_menu` VALUES (3, '用户管理', 'user', 1, 1, '/sys/user', 'sys/user/index', 'C', 'system:user:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (4, '角色管理', 'peoples', 1, 2, '/sys/role', 'sys/role/index', 'C', 'system:role:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (5, '菜单管理', 'tree-table', 1, 3, '/sys/menu', 'sys/menu/index', 'C', 'system:menu:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (8, '用户新增', '#', 3, 2, '', '', 'F', 'system:user:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES (9, '用户修改', '#', 3, 3, '', '', 'F', 'system:user:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES (10, '用户删除', '#', 3, 4, '', '', 'F', 'system:user:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES (11, '分配角色', '#', 3, 5, '', '', 'F', 'system:user:role', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配角色按钮');
INSERT INTO `sys_menu` VALUES (12, '重置密码', '#', 3, 6, '', '', 'F', 'system:user:resetPwd', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '重置密码按钮');
INSERT INTO `sys_menu` VALUES (13, '角色新增', '#', 4, 2, '', '', 'F', 'system:role:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES (14, '角色修改', '#', 4, 3, '', '', 'F', 'system:role:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES (15, '角色删除', '#', 4, 4, '', NULL, 'F', 'system:role:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES (16, '分配权限', '#', 4, 5, '', '', 'F', 'system:role:menu', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配权限按钮');
INSERT INTO `sys_menu` VALUES (17, '菜单新增', '#', 5, 2, '', NULL, 'F', 'system:menu:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加菜单按钮');
INSERT INTO `sys_menu` VALUES (18, '菜单修改', '#', 5, 3, '', NULL, 'F', 'system:menu:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改菜单按钮');
INSERT INTO `sys_menu` VALUES (19, '菜单删除', '#', 5, 4, '', NULL, 'F', 'system:menu:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除菜单按钮');
INSERT INTO `sys_menu` VALUES (20, '用户查询', '#', 3, 1, '', NULL, 'F', 'system:user:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '用户查询按钮');
INSERT INTO `sys_menu` VALUES (21, '角色查询', '#', 4, 1, '', NULL, 'F', 'system:role:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '角色查询按钮');
INSERT INTO `sys_menu` VALUES (22, '菜单查询', '#', 5, 1, '', NULL, 'F', 'system:menu:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '菜单查询按钮');
INSERT INTO `sys_menu` VALUES (34, '数据管理', 'data', 0, 2, '/data', NULL, 'M', '', '2025-07-16 11:28:46', '2025-07-16 11:28:57', '数据管理目录');
INSERT INTO `sys_menu` VALUES (37, '在线音乐', 'music', 34, 1, '/data/music', 'data/music/index', 'C', 'data:music:list', '2025-07-18 18:37:43', '2025-09-13 11:18:35', '在线音乐目录');
INSERT INTO `sys_menu` VALUES (38, '在线MV', 'mv', 34, 2, '/data/mv', 'data/mv/index', 'C', 'data:mv:list', '2025-07-25 18:33:55', '2025-09-13 11:19:07', '在线MV目录');
INSERT INTO `sys_menu` VALUES (39, '在线悦单', 'list', 34, 3, '/data/list', 'data/list/index', 'C', 'data:list:list', '2025-08-01 19:16:03', '2025-09-13 11:19:23', '在线悦单目录');
INSERT INTO `sys_menu` VALUES (40, '公告管理', 'notice', 1, 4, '/sys/notice', 'sys/notice/index', 'C', 'system:notice:list', '2025-08-14 22:14:06', '2025-09-16 14:16:58', '公告管理目录');
INSERT INTO `sys_menu` VALUES (41, '音乐查询', '', 37, 1, '', '', 'F', 'data:music:query', '2025-09-13 11:24:10', NULL, NULL);
INSERT INTO `sys_menu` VALUES (42, '音乐新增', '', 37, 2, '', '', 'F', 'data:music:add', '2025-09-13 11:25:22', NULL, NULL);
INSERT INTO `sys_menu` VALUES (43, '音乐修改', '', 37, 3, '', '', 'F', 'data:music:edit', '2025-09-13 11:26:18', NULL, NULL);
INSERT INTO `sys_menu` VALUES (44, '音乐删除', '', 37, 4, '', '', 'F', 'data:music:delete', '2025-09-13 11:27:22', NULL, NULL);
INSERT INTO `sys_menu` VALUES (45, 'MV查询', '', 38, 1, '', '', 'F', 'data:mv:query', '2025-09-13 11:28:31', NULL, NULL);
INSERT INTO `sys_menu` VALUES (46, 'MV新增', '', 38, 2, '', '', 'F', 'data:mv:add', '2025-09-13 11:29:26', NULL, NULL);
INSERT INTO `sys_menu` VALUES (47, 'MV修改', '', 38, 3, '', '', 'F', 'data:mv:edit', '2025-09-13 11:30:04', NULL, NULL);
INSERT INTO `sys_menu` VALUES (48, 'MV删除', '', 38, 4, '', '', 'F', 'data:mv:delete', '2025-09-13 11:30:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (49, '悦单查询', '', 39, 1, '', '', 'F', 'data:list:query', '2025-09-13 11:32:11', NULL, NULL);
INSERT INTO `sys_menu` VALUES (50, '悦单新增', '', 39, 2, '', '', 'F', 'data:list:add', '2025-09-13 11:32:57', NULL, NULL);
INSERT INTO `sys_menu` VALUES (51, '悦单修改', '', 39, 3, '', '', 'F', 'data:list:edit', '2025-09-13 11:33:33', NULL, NULL);
INSERT INTO `sys_menu` VALUES (52, '悦单删除', '', 39, 4, '', '', 'F', 'data:list:delete', '2025-09-13 11:34:18', NULL, NULL);
INSERT INTO `sys_menu` VALUES (53, '公告查询', '', 40, 1, '', '', 'F', 'system:notice:query', '2025-09-16 14:18:27', NULL, NULL);
INSERT INTO `sys_menu` VALUES (54, '公告新增', '', 40, 2, '', '', 'F', 'system:notice:add', '2025-09-16 14:19:27', NULL, NULL);
INSERT INTO `sys_menu` VALUES (55, '公告修改', '', 40, 3, '', '', 'F', 'system:notice:edit', '2025-09-16 14:20:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES (56, '公告删除', '', 40, 4, '', '', 'F', 'system:notice:delete', '2025-09-16 14:21:06', NULL, NULL);
INSERT INTO `sys_menu` VALUES (57, '安卓应用', 'android', 1, 6, '/sys/android', 'sys/android/index', 'C', 'system:android:list', '2025-10-20 13:04:38', '2025-10-28 11:31:56', NULL);
INSERT INTO `sys_menu` VALUES (58, '应用查询', '', 57, 1, '', '', 'F', 'system:android:query', '2025-10-20 13:09:41', NULL, NULL);
INSERT INTO `sys_menu` VALUES (59, '应用新增', '', 57, 2, '', '', 'F', 'system:android:add', '2025-10-20 13:10:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (60, '应用修改', '', 57, 3, '', '', 'F', 'system:android:edit', '2025-10-20 13:11:48', NULL, NULL);
INSERT INTO `sys_menu` VALUES (61, '应用删除', '', 57, 4, '', '', 'F', 'system:android:delete', '2025-10-20 13:12:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES (62, '反馈管理', 'feedback', 1, 5, '/sys/feedback', 'sys/feedback/index', 'C', 'system:feedback:list', '2025-10-28 11:34:19', NULL, NULL);
INSERT INTO `sys_menu` VALUES (63, '反馈查询', '', 62, 1, '', '', 'F', 'system:feedback:query', '2025-10-28 11:37:50', NULL, NULL);
INSERT INTO `sys_menu` VALUES (64, '反馈新增', '', 62, 2, '', '', 'F', 'system:feedback:add', '2025-10-28 11:38:38', NULL, NULL);
INSERT INTO `sys_menu` VALUES (65, '反馈修改', '', 62, 3, '', '', 'F', 'system:feedback:edit', '2025-10-28 11:39:13', NULL, NULL);
INSERT INTO `sys_menu` VALUES (66, '反馈删除', '', 62, 4, '', '', 'F', 'system:feedback:delete', '2025-10-28 11:40:08', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '公告发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '新功能上线', '新增在线音乐管理功能', '2025-08-15 10:57:40');
INSERT INTO `sys_notice` VALUES (2, '新功能上线-002', '新增在线MV管理功能', '2025-08-15 14:04:48');
INSERT INTO `sys_notice` VALUES (3, '新功能上线-003', '新增在线悦单管理功能', '2025-08-15 14:28:03');
INSERT INTO `sys_notice` VALUES (4, '新功能上线-004', '新增公告管理功能', '2025-08-15 14:28:40');
INSERT INTO `sys_notice` VALUES (5, '新功能上线-005', '新增查看MV功能', '2025-08-15 14:44:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '2022-07-04 14:40:44', '2022-07-04 14:40:47', '拥有系统最高权限');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', '2022-07-04 14:41:56', '2022-07-04 14:41:58', '普通角色');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色菜单主键ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 822 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (15, 3, 2);
INSERT INTO `sys_role_menu` VALUES (16, 3, 6);
INSERT INTO `sys_role_menu` VALUES (17, 3, 7);
INSERT INTO `sys_role_menu` VALUES (21, 7, 1);
INSERT INTO `sys_role_menu` VALUES (22, 7, 2);
INSERT INTO `sys_role_menu` VALUES (23, 7, 6);
INSERT INTO `sys_role_menu` VALUES (24, 7, 7);
INSERT INTO `sys_role_menu` VALUES (25, 6, 1);
INSERT INTO `sys_role_menu` VALUES (26, 6, 3);
INSERT INTO `sys_role_menu` VALUES (27, 6, 9);
INSERT INTO `sys_role_menu` VALUES (28, 6, 10);
INSERT INTO `sys_role_menu` VALUES (29, 19, 1);
INSERT INTO `sys_role_menu` VALUES (30, 19, 3);
INSERT INTO `sys_role_menu` VALUES (31, 19, 2);
INSERT INTO `sys_role_menu` VALUES (32, 19, 6);
INSERT INTO `sys_role_menu` VALUES (208, 20, 1);
INSERT INTO `sys_role_menu` VALUES (209, 20, 3);
INSERT INTO `sys_role_menu` VALUES (210, 20, 20);
INSERT INTO `sys_role_menu` VALUES (211, 20, 8);
INSERT INTO `sys_role_menu` VALUES (212, 20, 9);
INSERT INTO `sys_role_menu` VALUES (213, 20, 33);
INSERT INTO `sys_role_menu` VALUES (214, 20, 10);
INSERT INTO `sys_role_menu` VALUES (215, 20, 11);
INSERT INTO `sys_role_menu` VALUES (216, 20, 4);
INSERT INTO `sys_role_menu` VALUES (217, 20, 21);
INSERT INTO `sys_role_menu` VALUES (218, 20, 13);
INSERT INTO `sys_role_menu` VALUES (219, 20, 5);
INSERT INTO `sys_role_menu` VALUES (220, 20, 22);
INSERT INTO `sys_role_menu` VALUES (221, 20, 17);
INSERT INTO `sys_role_menu` VALUES (222, 20, 18);
INSERT INTO `sys_role_menu` VALUES (223, 20, 2);
INSERT INTO `sys_role_menu` VALUES (224, 20, 6);
INSERT INTO `sys_role_menu` VALUES (225, 20, 7);
INSERT INTO `sys_role_menu` VALUES (232, 21, 1);
INSERT INTO `sys_role_menu` VALUES (233, 21, 9);
INSERT INTO `sys_role_menu` VALUES (234, 21, 4);
INSERT INTO `sys_role_menu` VALUES (235, 21, 21);
INSERT INTO `sys_role_menu` VALUES (236, 21, 2);
INSERT INTO `sys_role_menu` VALUES (237, 21, 6);
INSERT INTO `sys_role_menu` VALUES (238, 21, 7);
INSERT INTO `sys_role_menu` VALUES (251, 4, 1);
INSERT INTO `sys_role_menu` VALUES (252, 4, 2);
INSERT INTO `sys_role_menu` VALUES (253, 4, 6);
INSERT INTO `sys_role_menu` VALUES (254, 4, 7);
INSERT INTO `sys_role_menu` VALUES (750, 1, 1);
INSERT INTO `sys_role_menu` VALUES (751, 1, 3);
INSERT INTO `sys_role_menu` VALUES (752, 1, 20);
INSERT INTO `sys_role_menu` VALUES (753, 1, 8);
INSERT INTO `sys_role_menu` VALUES (754, 1, 9);
INSERT INTO `sys_role_menu` VALUES (755, 1, 10);
INSERT INTO `sys_role_menu` VALUES (756, 1, 11);
INSERT INTO `sys_role_menu` VALUES (757, 1, 12);
INSERT INTO `sys_role_menu` VALUES (758, 1, 4);
INSERT INTO `sys_role_menu` VALUES (759, 1, 21);
INSERT INTO `sys_role_menu` VALUES (760, 1, 13);
INSERT INTO `sys_role_menu` VALUES (761, 1, 14);
INSERT INTO `sys_role_menu` VALUES (762, 1, 15);
INSERT INTO `sys_role_menu` VALUES (763, 1, 16);
INSERT INTO `sys_role_menu` VALUES (764, 1, 5);
INSERT INTO `sys_role_menu` VALUES (765, 1, 22);
INSERT INTO `sys_role_menu` VALUES (766, 1, 17);
INSERT INTO `sys_role_menu` VALUES (767, 1, 18);
INSERT INTO `sys_role_menu` VALUES (768, 1, 19);
INSERT INTO `sys_role_menu` VALUES (769, 1, 40);
INSERT INTO `sys_role_menu` VALUES (770, 1, 53);
INSERT INTO `sys_role_menu` VALUES (771, 1, 54);
INSERT INTO `sys_role_menu` VALUES (772, 1, 55);
INSERT INTO `sys_role_menu` VALUES (773, 1, 56);
INSERT INTO `sys_role_menu` VALUES (774, 1, 62);
INSERT INTO `sys_role_menu` VALUES (775, 1, 63);
INSERT INTO `sys_role_menu` VALUES (776, 1, 64);
INSERT INTO `sys_role_menu` VALUES (777, 1, 65);
INSERT INTO `sys_role_menu` VALUES (778, 1, 66);
INSERT INTO `sys_role_menu` VALUES (779, 1, 57);
INSERT INTO `sys_role_menu` VALUES (780, 1, 58);
INSERT INTO `sys_role_menu` VALUES (781, 1, 59);
INSERT INTO `sys_role_menu` VALUES (782, 1, 60);
INSERT INTO `sys_role_menu` VALUES (783, 1, 61);
INSERT INTO `sys_role_menu` VALUES (784, 1, 34);
INSERT INTO `sys_role_menu` VALUES (785, 1, 37);
INSERT INTO `sys_role_menu` VALUES (786, 1, 41);
INSERT INTO `sys_role_menu` VALUES (787, 1, 42);
INSERT INTO `sys_role_menu` VALUES (788, 1, 43);
INSERT INTO `sys_role_menu` VALUES (789, 1, 44);
INSERT INTO `sys_role_menu` VALUES (790, 1, 38);
INSERT INTO `sys_role_menu` VALUES (791, 1, 45);
INSERT INTO `sys_role_menu` VALUES (792, 1, 46);
INSERT INTO `sys_role_menu` VALUES (793, 1, 47);
INSERT INTO `sys_role_menu` VALUES (794, 1, 48);
INSERT INTO `sys_role_menu` VALUES (795, 1, 39);
INSERT INTO `sys_role_menu` VALUES (796, 1, 49);
INSERT INTO `sys_role_menu` VALUES (797, 1, 50);
INSERT INTO `sys_role_menu` VALUES (798, 1, 51);
INSERT INTO `sys_role_menu` VALUES (799, 1, 52);
INSERT INTO `sys_role_menu` VALUES (822, 2, 20);
INSERT INTO `sys_role_menu` VALUES (823, 2, 9);
INSERT INTO `sys_role_menu` VALUES (824, 2, 53);
INSERT INTO `sys_role_menu` VALUES (825, 2, 63);
INSERT INTO `sys_role_menu` VALUES (826, 2, 64);
INSERT INTO `sys_role_menu` VALUES (827, 2, 65);
INSERT INTO `sys_role_menu` VALUES (828, 2, 66);
INSERT INTO `sys_role_menu` VALUES (829, 2, 34);
INSERT INTO `sys_role_menu` VALUES (830, 2, 37);
INSERT INTO `sys_role_menu` VALUES (831, 2, 41);
INSERT INTO `sys_role_menu` VALUES (832, 2, 42);
INSERT INTO `sys_role_menu` VALUES (833, 2, 43);
INSERT INTO `sys_role_menu` VALUES (834, 2, 44);
INSERT INTO `sys_role_menu` VALUES (835, 2, 38);
INSERT INTO `sys_role_menu` VALUES (836, 2, 45);
INSERT INTO `sys_role_menu` VALUES (837, 2, 46);
INSERT INTO `sys_role_menu` VALUES (838, 2, 47);
INSERT INTO `sys_role_menu` VALUES (839, 2, 48);
INSERT INTO `sys_role_menu` VALUES (840, 2, 39);
INSERT INTO `sys_role_menu` VALUES (841, 2, 49);
INSERT INTO `sys_role_menu` VALUES (842, 2, 50);
INSERT INTO `sys_role_menu` VALUES (843, 2, 51);
INSERT INTO `sys_role_menu` VALUES (844, 2, 52);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'default.jpg' COMMENT '用户头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'java1234', '$2a$10$lJbJLsUFkA67J4Cvk6RFIu.Zd1hdTD1iUzTAAW0XQr2sPltS5aw3e', '20250908034930000000985.jpg', 'caofeng4012@126.com', '18862857412', '2022-08-29 22:10:52', '0', '2022-06-09 08:47:52', '2025-09-08 15:49:31', '备注');
INSERT INTO `sys_user` VALUES (2, 'common', '$2a$10$tiArwm0GxChyEP5k0JGzsOuzyY15IKA.ZTl8S2aj3haYlKAfpwfl.', '222.jpg', '', '', '2022-08-22 21:34:39', '0', '2022-07-05 11:22:47', NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'test', '$2a$10$tiArwm0GxChyEP5k0JGzsOuzyY15IKA.ZTl8S2aj3haYlKAfpwfl.', '333.jpg', '', '', '2022-07-24 17:36:07', '0', '2022-07-15 11:23:09', NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '1', '$2a$10$lD0Fx7oMsFFmX9hVkmYy7eJteH8pBaXXro1X9DEMP5sbM.Z6Co55m', 'default.jpg', '', '', NULL, '1', '2022-07-20 11:23:33', NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '2', NULL, 'default.jpg', '', '', NULL, '1', '2022-07-23 11:24:20', NULL, NULL);
INSERT INTO `sys_user` VALUES (15, 'fdsfs', '$2a$10$AQVcp4hQ7REc5o7ztVnI7eX.sJdcYy3d1x2jm5CfrcCoMZMPacfpi', 'default.jpg', 'fdfa4@qq.com', '18862851414', '2022-08-02 02:22:45', '1', '2022-08-02 02:21:24', '2022-08-01 18:23:16', 'fdfds4');
INSERT INTO `sys_user` VALUES (28, 'sdfss2', '$2a$10$7aNJxwVmefI0XAk64vrzYuOqeeImYJUQnoBrtKP9pLTGTWO2CXQ/y', 'default.jpg', 'dfds3@qq.com', '18862857413', NULL, '1', '2022-08-07 00:42:46', '2022-08-06 16:43:04', 'ddd33');
INSERT INTO `sys_user` VALUES (29, 'ccc', '$2a$10$7cbWeVwDWO9Hh3qbJrvTHOn0E/DLYXxnIZpxZei0jY4ChfQbJuhi.', '20220829080150000000341.jpg', '3242@qq.com', '18862584120', '2022-08-29 19:52:27', '0', '2022-08-29 17:04:58', NULL, 'xxx');
INSERT INTO `sys_user` VALUES (30, 'ccc666', '$2a$10$Tmw5VCM/K2vb837AZDYHQOqE3gPiRZKevxLsh/ozndpTSjdwABqaK', '20220829100454000000771.jpg', 'fdafds@qq.com', '18865259845', '2022-08-29 22:05:18', '0', '2022-08-29 22:00:39', NULL, 'ccc');
INSERT INTO `sys_user` VALUES (31, '1', NULL, 'default.jpg', '', '', NULL, '0', '2023-06-14 11:24:36', NULL, NULL);
INSERT INTO `sys_user` VALUES (32, '2', NULL, 'default.jpg', '', '', NULL, '0', '2024-04-18 11:24:51', NULL, NULL);
INSERT INTO `sys_user` VALUES (40, 'common2', '$2a$10$25fGtgLQyUziPqLh/vroI.s3BiUFXZfVcc8KO1TyIZmKrK0lJOoLy', 'default.jpg', 'dfs2@qq.com', '15525462582', NULL, '1', '2025-07-03 20:43:58', '2025-07-05 11:16:31', 'xxx2');
INSERT INTO `sys_user` VALUES (42, 'android123', '$2a$10$FhHVIcpbUyPrl/9dTAaOe.x7qFpchAPmmDZ8Yxze6//n4Uxr7.uq6', '20250909113300000000634.jpg', '', '', NULL, '0', '2025-08-25 15:09:17', '2025-09-09 11:33:01', NULL);
INSERT INTO `sys_user` VALUES (43, 'ZTEn788', '$2a$10$w5Dr0wbp6.r1seG2fXZMLOvQb3wCUxyaL2lmXoSjY4X3U6XZnXMR2', 'default.jpg', '', '', NULL, '0', '2025-09-09 13:18:34', NULL, NULL);
INSERT INTO `sys_user` VALUES (44, 'test123', '$2a$10$P5NFAzA2KLOauIY.NxU7aesaoIrfcsRHJXaOjuWj887ZCvfUsMlja', 'default.jpg', '', '', NULL, '0', '2025-09-09 13:25:08', NULL, NULL);
INSERT INTO `sys_user` VALUES (45, 'test2', '$2a$10$uuG7SzL8DlIkvyjPzqQHHOBb1cUEcEIBffX5KqSDUEhLF921KGstu', '20250924015209000000442.jpg', 'jdhh@qq.com', '13546878655', NULL, '0', '2025-09-09 13:42:44', '2025-09-24 13:52:09', NULL);
INSERT INTO `sys_user` VALUES (46, '1222', '$2a$10$GqZqMSk/TCxw/OSUtwiJEe.4YxEroO15J2boMYhIhgqEvPfIK8ouu', 'default.jpg', '', '', NULL, '0', '2025-09-12 12:59:48', NULL, '123');
INSERT INTO `sys_user` VALUES (47, 'web123', '$2a$10$rr/bFumk7ZKKPBmcAmEwVOlUIwUEqDVqUrJGYT7lOPHeHMypxjuSO', 'default.jpg', 'web123@qq.com', '13533445789', NULL, '0', '2025-10-02 10:37:19', NULL, '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色主键ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (4, 1, 2);
INSERT INTO `sys_user_role` VALUES (7, 3, 2);
INSERT INTO `sys_user_role` VALUES (28, 42, 2);
INSERT INTO `sys_user_role` VALUES (29, 43, 2);
INSERT INTO `sys_user_role` VALUES (30, 44, 2);
INSERT INTO `sys_user_role` VALUES (31, 45, 2);
INSERT INTO `sys_user_role` VALUES (32, 46, 2);
INSERT INTO `sys_user_role` VALUES (33, 47, 2);
INSERT INTO `sys_user_role` VALUES (34, 4, 2);
INSERT INTO `sys_user_role` VALUES (35, 5, 2);
INSERT INTO `sys_user_role` VALUES (36, 15, 2);
INSERT INTO `sys_user_role` VALUES (37, 28, 2);
INSERT INTO `sys_user_role` VALUES (38, 29, 2);
INSERT INTO `sys_user_role` VALUES (39, 30, 2);
INSERT INTO `sys_user_role` VALUES (40, 31, 2);
INSERT INTO `sys_user_role` VALUES (41, 32, 2);
INSERT INTO `sys_user_role` VALUES (42, 40, 2);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 13/12/2021 16:42:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about`  (
  `id` bigint(20) NOT NULL,
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES (1, 'title', '标题', '关于帅气的 Naccl');
INSERT INTO `about` VALUES (2, 'musicId', '网易云歌曲ID', '423015580');
INSERT INTO `about` VALUES (3, 'content', '正文Markdown', '');
INSERT INTO `about` VALUES (4, 'commentEnabled', '评论开关', 'true');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `blog_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for ms_admin
-- ----------------------------
DROP TABLE IF EXISTS `ms_admin`;
CREATE TABLE `ms_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_admin
-- ----------------------------
INSERT INTO `ms_admin` VALUES (1, 'admin', '$2a$10$RZECQ90DjOT/t1mhnXsl5.XSuZWc0Sa1XduPxj2rb4yaSYcje3nWW');
INSERT INTO `ms_admin` VALUES (2, 'mszlu', '$2a$10$RZECQ90DjOT/t1mhnXsl5.XSuZWc0Sa1XduPxj2rb4yaSYcje3nWW');

-- ----------------------------
-- Table structure for ms_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `ms_admin_permission`;
CREATE TABLE `ms_admin_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_admin_permission
-- ----------------------------
INSERT INTO `ms_admin_permission` VALUES (1, 1, 1);
INSERT INTO `ms_admin_permission` VALUES (2, 2, 1);

-- ----------------------------
-- Table structure for ms_article
-- ----------------------------
DROP TABLE IF EXISTS `ms_article`;
CREATE TABLE `ms_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_counts` int(11) NULL DEFAULT NULL COMMENT '评论数量',
  `create_date` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `view_counts` int(11) NULL DEFAULT NULL COMMENT '浏览数量',
  `weight` int(11) NOT NULL COMMENT '是否置顶',
  `author_id` bigint(20) NULL DEFAULT NULL COMMENT '作者id',
  `body_id` bigint(20) NULL DEFAULT NULL COMMENT '内容id',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1469579087743401986 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_article
-- ----------------------------
INSERT INTO `ms_article` VALUES (1, 20, 1602132131231, '通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过`java -jar`命令就可以运行起来。\r\n\r\n这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。', 'springboot介绍以及入门案例', 194, 0, 1, 1, 2);
INSERT INTO `ms_article` VALUES (9, 0, 1632132131231, 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。', 'Vue.js 到底是什么', 26, 0, 1, 20, 2);
INSERT INTO `ms_article` VALUES (10, 0, 1532132131231, '本节将介绍如何在项目中使用 Element。', 'Element相关', 11, 0, 1, 21, 2);
INSERT INTO `ms_article` VALUES (1405564731300831233, 0, 1432132131231, '66666666666', '666666666666666', 2, 0, 1, 1405564731351162882, 2);
INSERT INTO `ms_article` VALUES (1405909844724051969, 0, 1332132131231, '123', '123123', 3, 0, 1, 1405909844828909569, 3);
INSERT INTO `ms_article` VALUES (1405916999732707330, 0, 1232132131231, 'springboot入门案例', 'SpringBoot入门案例', 5, 0, 1, 1405916999854342146, 2);
INSERT INTO `ms_article` VALUES (1468182881431461889, 0, 1638877051112, '魔刀千刃简介', '魔刀千刃', 17, 0, 1, 1468182881431461890, 4);
INSERT INTO `ms_article` VALUES (1468187310301925377, 1, 1638878107043, '李搏猛是神', '李搏猛牛逼', 8, 0, 1, 1468187310331285506, 2);
INSERT INTO `ms_article` VALUES (1468192662137425921, 1, 1638879383050, '村正简介', '村正', 5, 0, 1, 1468192662275837954, 3);
INSERT INTO `ms_article` VALUES (1468227279309623298, 0, 1638887636398, '测试摘要', '测试', 14, 0, 1, 1468227279322206210, 2);
INSERT INTO `ms_article` VALUES (1468231964523352066, 0, 1638888753433, '测试', 'test', 6, 0, 1468228187896532994, 1468231964527546370, 2);
INSERT INTO `ms_article` VALUES (1468473952799608834, 0, 1638946447937, '啥啥啥', '程序猿', 8, 0, 1468473254548656129, 1468473952807997441, 3);
INSERT INTO `ms_article` VALUES (1468562564480266241, 0, 1638967574620, '1111', '新文章', 3, 0, 1, 1468562564547375106, 1);
INSERT INTO `ms_article` VALUES (1468562816797102082, 1, 1638967634775, '111', '新的', 67, 0, 1, 1468562816851628034, 1);
INSERT INTO `ms_article` VALUES (1469349701563756546, 0, 1639155242724, '测试', 'Redis测试', 4, 0, 1, 1469349701626671105, 4);
INSERT INTO `ms_article` VALUES (1469350256096944130, 0, 1639155374936, '测试', 'Redis测试', 0, 0, 1, 1469350256159858689, 4);
INSERT INTO `ms_article` VALUES (1469574735540588545, 0, 1639208895006, '111', '新文章', 0, 0, 1, 1469574735607697410, 2);
INSERT INTO `ms_article` VALUES (1469578376494260225, 0, 1639209763076, '111', '文章', 1, 0, 1, 1469578376552980481, 2);
INSERT INTO `ms_article` VALUES (1469579087743401985, 1, 1639209932652, '12323', '信信信的', 7, 0, 1, 1469579087810510850, 1);

-- ----------------------------
-- Table structure for ms_article_body
-- ----------------------------
DROP TABLE IF EXISTS `ms_article_body`;
CREATE TABLE `ms_article_body`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content_html` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `article_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1469579087810510851 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_article_body
-- ----------------------------
INSERT INTO `ms_article_body` VALUES (1, '# 1. Spring Boot介绍\r\n\r\n## 1.1 简介\r\n\r\n在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？\r\n\r\n在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？\r\n\r\n那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！\r\n\r\nSpring Boot让我们的Spring应用变的更轻量化。\r\n\r\n我们不必像以前那样繁琐的构建项目、打包应用、部署到Tomcat等应用服务器中来运行我们的业务服务。\r\n\r\n通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过`java -jar`命令就可以运行起来。\r\n\r\n这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。\r\n\r\n**总结一下Spring Boot的主要优点：**\r\n\r\n1. 为所有Spring开发者更快的入门\r\n2. 开箱即用，提供各种默认配置来简化项目配置\r\n3. 内嵌式容器简化Web项目\r\n4. 没有冗余代码生成和XML配置的要求\r\n5. 统一的依赖管理\r\n6. 自动装配，更易使用，更易扩展\r\n\r\n## 1.2 使用版本说明\r\n\r\nSpringboot版本：使用最新的2.5.0版本\r\n\r\n教程参考了官方文档进行制作，权威。\r\n\r\n其他依赖版本：\r\n\r\n	1. Maven  需求：3.5+\r\n\r\n   	2. JDK 需求  8+\r\n   	3. Spring Framework 5.3.7以上版本\r\n   	4. Tomcat 9.0\r\n   	5. Servlet版本 4.0  但是可以部署到Servlet到3.1+的容器中\r\n\r\n# 2. 快速入门\r\n\r\n快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。\r\n\r\n教程使用的Idea版本：2019.3\r\n\r\n## 2.1 创建基础项目\r\n\r\n**第一步：** 创建maven项目\r\n\r\npom.xml :\r\n\r\n~~~xml\r\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n    <modelVersion>4.0.0</modelVersion>\r\n\r\n    <groupId>com.xiaopizhu</groupId>\r\n    <artifactId>helloSpringBoot</artifactId>\r\n    <version>1.0-SNAPSHOT</version>\r\n	<!--springboot的父工程其中定义了常用的依赖，并且无依赖冲突-->\r\n    <parent>\r\n        <groupId>org.springframework.boot</groupId>\r\n        <artifactId>spring-boot-starter-parent</artifactId>\r\n        <version>2.5.0</version>\r\n    </parent>\r\n</project>\r\n~~~\r\n\r\n注意上方的parent必须加，其中定义了springboot官方支持的n多依赖，基本上常用的已经有了，所以接下来导入依赖的时候，绝大部分都可以不加版本号。\r\n\r\n此时的工程结构为：\r\n\r\n![image-20210523173241557](img/image-20210523173241557.png)\r\n\r\n**第二步：** 添加web依赖\r\n\r\n~~~xml\r\n<dependencies>\r\n    <dependency>\r\n        <groupId>org.springframework.boot</groupId>\r\n        <artifactId>spring-boot-starter-web</artifactId>\r\n    </dependency>\r\n</dependencies>\r\n~~~\r\n\r\n添加上方的web依赖，其中间接依赖了spring-web，spring-webmvc，spring-core等spring和springmvc的包，并且集成了tomcat。\r\n\r\n**第三步：** 编写启动类\r\n\r\n~~~java\r\npackage com.xiaopizhu.springboot;\r\n\r\nimport org.springframework.boot.SpringApplication;\r\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\r\n\r\n@SpringBootApplication\r\npublic class HelloApp {\r\n\r\n    public static void main(String[] args) {\r\n        SpringApplication.run(HelloApp.class,args);\r\n    }\r\n}\r\n\r\n~~~\r\n\r\n@SpringBootApplication注解标识了HelloApp为启动类，也是Spring Boot的核心。\r\n\r\n**第四步：** 运行启动类的main方法\r\n\r\n![image-20210523173712142](img/image-20210523173712142.png)\r\n\r\n看到如上配置，证明启动成功，tomcat端口号默认为8080。\r\n\r\n**第五步：**  如果想要修改端口号，可以在resources目录下新建application.properties\r\n\r\n~~~properties\r\nserver.port=8082\r\n~~~\r\n\r\n**第六步：** 重新运行\r\n\r\n![image-20210523174011613](img/image-20210523174011613.png)\r\n\r\n此时的项目结构为：\r\n\r\n![image-20210523174032053](img/image-20210523174032053.png)\r\n\r\n**src/main/java :**  编写java代码，注意启动类需要放在项目的根包下。\r\n\r\n**src/main/resources:**  放置资源的目录，比如springboot的配置文件，静态文件，mybatis配置，日志配置等。\r\n\r\n**src/test/java:**  测试代码\r\n\r\n## 2.2 编写一个Http接口\r\n\r\n**第一步：**创建`HelloController`类，内容如下：\r\n\r\n~~~java\r\npackage com.xiaopizhu.springboot.controller;\r\n\r\nimport org.springframework.web.bind.annotation.GetMapping;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RestController;\r\n\r\n@RestController\r\n@RequestMapping(\"hello\")\r\npublic class HelloController {\r\n\r\n    @GetMapping(\"boot\")\r\n    public String hello(){\r\n        return \"hello spring boot\";\r\n    }\r\n\r\n}\r\n\r\n~~~\r\n\r\n**注意包名，必须在启动类所在的包名下。**\r\n\r\n**第二步：**重启程序，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\r\n\r\n得到结果：hello spring boot\r\n\r\n## 2.3 编写单元测试用例\r\n\r\n**第一步：**添加spring boot测试依赖\r\n\r\n~~~xml\r\n		<dependency>\r\n            <groupId>org.springframework.boot</groupId>\r\n            <artifactId>spring-boot-starter-test</artifactId>\r\n            <scope>test</scope>\r\n        </dependency>\r\n~~~\r\n\r\n**第二步：**在src/test 下，编写测试用例\r\n\r\n~~~java\r\npackage com.xiaopizhu.springboot.controller;\r\n\r\nimport org.junit.jupiter.api.BeforeAll;\r\nimport org.junit.jupiter.api.BeforeEach;\r\nimport org.junit.jupiter.api.Test;\r\nimport org.springframework.boot.test.context.SpringBootTest;\r\nimport org.springframework.http.MediaType;\r\nimport org.springframework.test.web.servlet.MockMvc;\r\nimport org.springframework.test.web.servlet.request.MockMvcRequestBuilders;\r\nimport org.springframework.test.web.servlet.setup.MockMvcBuilders;\r\n\r\nimport static org.hamcrest.Matchers.equalTo;\r\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\r\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\r\n\r\n@SpringBootTest\r\npublic class TestHelloController {\r\n\r\n    private MockMvc mockMvc;\r\n\r\n    @BeforeEach\r\n    public void beforeEach(){\r\n        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();\r\n    }\r\n    @Test\r\n    public void testHello() throws Exception {\r\n        mockMvc.perform(MockMvcRequestBuilders.get(\"/hello/boot\")\r\n                .accept(MediaType.APPLICATION_JSON))\r\n                .andExpect(status().isOk())\r\n                .andExpect(content().string(equalTo(\"hello spring boot\")));\r\n    }\r\n}\r\n\r\n~~~\r\n\r\n上面的测试用例，是构建一个空的`WebApplicationContext`，并且在before中加载了HelloController，得以在测试用例中mock调用，模拟请求。\r\n\r\n## 2.4 打包为jar运行\r\n\r\n**第一步：**添加打包(maven构建springboot)插件\r\n\r\n~~~xml\r\n  <build>\r\n        <plugins>\r\n            <plugin>\r\n                <groupId>org.springframework.boot</groupId>\r\n                <artifactId>spring-boot-maven-plugin</artifactId>\r\n            </plugin>\r\n        </plugins>\r\n    </build>\r\n~~~\r\n\r\n在idea的右侧 maven中，使用package来打包程序，打包完成后，在target目录下生成helloSpringBoot-1.0-SNAPSHOT.jar\r\n\r\n![image-20210523181737720](img/image-20210523181737720.png)\r\n\r\n**第二步：**打开cmd：找到jar对应的目录\r\n\r\n输入命令\r\n\r\n~~~shell\r\njava -jar helloSpringBoot-1.0-SNAPSHOT.jar\r\n~~~\r\n\r\n![image-20210523182426404](img/image-20210523182426404.png)\r\n\r\n**第三步：**测试，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\r\n\r\n得到结果：hello spring boot\r\n\r\n## 2.5 查看jar包内容\r\n\r\n~~~shell\r\njar tvf helloSpringBoot-1.0-SNAPSHOT.jar\r\n~~~\r\n\r\n# 3. 小结\r\n\r\n1. 通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。\r\n2. 通过修改配置文件，更改端口号\r\n3. 编写了测试用例\r\n4. 打包jar包运行\r\n\r\n', '<h2>2018-01-04</h2>\n<pre><code class=\"lang-\"># 使用vue的Webpack模板生成脚手架\n</code></pre>\n<h2>2018-01-05</h2>\n<pre><code class=\"lang-\"># 引入ElementUI\n\n# babel-plugin-component自定义主题\n# 首页\n# 登陆页\n# 注册页面\n# 日志页\n</code></pre>\n<h2>2018-01-07</h2>\n<pre><code class=\"lang-\"># 调整底部栏始终固定在底部\n# 日志页 添加时间轴\n# 首页的文章列表\n</code></pre>\n<h2>2018-01-08</h2>\n<pre><code class=\"lang-\"># 使用组件-博客作者tab页 \n# 添加第三方图标\n</code></pre>\n<h2>2018-01-09</h2>\n<pre><code class=\"lang-\"># 调整顶部导航栏：激活文字颜色，click点击\n# 组件-最新文章tab页\n\n# 最新文章、最热文章使用相同组件\n# 底部栏设计\n# 页面与两边边距改为100\n</code></pre>\n<h2>2018-01-10</h2>\n<pre><code class=\"lang-\"># 写博客 引入mavonEditor编辑器\n# 顶部导航栏都放入一个Menu中\n# 写文章页面\n#　mavonEditor局部引入\n\n#　页面的中间区域固定宽度，自动居中\n# 发布和取消\n# 发布dialog\n\n</code></pre>\n<h2>2018-01-11</h2>\n<pre><code class=\"lang-\"># 文章组件用守卫来改变body背景色\n# 调整登陆和注册页面，使其居中\n\n#子页面调整根元素为div\n#文章详情页\n\n</code></pre>\n<h2>2018-01-12</h2>\n<pre><code class=\"lang-\"># 文章详情页  内容  评论等\n\n</code></pre>\n<h2>2018-01-13</h2>\n<pre><code class=\"lang-\">## 重新调整页面结构	\n#顶部和底部 抽成  BaseHeader BaseFooter 组件\n#BlogView为单独页，以前是Home的子路由\n\n</code></pre>\n<h2>2018-01-15</h2>\n<pre><code class=\"lang-\"># 文章分类去掉子级\n# 将首页的文章列表抽成 ArticleItem组件\n# 增加文章的评论展示\n# 增加文章分类、标签页\n\n</code></pre>\n<h2>2018-01-15  2</h2>\n<pre><code class=\"lang-\"># 回到顶部去掉过渡动画（影响顶部导航栏）\n# 顶部导航栏 增加登录后菜单\n# 首页增加 最热标签\n# 增加 文章分类 标签的详情页\n# 将文章详情页、 文章分类标签页 改为Home的子路由（以前单独页）\n# Home组件增加路由判断：更正导航栏的状态、条件显示底部栏\n\n</code></pre>\n<h2>2018-01-16</h2>\n<pre><code class=\"lang-\"># 将写文章的顶部Header合并到BaseHeader中\n# 图片都放到了static目录下\n\n</code></pre>\n<h2>2018-01-24</h2>\n<pre><code class=\"lang-\"># 将自定义的theme放到assets下\n# 加入axios\n# 加入vuex\n# 实现登录\n# 实现退出\n\n</code></pre>\n<h2>2018-01-25</h2>\n<pre><code class=\"lang-\"># 实现注册逻辑\n# 写文章功能实现\n# 写文章时支持插入图片\n\n</code></pre>\n<h2>2018-01-26</h2>\n<pre><code class=\"lang-\"># 引入lodash工具类\n# 优化写文章的工具栏：滚动时固定顶部\n# 写文章 后台获取文章分类和标签\n\n# 首页的文章列表\n\n</code></pre>\n<h2>2018-01-27</h2>\n<pre><code class=\"lang-\"># 修改首页文章列表的样式\n# 首页加载文章功能\n# 文章查看功能\n# 文章分类和标签功能列表\n\n</code></pre>\n<h2>2018-01-28</h2>\n<pre><code class=\"lang-\"># 文章分类和标签详情\n\n</code></pre>\n<h2>2018-01-29</h2>\n<pre><code class=\"lang-\"># 文章分类和标签的文章数\n# 首页最热文章\n# 首页最新文章\n# 首页最热标签\n\n</code></pre>\n<h2>2018-01-30</h2>\n<pre><code class=\"lang-\"># BaseHeader放回views中\n# 修改Axios后置拦截，全局处理错误\n# 将登录 退出 和头像 放到一起\n\n</code></pre>\n', 1);
INSERT INTO `ms_article_body` VALUES (20, 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。\n\n如果你想在深入学习 Vue 之前对它有更多了解，我们制作了一个视频，带您了解其核心概念和一个示例工程。\n如果你已经是有经验的前端开发者，想知道 Vue 与其它库/框架有哪些区别，请查看对比其它框架。\n\n# 起步\n\n> 官方指南假设你已了解关于 HTML、CSS 和 JavaScript 的中级知识。如果你刚开始学习前端开发，将框架作为你的第一步可能不是最好的主意——掌握好基础知识再来吧！之前有其它框架的使用经验会有帮助，但这不是必需的。\n\n尝试 Vue.js 最简单的方法是使用 JSFiddle 上的 Hello World 例子。你可以在浏览器新标签页中打开它，跟着例子学习一些基础用法。或者你也可以创建一个 .html 文件，然后通过如下方式引入 Vue：\n\n```javascript\n<script src=\"https://cdn.jsdelivr.net/npm/vue\"></script>\n\n```\n安装教程给出了更多安装 Vue 的方式。请注意我们不推荐新手直接使用 vue-cli，尤其是在你还不熟悉基于 Node.js 的构建工具时。\n\n# 声明式渲染\nVue.js 的核心是一个允许采用简洁的模板语法来声明式地将数据渲染进 DOM 的系统：\n```javascript\n<div id=\"app\">\n  {{ message }}\n</div>\n\n```\n```javascript\nvar app = new Vue({\n  el: \'#app\',\n  data: {\n    message: \'Hello Vue!\'\n  }\n})\n\n```\n我们已经成功创建了第一个 Vue 应用！看起来这跟渲染一个字符串模板非常类似，但是 Vue 在背后做了大量工作。现在数据和 DOM 已经被建立了关联，所有东西都是响应式的。我们要怎么确认呢？打开你的浏览器的 JavaScript 控制台 (就在这个页面打开)，并修改 app.message 的值，你将看到上例相应地更新。\n\n除了文本插值，我们还可以像这样来绑定元素特性：\n\n\n\n\n\n\n', '<p>Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。</p>\n<p>如果你想在深入学习 Vue 之前对它有更多了解，我们制作了一个视频，带您了解其核心概念和一个示例工程。<br />\n如果你已经是有经验的前端开发者，想知道 Vue 与其它库/框架有哪些区别，请查看对比其它框架。</p>\n<h1>起步</h1>\n<blockquote>\n<p>官方指南假设你已了解关于 HTML、CSS 和 JavaScript 的中级知识。如果你刚开始学习前端开发，将框架作为你的第一步可能不是最好的主意——掌握好基础知识再来吧！之前有其它框架的使用经验会有帮助，但这不是必需的。</p>\n</blockquote>\n<p>尝试 Vue.js 最简单的方法是使用 JSFiddle 上的 Hello World 例子。你可以在浏览器新标签页中打开它，跟着例子学习一些基础用法。或者你也可以创建一个 .html 文件，然后通过如下方式引入 Vue：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\">&lt;script src=<span class=\"hljs-string\">\"https://cdn.jsdelivr.net/npm/vue\"</span>&gt;<span class=\"xml\"><span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">script</span>&gt;</span></span>\n\n</code></div></pre>\n<p>安装教程给出了更多安装 Vue 的方式。请注意我们不推荐新手直接使用 vue-cli，尤其是在你还不熟悉基于 Node.js 的构建工具时。</p>\n<h1>声明式渲染</h1>\n<p>Vue.js 的核心是一个允许采用简洁的模板语法来声明式地将数据渲染进 DOM 的系统：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\">&lt;div id=<span class=\"hljs-string\">\"app\"</span>&gt;\n  {{ message }}\n&lt;<span class=\"hljs-regexp\">/div&gt;\n\n</span></code></div></pre>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\"><span class=\"hljs-keyword\">var</span> app = <span class=\"hljs-keyword\">new</span> Vue({\n  <span class=\"hljs-attr\">el</span>: <span class=\"hljs-string\">\'#app\'</span>,\n  <span class=\"hljs-attr\">data</span>: {\n    <span class=\"hljs-attr\">message</span>: <span class=\"hljs-string\">\'Hello Vue!\'</span>\n  }\n})\n\n</code></div></pre>\n<p>我们已经成功创建了第一个 Vue 应用！看起来这跟渲染一个字符串模板非常类似，但是 Vue 在背后做了大量工作。现在数据和 DOM 已经被建立了关联，所有东西都是响应式的。我们要怎么确认呢？打开你的浏览器的 JavaScript 控制台 (就在这个页面打开)，并修改 app.message 的值，你将看到上例相应地更新。</p>\n<p>除了文本插值，我们还可以像这样来绑定元素特性：</p>\n', 9);
INSERT INTO `ms_article_body` VALUES (21, '## 快速上手\n\n本节将介绍如何在项目中使用 Element。\n\n### 使用 Starter Kit\n我们提供了通用的项目模板，你可以直接使用。对于 Laravel 用户，我们也准备了相应的模板，同样可以直接下载使用。\n\n如果不希望使用我们提供的模板，请继续阅读。\n\n### 使用 vue-cli\n\n我们还可以使用 vue-cli 初始化项目，命令如下：\n\n```language\n> npm i -g vue-cli\n> mkdir my-project && cd my-project\n> vue init webpack\n> npm i && npm i element-ui\n```\n\n### 引入 Element\n你可以引入整个 Element，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 Element。\n\n#### 完整引入\n在 main.js 中写入以下内容：\n```javascript\nimport Vue from \'vue\'\nimport ElementUI from \'element-ui\'\nimport \'element-ui/lib/theme-chalk/index.css\'\nimport App from \'./App.vue\'\n\nVue.use(ElementUI)\n\nnew Vue({\n  el: \'#app\',\n  render: h => h(App)\n})\n\n```\n以上代码便完成了 Element 的引入。需要注意的是，样式文件需要单独引入。\n\n#### 按需引入\n借助 babel-plugin-component，我们可以只引入需要的组件，以达到减小项目体积的目的。\n\n首先，安装 babel-plugin-component：\n\n', '<h2>快速上手</h2>\n<p>本节将介绍如何在项目中使用 Element。</p>\n<h3>使用 Starter Kit</h3>\n<p>我们提供了通用的项目模板，你可以直接使用。对于 Laravel 用户，我们也准备了相应的模板，同样可以直接下载使用。</p>\n<p>如果不希望使用我们提供的模板，请继续阅读。</p>\n<h3>使用 vue-cli</h3>\n<p>我们还可以使用 vue-cli 初始化项目，命令如下：</p>\n<pre><code class=\"lang-language\">&gt; npm i -g vue-cli\n&gt; mkdir my-project &amp;&amp; cd my-project\n&gt; vue init webpack\n&gt; npm i &amp;&amp; npm i element-ui\n</code></pre>\n<h3>引入 Element</h3>\n<p>你可以引入整个 Element，或是根据需要仅引入部分组件。我们先介绍如何引入完整的 Element。</p>\n<h4>完整引入</h4>\n<p>在 main.js 中写入以下内容：</p>\n<pre><div class=\"hljs\"><code class=\"lang-javascript\"><span class=\"hljs-keyword\">import</span> Vue <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'vue\'</span>\n<span class=\"hljs-keyword\">import</span> ElementUI <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'element-ui\'</span>\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-string\">\'element-ui/lib/theme-chalk/index.css\'</span>\n<span class=\"hljs-keyword\">import</span> App <span class=\"hljs-keyword\">from</span> <span class=\"hljs-string\">\'./App.vue\'</span>\n\nVue.use(ElementUI)\n\n<span class=\"hljs-keyword\">new</span> Vue({\n  <span class=\"hljs-attr\">el</span>: <span class=\"hljs-string\">\'#app\'</span>,\n  <span class=\"hljs-attr\">render</span>: <span class=\"hljs-function\"><span class=\"hljs-params\">h</span> =&gt;</span> h(App)\n})\n\n</code></div></pre>\n<p>以上代码便完成了 Element 的引入。需要注意的是，样式文件需要单独引入。</p>\n<h4>按需引入</h4>\n<p>借助 babel-plugin-component，我们可以只引入需要的组件，以达到减小项目体积的目的。</p>\n<p>首先，安装 babel-plugin-component：</p>\n', 10);
INSERT INTO `ms_article_body` VALUES (1405564731351162882, '666666666666', '<p>666666666666</p>\n', 1405564731300831233);
INSERT INTO `ms_article_body` VALUES (1405909844828909569, '# 1. Spring Boot介绍\n\n## 1.1 简介\n\n在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？\n\n在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？\n\n那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！\n\nSpring Boot让我们的Spring应用变的更轻量化。\n\n我们不必像以前那样繁琐的构建项目、打包应用、部署到Tomcat等应用服务器中来运行我们的业务服务。\n\n通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过`java -jar`命令就可以运行起来。\n\n这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。\n\n**总结一下Spring Boot的主要优点：**\n\n1. 为所有Spring开发者更快的入门\n2. 开箱即用，提供各种默认配置来简化项目配置\n3. 内嵌式容器简化Web项目\n4. 没有冗余代码生成和XML配置的要求\n5. 统一的依赖管理\n6. 自动装配，更易使用，更易扩展\n\n## 1.2 使用版本说明\n\nSpringboot版本：使用最新的2.5.0版本\n\n教程参考了官方文档进行制作，权威。\n\n其他依赖版本：\n\n	1. Maven  需求：3.5+\n\n   	2. JDK 需求  8+\n   	3. Spring Framework 5.3.7以上版本\n   	4. Tomcat 9.0\n   	5. Servlet版本 4.0  但是可以部署到Servlet到3.1+的容器中\n\n# 2. 快速入门\n\n快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。\n\n教程使用的Idea版本：2019.3\n\n## 2.1 创建基础项目\n\n**第一步：** 创建maven项目\n\npom.xml :\n\n~~~xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n\n    <groupId>com.xiaopizhu</groupId>\n    <artifactId>helloSpringBoot</artifactId>\n    <version>1.0-SNAPSHOT</version>\n	<!--springboot的父工程其中定义了常用的依赖，并且无依赖冲突-->\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n</project>\n~~~\n\n注意上方的parent必须加，其中定义了springboot官方支持的n多依赖，基本上常用的已经有了，所以接下来导入依赖的时候，绝大部分都可以不加版本号。\n\n此时的工程结构为：\n\n![image20210523173241557.png](https://static.mszlu.com/6fc39758-0db2-431d-9f94-a705aa2c7e59.png)\n\n**第二步：** 添加web依赖\n\n~~~xml\n<dependencies>\n    <dependency>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-web</artifactId>\n    </dependency>\n</dependencies>\n~~~\n\n添加上方的web依赖，其中间接依赖了spring-web，spring-webmvc，spring-core等spring和springmvc的包，并且集成了tomcat。\n\n**第三步：** 编写启动类\n\n~~~java\npackage com.xiaopizhu.springboot;\n\nimport org.springframework.boot.SpringApplication;\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\n\n@SpringBootApplication\npublic class HelloApp {\n\n    public static void main(String[] args) {\n        SpringApplication.run(HelloApp.class,args);\n    }\n}\n\n~~~\n\n@SpringBootApplication注解标识了HelloApp为启动类，也是Spring Boot的核心。\n\n**第四步：** 运行启动类的main方法\n\n![image-20210523173712142](img/image-20210523173712142.png)\n\n看到如上配置，证明启动成功，tomcat端口号默认为8080。\n\n**第五步：**  如果想要修改端口号，可以在resources目录下新建application.properties\n\n~~~properties\nserver.port=8082\n~~~\n\n**第六步：** 重新运行\n\n![image-20210523174011613](img/image-20210523174011613.png)\n\n此时的项目结构为：\n\n![image-20210523174032053](img/image-20210523174032053.png)\n\n**src/main/java :**  编写java代码，注意启动类需要放在项目的根包下。\n\n**src/main/resources:**  放置资源的目录，比如springboot的配置文件，静态文件，mybatis配置，日志配置等。\n\n**src/test/java:**  测试代码\n\n## 2.2 编写一个Http接口\n\n**第一步：**  创建`HelloController`类，内容如下：\n\n~~~java\npackage com.xiaopizhu.springboot.controller;\n\nimport org.springframework.web.bind.annotation.GetMapping;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(\"hello\")\npublic class HelloController {\n\n    @GetMapping(\"boot\")\n    public String hello(){\n        return \"hello spring boot\";\n    }\n\n}\n\n~~~\n\n**注意包名，必须在启动类所在的包名下。**\n\n**第二步： ** 重启程序，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\n\n得到结果：hello spring boot\n\n## 2.3 编写单元测试用例\n\n**第一步： ** 添加spring boot测试依赖\n\n~~~xml\n		<dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n            <scope>test</scope>\n        </dependency>\n~~~\n\n**第二步：** 在src/test 下，编写测试用例\n\n~~~java\npackage com.xiaopizhu.springboot.controller;\n\nimport org.junit.jupiter.api.BeforeAll;\nimport org.junit.jupiter.api.BeforeEach;\nimport org.junit.jupiter.api.Test;\nimport org.springframework.boot.test.context.SpringBootTest;\nimport org.springframework.http.MediaType;\nimport org.springframework.test.web.servlet.MockMvc;\nimport org.springframework.test.web.servlet.request.MockMvcRequestBuilders;\nimport org.springframework.test.web.servlet.setup.MockMvcBuilders;\n\nimport static org.hamcrest.Matchers.equalTo;\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n\n@SpringBootTest\npublic class TestHelloController {\n\n    private MockMvc mockMvc;\n\n    @BeforeEach\n    public void beforeEach(){\n        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();\n    }\n    @Test\n    public void testHello() throws Exception {\n        mockMvc.perform(MockMvcRequestBuilders.get(\"/hello/boot\")\n                .accept(MediaType.APPLICATION_JSON))\n                .andExpect(status().isOk())\n                .andExpect(content().string(equalTo(\"hello spring boot\")));\n    }\n}\n\n~~~\n\n上面的测试用例，是构建一个空的`WebApplicationContext`，并且在before中加载了HelloController，得以在测试用例中mock调用，模拟请求。\n\n## 2.4 打包为jar运行\n\n**第一步：** 添加打包(maven构建springboot)插件\n\n~~~xml\n  <build>\n        <plugins>\n            <plugin>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-maven-plugin</artifactId>\n            </plugin>\n        </plugins>\n    </build>\n~~~\n\n在idea的右侧 maven中，使用package来打包程序，打包完成后，在target目录下生成helloSpringBoot-1.0-SNAPSHOT.jar\n\n![image-20210523181737720](img/image-20210523181737720.png)\n\n**第二步： ** 打开cmd：找到jar对应的目录\n\n输入命令\n\n~~~shell\njava -jar helloSpringBoot-1.0-SNAPSHOT.jar\n~~~\n\n![image-20210523182426404](img/image-20210523182426404.png)\n\n**第三步：**  测试，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\n\n得到结果：hello spring boot\n\n## 2.5 查看jar包内容\n\n~~~shell\njar tvf helloSpringBoot-1.0-SNAPSHOT.jar\n~~~\n\n# 3. 小结\n\n1. 通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。\n2. 通过修改配置文件，更改端口号\n3. 编写了测试用例\n4. 打包jar包运行\n\n', '<h1><a id=\"1_Spring_Boot_0\"></a>1. Spring Boot介绍</h1>\n<h2><a id=\"11__2\"></a>1.1 简介</h2>\n<p>在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？</p>\n<p>在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？</p>\n<p>那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！</p>\n<p>Spring Boot让我们的Spring应用变的更轻量化。</p>\n<p>我们不必像以前那样繁琐的构建项目、打包应用、部署到Tomcat等应用服务器中来运行我们的业务服务。</p>\n<p>通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过<code>java -jar</code>命令就可以运行起来。</p>\n<p>这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。</p>\n<p><strong>总结一下Spring Boot的主要优点：</strong></p>\n<ol>\n<li>为所有Spring开发者更快的入门</li>\n<li>开箱即用，提供各种默认配置来简化项目配置</li>\n<li>内嵌式容器简化Web项目</li>\n<li>没有冗余代码生成和XML配置的要求</li>\n<li>统一的依赖管理</li>\n<li>自动装配，更易使用，更易扩展</li>\n</ol>\n<h2><a id=\"12__27\"></a>1.2 使用版本说明</h2>\n<p>Springboot版本：使用最新的2.5.0版本</p>\n<p>教程参考了官方文档进行制作，权威。</p>\n<p>其他依赖版本：</p>\n<pre><code>1. Maven  需求：3.5+\n\n2. JDK 需求  8+\n3. Spring Framework 5.3.7以上版本\n4. Tomcat 9.0\n5. Servlet版本 4.0  但是可以部署到Servlet到3.1+的容器中\n</code></pre>\n<h1><a id=\"2__42\"></a>2. 快速入门</h1>\n<p>快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。</p>\n<p>教程使用的Idea版本：2019.3</p>\n<h2><a id=\"21__48\"></a>2.1 创建基础项目</h2>\n<p><strong>第一步：</strong> 创建maven项目</p>\n<p>pom.xml :</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\"><span class=\"php\"><span class=\"hljs-meta\">&lt;?</span>xml version=<span class=\"hljs-string\">\"1.0\"</span> encoding=<span class=\"hljs-string\">\"UTF-8\"</span><span class=\"hljs-meta\">?&gt;</span></span>\n<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">project</span> <span class=\"hljs-attr\">xmlns</span>=<span class=\"hljs-string\">\"http://maven.apache.org/POM/4.0.0\"</span>\n         <span class=\"hljs-attr\">xmlns:xsi</span>=<span class=\"hljs-string\">\"http://www.w3.org/2001/XMLSchema-instance\"</span>\n         <span class=\"hljs-attr\">xsi:schemaLocation</span>=<span class=\"hljs-string\">\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">modelVersion</span>&gt;</span>4.0.0<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">modelVersion</span>&gt;</span>\n\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>com.xiaopizhu<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>helloSpringBoot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">version</span>&gt;</span>1.0-SNAPSHOT<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">version</span>&gt;</span>\n	<span class=\"hljs-comment\">&lt;!--springboot的父工程其中定义了常用的依赖，并且无依赖冲突--&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">parent</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-parent<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">version</span>&gt;</span>2.5.0<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">version</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">parent</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">project</span>&gt;</span>\n</code></div></pre>\n<p>注意上方的parent必须加，其中定义了springboot官方支持的n多依赖，基本上常用的已经有了，所以接下来导入依赖的时候，绝大部分都可以不加版本号。</p>\n<p>此时的工程结构为：</p>\n<p><img src=\"https://static.mszlu.com/6fc39758-0db2-431d-9f94-a705aa2c7e59.png\" alt=\"image20210523173241557.png\" /></p>\n<p><strong>第二步：</strong> 添加web依赖</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\"><span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependencies</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependency</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-web<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependency</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependencies</span>&gt;</span>\n</code></div></pre>\n<p>添加上方的web依赖，其中间接依赖了spring-web，spring-webmvc，spring-core等spring和springmvc的包，并且集成了tomcat。</p>\n<p><strong>第三步：</strong> 编写启动类</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot;\n\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.SpringApplication;\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.autoconfigure.SpringBootApplication;\n\n<span class=\"hljs-meta\">@SpringBootApplication</span>\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">HelloApp</span> </span>{\n\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span> </span>{\n        SpringApplication.run(HelloApp.class,args);\n    }\n}\n\n</code></div></pre>\n<p>@SpringBootApplication注解标识了HelloApp为启动类，也是Spring Boot的核心。</p>\n<p><strong>第四步：</strong> 运行启动类的main方法</p>\n<p><img src=\"img/image-20210523173712142.png\" alt=\"image-20210523173712142\" /></p>\n<p>看到如上配置，证明启动成功，tomcat端口号默认为8080。</p>\n<p><strong>第五步：</strong>  如果想要修改端口号，可以在resources目录下新建application.properties</p>\n<pre><code class=\"lang-properties\">server.port=8082\n</code></pre>\n<p><strong>第六步：</strong> 重新运行</p>\n<p><img src=\"img/image-20210523174011613.png\" alt=\"image-20210523174011613\" /></p>\n<p>此时的项目结构为：</p>\n<p><img src=\"img/image-20210523174032053.png\" alt=\"image-20210523174032053\" /></p>\n<p><strong>src/main/java :</strong>  编写java代码，注意启动类需要放在项目的根包下。</p>\n<p><strong>src/main/resources:</strong>  放置资源的目录，比如springboot的配置文件，静态文件，mybatis配置，日志配置等。</p>\n<p><strong>src/test/java:</strong>  测试代码</p>\n<h2><a id=\"22_Http_138\"></a>2.2 编写一个Http接口</h2>\n<p><strong>第一步：</strong>  创建<code>HelloController</code>类，内容如下：</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot.controller;\n\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.GetMapping;\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.RequestMapping;\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.RestController;\n\n<span class=\"hljs-meta\">@RestController</span>\n<span class=\"hljs-meta\">@RequestMapping</span>(<span class=\"hljs-string\">\"hello\"</span>)\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">HelloController</span> </span>{\n\n    <span class=\"hljs-meta\">@GetMapping</span>(<span class=\"hljs-string\">\"boot\"</span>)\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> String <span class=\"hljs-title\">hello</span><span class=\"hljs-params\">()</span></span>{\n        <span class=\"hljs-keyword\">return</span> <span class=\"hljs-string\">\"hello spring boot\"</span>;\n    }\n\n}\n\n</code></div></pre>\n<p><strong>注意包名，必须在启动类所在的包名下。</strong></p>\n<p>**第二步： ** 重启程序，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot</p>\n<p>得到结果：hello spring boot</p>\n<h2><a id=\"23__168\"></a>2.3 编写单元测试用例</h2>\n<p>**第一步： ** 添加spring boot测试依赖</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\">		<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependency</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-test<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">scope</span>&gt;</span>test<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">scope</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependency</span>&gt;</span>\n</code></div></pre>\n<p><strong>第二步：</strong> 在src/test 下，编写测试用例</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot.controller;\n\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.BeforeAll;\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.BeforeEach;\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.Test;\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.test.context.SpringBootTest;\n<span class=\"hljs-keyword\">import</span> org.springframework.http.MediaType;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.MockMvc;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.request.MockMvcRequestBuilders;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.setup.MockMvcBuilders;\n\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.hamcrest.Matchers.equalTo;\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n\n<span class=\"hljs-meta\">@SpringBootTest</span>\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">TestHelloController</span> </span>{\n\n    <span class=\"hljs-keyword\">private</span> MockMvc mockMvc;\n\n    <span class=\"hljs-meta\">@BeforeEach</span>\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">beforeEach</span><span class=\"hljs-params\">()</span></span>{\n        mockMvc = MockMvcBuilders.standaloneSetup(<span class=\"hljs-keyword\">new</span> HelloController()).build();\n    }\n    <span class=\"hljs-meta\">@Test</span>\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">testHello</span><span class=\"hljs-params\">()</span> <span class=\"hljs-keyword\">throws</span> Exception </span>{\n        mockMvc.perform(MockMvcRequestBuilders.get(<span class=\"hljs-string\">\"/hello/boot\"</span>)\n                .accept(MediaType.APPLICATION_JSON))\n                .andExpect(status().isOk())\n                .andExpect(content().string(equalTo(<span class=\"hljs-string\">\"hello spring boot\"</span>)));\n    }\n}\n\n</code></div></pre>\n<p>上面的测试用例，是构建一个空的<code>WebApplicationContext</code>，并且在before中加载了HelloController，得以在测试用例中mock调用，模拟请求。</p>\n<h2><a id=\"24_jar_220\"></a>2.4 打包为jar运行</h2>\n<p><strong>第一步：</strong> 添加打包(maven构建springboot)插件</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\">  <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">build</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">plugins</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">plugin</span>&gt;</span>\n                <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n                <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-maven-plugin<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">plugin</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">plugins</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">build</span>&gt;</span>\n</code></div></pre>\n<p>在idea的右侧 maven中，使用package来打包程序，打包完成后，在target目录下生成helloSpringBoot-1.0-SNAPSHOT.jar</p>\n<p><img src=\"img/image-20210523181737720.png\" alt=\"image-20210523181737720\" /></p>\n<p>**第二步： ** 打开cmd：找到jar对应的目录</p>\n<p>输入命令</p>\n<pre><div class=\"hljs\"><code class=\"lang-shell\">java -jar helloSpringBoot-1.0-SNAPSHOT.jar\n</code></div></pre>\n<p><img src=\"img/image-20210523182426404.png\" alt=\"image-20210523182426404\" /></p>\n<p><strong>第三步：</strong>  测试，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot</p>\n<p>得到结果：hello spring boot</p>\n<h2><a id=\"25_jar_253\"></a>2.5 查看jar包内容</h2>\n<pre><div class=\"hljs\"><code class=\"lang-shell\">jar tvf helloSpringBoot-1.0-SNAPSHOT.jar\n</code></div></pre>\n<h1><a id=\"3__259\"></a>3. 小结</h1>\n<ol>\n<li>通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。</li>\n<li>通过修改配置文件，更改端口号</li>\n<li>编写了测试用例</li>\n<li>打包jar包运行</li>\n</ol>\n', 1405909844724051969);
INSERT INTO `ms_article_body` VALUES (1405916999854342146, '# 1. Spring Boot介绍\n\n## 1.1 简介\n\n在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？\n\n在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？\n\n那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！\n\nSpring Boot让我们的Spring应用变的更轻量化。\n\n我们不必像以前那样繁琐的构建项目、打包应用、部署到Tomcat等应用服务器中来运行我们的业务服务。\n\n通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过`java -jar`命令就可以运行起来。\n\n这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。\n\n**总结一下Spring Boot的主要优点：**\n\n1. 为所有Spring开发者更快的入门\n2. 开箱即用，提供各种默认配置来简化项目配置\n3. 内嵌式容器简化Web项目\n4. 没有冗余代码生成和XML配置的要求\n5. 统一的依赖管理\n6. 自动装配，更易使用，更易扩展\n\n## 1.2 使用版本说明\n\nSpringboot版本：使用最新的2.5.0版本\n\n教程参考了官方文档进行制作，权威。\n\n其他依赖版本：\n\n	1. Maven  需求：3.5+\n\n   	2. JDK 需求  8+\n   	3. Spring Framework 5.3.7以上版本\n   	4. Tomcat 9.0\n   	5. Servlet版本 4.0  但是可以部署到Servlet到3.1+的容器中\n\n# 2. 快速入门\n\n快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。\n\n教程使用的Idea版本：2019.3\n\n## 2.1 创建基础项目\n\n**第一步：** 创建maven项目\n\npom.xml :\n\n~~~xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n\n    <groupId>com.xiaopizhu</groupId>\n    <artifactId>helloSpringBoot</artifactId>\n    <version>1.0-SNAPSHOT</version>\n	<!--springboot的父工程其中定义了常用的依赖，并且无依赖冲突-->\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n</project>\n~~~\n\n注意上方的parent必须加，其中定义了springboot官方支持的n多依赖，基本上常用的已经有了，所以接下来导入依赖的时候，绝大部分都可以不加版本号。\n\n此时的工程结构为：\n\n![image20210523173241557.png](https://static.mszlu.com/a7302c88-e106-46ad-9713-dc9a6d523957.png)\n\n**第二步：** 添加web依赖\n\n~~~xml\n<dependencies>\n    <dependency>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-web</artifactId>\n    </dependency>\n</dependencies>\n~~~\n\n添加上方的web依赖，其中间接依赖了spring-web，spring-webmvc，spring-core等spring和springmvc的包，并且集成了tomcat。\n\n**第三步：** 编写启动类\n\n~~~java\npackage com.xiaopizhu.springboot;\n\nimport org.springframework.boot.SpringApplication;\nimport org.springframework.boot.autoconfigure.SpringBootApplication;\n\n@SpringBootApplication\npublic class HelloApp {\n\n    public static void main(String[] args) {\n        SpringApplication.run(HelloApp.class,args);\n    }\n}\n\n~~~\n\n@SpringBootApplication注解标识了HelloApp为启动类，也是Spring Boot的核心。\n\n**第四步：** 运行启动类的main方法\n\n![image20210523173712142.png](https://static.mszlu.com/dc17b43e-08df-46b0-bb7a-eb73044de7da.png)\n\n看到如上配置，证明启动成功，tomcat端口号默认为8080。\n\n**第五步：**  如果想要修改端口号，可以在resources目录下新建application.properties\n\n~~~properties\nserver.port=8082\n~~~\n\n**第六步：** 重新运行\n\n![image20210523174011613.png](https://static.mszlu.com/318e4b7e-95d2-4e43-a18f-279ea6ff9495.png)\n\n此时的项目结构为：\n\n![image20210523174032053.png](https://static.mszlu.com/756e4ae0-c4c7-43a5-9b5d-bc7ce974a942.png)\n\n**src/main/java :**  编写java代码，注意启动类需要放在项目的根包下。\n\n**src/main/resources:**  放置资源的目录，比如springboot的配置文件，静态文件，mybatis配置，日志配置等。\n\n**src/test/java:**  测试代码\n\n## 2.2 编写一个Http接口\n\n**第一步：**  创建`HelloController`类，内容如下：\n\n~~~java\npackage com.xiaopizhu.springboot.controller;\n\nimport org.springframework.web.bind.annotation.GetMapping;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(\"hello\")\npublic class HelloController {\n\n    @GetMapping(\"boot\")\n    public String hello(){\n        return \"hello spring boot\";\n    }\n\n}\n\n~~~\n\n**注意包名，必须在启动类所在的包名下。**\n\n**第二步： ** 重启程序，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\n\n得到结果：hello spring boot\n\n## 2.3 编写单元测试用例\n\n**第一步： ** 添加spring boot测试依赖\n\n~~~xml\n		<dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n            <scope>test</scope>\n        </dependency>\n~~~\n\n**第二步：** 在src/test 下，编写测试用例\n\n~~~java\npackage com.xiaopizhu.springboot.controller;\n\nimport org.junit.jupiter.api.BeforeAll;\nimport org.junit.jupiter.api.BeforeEach;\nimport org.junit.jupiter.api.Test;\nimport org.springframework.boot.test.context.SpringBootTest;\nimport org.springframework.http.MediaType;\nimport org.springframework.test.web.servlet.MockMvc;\nimport org.springframework.test.web.servlet.request.MockMvcRequestBuilders;\nimport org.springframework.test.web.servlet.setup.MockMvcBuilders;\n\nimport static org.hamcrest.Matchers.equalTo;\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\nimport static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n\n@SpringBootTest\npublic class TestHelloController {\n\n    private MockMvc mockMvc;\n\n    @BeforeEach\n    public void beforeEach(){\n        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();\n    }\n    @Test\n    public void testHello() throws Exception {\n        mockMvc.perform(MockMvcRequestBuilders.get(\"/hello/boot\")\n                .accept(MediaType.APPLICATION_JSON))\n                .andExpect(status().isOk())\n                .andExpect(content().string(equalTo(\"hello spring boot\")));\n    }\n}\n\n~~~\n\n上面的测试用例，是构建一个空的`WebApplicationContext`，并且在before中加载了HelloController，得以在测试用例中mock调用，模拟请求。\n\n## 2.4 打包为jar运行\n\n**第一步：** 添加打包(maven构建springboot)插件\n\n~~~xml\n  <build>\n        <plugins>\n            <plugin>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-maven-plugin</artifactId>\n            </plugin>\n        </plugins>\n    </build>\n~~~\n\n在idea的右侧 maven中，使用package来打包程序，打包完成后，在target目录下生成helloSpringBoot-1.0-SNAPSHOT.jar\n\n![image20210523181737720.png](https://static.mszlu.com/45aa0db2-598f-4564-964c-a2d889dfbafe.png)\n\n**第二步： ** 打开cmd：找到jar对应的目录\n\n输入命令\n\n~~~shell\njava -jar helloSpringBoot-1.0-SNAPSHOT.jar\n~~~\n\n![image20210523182426404.png](https://static.mszlu.com/74376f77-b8eb-4c2c-a4cc-d3bfe24901e3.png)\n\n**第三步：**  测试，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot\n\n得到结果：hello spring boot\n\n## 2.5 查看jar包内容\n\n~~~shell\njar tvf helloSpringBoot-1.0-SNAPSHOT.jar\n~~~\n\n# 3. 小结\n\n1. 通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。\n2. 通过修改配置文件，更改端口号\n3. 编写了测试用例\n4. 打包jar包运行\n\n', '<h1><a id=\"1_Spring_Boot_0\"></a>1. Spring Boot介绍</h1>\n<h2><a id=\"11__2\"></a>1.1 简介</h2>\n<p>在您第1次接触和学习Spring框架的时候，是否因为其繁杂的配置而退却了？</p>\n<p>在你第n次使用Spring框架的时候，是否觉得一堆反复黏贴的配置有一些厌烦？</p>\n<p>那么您就不妨来试试使用Spring Boot来让你更易上手，更简单快捷地构建Spring应用！</p>\n<p>Spring Boot让我们的Spring应用变的更轻量化。</p>\n<p>我们不必像以前那样繁琐的构建项目、打包应用、部署到Tomcat等应用服务器中来运行我们的业务服务。</p>\n<p>通过Spring Boot实现的服务，只需要依靠一个Java类，把它打包成jar，并通过<code>java -jar</code>命令就可以运行起来。</p>\n<p>这一切相较于传统Spring应用来说，已经变得非常的轻便、简单。</p>\n<p><strong>总结一下Spring Boot的主要优点：</strong></p>\n<ol>\n<li>为所有Spring开发者更快的入门</li>\n<li>开箱即用，提供各种默认配置来简化项目配置</li>\n<li>内嵌式容器简化Web项目</li>\n<li>没有冗余代码生成和XML配置的要求</li>\n<li>统一的依赖管理</li>\n<li>自动装配，更易使用，更易扩展</li>\n</ol>\n<h2><a id=\"12__27\"></a>1.2 使用版本说明</h2>\n<p>Springboot版本：使用最新的2.5.0版本</p>\n<p>教程参考了官方文档进行制作，权威。</p>\n<p>其他依赖版本：</p>\n<pre><code>1. Maven  需求：3.5+\n\n2. JDK 需求  8+\n3. Spring Framework 5.3.7以上版本\n4. Tomcat 9.0\n5. Servlet版本 4.0  但是可以部署到Servlet到3.1+的容器中\n</code></pre>\n<h1><a id=\"2__42\"></a>2. 快速入门</h1>\n<p>快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。</p>\n<p>教程使用的Idea版本：2019.3</p>\n<h2><a id=\"21__48\"></a>2.1 创建基础项目</h2>\n<p><strong>第一步：</strong> 创建maven项目</p>\n<p>pom.xml :</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\"><span class=\"php\"><span class=\"hljs-meta\">&lt;?</span>xml version=<span class=\"hljs-string\">\"1.0\"</span> encoding=<span class=\"hljs-string\">\"UTF-8\"</span><span class=\"hljs-meta\">?&gt;</span></span>\n<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">project</span> <span class=\"hljs-attr\">xmlns</span>=<span class=\"hljs-string\">\"http://maven.apache.org/POM/4.0.0\"</span>\n         <span class=\"hljs-attr\">xmlns:xsi</span>=<span class=\"hljs-string\">\"http://www.w3.org/2001/XMLSchema-instance\"</span>\n         <span class=\"hljs-attr\">xsi:schemaLocation</span>=<span class=\"hljs-string\">\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">modelVersion</span>&gt;</span>4.0.0<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">modelVersion</span>&gt;</span>\n\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>com.xiaopizhu<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>helloSpringBoot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">version</span>&gt;</span>1.0-SNAPSHOT<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">version</span>&gt;</span>\n	<span class=\"hljs-comment\">&lt;!--springboot的父工程其中定义了常用的依赖，并且无依赖冲突--&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">parent</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-parent<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">version</span>&gt;</span>2.5.0<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">version</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">parent</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">project</span>&gt;</span>\n</code></div></pre>\n<p>注意上方的parent必须加，其中定义了springboot官方支持的n多依赖，基本上常用的已经有了，所以接下来导入依赖的时候，绝大部分都可以不加版本号。</p>\n<p>此时的工程结构为：</p>\n<p><img src=\"https://static.mszlu.com/a7302c88-e106-46ad-9713-dc9a6d523957.png\" alt=\"image20210523173241557.png\" /></p>\n<p><strong>第二步：</strong> 添加web依赖</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\"><span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependencies</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependency</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-web<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependency</span>&gt;</span>\n<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependencies</span>&gt;</span>\n</code></div></pre>\n<p>添加上方的web依赖，其中间接依赖了spring-web，spring-webmvc，spring-core等spring和springmvc的包，并且集成了tomcat。</p>\n<p><strong>第三步：</strong> 编写启动类</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot;\n\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.SpringApplication;\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.autoconfigure.SpringBootApplication;\n\n<span class=\"hljs-meta\">@SpringBootApplication</span>\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">HelloApp</span> </span>{\n\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">(String[] args)</span> </span>{\n        SpringApplication.run(HelloApp.class,args);\n    }\n}\n\n</code></div></pre>\n<p>@SpringBootApplication注解标识了HelloApp为启动类，也是Spring Boot的核心。</p>\n<p><strong>第四步：</strong> 运行启动类的main方法</p>\n<p><img src=\"https://static.mszlu.com/dc17b43e-08df-46b0-bb7a-eb73044de7da.png\" alt=\"image20210523173712142.png\" /></p>\n<p>看到如上配置，证明启动成功，tomcat端口号默认为8080。</p>\n<p><strong>第五步：</strong>  如果想要修改端口号，可以在resources目录下新建application.properties</p>\n<pre><code class=\"lang-properties\">server.port=8082\n</code></pre>\n<p><strong>第六步：</strong> 重新运行</p>\n<p><img src=\"https://static.mszlu.com/318e4b7e-95d2-4e43-a18f-279ea6ff9495.png\" alt=\"image20210523174011613.png\" /></p>\n<p>此时的项目结构为：</p>\n<p><img src=\"https://static.mszlu.com/756e4ae0-c4c7-43a5-9b5d-bc7ce974a942.png\" alt=\"image20210523174032053.png\" /></p>\n<p><strong>src/main/java :</strong>  编写java代码，注意启动类需要放在项目的根包下。</p>\n<p><strong>src/main/resources:</strong>  放置资源的目录，比如springboot的配置文件，静态文件，mybatis配置，日志配置等。</p>\n<p><strong>src/test/java:</strong>  测试代码</p>\n<h2><a id=\"22_Http_138\"></a>2.2 编写一个Http接口</h2>\n<p><strong>第一步：</strong>  创建<code>HelloController</code>类，内容如下：</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot.controller;\n\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.GetMapping;\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.RequestMapping;\n<span class=\"hljs-keyword\">import</span> org.springframework.web.bind.annotation.RestController;\n\n<span class=\"hljs-meta\">@RestController</span>\n<span class=\"hljs-meta\">@RequestMapping</span>(<span class=\"hljs-string\">\"hello\"</span>)\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">HelloController</span> </span>{\n\n    <span class=\"hljs-meta\">@GetMapping</span>(<span class=\"hljs-string\">\"boot\"</span>)\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> String <span class=\"hljs-title\">hello</span><span class=\"hljs-params\">()</span></span>{\n        <span class=\"hljs-keyword\">return</span> <span class=\"hljs-string\">\"hello spring boot\"</span>;\n    }\n\n}\n\n</code></div></pre>\n<p><strong>注意包名，必须在启动类所在的包名下。</strong></p>\n<p>**第二步： ** 重启程序，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot</p>\n<p>得到结果：hello spring boot</p>\n<h2><a id=\"23__168\"></a>2.3 编写单元测试用例</h2>\n<p>**第一步： ** 添加spring boot测试依赖</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\">		<span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">dependency</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-starter-test<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">scope</span>&gt;</span>test<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">scope</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">dependency</span>&gt;</span>\n</code></div></pre>\n<p><strong>第二步：</strong> 在src/test 下，编写测试用例</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\"><span class=\"hljs-keyword\">package</span> com.xiaopizhu.springboot.controller;\n\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.BeforeAll;\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.BeforeEach;\n<span class=\"hljs-keyword\">import</span> org.junit.jupiter.api.Test;\n<span class=\"hljs-keyword\">import</span> org.springframework.boot.test.context.SpringBootTest;\n<span class=\"hljs-keyword\">import</span> org.springframework.http.MediaType;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.MockMvc;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.request.MockMvcRequestBuilders;\n<span class=\"hljs-keyword\">import</span> org.springframework.test.web.servlet.setup.MockMvcBuilders;\n\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.hamcrest.Matchers.equalTo;\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;\n<span class=\"hljs-keyword\">import</span> <span class=\"hljs-keyword\">static</span> org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n\n<span class=\"hljs-meta\">@SpringBootTest</span>\n<span class=\"hljs-keyword\">public</span> <span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> <span class=\"hljs-title\">TestHelloController</span> </span>{\n\n    <span class=\"hljs-keyword\">private</span> MockMvc mockMvc;\n\n    <span class=\"hljs-meta\">@BeforeEach</span>\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">beforeEach</span><span class=\"hljs-params\">()</span></span>{\n        mockMvc = MockMvcBuilders.standaloneSetup(<span class=\"hljs-keyword\">new</span> HelloController()).build();\n    }\n    <span class=\"hljs-meta\">@Test</span>\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">testHello</span><span class=\"hljs-params\">()</span> <span class=\"hljs-keyword\">throws</span> Exception </span>{\n        mockMvc.perform(MockMvcRequestBuilders.get(<span class=\"hljs-string\">\"/hello/boot\"</span>)\n                .accept(MediaType.APPLICATION_JSON))\n                .andExpect(status().isOk())\n                .andExpect(content().string(equalTo(<span class=\"hljs-string\">\"hello spring boot\"</span>)));\n    }\n}\n\n</code></div></pre>\n<p>上面的测试用例，是构建一个空的<code>WebApplicationContext</code>，并且在before中加载了HelloController，得以在测试用例中mock调用，模拟请求。</p>\n<h2><a id=\"24_jar_220\"></a>2.4 打包为jar运行</h2>\n<p><strong>第一步：</strong> 添加打包(maven构建springboot)插件</p>\n<pre><div class=\"hljs\"><code class=\"lang-xml\">  <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">build</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">plugins</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">plugin</span>&gt;</span>\n                <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">groupId</span>&gt;</span>org.springframework.boot<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">groupId</span>&gt;</span>\n                <span class=\"hljs-tag\">&lt;<span class=\"hljs-name\">artifactId</span>&gt;</span>spring-boot-maven-plugin<span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">artifactId</span>&gt;</span>\n            <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">plugin</span>&gt;</span>\n        <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">plugins</span>&gt;</span>\n    <span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">build</span>&gt;</span>\n</code></div></pre>\n<p>在idea的右侧 maven中，使用package来打包程序，打包完成后，在target目录下生成helloSpringBoot-1.0-SNAPSHOT.jar</p>\n<p><img src=\"https://static.mszlu.com/45aa0db2-598f-4564-964c-a2d889dfbafe.png\" alt=\"image20210523181737720.png\" /></p>\n<p>**第二步： ** 打开cmd：找到jar对应的目录</p>\n<p>输入命令</p>\n<pre><div class=\"hljs\"><code class=\"lang-shell\">java -jar helloSpringBoot-1.0-SNAPSHOT.jar\n</code></div></pre>\n<p><img src=\"https://static.mszlu.com/74376f77-b8eb-4c2c-a4cc-d3bfe24901e3.png\" alt=\"image20210523182426404.png\" /></p>\n<p><strong>第三步：</strong>  测试，使用postman或者直接在浏览器输入http://localhost:8082/hello/boot</p>\n<p>得到结果：hello spring boot</p>\n<h2><a id=\"25_jar_253\"></a>2.5 查看jar包内容</h2>\n<pre><div class=\"hljs\"><code class=\"lang-shell\">jar tvf helloSpringBoot-1.0-SNAPSHOT.jar\n</code></div></pre>\n<h1><a id=\"3__259\"></a>3. 小结</h1>\n<ol>\n<li>通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。</li>\n<li>通过修改配置文件，更改端口号</li>\n<li>编写了测试用例</li>\n<li>打包jar包运行</li>\n</ol>\n', 1405916999732707330);
INSERT INTO `ms_article_body` VALUES (1468182881431461890, '![modao.jpg](http://r3qle33oa.hn-bkt.clouddn.com/d5ea33ca-1bad-4234-959a-4f9fa6e0db25.jpg)\n魔刀千刃，是国漫《刺客伍六七》系列中的一把强力武器。是玄武国首席暗影刺客柒的佩刀，神锻国的国宝之一，被千年恶灵看守。刀刃是由上千块碎片组成，黑色刀柄半截刀身，刀身上有蓝色条纹，显现出的是恶灵的图案，可以发出蓝色或紫色的光。只有条纹发光，才可以发挥出强大的力量。\n**“只攻不防，天下无双。——魔刀千刃”**', '<p><img src=\"http://r3qle33oa.hn-bkt.clouddn.com/d5ea33ca-1bad-4234-959a-4f9fa6e0db25.jpg\" alt=\"modao.jpg\" /><br />\n魔刀千刃，是国漫《刺客伍六七》系列中的一把强力武器。是玄武国首席暗影刺客柒的佩刀，神锻国的国宝之一，被千年恶灵看守。刀刃是由上千块碎片组成，黑色刀柄半截刀身，刀身上有蓝色条纹，显现出的是恶灵的图案，可以发出蓝色或紫色的光。只有条纹发光，才可以发挥出强大的力量。<br />\n<strong>“只攻不防，天下无双。——魔刀千刃”</strong></p>\n', 1468182881431461889);
INSERT INTO `ms_article_body` VALUES (1468187310331285506, '## 我是神', '<h2><a id=\"_0\"></a>我是神</h2>\n', 1468187310301925377);
INSERT INTO `ms_article_body` VALUES (1468192662275837954, '![村正.jpg](http://r3qle33oa.hn-bkt.clouddn.com/edbd2156-0f48-47f6-844a-562e198e0781.jpg)\n村正（平假名：むらまさ，Muramasa），可视为一类日本刀的名字，别名千子村正（平假名：せんごむらまさ，SengoMuramasa），在伊势国桑名（现今的三重县桑名市）为一族活跃刀匠的名字，当时村正家族所铸造的刀均称为村正，后因历史原因也出现一些名为村正的日本刀。\n村正，日本最有名的日本刀之一。原是室町中期至天正年间约一百年间的伊势的刀工之名，其时正是日本进入战国时代的动荡时期，对于日本刀的需求很大。村正只生产最优秀的、可用于实战的刀。 也许是因为它太过锐利，到了江户时代就开始有“邪剑”、“妖刀”的称号，而被世人所避忌。在现存的妖刀中，以被称做“妙法村正”的最为有名。\n村正可以被定义为村正家族联产的刀，是一种日本刀的类别，并不特指或局限于某一把刀。', '<p><img src=\"http://r3qle33oa.hn-bkt.clouddn.com/edbd2156-0f48-47f6-844a-562e198e0781.jpg\" alt=\"村正.jpg\" /><br />\n村正（平假名：むらまさ，Muramasa），可视为一类日本刀的名字，别名千子村正（平假名：せんごむらまさ，SengoMuramasa），在伊势国桑名（现今的三重县桑名市）为一族活跃刀匠的名字，当时村正家族所铸造的刀均称为村正，后因历史原因也出现一些名为村正的日本刀。<br />\n村正，日本最有名的日本刀之一。原是室町中期至天正年间约一百年间的伊势的刀工之名，其时正是日本进入战国时代的动荡时期，对于日本刀的需求很大。村正只生产最优秀的、可用于实战的刀。 也许是因为它太过锐利，到了江户时代就开始有“邪剑”、“妖刀”的称号，而被世人所避忌。在现存的妖刀中，以被称做“妙法村正”的最为有名。<br />\n村正可以被定义为村正家族联产的刀，是一种日本刀的类别，并不特指或局限于某一把刀。</p>\n', 1468192662137425921);
INSERT INTO `ms_article_body` VALUES (1468227279322206210, '测试![赛博朋克壁纸.jpg](http://r3qle33oa.hn-bkt.clouddn.com/6927665b-23e9-4609-994a-1eeeaededff5.jpg)', '<p>测试<img src=\"http://r3qle33oa.hn-bkt.clouddn.com/6927665b-23e9-4609-994a-1eeeaededff5.jpg\" alt=\"赛博朋克壁纸.jpg\" /></p>\n', 1468227279309623298);
INSERT INTO `ms_article_body` VALUES (1468231964527546370, '~~~ java /**\n     * user转换为loginVo\n     * @param sysUser\n     * @return\n     */\n    private LoginVo userToLoginVo(SysUser sysUser) {\n        LoginVo loginVo =new LoginVo();\n        BeanUtils.copyProperties(sysUser, loginVo);\n        return loginVo; ', '<pre><div class=\"hljs\"><code class=\"lang-java\">     * user转换为loginVo\n     * <span class=\"hljs-meta\">@param</span> sysUser\n     * <span class=\"hljs-meta\">@return</span>\n     */\n    <span class=\"hljs-function\"><span class=\"hljs-keyword\">private</span> LoginVo <span class=\"hljs-title\">userToLoginVo</span><span class=\"hljs-params\">(SysUser sysUser)</span> </span>{\n        LoginVo loginVo =<span class=\"hljs-keyword\">new</span> LoginVo();\n        BeanUtils.copyProperties(sysUser, loginVo);\n        <span class=\"hljs-keyword\">return</span> loginVo; </code></div></pre>\n', 1468231964523352066);
INSERT INTO `ms_article_body` VALUES (1468473952807997441, '李博猛牛逼之路\n\n哈哈哈哈哈哈', '<p>李博猛牛逼之路</p>\n<p>哈哈哈哈哈哈</p>\n', 1468473952799608834);
INSERT INTO `ms_article_body` VALUES (1468562564547375106, '1111', '<p>1111</p>\n', 1468562564480266241);
INSERT INTO `ms_article_body` VALUES (1468562816851628034, '## 1111', '<h2><a id=\"1111_0\"></a>1111</h2>\n', 1468562816797102082);
INSERT INTO `ms_article_body` VALUES (1469349701626671105, '测试', '<p>测试</p>\n', 1469349701563756546);
INSERT INTO `ms_article_body` VALUES (1469350256159858689, '开始测试', '<p>开始测试</p>\n', 1469350256096944130);
INSERT INTO `ms_article_body` VALUES (1469574735607697410, '新文章来拉', '<p>新文章来拉</p>\n', 1469574735540588545);
INSERT INTO `ms_article_body` VALUES (1469578376552980481, '文章', '<p>文章</p>\n', 1469578376494260225);
INSERT INTO `ms_article_body` VALUES (1469579087810510850, '0507', '<p>0507</p>\n', 1469579087743401985);

-- ----------------------------
-- Table structure for ms_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `ms_article_tag`;
CREATE TABLE `ms_article_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1469579087856648195 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_article_tag
-- ----------------------------
INSERT INTO `ms_article_tag` VALUES (1, 1, 7);
INSERT INTO `ms_article_tag` VALUES (2, 1, 5);
INSERT INTO `ms_article_tag` VALUES (3, 1, 8);
INSERT INTO `ms_article_tag` VALUES (4, 9, 7);
INSERT INTO `ms_article_tag` VALUES (5, 10, 7);
INSERT INTO `ms_article_tag` VALUES (6, 10, 8);
INSERT INTO `ms_article_tag` VALUES (7, 10, 5);
INSERT INTO `ms_article_tag` VALUES (8, 10, 6);
INSERT INTO `ms_article_tag` VALUES (1405564731321802753, 1405564731300831233, 5);
INSERT INTO `ms_article_tag` VALUES (1405909844761800706, 1405909844724051969, 5);
INSERT INTO `ms_article_tag` VALUES (1405916999787233281, 1405916999732707330, 5);
INSERT INTO `ms_article_tag` VALUES (1468187310410977282, 1468187310301925377, 5);
INSERT INTO `ms_article_tag` VALUES (1468187310419365889, 1468187310301925377, 6);
INSERT INTO `ms_article_tag` VALUES (1468187310419365890, 1468187310301925377, 7);
INSERT INTO `ms_article_tag` VALUES (1468192662422638593, 1468192662137425921, 8);
INSERT INTO `ms_article_tag` VALUES (1468227279368343554, 1468227279309623298, 8);
INSERT INTO `ms_article_tag` VALUES (1468231964535934978, 1468231964523352066, 5);
INSERT INTO `ms_article_tag` VALUES (1468473952854134786, 1468473952799608834, 5);
INSERT INTO `ms_article_tag` VALUES (1468562564580929538, 1468562564480266241, 5);
INSERT INTO `ms_article_tag` VALUES (1468562816952291329, 1468562816797102082, 5);
INSERT INTO `ms_article_tag` VALUES (1469349701656031234, 1469349701563756546, 5);
INSERT INTO `ms_article_tag` VALUES (1469350256197607426, 1469350256096944130, 5);
INSERT INTO `ms_article_tag` VALUES (1469574735632863234, 1469574735540588545, 5);
INSERT INTO `ms_article_tag` VALUES (1469578376599117826, 1469578376494260225, 5);
INSERT INTO `ms_article_tag` VALUES (1469579087856648194, 1469579087743401985, 5);

-- ----------------------------
-- Table structure for ms_category
-- ----------------------------
DROP TABLE IF EXISTS `ms_category`;
CREATE TABLE `ms_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_category
-- ----------------------------
INSERT INTO `ms_category` VALUES (1, '/static/category/front.png', '前端', '前端是什么，大前端');
INSERT INTO `ms_category` VALUES (2, '/static/category/back.png', '后端', '后端最牛叉');
INSERT INTO `ms_category` VALUES (3, '/static/category/lift.jpg', '生活', '生活趣事');
INSERT INTO `ms_category` VALUES (4, '/static/category/database.png', '数据库', '没数据库，啥也不管用');
INSERT INTO `ms_category` VALUES (5, '/static/category/language.png', '编程语言', '好多语言，该学哪个？');

-- ----------------------------
-- Table structure for ms_comment
-- ----------------------------
DROP TABLE IF EXISTS `ms_comment`;
CREATE TABLE `ms_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `to_uid` bigint(20) NOT NULL,
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1469580328435302403 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_comment
-- ----------------------------
INSERT INTO `ms_comment` VALUES (53, '写的好', 123213213213, 1, 1, 0, 1, '1');
INSERT INTO `ms_comment` VALUES (54, '111', 123123123123, 1, 1, 53, 1, '2');
INSERT INTO `ms_comment` VALUES (56, '222', 12312, 1, 1, 0, 1, '1');
INSERT INTO `ms_comment` VALUES (333, '我是子子', 213232223, 1, 1, 1405209691876790274, 1, '3');
INSERT INTO `ms_comment` VALUES (1405204547248377858, '123', 1623861846172, 1, 1, 53, 1, '2');
INSERT INTO `ms_comment` VALUES (1405205050975899650, '123123', 1623861966270, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405205572185280513, '3333', 1623862090534, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405206087392612353, '7777', 1623862213367, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405206147568291842, '7777', 1623862227714, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405206347246522370, '666', 1623862275315, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405208637198131202, '99999999999999999999', 1623862821278, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1405209691876790274, '66666666666666666', 1623863072732, 1, 1, 1405208637198131202, 1, '2');
INSERT INTO `ms_comment` VALUES (1468087118189838338, '我是神', 1638854196336, 1, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468087672114790401, '我是神神', 1638854332836, 1, 1, 1468087118189838300, 1, '2');
INSERT INTO `ms_comment` VALUES (1468089117421621250, '我是深深', 1638854696022, 1, 1, 1468087118189838338, 1, '2');
INSERT INTO `ms_comment` VALUES (1468089200917630977, '神神神', 1638854715934, 1, 1, 1468087118189838338, 1, '2');
INSERT INTO `ms_comment` VALUES (1468091693408026625, '我来拉', 1638855310189, 1, 1468091583290769410, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468092090558312450, '强啊李搏猛', 1638855404876, 1, 1468091583290769410, 1468087118189838338, 1, '2');
INSERT INTO `ms_comment` VALUES (1468108565838884866, '123123', 1638859332891, 1468108070718074881, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468113565705154561, '2222', 1638860524947, 1468108070718074881, 1, 1468108565838884866, 1, '2');
INSERT INTO `ms_comment` VALUES (1468115444342263810, '111', 1638860972850, 1468108070718074881, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468115490425081858, '222', 1638860983842, 1468108070718074881, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468115784391266305, '成功啦哈哈哈', 1638861053929, 1468108070718074881, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468128057579044866, '太强了', 1638863980079, 1468125665064230913, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468128903192657921, '真的强', 1638864181691, 1468125665064230913, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468226516596412417, '我很强', 1638887454541, 1468192662137425921, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1468231328071274498, '强', 1638888601688, 1, 1468228187896532994, 1468091693408026625, 1468091583290769410, '2');
INSERT INTO `ms_comment` VALUES (1468473442570915842, '傻狗u', 1638946326282, 1468187310301925377, 1468473254548656129, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1469557927236403201, 'hahah', 1639204887572, 1468562816797102082, 1, 0, 0, '1');
INSERT INTO `ms_comment` VALUES (1469558307001270274, '可以', 1639204978119, 1, 1, 1468091693408026625, 1468091583290769410, '2');
INSERT INTO `ms_comment` VALUES (1469580328435302402, '什么', 1639210228437, 1469579087743401985, 1, 0, 0, '1');

-- ----------------------------
-- Table structure for ms_permission
-- ----------------------------
DROP TABLE IF EXISTS `ms_permission`;
CREATE TABLE `ms_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_permission
-- ----------------------------
INSERT INTO `ms_permission` VALUES (1, '查询权限列表', '/admin/permission/permissionList', '查询权限列表');
INSERT INTO `ms_permission` VALUES (2, '11', '11', '111');
INSERT INTO `ms_permission` VALUES (7, '1213', '123', '123');
INSERT INTO `ms_permission` VALUES (8, '删除权限', '/admin/permission/add', '删除权限');

-- ----------------------------
-- Table structure for ms_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `ms_sys_log`;
CREATE TABLE `ms_sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` bigint(20) NULL DEFAULT NULL,
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `module` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `operation` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `userid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for ms_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_sys_user`;
CREATE TABLE `ms_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `admin` bit(1) NULL DEFAULT NULL COMMENT '是否管理员',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_date` bigint(20) NULL DEFAULT NULL COMMENT '注册时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `last_login` bigint(20) NULL DEFAULT NULL COMMENT '最后登录时间',
  `mobile_phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468572859206496259 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_sys_user
-- ----------------------------
INSERT INTO `ms_sys_user` VALUES (1, 'admin', b'1', '/static/user/admin.png', 20210622223122, b'0', '11', 20210630223130, '12', '李搏猛', '5bf96431fd87b0b92db4a03058bd1374', '12', '1');
INSERT INTO `ms_sys_user` VALUES (1465606476189523969, '807073364', b'0', '/static/user/user_1.png', 1638262774750, b'0', '', 1638262781178, NULL, 'lbm', '843a4d2b053686bde5f541e6e8983272', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1465607885056233474, '8070733645', b'0', '/static/user/user_1.png', 1638263124128, b'0', '', 1638263124129, NULL, 'lbm', '843a4d2b053686bde5f541e6e8983272', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468091583290769410, 'q807073364', b'0', '/static/user/user_1.png', 1638855283931, b'0', '', 1638855283931, NULL, '游客256', '46892ff26b090cd75fd3c3e1239604c9', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468228187896532994, '1047480643', b'0', '/static/user/user_1.png', 1638887853012, b'0', '', 1638887853012, NULL, '毕达哥拉斯', '693d0bfdaecee9acaf60d203f5151483', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468473254548656129, '李明傻狗', b'0', '/static/user/user_1.png', 1638946281455, b'0', '', 1638946281455, NULL, '傻狗', '843a4d2b053686bde5f541e6e8983272', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468563894150152193, 'test', b'0', '/static/user/user_1.png', 1638967891617, b'0', '', 1638967891617, NULL, 'lbm', '693d0bfdaecee9acaf60d203f5151483', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468564778846269441, 'test02', b'0', '/static/user/user_1.png', 1638968102545, b'0', '', 1638968102545, NULL, 'lbm', '693d0bfdaecee9acaf60d203f5151483', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468569589096411138, 'test03', b'0', '/static/user/user_1.png', 1638969249392, b'0', '', 1638969249392, NULL, 'lbm', '96e3e8ef438b11325acc4f6402b2a79f', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468572193453010945, 'test04', b'0', '/static/user/user_1.png', 1638969870324, b'0', '', 1638969870324, NULL, 'lbm', '96e3e8ef438b11325acc4f6402b2a79f', NULL, NULL);
INSERT INTO `ms_sys_user` VALUES (1468572859206496258, '80707', b'0', '/static/user/user_1.png', 1638970029056, b'0', '', 1638970029056, NULL, 'lbm', '96e3e8ef438b11325acc4f6402b2a79f', NULL, NULL);

-- ----------------------------
-- Table structure for ms_tag
-- ----------------------------
DROP TABLE IF EXISTS `ms_tag`;
CREATE TABLE `ms_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_tag
-- ----------------------------
INSERT INTO `ms_tag` VALUES (5, '/static/tag/java.png', 'springboot');
INSERT INTO `ms_tag` VALUES (6, '/static/tag/java.png', 'spring');
INSERT INTO `ms_tag` VALUES (7, '/static/tag/java.png', 'springmvc');
INSERT INTO `ms_tag` VALUES (8, '/static/tag/css.png', '刀剑录');

-- ----------------------------
-- Table structure for site_setting
-- ----------------------------
DROP TABLE IF EXISTS `site_setting`;
CREATE TABLE `site_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1基础设置，2页脚徽标，3资料卡，4友链信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of site_setting
-- ----------------------------
INSERT INTO `site_setting` VALUES (1, 'webTitleSuffix', '网页标题后缀', ' - Naccl\'s Blog', 1);
INSERT INTO `site_setting` VALUES (2, 'blogName', '博客名称', 'Naccl\'s Blog', 1);
INSERT INTO `site_setting` VALUES (3, 'footerImgTitle', '页脚图片标题', '手机看本站', 1);
INSERT INTO `site_setting` VALUES (4, 'footerImgUrl', '页脚图片路径', '/img/qr.png', 1);
INSERT INTO `site_setting` VALUES (5, 'copyright', 'Copyright', '{\"title\":\"Copyright © 2019 - 2020\",\"siteName\":\"NACCL\'S BLOG\"}', 1);
INSERT INTO `site_setting` VALUES (6, 'beian', 'ICP备案号', '', 1);
INSERT INTO `site_setting` VALUES (7, 'badge', '徽标', '{\"title\":\"由 Spring Boot 强力驱动\",\"url\":\"https://spring.io/projects/spring-boot/\",\"subject\":\"Powered\",\"value\":\"Spring Boot\",\"color\":\"blue\"}', 2);
INSERT INTO `site_setting` VALUES (8, 'badge', '徽标', '{\"title\":\"Vue.js 客户端渲染\",\"url\":\"https://cn.vuejs.org/\",\"subject\":\"SPA\",\"value\":\"Vue.js\",\"color\":\"brightgreen\"}', 2);
INSERT INTO `site_setting` VALUES (9, 'badge', '徽标', '{\"title\":\"UI 框架 Semantic-UI\",\"url\":\"https://semantic-ui.com/\",\"subject\":\"UI\",\"value\":\"Semantic-UI\",\"color\":\"semantic-ui\"}', 2);
INSERT INTO `site_setting` VALUES (10, 'badge', '徽标', '{\"title\":\"阿里云提供服务器及域名相关服务\",\"url\":\"https://www.aliyun.com/\",\"subject\":\"VPS & DNS\",\"value\":\"Aliyun\",\"color\":\"blueviolet\"}', 2);
INSERT INTO `site_setting` VALUES (11, 'badge', '徽标', '{\"title\":\"jsDelivr 提供 CDN 加速服务\",\"url\":\"https://www.jsdelivr.com/\",\"subject\":\"CDN\",\"value\":\"jsDelivr\",\"color\":\"orange\"}', 2);
INSERT INTO `site_setting` VALUES (12, 'badge', '徽标', '{\"title\":\"GitHub 提供图床\",\"url\":\"https://github.com/\",\"subject\":\"OSS\",\"value\":\"GitHub\",\"color\":\"github\"}', 2);
INSERT INTO `site_setting` VALUES (13, 'badge', '徽标', '{\"title\":\"本站点采用 CC BY 4.0 国际许可协议进行许可\",\"url\":\"https://creativecommons.org/licenses/by/4.0/\",\"subject\":\"CC\",\"value\":\"BY 4.0\",\"color\":\"lightgray\"}', 2);
INSERT INTO `site_setting` VALUES (14, 'avatar', '图片路径', '/img/avatar.jpg', 3);
INSERT INTO `site_setting` VALUES (15, 'name', '昵称', 'Naccl', 3);
INSERT INTO `site_setting` VALUES (16, 'rollText', '滚动个签', '\"云鹤当归天，天不迎我妙木仙；\",\"游龙当归海，海不迎我自来也。\"', 3);
INSERT INTO `site_setting` VALUES (17, 'github', 'GitHub地址', 'https://github.com/Naccl', 3);
INSERT INTO `site_setting` VALUES (18, 'qq', 'QQ链接', 'http://sighttp.qq.com/authd?IDKEY=', 3);
INSERT INTO `site_setting` VALUES (19, 'bilibili', 'bilibili链接', 'https://space.bilibili.com/', 3);
INSERT INTO `site_setting` VALUES (20, 'netease', '网易云音乐', 'https://music.163.com/#/user/home?id=', 3);
INSERT INTO `site_setting` VALUES (21, 'email', 'email', 'mailto:i@naccl.top', 3);
INSERT INTO `site_setting` VALUES (22, 'favorite', '自定义', '{\"title\":\"最喜欢的动漫 📺\",\"content\":\"异度侵入、春物语、NO GAME NO LIFE、实力至上主义的教室、辉夜大小姐、青春猪头少年不会梦到兔女郎学姐、路人女主、Re0、魔禁、超炮、俺妹、在下坂本、散华礼弥、OVERLORD、慎勇、人渣的本愿、白色相簿2、死亡笔记、DARLING in the FRANXX、鬼灭之刃\"}', 3);
INSERT INTO `site_setting` VALUES (23, 'favorite', '自定义', '{\"title\":\"最喜欢我的女孩子们 🤤\",\"content\":\"芙兰达、土间埋、食蜂操祈、佐天泪爷、樱岛麻衣、桐崎千棘、02、亚丝娜、高坂桐乃、五更琉璃、安乐冈花火、一色彩羽、英梨梨、珈百璃、时崎狂三、可儿那由多、和泉纱雾、早坂爱\"}', 3);
INSERT INTO `site_setting` VALUES (24, 'favorite', '自定义', '{\"title\":\"最喜欢玩的游戏 🎮\",\"content\":\"Stellaris、巫师、GTA、荒野大镖客、刺客信条、魔兽争霸、LOL、PUBG\"}', 3);
INSERT INTO `site_setting` VALUES (25, 'reward', '赞赏码路径', '/img/reward.jpg', 1);
INSERT INTO `site_setting` VALUES (26, 'commentAdminFlag', '博主评论标识', '咕咕', 1);
INSERT INTO `site_setting` VALUES (27, 'friendContent', '友链页面信息', '随机排序，不分先后。欢迎交换友链~(￣▽￣)~*\n\n* 昵称：Naccl\n* 一句话：游龙当归海，海不迎我自来也。\n* 网址：[https://naccl.top](https://naccl.top)\n* 头像URL：[https://naccl.top/img/avatar.jpg](https://naccl.top/img/avatar.jpg)\n\n仅凭个人喜好添加友链，请在收到我的回复邮件后再于贵站添加本站链接。原则上已添加的友链不会删除，如果你发现自己被移除了，恕不另行通知，只需和我一样做就好。\n\n', 4);
INSERT INTO `site_setting` VALUES (28, 'friendCommentEnabled', '友链页面评论开关', '1', 4);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签颜色(可选)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for visit_record
-- ----------------------------
DROP TABLE IF EXISTS `visit_record`;
CREATE TABLE `visit_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pv` int(11) NOT NULL COMMENT '访问量',
  `uv` int(11) NOT NULL COMMENT '独立用户',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日期\"02-23\"',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of visit_record
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

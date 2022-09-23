# LbmBlog博客

## 简介

LbmBlog是一个前后端分离的博客，是一个本人开发使用、长期维护的真实博客项目。后端主要使用SpringBoot开发，前端使用Vue+element开发

**预览地址**：http://libomeng.cn:81 | **后台管理**： http://libomeng.cn:82

### 模块简介

#### 后端

+ **Blog-api**:博客主页面调用的后端API。采用SpringBoot+MybatisPlus开发。使用Redis作为常用数据缓存，并使用ThreadPool执行异步任务提升并发量。
+ **Blog-admin**:博客后台管理页面调用的API。采用SpringBoot开发。使用SpringSecurity安全框架+JWT做权限控制

### 前端

+ Blog-app: 博客主页面。使用Vue、Vue-Cli 3.x、VueX 开发，组件库使用Element-UI
+ Blog-cms:博客后台管理页面，技术栈同Blog-app

## 主要功能

+ 文章、标签、类别分类查询功能
+ 文章分组展示：热门文章、最新文章、文章时间归档等功能
+ 文章、标签、类别 管理功能。包含有添加、删除、修改文章|标签|类别  文章编辑区支持`Markdown`
+ 文章状态管理，发布、置顶等
+ 支持redis缓存，支持自动更新缓存
+ 支持定时任务
+ 集成了图传上传功能
+ 使用SpringSecurity，实现用户权限控制功能
+ 集成音乐播放插件
+ 服务信息统计：服务浏览量、运行时间等

## 快速使用

1. #### clone项目代码

~~~ shell
git clone https://github.com/libomeng/test.git
~~~



#### 2.修改你的数据库配置文件

~~~xml 
spring:
  application:
    name: lbm
  datasource:
    url: "" #your url
    username: "" # your username
    password: "" # your password
	redis:
    port: 6379
    host:      #your host
    password:  #your password
~~~

#### 3.依赖管理

+ **Blog-app、Blog-cms**

~~~ shell
npm install
~~~

+ **Blog-api、Blog-admin**

  使用maven依赖管理

#### 4.运行

+ **Blog-api**、**Blog-admin**

  直接RUN就完事

+ **Blog-app**、**Blog-cms**

~~~ shell
npm run dev
~~~

#### 5.打包

+ **Blog-api**、**Blog-admin**

~~~ shell
mvn clean
mvn package
~~~

+ **Blog-app**

~~~ shell
npm run build
~~~

+ **Blog-cms**

~~~shell
npm run build:prod
~~~



## Docker部署

项目提供了docker用于生产环境部署

### 项目结构

~~~ 
 |_Lbm-Blog
	 |_blog_api
		 |_blog-api-0.0.1-SNAPSHOT.jar.jar
		 |_DockerFile_blog_api
	 |_blog_admin
		 |_blog_admin.jar
		 |_DockerFile_blog_admin
	 |_blog_app
	 |_blog_cms
~~~

### docker-compose

~~~ yaml
version: "3.8"
services:
  blog_app: # 服务名称，用户自定义
    image: nginx:latest  # 镜像版本
    restart: always
    ports:
      - 80:80 #端口号
    volumes: # 挂载
      - /root/lbmblog/blog_app/html:/usr/share/nginx/html # :前你的文件路径
      - /root/lbmblog/blog_app/nginx.conf:/etc/nginx/nginx.conf
    privileged: true # 这个必须要，解决nginx的文件调用的权限问题
  blog_cms: # 服务名称，用户自定义
    image: nginx:latest # 镜像版本
    restart: always #自动重启
    ports:
      - 82:82
    volumes: # 挂载
      - /root/lbmblog/blog_cms/html:/usr/share/nginx/html
      - /root/lbmblog/blog_cms/nginx.conf:/etc/nginx/nginx.conf
    privileged: true # 这个必须要，解决nginx的文件调用的权限问题
  blog_api:
    build: 
      context: ./blog_api
      dockerfile: Dockerfile_blog_api
    restart: always
    ports:
      - 6088:6088
  blog_admin:
    build: 
      context: ./blog_admin
      dockerfile: Dockerfile_blog_admin
    restart: always
    ports:
      - 6089:6089
~~~

### DockerFile

#### DockerFile_Blog_api

~~~yaml
FROM openjdk:8 # images版本
EXPOSE 6088 # 暴露的端口号
ADD blog-api-0.0.1-SNAPSHOT.jar blog_api.jar #重命名
RUN bash -c 'touch /blog_api.jar'
ENTRYPOINT ["java", "-jar", "blog_api.jar","--spring.profiles.active=prod","--allowedOrigins=http://libomeng.cn"] #spring.profiles.active 使用的配置文件 allowedOrigins：跨域地址
~~~

#### DockerFile_Blog_admin

~~~ yaml
FROM java:8 # images版本
EXPOSE 6089 # 暴露的端口号
ADD blog-admin-0.0.1-SNAPSHOT.jar blog_admin.jar #重命名
RUN bash -c 'touch /blog_admin.jar'
ENTRYPOINT ["java", "-jar", "/blog_admin.jar","--spring.profiles.active=prod","--allowedOrigins=http://libomeng.cn:82"]  #spring.profiles.active 使用的配置文件 allowedOrigins：跨域地址
~~~

### 部署

~~~ shell
docker-compose up -d
~~~

## 特别鸣谢

@ [PanJiaChen](https://github.com/PanJiaChen)/**[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)** 优秀的Vue模版

@[SevenOutman](https://github.com/SevenOutman)/**[vue-aplayer](https://github.com/SevenOutman/vue-aplayer)** 基于Vue的音乐播放小组件

@[hinesboy](https://github.com/hinesboy)/**[mavonEditor](https://github.com/hinesboy/mavonEditor)** Markdown编辑器
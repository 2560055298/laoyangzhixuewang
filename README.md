<p align="center">
    <img width="130" src="https://cdn.laoyangzhijia.com/my-picture-master/picture9/111.jpg">
</p>
<h1 align="center" style="color:#99d9ea">老洋自学网</h1>
<p>
  <a href="https://www.yangzaikongzhongfei.com/"><img src="https://img.shields.io/badge/%E8%80%81%E6%B4%8B%E8%87%AA%E5%AD%A6%E7%BD%91-%E4%B8%AA%E4%BA%BA%E7%AB%99%E7%82%B9-brightgreen" alt="网站地址"></a>&nbsp;
  <a href="https://www.bilibili.com/video/BV1m44y1T7v7?spm_id_from=333.999.0.0"><img src="https://img.shields.io/badge/%E8%80%81%E6%B4%8B%E8%87%AA%E5%AD%A6%E7%BD%91-%E9%A1%B9%E7%9B%AE%E5%B1%95%E7%A4%BA-brightgreen" alt="项目展示"></a>&nbsp;
    <a href="https://github.com/2560055298/laoyangzhixuewang"><img src="https://img.shields.io/badge/gitHub-%E9%A1%B9%E7%9B%AE%E5%9C%B0%E5%9D%80-red" alt="gitHub"></a>&nbsp;
  <a href="https://gitee.com/sheep-are-flying-in-the-sky/laoyangzhixuewang"><img src="https://img.shields.io/badge/%E7%A0%81%E4%BA%91-%E9%A1%B9%E7%9B%AE%E5%9C%B0%E5%9D%80-orange" alt="码云"></a>
</p>







## 友情提示

> **项目部署文档**：[在线访问地址](https://2560055298.github.io/laoyangzixuewangDeploy)。



## 创建背景

在学习计算机的这条路上，大多时候是去网上找资源学习。<br/>
我十分感激：许多传道受业解惑的线上老师。<br/>
所以我想将来某一天，自己有一定能力时，也去录制一些技术类、编程自学经验类的视频发布到网上供大家学习和参考，因此做了老洋自学网这个项目。<br/>






## 项目介绍

`老洋自学网`项目是一个（在线学习平台），包括（前台网站系统）及（后台运营平台），采用 B2C 模式及（微服务架构）实现的（前后端分离）系统，适用于：计算机专业学生学习编程课程。<br/>

前台网站系统包含：首页、课程页面、讲师页面、网站愿景、后台登录等页面<br/>

后台运营平台包含：讲师管理、分类管理、课程管理、统计分析等模块 <br/>



### 项目演示

项目演示地址： 

> 前台：网站系统

![image-20220228074507250](https://cdn.laoyangzhijia.com/my-picture-master/pic15/image-20220228074507250.png)





> 后台：运营平台

![image-20220228074602167](https://cdn.laoyangzhijia.com/my-picture-master/pic15/image-20220228074602167.png)





### 组织结构

``` lua
laoyangzixuewang
├── front_next      		-- 前端（前台）代码
├── front_vue_admin		    -- 前端（后台）代码
├── after_code      		-- 后端代码
├── laoyangzhixuewang.sql   -- 数据库表
├── README.md				-- 项目介绍
```



### 技术选型


#### 前端技术

| 技术                | 说明            | 官网                                                 |
| ----------------------------- | --------------- | ---------------|
| Vue                | 前端框架         | https://cn.vuejs.org/        |
| Nuxt               | 前端框架         | https://nuxtjs.org/            |
| Element UI | 组件库     | https://element.eleme.cn/#/zh-CN |
| vue-admin-template | 后台模板 | https://gitee.com/panjiachen/vue-admin-template |
| Nodejs | JavaScript 运行环境 | https://nodejs.org/en/ |
| Axios     | 网络请求库          | https://www.axios-http.cn/                      |






#### 后端技术

| 技术         | 说明             | 官网                                    |
| ------------ | ---------------- | --------------------------------------- |
| SpringBoot   | 容器+MVC框架     | https://spring.io/projects/spring-boo   |
| SpringCloud  | 框架的有序集合   | https://spring.io/projects/spring-cloud |
| Nacos        | 注册中心         | https://nacos.io/zh-cn/                 |
| Hystrix      | 熔断器           | https://github.com/Netflix/Hystrix      |
| MyBatis-Plus | ORM框架          | https://baomidou.com/                   |
| MySQL        | 数据库           | https://www.mysql.com/                  |
| Redis        | 缓存             | http://www.redis.cn/                    |
| Nginx        | 网关             | https://nginx.org/en/                   |
| SLF4J        | 日志门面         | http://www.slf4j.org/                   |
| Logback      | 日志技术         | http://logback.qos.ch/                  |
| Lombok       | 简化对象封装工具 | https://github.com/rzwitserloot/lombok  |
| SMS          | 腾讯云短信服务   | https://cloud.tencent.com/product/sms   |



#### 架构图

##### 业务架构图

![image-20220228083106675](https://cdn.laoyangzhijia.com/my-picture-master/pic15/image-20220228083106675.png)



## 环境搭建

### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| Navicat   | 数据库连接工具    | http://www.formysql.com/xiazai.html             |
| X-shell       | Linux远程连接工具   | http://www.netsarang.com/download/software.html |
| Xftp | Linux远程传输工具 | https://www.netsarang.com/zh/xftp/ |
| Git   | 版本控制  | https://git-scm.com/  |
| Snipaste  | 屏幕截图工具      | https://www.snipaste.com/                       |
| Typora | Markdown编辑器 | https://typora.io/ |



### 开发环境

| 工具   | 版本号  | 下载                                                         |
| ------ | ------- | ------------------------------------------------------------ |
| JDK    | 1.8     | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql  | 5.7     | https://www.mysql.com/                                       |
| Nacos  | 1.4.1   | https://nacos.io/zh-cn/                                      |
| Nginx  | 1.20.2  | https://nginx.org/en/                                        |
| Nodejs | 14.16.0 | https://nodejs.org/en/                                       |



## 参考

- 谷粒学苑：[项目教程](https://www.bilibili.com/video/BV1dQ4y1A75e)

- 腾讯课堂：[腾讯课堂](https://ke.qq.com/)

- 中国大学MOOC：[中国大学MOOC](https://www.icourse163.org/)
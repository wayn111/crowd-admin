# crowdfounding

### Language
- [简体中文](README_zh.md)|[English](README_en.md)

### 项目介绍
crowdfounding(众筹)是一个面向学习型的开源框架，集合了开发者日常工作项目上所学以及对开源项目
[AdminLTE-admin](https://gitee.com/zhougaojun/KangarooAdmin/tree/master)，
[bootdo](https://gitee.com/lcg0124/bootdo)，
[RuoYi](https://gitee.com/y_project/RuoYi)等的学习总结后，开发出的后台管理系统，
基于经典技术组合（Spring、Apache Shiro、MyBatis、Websocket、Quartz、JSP），集成了系统管理、消息通知、服务监控、任务调度、代码生成等常用功能，
也是开发者这两年工作生涯技术上的缩影，_代码层次良好，注重复用，追求功能上的尽量简介而不失优雅_，取名crowdfounding！
(ctrl+c/ctrl+c)

ps:项目名是开发者某日照网上教程copy而来 :smile: 


### 技术选型
1. 后端
    - 核心框架：Spring
    - 控制层框架：SpringMVC
    - 权限控制：Shiro
    - 消息推送：Websocket
    - 持久层框架：Mybatis-Plus
    - 日志管理：SLF4J > logback
    - 缓存控制：Ehcache/Redis可切换
    - 环境控制：使用spring profile可根据`System/JVM`参数灵活切换配置文件
2. 前端
    - 模板选型：Jsp
    - 管理模板：H+
    - JS框架：jQuery
    - 数据表格：bootstrapTable
    - 弹出层：layer
    - 通知消息：Toastr
    - 消息推送/轮询：sockJs、stomp
    - 树结构控件：jsTree
    - checkbox选择控件：bootstrapSwitch
3. 开发平台
    - JDK版本：1.8+
    - Maven：3.5+
    - 数据库：mysql5+
    - ide：Eclipse/Idea
 
### 内置模块
1. 系统管理
    - 用户管理：系统操作者，可绑定多角色
    - 角色管理：菜单权限携带者，可配置到按钮级权限
    - 菜单管理：配置系统目录，菜单链接，操作权限
    - 部门管理：用户所属部门
    - 日志操作：记录用户操作，包含请求参数
2. 办公通知
    - 我的通知：接收当前用户得通知信息
    - 通知管理：用户发送并管理通知消息
3. 基础管理
    - 数据字典：对系统中经常使用的一些较为固定的数据进行维护
4. 系统工具
    - 代码生成：可动态根据数据库表，生成后台java代码
    - 任务调度：根据调度策略以及执行目标配置任务
    - 任务日志：记录任务日志，方便排错追踪
5. 系统监控
    - 在线用户：当前系统中活跃用户状态监控，可强制下线
    - 数据监控：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈
    - 系统服务：监视当前系统CPU、内存、磁盘、堆栈等相关信息


### 开发教程
- 此处参考[RuoYi](https://gitee.com/y_project/RuoYi)文档
- 登陆账号：admin 密码：123456
- 如有疑问，QQ:1669738430
 
### 获取源码
- [crowdfounding 码云](https://gitee.com/wayn111/crowdfounding)
- [crowdfounding github](https://github.com/wayn111/crowdfounding)

### 在线演示
- <a href="http://wayn.xin" target="_blank">crowdfounding-web</a>
- 账号：admin 密码：123456

### 参考项目
- [AdminLTE-admin](https://gitee.com/zhougaojun/KangarooAdmin/tree/master)
- [bootdo](https://gitee.com/lcg0124/bootdo)
- [RuoYi](https://gitee.com/y_project/RuoYi)

### 实例截图
__系统登陆__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/175955_6658801e_1731679.png "系统登陆.png")
__首页__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180055_d2eda5fd_1731679.png "首页.png")
__用户管理__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180332_d2e3162e_1731679.png "用户管理.png")
__添加角色__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180453_538d9788_1731679.png "添加角色.png")
__菜单管理__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180536_36473cbc_1731679.png "菜单管理.png")
__通知管理__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180652_d022dcf8_1731679.png "通知管理.png")
__查看通知__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180748_5f342010_1731679.png "查看通知.png")
__字典管理__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0824/180904_135aa88e_1731679.png "字典管理.png")
__日志查看__
![日志查看](https://images.gitee.com/uploads/images/2019/0714/171557_056253cd_1731679.png "log.png")
__系统服务__
![输入图片说明](https://images.gitee.com/uploads/images/2019/0719/173156_b2dc84a5_1731679.png "server.png")

# crowd-admin

### Language
- [简体中文](README.md)|[English](README_en.md)

### 项目介绍
基于Spring,Shiro,Redis,Mybatis的通用后台权限管理系统，并且集成了rbac权限管理，后台消息推送，邮件发送，任务调度，代码生成，elfinder文件管理等常用功能，易于上手，学习，使用二次开发。

#### 主要特性
- 项目按功能模块化，提升开发，测试效率
- 支持后台消息推送
- 集成elfinder进行文件管理
- 支持数据字典
- 支持邮件发送，采用activeMQ异步解耦
- 支持在线用户监控，登出等操作
- 支持redis/ehcache切换使用
- 支持ip2region本地化
- 支持多数据源操作
- 集成日志切面，方便日志记录
- 前端js代码简洁，清晰，避免过度封装
- 支持统一输出异常，避免繁琐的判断

### 技术选型
1. 后端
    - 核心框架：Spring
    - 控制层框架：SpringMVC
    - 权限控制：Shiro
    - 消息中间件：activeMQ
    - 消息推送：Websocket
    - 邮件发送：javax.mail
    - 任务调度：Quartz
    - 持久层框架：Mybatis-Plus
    - 日志管理：SLF4J > logback
    - 缓存控制：Ehcache/Redis可切换
    - 环境控制：使用spring profile可根据`System/JVM`参数灵活切换配置文件
2. 前端
    - 模板选型：Jsp
    - 管理模板：H+
    - JS框架：jQuery
    - 数据表格：bootstrapTable
    - 文件管理：elfinder
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
2. 邮件服务
    - 邮件发送：基于activeMQ异步发送邮件
3. 办公通知
    - 我的通知：接收当前用户得通知信息
    - 通知管理：管理员发送并管理通知消息
4. 基础管理
    - 数据字典：对系统中经常使用的一些较为固定的数据进行维护
    - 文件管理：方便管理查看系统内上传文件
5. 系统工具
    - 代码生成：可动态根据数据库表，生成后台java代码
    - 任务调度：根据调度策略以及执行目标配置任务调度
    - 任务日志：记录任务日志，方便排错追踪
6. 系统监控
    - 在线用户：当前系统中活跃用户状态监控，可强制下线
    - 数据监控：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈
    - 系统服务：监视当前系统CPU、内存、磁盘、堆栈等相关信息


### 开发教程
- [RuoYi](https://gitee.com/y_project/RuoYi)文档
- [Mybatis Plus](https://mp.baomidou.com/guide)文档

### 获取源码
- [crowd-admin 码云](https://gitee.com/wayn111/crowdfounding)
- [crowd-admin github](https://github.com/wayn111/crowdfounding)

### 在线演示
- <a href="http://wayn.xin:8080" target="_blank">crowd-admin</a>

### 参考项目
- [AdminLTE-admin](https://gitee.com/zhougaojun/KangarooAdmin/tree/master)
- [bootdo](https://gitee.com/lcg0124/bootdo)
- [RuoYi](https://gitee.com/y_project/RuoYi)

### 实例截图
__系统登陆__
![输入图片说明](./crowdfounding-web/crowdfounding-img/系统登陆.png "系统登陆.png")
__首页__
![输入图片说明](./crowdfounding-web/crowdfounding-img/首页.png "首页.png")
__用户管理__
![输入图片说明](./crowdfounding-web/crowdfounding-img/用户管理.png "用户管理.png")
__添加角色__
![输入图片说明](./crowdfounding-web/crowdfounding-img/添加角色.png "添加角色.png")
__菜单管理__
![输入图片说明](./crowdfounding-web/crowdfounding-img/菜单管理.png "菜单管理.png")
__通知管理__
![输入图片说明](./crowdfounding-web/crowdfounding-img/通知管理.png "通知管理.png")
__查看通知__
![输入图片说明](./crowdfounding-web/crowdfounding-img/查看通知.png "查看通知.png")
__字典管理__
![输入图片说明](./crowdfounding-web/crowdfounding-img/字典管理.png "字典管理.png")

### 特别赞助
<a href="https://www.jetbrains.com/" target="_blank">
<img src="./crowdfounding-web/crowdfounding-img/jetbrains.png" width="100px" alt=""></a>

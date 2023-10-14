# crowd-admin

| 分支名称                                                               | Spring Boot 版本 | JDK 版本 |   
|--------------------------------------------------------------------|----------------|--------|
| [main](https://github.com/wayn111/crowd-admin)                     | 2.7.16         | 17     |s |
| [spring4.0](https://github.com/wayn111/crowd-admin/tree/spring4.0) | spring4.0      | 1.8    | s|
 
---

# Language

- [简体中文](README.md)|[English](README_en.md)

# 项目介绍

基于jdk17、springboot2.7重构而来，crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目

# 主要特性
- 前后端支持stomp高级协议，完善的websocket配置，支持自定义用户认证、连接周期监听等
- 支持系统参数配置，支持spring内存的@Cacheable注解
- 项目按功能模块化拆分，自定义全局统一异常输出，代码清晰合理
- 集成elfinder模块，包含完整的文件管理操作，包含文件上传、删除、压缩、移动、预览等诸多操作
- 支持后台消息/邮件推送、数据字典、系统参数配置、各模块操作日记记录
- 支持系统服务监控、在线用户监控、数据源监控等操作
- 支持redis/ehcache切换使用
- 支持ip2region本地化
- 支持多数据源操作
- 前端使用H+模板，样式美观，支持ajax下载文件，js代码简洁，清晰，避免过度封装
- [在线地址](http://121.4.124.33/crowd/)

> 如果有任何使用问题，欢迎提交Issue或加关注我公众号私信我告知，方便互相交流反馈～ 💘。最后，喜欢的话麻烦给我个star

关注公众号：waynblog，每周更新最新技术文章。回复关键字：
- **学习**：加群交流，群内问题都会一一解答。
- **演示账号**：获得线上项目管理后台演示账号。
- **开源项目**：获取博主自己写的三个开源项目，包含PC、H5商城、后台权限管理系统等。

<img src="images/wx-mp-code.png" width = "100" />

---

- [crowd-admin](#crowd-admin)
  - [内置模块](#内置模块)
  - [技术选型](#技术选型)
  - [开发部署](#开发部署)
  - [参考资料](#参考资料)
  - [获取源码](#获取源码)
  - [实例截图](#实例截图)
  - [特别赞助](#特别赞助)

---

# 内置模块

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
    - 文件管理：集成elfinder，便于对系统内文件进行查看修改
5. 系统工具
    - 代码生成：可动态根据数据库表，生成后台java代码
    - 任务调度：根据调度策略以及执行目标配置任务调度
    - 任务日志：记录任务日志，方便排错追踪
6. 系统监控
    - 在线用户：当前系统中活跃用户状态监控，可强制下线用户
    - 数据监控：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈
    - 系统服务：监视当前系统CPU、内存、磁盘、堆栈等相关信息

---

# 技术选型

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
    - 环境控制：使用spring profile可根据`-Dspring.profiles.active=dev`参数灵活切换配置文件
2. 前端
    - 模板选型：thymeleaf
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
    - JDK版本：17+
    - Maven：3.5+
    - 数据库：mysql8+（设置表名称大小写忽略）
    - 缓存：ehcache/redis
    - ide：Eclipse/Idea

---

# 开发部署

```
# 1. 克隆项目
git clone git@github.com:wayn111/crowd-amin.git

# 2. 导入项目依赖
将crowd-admin目录用idea打开，导入maven依赖

# 3. 安装Mysql8.0+、Redis3.0+、Jdk17+、Maven3.5+

# 4. 导入sql文件
在项目根目录下crowd-web文件夹下，找到`crowd-admin.sql`文件，新建mysql数据库crowd-admin，导入其中

# 5. 修改Mysql、Redis连接配置
修改`application-dev.yml`文件中数据连接配置相关信息

# 6. 启动项目
进入crowd-web木块，找到CrowdApplication文件，在idea中右键run application运行

# 7. 访问
打开浏览器输入：http://localhost:8080/crowd/
```
---

# 参考资料

- [RuoYi](http://doc.ruoyi.vip/) 文档
- [Mybatis Plus](https://mp.baomidou.com/guide) 文档
- [AdminLTE-admin](https://gitee.com/zhougaojun/KangarooAdmin/tree/master)
- [bootdo](https://gitee.com/lcg0124/bootdo)
- [RuoYi](https://gitee.com/y_project/RuoYi)


# 获取源码

- [crowd-admin github](https://github.com/wayn111/crowd-admin)
- [crowd-admin 码云](https://gitee.com/wayn111/crowdfounding)

---

# 实例截图

__系统登陆__
![输入图片说明](./crowd-web/crowd-img/系统登陆.png "系统登陆.png")
__首页__
![输入图片说明](./crowd-web/crowd-img/首页1.png "首页1.png")
![输入图片说明](./crowd-web/crowd-img/首页2.png "首页2.png")
__用户管理__
![输入图片说明](./crowd-web/crowd-img/用户管理.png "用户管理.png")
__菜单管理__
![输入图片说明](./crowd-web/crowd-img/菜单管理.png "菜单管理.png")
__通知管理__
![输入图片说明](./crowd-web/crowd-img/通知管理.png "通知管理.png")
__查看通知__
![输入图片说明](./crowd-web/crowd-img/查看通知.png "查看通知.png")
__文件管理__
![输入图片说明](./crowd-web/crowd-img/文件管理.png "文件管理.png")
__系统服务__
![输入图片说明](./crowd-web/crowd-img/系统服务.jpg "系统服务.jpg")

---

# 特别赞助

<a href="https://www.jetbrains.com/" target="_blank">
<img src="./crowd-web/crowd-img/jetbrains-training-partner.svg" width="20%" alt=""></a>

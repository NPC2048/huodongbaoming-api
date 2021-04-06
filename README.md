## 活动报名-H5

***

一个简单的活动报名h5app, api 端

* 使用 springboot + mybatis-plus + sa-token 实现

***

## 功能模块

### 审核模块

* 只有管理员角色的用户能看到审核模块
* 管理员在此审核活动，通过或者拒绝活动

### 活动模块

* 默认首页，展示通过的活动，普通用户可点击活动进行报名

### 发布模块

* 用户在此模块进行发布活动，发布活动后由管理员审核，审核通过后才会显示在首页

### 我的模块

* 展示用户的基本信息，头像昵称等
* 修改用户基本信息
* 查看报名过的活动
* 查看自己发布过的活动
* 修改头像/邮箱（需要邮箱验证）
* 修改密码
* 退出登录

### 登录/注册/忘记密码模块

* 登录：输入用户名和密码进行登录
* 注册：输入邮箱、用户名等信息进行注册（需要邮箱验证）
* 忘记密码：忘记密码时在此输入用户名或邮箱获取邮箱验证码进行重置密码

## 运行项目

***

### 环境

* ide(idea, eclipse, vscode, ...)
* jdk 8
* mysql 8

### 前置条件

* 配置邮件参数：JVM参数配置邮件服务器地址，用户名、密码等参数

> 例如: * -Dspring.mail.username=邮箱服务器 
> -Dspring.mail.password=密码
> -Dspring.mail.host=服务器地址[可选，默认 stmp.163.com] -Dspring.mail.port=端口[可选，默认443]

### 以开发模式运行

> 直接运行 HuodongbaomingApplication 即可, spring.profiles.active 默认为 dev

### prod

* 将项目打包

> java -jar huodongbaoming.jar



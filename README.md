



# 一个用springboot+mybatis实现的论坛demo

## 简介

年初写的一个小demo用了spring+spring mvc+thymeleaf+mybatis 
使用了JWT做token验证，实现了无状态登录，所有的数据传输都使用了api，实现了简单的前后端分离
mysql用了这里 https://github.com/abel533/Mapper 的一个通用mapper，省去了编写xml

## 安装

### 部署环境

- java 1.8
- tomcat 8.5+ (建议9)
- mysql 5.5+
- maven

### 部署方法

1. 安装java1.8, mysql5.5, maven 和 tomcat9

2. 克隆整个项目

   ```shell
   git clone https://github.com/kiritoxkiriko/bbsdemo.git
   cd bbsdemo
   git checkout package_war
   ```

3. mysql执行项目根目录下的 bbs.sql 脚本创建数据库

4. 修改项目根目录下 /src/resources/application.yml 文件

   ```yaml
   spring:
     datasource:
       name: bbs
       url: jdbc:mysql://127.0.0.1:3306/bbs?useSSL=false
       username: yourusername
       password: yourpassword
   
   ```

   将数据库用户名密码改为你自己的密码

5. 在项目根目录下执行 

   ```shell
   mvn package
   ```

   在项目根目录下会生成 /target/bbsdemo-0.0.1-SNAPSHOT1.war

   将war包部署至tomcat服务器即可

还可以使用release中我打包好的war包, 用解压软件打开war包, 修改/WEB-INF/classes/com/application.yml 文件即可

如想在开发环境进行调试，checkout到master分支即可

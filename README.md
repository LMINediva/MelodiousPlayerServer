# 悦听影音后台管理系统后端

## 简介：
这是一款基于SpringSecurity+MyBatis-Plus+Redis+MySQL技术开源的软件，具备用户、角色、菜单、公告、反馈、安卓应用、音乐、MV和音乐清单管理、服务监控等功能。本项目基础架构来源于[**Java知识分享网**](www.java1234.vip)，资源文件来源于互联网，本软件主要作为学习和交流使用。

## 在线体验
- test2/123456

演示地址：http://hk.frpee.top:17116/

## 图片展示
具体查看**悦听影音后台管理系统前端界面**（[**github**](https://github.com/LMINediva/melodious-player-admin) | [**gitee**](https://gitee.com/lminediva/melodious-player-admin)）的图片展示。

## 运行说明
1、在MySQL中创建名为melodious_player的数据库，然后运行resource目录下的melodious_player.sql文件，创建所需的数据表和数据。  
2、修改项目配置文件，在application.yml 文件中配置jasypt加密的秘钥：
```
jasypt:
  encryptor:
    password: 你自己的jasypt加密的秘钥
```
3、找到src\test\java\com\melodiousplayer目录下的MelodiousPlayerServerApplicationTests.java文件，输入你自己的MySQL的Url、用户名和密码，然后运行，将输出的结果替换到application.yml 文件中的MySQL配置，Redis的配置修改同上。  
4、修改完成后，可将application.yml 文件中配置jasypt加密的秘钥的代码删除，在IDEA中添加启动参数：-Djasypt.encryptor.password=你自己的jasypt加密的秘钥。  
5、下载百度网盘文件中的演示资源文件：MelodiousPlayerServer.rar 链接: https://pan.baidu.com/s/1fS3PrnONgzEiUkiPQ58jAw?pwd=kmrd 提取码: kmrd，解压到本项目根目录下。  
6、如需将项目打包成jar包，需先打包前端页面，具体查看**悦听影音后台管理系统前端界面**（[**github**](https://github.com/LMINediva/melodious-player-admin) | [**gitee**](https://gitee.com/lminediva/melodious-player-admin)）的运行说明，打包好后，需将演示资源文件解压到jar包所在目录中，然后运行：java -Djasypt.encryptor.password=你自己的jasypt加密的秘钥 -jar xxx.jar 命令来运行jar包。
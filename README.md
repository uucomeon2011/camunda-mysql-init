#spring boot 集成 camunda + mysql

#说明
##1.sql在doc目录下的camunda-sql-scripts-7.10.0.zip，也可以从以[这里](https://app.camunda.com/nexus/content/repositories/camunda-bpm/org/camunda/bpm/distro/camunda-sql-scripts/7.10.0/)直接下载
##2.自带的是h2数据库，去掉pom.xml里的h2,加入mysql-connector-java
##3.配置文件
```bash
  camunda.bpm.database.type=mysql
  camunda.bpm.database.schema-update= false
  
  spring.datasource.url=jdbc:mysql://localhost:3306/camunda?useSSL=false
  spring.datasource.username=root
  spring.datasource.password=123456
  spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```



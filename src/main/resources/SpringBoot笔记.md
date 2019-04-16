# SpringBoot

##1.Spring Boot概念

 Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用的 初始搭建以及开发过程。该框架 使用了特定的方式来进行配置，从而使开发人员不 再需要定义样板化的配置。通过这种方式，Spring Boot致力于在 蓬勃发展的快速应 用开发领域(rapid application development)成为领导者。用我的话来理解，就是spring boot其实 不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，spring boot整合了所有的框 架。 

**springboot(微框架) = springmvc(控制器) + spring(项目管理)**    

## 2.SpringBoot的特点

- 创建了**独立的**应用程序
- 嵌入的Tomcat，无需部署WAR文件    
- 简化Maven配置   
- 自动配置Spring    
- 没有XML配置    

  # 3.SpringBoot约束	

- src/main/java

  - com.baizhi   主包

    ​	entity     子包

    ​       dao

    ​       service

    ​      Controller

    ​      入口类：**注意：**入口类一定要配置在主包下与子包同级

    ​    Application.class

- src/main/resources

  ​	sprngboot配置

  ​        application.yml

  ​	application.yaml

  ​        application.properties

  ​      **注意：**名字一定要叫  applocation

- src/test/java

- src/test/resources


##4.使用maven搭建第一个SpringBoot项目

### 1.创建maven项目

### 2.导入jar包

**2.1导入父级项目依赖**

~~~xml
  <!--父级项目依赖-->
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.8.RELEASE</version>
  </parent>
~~~

**2.2导入jar包**

~~~xml
    <!--web支持的jar springboot的启动器-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--测试支持的jar-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <!-- 只在test测试里面运行 -->
      <scope>test</scope>
    </dependency>
~~~

###配置入口类

~~~java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //参数：入口类类对象，主函数的参数
        SpringApplication.run(Application.class,args);
    }
}
~~~

**@SpringBootApplication**

~~~java
1.@SpringBootApplication=@Configuration+@EnableAutoConfiguration+@ComponentScan
//当前的注解是一个组合注解
@Configuration  //作用:声明当前类是一个配置类
@ComponentScan  //作用:组件扫描   把一些加有@service  @controller @component  @repository注解的对象交由工厂管理
@EnableAutoConfiguration    //开启自动配置 datasource  sqlsessionFactorybean  mapperScanner 
2.@RestController //作用:组合注解 @Controller @ResponseBody  声明当前类为控制器,并且类中所用方法返回json  
~~~

### 3.springBoot配置

**SpringBoot默认启动没有项目名**

~~~yaml
server:
  port: 9898    #修改端口号
  context-path: /springboot   #配置项目名
~~~

## 4.修改启动banner

**注意:名字必须叫banner.txt**

~~~java
${AnsiColor.BRIGHT_YELLOW}
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
~~~

## 5.配置文件的拆分

>在项目中配置多套环境的配置方法。
>在实际的的开发中，对于一个工程，经常会有多种环境配置，开发环境，测试环境，准生产环境，生产环境，在不同的环境下，配置有可能是不一样的，比如接口地址、数据库连接配置等。为了避免频繁的修改配置文件，我们想要简便地切换各种环境配置,好在SpringBoot提供了这样的功能，可以很方便地切换不同场景下的配置。每个环境的参数不同，所以我们就可以把每个环境的参数配置到yml文件中，这样在想用哪个环境的时候只需要在主配置文件中将用的配置文件写上就行如下：



**1.创建多个配置文件**

application.yml     主配置文件

~~~yaml
spring:
  profiles:
    active: prod
~~~

application-dev.yml    生产环境

~~~~yaml
server:
  context-path: /springbootDev
~~~~

application-prod.yml  开发环境

~~~~yaml
server:
  context-path: /springbootProd
~~~~

##6.springBoot与第三方技术的集成

###6.1springBoot与mybatis集成

**1.导入jar包**

~~~xml
<!--整合mybatis-->
<!--mybatis和springboot的整合包-->
<dependency>
<groupId>org.mybatis.spring.boot</groupId>
<artifactId>mybatis-spring-boot-starter</artifactId>
<version>1.0.0</version>
</dependency>

<!--数据源的jar包-->
<dependency>
<groupId>commons-dbcp</groupId>
<artifactId>commons-dbcp</artifactId>
<version>1.4</version>
</dependency>
<!--数据库驱动-->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>5.1.38</version>
</dependency>
<!--mybatis的核心jar-->
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis</artifactId>
<version>3.2.8</version>
</dependency>
~~~

**2.配置相关参数**

~~~yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/cmfz
    username: root
    password: root
mybatis:
  mapper-locations: classpath:com/baizhi/mapper/*Mapper.xml
  type-aliases-package: com.baizhi.entity
~~~

**3.在入口类上加入注解扫描DAO**

注意：springboot没有提供basepackage相关的配置参数，所以在入口类上需要指定DAO的包路径：

~~~java
@MapperScan("com.baizhi.dao")
~~~

###6.2springBoot集成jsp

- 1.导入相关jar包

  ~~~XML
  <!-- 给内嵌tomcat提供jsp解析功能的jar-->
  <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
  </dependency>
  <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
  </dependency>
  ~~~

- 2.以插件形式启动  官方提供的插件

  ~~~xml
  <!--插件启动-->
  <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
  </plugin>
  ~~~

**修改springboot配置的视图解析器**

~~~yaml
spring:
  mvc:
    view:
      prefix: /
      suffix: .jsp
~~~

   **注意**:由于内嵌tomcat默认不支持页面热部署的功能需要添加相关的配置

**springboot配置jsp热部署**

~~~yaml
server:
  jsp-servlet:
    init-parameters:
      development: true
~~~

## 7.lombok    

###7.1.概念 

使java代码变的更加优雅,以注解的方式代替之前的冗长代码,底层采用字节码技术生成相应方法

### 7.2.使用lombok    

- 1.下载lombok相关插件    

  File ----> Settings ----> Plugins ----> 搜索lombok ----> download    

- 2.下载lombok的jar    

~~~xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.4</version>
    <scope>provided</scope>
</dependency>
~~~

- 常用注解

  ~~~xml
  @Data 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、
  toString 方法
  @Setter ：注解在属性上；为属性提供 setting 方法
  @Getter ：注解在属性上；为属性提供 getting 方法
  @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
  @NoArgsConstructor ：注解在类上；为类提供一个无参的构造方法
  @AllArgsConstructor ：注解在类上；为类提供一个全参的构造方法
  @NonNull : 如果给参数加个这个注解 参数为null会抛出空指针异常
  @Value : 注解和@Data类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法.
  ~~~


## 8.MBG(mybatis-generator)    

###8.1.相关介绍 

**mybatis的一个插件,根据创建数据库表,生成实体类,dao,mapper文件**

###8.2.使用    

**1.导入jar**    

~~~XML
<!--导入MBG依赖-->
<dependency>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-core</artifactId>
	<version>1.3.5</version>
</dependency>
~~~

**2.导入插件**

~~~~xml
<!--导入MBG插件-->
<plugin>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.3.5</version>
</plugin>
~~~~

2.添加generatorConfig.xml配置文件    

**注意：名字必须叫generatorConfig.xml**

~~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--数据库驱动-->
    <classPathEntry location="D:\后期项目\code\cmfz_zhangcn\WebRoot\WEB-INF\lib\mysql-connector-java-5.1.38.jar"/>
    <context id="mysql" targetRuntime="MyBatis3">
        <!--是否忽略注释的生成 true -->
        <commentGenerator>
            <!-- 这个元素用来去除指定生成的注释中是否包含生成的日期 false:表示包含 -->
            <!-- 如果生成日期，会造成即使修改一个字段，整个实体类所有属性都会发生变化，不利于版本控制，所以
            设置为true -->
            <property name="suppressDate" value="true" />
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
        <!--数据库链接地址账号密码-->
        <!--数据库链接URL，用户名、密码 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/cmfz"
                        userId="root"
                        password="root">
        </jdbcConnection>
        <!--生成Model类存放位置 entity-->
        <javaModelGenerator targetPackage="com.baizhi.entity"
                            targetProject="./src/main/java">
            <!--是否合并-->
            <property name="enableSubPackages" value="true"/>
            <!--去除空格-->
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="com.baizhi.mapper"
                         targetProject="./src/main/resources">
            <!---->
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!--生成Dao类存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.baizhi.dao"
                             targetProject="./src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!--生成对应表及类名-->
        <table tableName="t_emp" domainObjectName="Emp" ></table>
        <table tableName="t_user" domainObjectName="Student" ></table>
    </context>
</generatorConfiguration>
~~~~

## 9.springboot细节

### 9.1.乱码

1.由于springboot启动其中已经配置了编码过滤器,源码如下,并且默认值为UTF-8,所以不需要配置

```java
	@Bean
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}
```

2.如果配置配置项如下

```yaml
spring:
  http:
    encoding:
      charset: UTF-8
      force: true
```

### 9.2.文件上传

1.springboot默认装配了文件处理器,源码如下  默认单个文件大小为1MB,一次请求携带的文件大小为10MB

```java
@Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	@ConditionalOnMissingBean(MultipartResolver.class)
	public StandardServletMultipartResolver multipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		multipartResolver.setResolveLazily(this.multipartProperties.isResolveLazily());
		return multipartResolver;
}
```

2.自定义配置

```yaml
spring:
  http:
    multipart:
      max-file-size: 10MB
      max-request-size: 100MB
```

### 9.3.日期格式如何接收

1.方法1:在实体类上加上注解

```java
@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
```

2.方法2:全局配置,配置yaml

```yaml
#配置Jackson时间格式转换
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
```

### 9.4.静态资源处理

1.不需要处理

get请求优先找静态资源,找controller

post请求直接找controller

### 9.5.测试

1.导入测试相关的启动器,默认集成的junit所以无需再次导入junit的jar

```xml
<!--测试支持的jar-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <!-- 只在test测试里面运行 -->
    <scope>test</scope>
</dependency>
```

2.注解

```java
@Runwith(SpringRunner.class)   //启动spring的工厂
@SpringBootTest(classes = App.class)      //可以不写，自动配置，项目中只能有一个maven函数
//开启自动配置,并且装配项目中相关bean,如dao,service,controller
```

### 9.6.fastjson的集成      将类型转换器交由工厂管理

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.54</version>
</dependency>
```

- 2.相关注解

```java
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@JSONField(format = "yyyy-MM-dd HH:mm:ss")
```

## 10.通用Mapper

> 通用 Mapper4 是一个可以实现任意 MyBatis 通用方法的框架，项目提供了常规的增删改查操作以及`Example` 相关的单表操作。通用 Mapper 是为了解决 MyBatis 使用中 90% 的基本操作，使用它可以很方便的进行开发，可以节省开发人员大量的时间。 

### 10.1MBG与通用Mapper

| MBG                     | 通用mapper   |
| ----------------------- | ---------- |
| 根据数据库库表生成实体类，Dao,mapper | 根据实体类生成sql |
|                         |            |

### 10.2引入依赖

```xml
<!-- https://mvnrepository.com/artifact/tk.mybatis/mapper-spring-boot-starter -->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
```

### 10.3引入数据源及数据库的驱动

~~~xml
<!--数据源的jar包-->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>

<!--数据库驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.38</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.4</version>
    <scope>provided</scope>
</dependency>

~~~

### 10.4配置链接相关参数

~~~yamL
server:
  port: 8989
  context-path: /springboot
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/cmfz
    username: root
    password: root
mybatis:
  type-aliases-package: com.baizhi.entity
~~~

### 10.5继承Mapper

~~~java
package com.baizhi.dao;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;
public interface UserMapper extends Mapper<User> {
}
~~~

### 10.6引入相关注解

~~~java
@MapperScan("com.baizhi.dao")
~~~

**注意:一定不要倒错包**

~~~java
import tk.mybatis.spring.annotation.MapperScan;
~~~

### 10.7实体类加入相关注解

~~~java
@Table(name = "t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer id;
    @Column(name="user_name")
    private String name;
    private Integer age;
    private Date  bir;
    @Transient
    private String sex;
}
~~~

**注解解析**

~~~java
@Table(name = "t_user") //写在类上 指定实体类映射的数据库的表名
@Id    //写在属性上 表示这个属性对应的是数据库表的主键字段
@Column(name="user_name")  //写在属性上 如果实体属性名与数据库字段不一致，表示这个属性映射的数据库字段名
@Transient     //写在属性上  表示该属性在数据库不存在
~~~

## 11.springboot集成logback    

### 1.1.log4j.properties    

~~~properties
log4j.rootLogger = ERROR,bb
log4j.appender.bb = org.apache.log4j.ConsoleAppender
log4j.appender.bb.layout = org.apache.log4j.PatternLayout
log4j.appender.bb.layout.conversionPattern = %d{yyyy-MM-dd} %t %c [%p] %m%n
log4j.logger.com.baizhi.dao = DEBUG
~~~

- 1.日志的级别:debug---info---warn--error 日志级别越低打印日志量越多,越详细 
- 2.日志的分类: 父日志(根日志) 控制全局日志 子日志 某个包中的日志 子日志优先级高于父日志 
- 3.Appender 为日志输出目的地，Log4j提供的appender有以下几种    

~~~JAVA
org.apache.log4j.ConsoleAppender（控制台）， org.apache.log4j.FileAppender（文件）， org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件）， org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）， org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）
~~~

- 4.Layout：日志输出格式，Log4j提供的layout有以下几种：    

~~~JAVA
org.apache.log4j.HTMLLayout（以HTML表格形式布局）， org.apache.log4j.PatternLayout（可以灵活地指定布局模式）， org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串）， org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）  
~~~

- 5.打印参数: Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，如下:    

~~~JAVA
%m 输出代码中指定的消息
%p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
%r 输出自应用启动到输出该log信息耗费的毫秒数
%c 输出所属的类目，通常就是所在类的全名
%t 输出产生该日志事件的线程名
%n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
%d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd
HH:mm:ss , SSS}，输出类似：2002年10月18日 22 ： 10 ： 28 ， 921
%l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：
Testlog4.main(TestLog4.java: 10 )
~~~

###1.2.logback.xml 

1.由于springboot集成了logback所以不需要引入jar直接导入配置文件即可    

~~~JAVA
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <!--代表日志展示的位置
    name: 输出的别名
    class:以哪种方式输出
    -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--用来指定日志的展示格式-->
        <layout class="ch.qos.logback.classic.PatternLayout"><pattern>[%p] [%d{yyyy-MM-dd HH:mm:ss}] %m [%c] %n</pattern>
        </layout>
    </appender>
    <!--项目中根日志 只能出现一次-->
    <root level="warn">
        <!--根日志输出位置-->
        <appender-ref ref="stdout"/>
    </root>
    <!--子日志多个 继承根日志的输出位置-->
    <logger name="com.baizhi.dao" level="debug"/>
</configuration>
~~~


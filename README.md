# Sentinel控制台

## 1. 概述

Sentinel 控制台是流量控制、熔断降级规则统一配置和管理的入口，它为用户提供了机器自发现、簇点链路自发现、监控、规则配置等功能。在 Sentinel 控制台上，我们可以配置规则并实时查看流量控制效果。

因为sentinel源码太过庞大，所以该项目将sentinel-dashboard源码抽取出来，并对其进行改造，将sentinel控制台与nacos进行持久化。

## 2. 功能描述
目前支持流控规则和降级规则的持久化
1. 流控规则nacos配置文件需要命名为 appName(应用名)+“-flow-rule”
2. 降级规则nacos配置文件需要命名为 appName(应用名)+“-degrade-rule”
3. 配置文件中nacos的配置项设置成你的nacos信息，以及应用端需要添加sentinel-datasource-nacos依赖，并进行相应的配置
 
## 3. 启动

> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。

借鉴如下命令启动控制台：

```
java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8858  -Dproject.name=sentinel-dashboard -jar D:\XXX\target\sentinel-dashboard.jar  
```
其中 -Dserver.port=8080 用于指定 Sentinel 控制台端口为 8080。

同时需要根据您的nacos信息来配置以下配置项

![nacos配置](/img/imgimage.png)

从 Sentinel 1.6.0 起，Sentinel 控制台引入基本的登录功能，默认用户名和密码都是 sentinel。可以参考 [鉴权模块文档](https://github.com/alibaba/Sentinel/wiki/%E6%8E%A7%E5%88%B6%E5%8F%B0#%E9%89%B4%E6%9D%83) 配置用户名和密码。

> 注：若您的应用为 Spring Boot 或 Spring Cloud 应用，您可以通过 Spring 配置文件来指定配置，详情请参考 [Spring Cloud Alibaba Sentinel](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel) 文档。

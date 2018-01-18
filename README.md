# 介绍
## 概述
### 1.stresstest 为基础压力测试程序，支持 http + json 与 自定义 api（api测试需要实现org.simple.invoke.api.Api接口） 2种测试方式，通过config.yml 进行配置
### 2.程序实现的主要功能
&emsp;2.1 外部jar的加载
&emsp;2.2 读取配置创建并运行对应的测试案例、线程与请求数
&emsp;2.3 收集结果与统计
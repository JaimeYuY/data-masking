# 数据脱敏实现实例
## 实现思路
1. 自定义脱敏的注释，包含：脱敏方法和脱敏字段
2. 使用AOP去拦截，处理需要脱敏的方法的需要脱敏的字段

## 数据脱敏演示
1. 下载源代码
2. 初始化sql
3. 修改配置文件的数据库连接配置
4. 运行com.ocean.angel.tool.controller.UserInfoControllerTest.save()方法，入库数据如下图：
5. 运行com.ocean.angel.tool.controller.UserInfoControllerTest.get(),查询脱敏数据，截图如下：

## 使用指南
1. 在方法上添加脱敏注解@SensitiveMethod
2. 在返回的实体类的敏感字段添加@MaskField注解，注解参数SensitiveType，根据数据内容自行选择
3. 运行注解方法，查询返回数据
# 微服务架构模式的切换

- 常规微服务架构模式 
- all-in-one架构模式（将大部分模块集成到ruoyi-system模块里）

### 常规微服务架构

1. 确保ruoyi-system模块的maven依赖里，**没有**引入其他模块的依赖，需要检查的依赖如下：
    - ruoyi-auth（鉴权模块）
    - ruoyi-file（文件模块）
    - ruoyi-job（定时任务模块）
    - 其他后续追加的业务模块...
2. nacos配置使用**GROUP_ALL_IN_1**分组
3. ruoyi-system模块的入口启动类使用`com.ruoyi.system.RuoYiSystemApplication`
4. 注解掉ruoyi-system模块里其他模式的入口启动类
5. 【可选】ruoyi-system模块的`bootstrap.yml`里，关闭配置项：`spring.main.allow-bean-definition-overriding: false`

### all-in-one架构模式

1. 确保ruoyi-system模块的maven依赖里，**引入了**其他模块的依赖，需要检查的依赖如下：
    - ruoyi-auth（鉴权模块）
    - ruoyi-file（文件模块）
    - ruoyi-job（定时任务模块）
    - 其他后续追加的业务模块...
2. nacos配置使用**DEFAULT_GROUP**分组
3. ruoyi-system模块的入口启动类使用`com.ruoyi.SystemAllApplication`
4. 注解掉ruoyi-system模块里其他模式的入口启动类
5. ruoyi-system模块的`bootstrap.yml`里，确保开启配置项：`spring.main.allow-bean-definition-overriding: true`

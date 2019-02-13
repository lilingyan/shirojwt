shiro jwt demo
---

#介绍
shiro首先会去session中获取登录状态标记，如果没有，则去登录。
登录如果成功，再保存登录标记到session。  
如果在realm中去校验jwt，那前文shiro第一步每次都会被执行(无效操作)
而且realm中的doGetAuthenticationInfo方法也无法使用缓存机制。

此demo遵照shiro逻辑
在第一次创建subject的时候，判断tokn，如果正确，直接进入。

#启动
直接运行ShiroJwtApp.java启动项目(spring boot)。  

#使用
默认访问 http://localhost:8080/swagger-ui.html  
demo样例不需要账号密码 直接访问**login**登录接口(校验逻辑自己实现)  
登录成功 在**Response Headers**中会返回**authorization**。  
**authorization**中就是生成的jwt的token(默认有效时长30s)  
>登录成功后，可以访问**get**接口做验证，如果成功，则返回*我是保护数据*，
>否则，返回*你还没登陆*
>(访问任意需要认证的接口，都会在返回的时候带上新生成的jwt的token)
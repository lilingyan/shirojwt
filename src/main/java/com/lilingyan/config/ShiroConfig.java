package com.lilingyan.config;

import com.lilingyan.shiro.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lilingyan
 * @Date 2018/10/16 14:42
 */
@Configuration
public class ShiroConfig {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AllowAllRealm realm() {
        AllowAllRealm realm = new AllowAllRealm();
        realm.setCredentialsMatcher(new AllowAllCredentialsMatcher());
        return realm;
    }

    @Bean
    public DefaultSecurityManager securityManager(Realm realm) {
        DefaultSecurityManager securityManager = new DefaultJwtWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSubjectFactory(new DefaultJwtWebSubjectFactory());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/gologin");

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();

        //登录
        filterChainDefinitionManager.put("/login", "anon");

        //swagger
        filterChainDefinitionManager.put("/swagger-ui.html", "anon");
        filterChainDefinitionManager.put("/favicon.ico", "anon");
        filterChainDefinitionManager.put("/", "anon");
        filterChainDefinitionManager.put("/null/**", "anon");
        filterChainDefinitionManager.put("/csrf", "anon");
        filterChainDefinitionManager.put("/v2/api-docs", "anon");
        filterChainDefinitionManager.put("/configuration/**", "anon");
        filterChainDefinitionManager.put("/swagger-resources/**", "anon");
        filterChainDefinitionManager.put("/webjars/**", "anon");
        filterChainDefinitionManager.put("/images/**", "anon");

        filterChainDefinitionManager.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        return shiroFilterFactoryBean;
    }

}
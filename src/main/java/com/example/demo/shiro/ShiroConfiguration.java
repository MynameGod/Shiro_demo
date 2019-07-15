package com.example.demo.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program:demo
 * @description:Shiro 配置类
 * @author: whh
 * @create: 2019-07-15 14:13
 */
@Configuration
public class ShiroConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录页面地址
        shiroFilterFactoryBean.setLoginUrl("/notlogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置没有权限的地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        //过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //登出
        filterChainDefinitionMap.put("/logout", "logout");
        //游客
        filterChainDefinitionMap.put("/guest/**", "anon");
        //用户
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        //管理员
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");

        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        //拦截剩余所有url,次剧代码完成其他设置之后编写,否则将拦截所有url.
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        logger.info("拦截器工厂类注册成功.....");
        return shiroFilterFactoryBean;
    }
    /*
    注入SecurityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    //开启shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

}

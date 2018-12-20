package com.toughguy.educationSystem.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.toughguy.educationSystem.security.AccountRealm;
import com.toughguy.educationSystem.security.CustomRealmAuthenticator;
import com.toughguy.educationSystem.security.SystemRealm;

@Configuration
public class ShiroConfig {
	
	@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login");
        bean.setLoginUrl("/unauth");
        bean.setUnauthorizedUrl("/403");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/loginWX", "anon"); //表示可以匿名访问(小程序)
        filterChainDefinitionMap.put("/captcha","anon");
        filterChainDefinitionMap.put("/common/**", "anon"); 
        filterChainDefinitionMap.put("/default/**","anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**","anon");
      //小程序匿名访问
        filterChainDefinitionMap.put("/wechat/**","anon");
        filterChainDefinitionMap.put("/message/**","anon");
//        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
	//SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
	//-- 配置核心安全事务管理器
	@Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setAuthenticator(customRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(systemRealm());
        realms.add(accountRealm());
        securityManager.setRealms(realms);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
	 /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
    	CustomRealmAuthenticator customRealmAuthenticator = new CustomRealmAuthenticator();
    	customRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return customRealmAuthenticator;
    }
	//-- 配置自定义权限认证授权器user
	@Bean(name="systemRealm")
	public SystemRealm systemRealm(){
		SystemRealm systemRealm = new SystemRealm();
		systemRealm.setCredentialsMatcher(credentialsMatcher());
		return systemRealm;
	}
	//-- 配置自定义权限认证授权器account
	@Bean(name="accountRealm")
	public AccountRealm accountRealm(){
		AccountRealm accountRealm = new AccountRealm();
		accountRealm.setCredentialsMatcher(credentialsMatcher());
		return accountRealm;
	}
	//-- 配置密码比较器
	@Bean(name="credentialsMatcher")
	public CredentialsMatcher credentialsMatcher(){
		return new PasswordMatcher();
	}
	
	@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    
  //自定义sessionManager   
    @Bean
    public SessionManager sessionManager() {  
        MySessionManager mySessionManager = new MySessionManager();  
        mySessionManager.setGlobalSessionTimeout(-1000);
        return mySessionManager;  
    }  
}

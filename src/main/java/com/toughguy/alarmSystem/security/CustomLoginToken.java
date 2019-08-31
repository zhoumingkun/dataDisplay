package com.toughguy.alarmSystem.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义shiro-token重写类，用于多类型用户校验
 * @author BOBO
 *
 */
@Configuration
public class CustomLoginToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 4942676327513353914L;
	
	private String loginType;
	
	public CustomLoginToken() {}
	
	public CustomLoginToken(final String username,final String password,final String loginType) {
		super(username,password);
		this.loginType = loginType;
	}
	
	public String getLoginType() {
		return loginType;
	}
	
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}


package com.toughguy.educationSystem.security;


import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.toughguy.educationSystem.model.authority.Operation;
import com.toughguy.educationSystem.model.authority.User;
import com.toughguy.educationSystem.service.authority.prototype.IAuthorityService;
import com.toughguy.educationSystem.service.authority.prototype.IUserService;

/**
 * 管理员realm
 * @author BOBO
 *
 */
public class SystemRealm extends AuthorizingRealm{
	
	@Autowired
	@Lazy	//-- 为解决shiro和redis框架的冲突而添加
	private IUserService userService;
	
	@Autowired
	@Lazy
	private IAuthorityService authService;
	
	
	/**
	 * 编写认证代码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//				logger.info("开始User身份认证..");
		        CustomLoginToken userToken = (CustomLoginToken)token;
		        String userName =  userToken.getUsername();
		        User user = userService.findByUserName(userName);

		        if (user == null) {
		            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
		            throw new UnknownAccountException("用户不存在！");
		        }

		        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
		        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		                user, //用户信息
		                user.getUserPass(), //密码
		                getName() //realm name
		        );
		        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName())); //设置盐

		        return authenticationInfo;
	}
	
	/**
	 * 编写授权代码
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//-- 编写授权代码
		//-- 以下的代码是测试代码，假设所有的用户都会有"user:list"的权限
		//-- 在实际的开发中，我们会自己写好user-role-permission模块，然后从数据库中查询，用户的权限
		//-- 并可以赋予用户权限
		String userName = (String)principals.fromRealm(getName()).iterator().next();
		SimpleAuthorizationInfo info = null;
		if(userName != null && !"".equals(userName)){
			info = new SimpleAuthorizationInfo();
			User user  = userService.findByUserName(userName);
			List <Operation> oprList = authService.findOperationsByUserId(user.getId());
			for (Operation o:oprList){
				String []permission = o.getPermission().split(",");
				for(String s:permission){
					info.addStringPermission(s);
				}
			}
		}
		return info;
		
	}
	
}

package com.toughguy.educationSystem.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toughguy.educationSystem.model.content.Account;
import com.toughguy.educationSystem.service.content.impl.AccountServiceImpl;
import com.toughguy.educationSystem.service.content.prototype.IAccountService;

/**
 * 学生用户realm
 * @author BOBO
 *
 */
public class AccountRealm extends AuthorizingRealm{

	private static final Logger logger =  LoggerFactory.getLogger(AccountRealm.class);
	
	@Autowired
	private IAccountService accountService;
	
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        CustomLoginToken userToken = (CustomLoginToken)token;
        String account =  userToken.getUsername(); //获取用户名，默认和login.html中的adminName对应。
        Account acc = accountService.findByAccount(account);
        
        if (acc == null) {
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            throw new UnknownAccountException("用户不存在！");
        }

        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                acc, //用户信息
                acc.getPassword(), //密码
                getName() //realm name
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(acc.getAccount())); //设置盐
        logger.info("返回account认证信息：" + authenticationInfo);
        return authenticationInfo;
    }
	//当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//        logger.info("开始acount权限授权(进行权限验证!!)");
//        if (principals == null) {
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        if(principals.getPrimaryPrincipal() instanceof Admin){
//            Account account = (Account) principals.getPrimaryPrincipal();
//            logger.info("当前account :" + account);
//            authorizationInfo.addRole("Account");
//            //每次都从数据库重新查找，确保能及时更新权限
//            account.setPermissionList(permissionService.findPermissionByAdmin(account.getId()));
//            account.getPermissionList().each {current_Permission ->
//                authorizationInfo?.addStringPermission(current_Permission?.getPermission())
//            }
//            logger.info("当前Admin授权角色：" +authorizationInfo?.getRoles() + "，权限：" + authorizationInfo?.getStringPermissions())
//            return authorizationInfo
//        }
//    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
}

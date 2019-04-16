package com.qp.shiro.realm;

import com.qp.dao.UserMapper;
import com.qp.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入了授权管理");
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();

        //根据主体查询角色   根据角色查权限
        //主体  角色   权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("super");
        authorizationInfo.addStringPermission("user:select");
        authorizationInfo.addStringPermission("user:add");
        authorizationInfo.addStringPermission("user:update");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入了认证管理");
//        String principal = (String)authenticationToken.getPrincipal();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        System.out.println("认证管理中的username："+username);
        if (username == null){
            throw new AccountException("用户名不存在");
        }
        //调用数据库
        //根据username(principal)查询user对象
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println("权限认证中从数据库中查出的user："+user);
        if (user == null){
            throw new UnknownAccountException("用户名不存在");
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}

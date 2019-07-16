package com.example.demo.shiro;

import com.example.demo.Bean.Permission;
import com.example.demo.Bean.Role;
import com.example.demo.Bean.User;
import com.example.demo.Service.RoleService;
import com.example.demo.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @program:demo
 * @description: 自定义授权方法
 * @author: whh
 * @create: 2019-07-15 15:18
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    private final static Logger logger = LoggerFactory.getLogger(CustomRealm.class);
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        User user = (User) principalCollection.getPrimaryPrincipal();
        if(user != null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            Role role = roleService.getById(user.getRole().getRId());
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions){
                simpleAuthorizationInfo.addStringPermission(permission.getPAlias());
            }
            simpleAuthorizationInfo.addRole(role.getRAlias());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户验证执行:" + token.getUsername());
        User user = userService.getByName(token.getUsername(),true);
        if(user == null){
            logger.info("用户["+token.getUsername()+"]不存在");
            throw new AccountException("账户不存在");
        }
        if(user.getStatus() == 0){
            logger.error("用户 { "+token.getUsername()+" } 被禁止登录 ");
            throw new DisabledAccountException("账号已经禁止登录");
        }else{
            user.setUpTime(LocalDate.now().toString());
            System.out.println("效验更新前ROLE："+user.getRole().getRId());
            userService.update(user,true,user.getId());
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CredentialsMatcher());

    }
}

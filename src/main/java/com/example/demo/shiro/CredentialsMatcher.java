package com.example.demo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 * @program:demo
 * @description: 密码效验
 * @author: whh
 * @create: 2019-07-15 17:04
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    private final static Logger logger = LoggerFactory.getLogger(CredentialsMatcher.class);
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        Object tokenCredentials = DigestUtils.md5DigestAsHex((authcToken.getUsername() + String.valueOf(authcToken.getPassword())).getBytes());
        Object accountCredentials = getCredentials(info);
        return accountCredentials.equals(tokenCredentials);
    }

}

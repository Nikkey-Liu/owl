package cn.wan.owl.realm;

import cn.wan.owl.mapper.UserMapper;
import cn.wan.owl.model.NUser;
import cn.wan.owl.model.User;
import cn.wan.owl.service.NUserService;
import cn.wan.owl.service.NUserServiceImpl;
import cn.wan.owl.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


//@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private NUserService nuserServiceImpl;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. Get the account number entered by the user
        String username = (String) token.getPrincipal();
        // 2. Find the user entity from the database through account
        NUser user = nuserServiceImpl.loginQueryUsername(username);
        if (user == null) {
            return null;
        }
        // 3. Identity processing through SimpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        // 4. Other business operations such as user account status verification
//        if (!user.getAvailable()) {
//            throw new AuthenticationException("The account has been disabled");
//        }
        //5.Return identity processing object
        return simpleAuthenticationInfo;
    }
}
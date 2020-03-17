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
        //1.获取用户输入的账号
        String username = (String) token.getPrincipal();
        //2.通过account从数据库中查找到user实体
        NUser user = nuserServiceImpl.loginQueryUsername(username);
        if (user == null) {
            return null;
        }
        //3.通过SimpleAuthenticationInfo做身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        //4.用户账号状态验证等其他业务操作
//        if (!user.getAvailable()) {
//            throw new AuthenticationException("该账号已经被禁用");
//        }
        //5.返回身份处理对象
        return simpleAuthenticationInfo;
    }
}

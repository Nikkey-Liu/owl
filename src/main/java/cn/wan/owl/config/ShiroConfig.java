package cn.wan.owl.config;

import cn.wan.owl.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /**
     * Configure Shiro's web filter to intercept browser requests and pass them to SecurityManager
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean webFilter(@Autowired SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //Configure the interception chain Use LinkedHashMap, because LinkedHashMap is ordered, shiro will intercept according to the order of addition
        // Map<K,V> K refers to the intercepted url V value is whether the url is intercepted
        Map<String, String> filterChainMap = new LinkedHashMap<>(16);

        //authc: All URLs must be authenticated before they can be accessed; anon: All URLs can be accessed anonymously, configure anon first and then configure authc.
//        filterChainMap.put("/login", "anon");
//        filterChainMap.put("/ui/register", "anon");
        filterChainMap.put("/register/**", "anon");
        filterChainMap.put("/login/**", "anon");
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/**", "authc");

        //Set the URL to jump after intercepting the request.
        shiroFilterFactoryBean.setLoginUrl("/login/ui");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //Hand over custom realm to SecurityManager
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public CustomRealm myShiroRealm() {
        return new CustomRealm();
    }
}

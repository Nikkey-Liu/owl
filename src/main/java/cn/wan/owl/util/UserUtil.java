package cn.wan.owl.util;

import cn.wan.owl.model.User;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: wanhongji
 * @Date: 2020/2/8
 */
public class UserUtil {

    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}

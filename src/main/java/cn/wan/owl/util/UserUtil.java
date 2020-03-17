package cn.wan.owl.util;

import cn.wan.owl.model.NUser;
import cn.wan.owl.model.User;
import org.apache.shiro.SecurityUtils;

public class UserUtil {

    public static NUser getCurrentUser() {
        return (NUser) SecurityUtils.getSubject().getPrincipal();
    }
}

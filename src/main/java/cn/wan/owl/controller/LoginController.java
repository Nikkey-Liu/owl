package cn.wan.owl.controller;

import cn.wan.owl.dto.UserLoginDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.model.NUser;
import cn.wan.owl.model.User;
import cn.wan.owl.service.NUserService;
import cn.wan.owl.service.UserService;
import cn.wan.owl.util.UserUtil;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    NUserService nUserServiceimpl;
    @GetMapping("/ui")
    public String ui() {
        return "login-1";
    }

    @PostMapping("/in")
    @ResponseBody
    public Object login(@RequestBody UserLoginDto loginDto ,HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginDto.getUserPasswordToken());
           session.setAttribute("loginUser", UserUtil.getCurrentUser());
         System.out.println("00000000000000000000000000+success");
            return CommonResponse.success();
        } catch (Exception e) {
            System.out.println("00000000000000000000000000");
            return CommonResponse.failed();
        }
    }

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(String account) {
        User user = userService.queryByUserAccount(account);
        System.out.println("json user " + JSON.toJSONString(user));
        return JSON.toJSONString(user);
    }

}

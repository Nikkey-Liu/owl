package cn.wan.owl.controller;

import cn.wan.owl.dto.UserLoginDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.model.User;
import cn.wan.owl.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/ui")
    public String ui() {
        return "login-1";
    }

    @PostMapping("/in")
    @ResponseBody
    public Object login(@RequestBody UserLoginDto loginDto) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginDto.getUserPasswordToken());
            return CommonResponse.success();
        } catch (Exception e) {
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

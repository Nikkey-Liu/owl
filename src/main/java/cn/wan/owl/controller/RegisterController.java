package cn.wan.owl.controller;

import cn.wan.owl.dto.RegisterDto;
import cn.wan.owl.dto.UserLoginDto;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.service.NUserService;
import cn.wan.owl.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    NUserService nUserServiceimpl;
    @GetMapping("/ui")
    public String ui() {
        return "register";
    }
    @PostMapping("/in")
    @ResponseBody
    public Object registe(@RequestBody RegisterDto registerDto , HttpSession session) {

        try {
            System.out.println("register--------");
            nUserServiceimpl.insertNuser(registerDto.getRegisterUserDto());
            System.out.println("register--------  finished");
            return CommonResponse.success();
        } catch (Exception e) {

            return CommonResponse.failed();
        }
    }

}

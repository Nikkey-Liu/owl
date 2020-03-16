package cn.wan.owl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ui")
public class UIController {

    @RequestMapping("/{path}")
    public String path(@PathVariable String path) {
        return path;
    }
}

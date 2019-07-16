package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:demo
 * @description: 登录控制器
 * @author: whh
 * @create: 2019-07-16 14:47
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }
}

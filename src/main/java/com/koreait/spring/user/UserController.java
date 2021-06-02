package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //빈 등록, 주소값과 매핑이 가능
@RequestMapping("/user")//매핑, 클래스 위에 적어주면 1차 주소값
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login")
    public String login(){ return "user/login"; }

    @RequestMapping(value = "/join")
    public String join(){ return "user/join"; }

    @RequestMapping(value = "/join", method= RequestMethod.POST)
    public String join(UserEntity param){
        System.out.println("param : " + param);
        service.join(param);
        return "redirect:/user/login";
    }
}

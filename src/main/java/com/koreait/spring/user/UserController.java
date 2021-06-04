package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller //빈 등록, 주소값과 매핑이 가능
@RequestMapping("/user")//매핑, 클래스 위에 적어주면 1차 주소값
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value="err", required = false, defaultValue="0") int err, Model model){
        switch (err){
            case 1://아이디 없음
                model.addAttribute("errMsg","아이디 확인해라");
                break;
            case 2://비밀번호 틀림
                model.addAttribute("errMsg","비밀번호 확인해라");
                break;
        }
        return "user/login";
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public String login(UserEntity param){

        return "redirect:" + service.login(param);
    }

    @RequestMapping(value = "/join")
    public String join(){ return "user/join"; }

    @RequestMapping(value = "/join", method= RequestMethod.POST)
    public String join(UserEntity param){
        System.out.println("param : " + param);
        service.join(param);
        return "redirect:/user/login";
    }
}

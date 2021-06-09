package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller //빈 등록, 주소값과 매핑이 가능
@RequestMapping("/user")//매핑, 클래스 위에 적어주면 1차 주소값
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login")
    public void login(@RequestParam(value="err", required = false, defaultValue="0") int err, Model model){
        switch (err){
            case 1://아이디 없음
                model.addAttribute("errMsg","아이디 확인해라");
                break;
            case 2://비밀번호 틀림
                model.addAttribute("errMsg","비밀번호 확인해라");
                break;
        }
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public String login(UserEntity param){

        return "redirect:" + service.login(param);
    }

    @RequestMapping(value = "/join")
    public void join(){

    }

    @RequestMapping(value = "/join", method= RequestMethod.POST)
    public String join(UserEntity param){
        System.out.println("param : " + param);
        service.join(param);
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req){
        hs.invalidate();
        String referer = req.getHeader("Referer");//로그아웃 시도한 페이지
        return "redirect:" + referer;
    }
    @RequestMapping("/profile")
    public void profile(){

    }

    @PostMapping(value = "/profile")
    // = @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profile(MultipartFile profileImg){
        return "redirect:" + service.uploadProfile(profileImg);
    }
}

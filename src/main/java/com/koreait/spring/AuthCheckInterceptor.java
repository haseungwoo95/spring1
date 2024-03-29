package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCheckInterceptor implements HandlerInterceptor {
    //controller로 보내기 전에 처리
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    //controller 의 handler가 끝나면 처리
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        UserEntity loginUser = (UserEntity) httpServletRequest.getSession().getAttribute("loginUser");
        if(loginUser == null){
            modelAndView.setViewName("/user/needLogin");
        }
    }

    //view까지 처리한 후
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

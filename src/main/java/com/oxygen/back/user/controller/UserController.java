package com.oxygen.back.user.controller;

import com.oxygen.back.user.model.User;
import com.oxygen.back.user.service.UserService;
import com.oxygen.utils.entity.JsonResponse;
import com.oxygen.utils.entity.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户登录显示页
     * @return 页面显示
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginForm(){
        System.out.println("框架貌似搭建好了！");
        System.out.println("hahaha");
        System.out.println("框架貌似搭建好了！");
        System.out.println("hahaha");
        System.out.println("框架貌似搭建好了！");
        System.out.println("hahaha");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    /**
     * 用户登录处理逻辑
     * @param userName 用户名
     * @param password 密码
     * @return JsonResponse 返回信息
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse login(@RequestParam("user_name") String userName, @RequestParam("password")String password, HttpSession session){
//        System.out.println(userName+password);
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        JsonResponse jsonResponse = userService.login(user,session);

        return jsonResponse;

    }
    @RequestMapping(value = "/quit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse quit(HttpSession httpSession){
        JsonResponse jsonResponse = new JsonResponse();
        if (httpSession.getAttribute(SessionKey.LOGIN_USER_INFO) != null) {
            httpSession.removeAttribute(SessionKey.LOGIN_USER_INFO);
        }
        jsonResponse.setError(false);
        jsonResponse.setMsg("退出成功");
        return jsonResponse;

    }

}

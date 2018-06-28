package com.oxygen.back.user.controller;

import com.oxygen.back.user.model.User;
import com.oxygen.back.user.service.UserService;
import com.oxygen.utils.entity.JsonResponse;
import com.oxygen.utils.entity.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录显示页
     *
     * @return 页面显示
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "user/loginForm";
    }

    /**
     * 用户登录处理逻辑
     *
     * @param userName 用户名
     * @param password 密码
     * @return JsonResponse 返回信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse login(@RequestParam("user_name") String userName, @RequestParam("password") String password, HttpSession session) {
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        return userService.login(user, session);

    }

    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse quit(HttpSession httpSession) {
        JsonResponse jsonResponse = new JsonResponse();
        if (httpSession.getAttribute(SessionKey.LOGIN_USER_INFO) != null) {
            httpSession.removeAttribute(SessionKey.LOGIN_USER_INFO);
        }
        jsonResponse.setError(false);
        jsonResponse.setMsg("退出成功");
        return jsonResponse;

    }

}

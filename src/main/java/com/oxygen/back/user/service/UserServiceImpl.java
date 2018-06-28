package com.oxygen.back.user.service;


import com.oxygen.back.user.dao.UserMapper;
import com.oxygen.back.user.model.User;
import com.oxygen.back.user.model.UserExample;
import com.oxygen.utils.entity.JsonResponse;
import com.oxygen.utils.entity.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName()).andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null) {
            return users.get(0);
        }
        return null;


    }


    @Override
    public JsonResponse login(User user, HttpSession session) {
        User theUser = getUser(user);
        JsonResponse<Map<String, Object>> jsonResponse = new JsonResponse<>();

        if (theUser != null) {
            session.setAttribute(SessionKey.LOGIN_USER_INFO, user);
            jsonResponse.setError(false);
            jsonResponse.setMsg("登录成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("user", user);
            dataMap.put("markUrl", session.getAttribute("markUrl"));
            jsonResponse.setData(dataMap);

        } else {
            jsonResponse.setMsg("登录失败，失败原因：用户名或者密码错误");
        }
        return jsonResponse;
    }


}

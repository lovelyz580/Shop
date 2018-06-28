package com.oxygen.back.user.service;


import com.oxygen.back.user.model.User;
import com.oxygen.utils.entity.JsonResponse;

import javax.servlet.http.HttpSession;


public interface UserService {
    /**
     * 获得用户
     *
     * @return 单个用户实体
     */
    User getUser(User user);

    /**
     * 登录操作
     *
     * @return 登录状态信息
     */
    JsonResponse login(User user, HttpSession session);

}

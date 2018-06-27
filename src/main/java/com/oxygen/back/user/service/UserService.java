package com.oxygen.back.user.service;


import com.oxygen.back.user.model.User;
import com.oxygen.utils.entity.JsonResponse;

import javax.servlet.http.HttpSession;


public interface UserService {
    User getUser(User user);
    JsonResponse login(User user, HttpSession session);

}

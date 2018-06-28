package com.oxygen.utils.interceptor;

import com.oxygen.utils.entity.SessionKey;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        //放行exceptUrls中配置的url
        if (exceptUrls != null) {
            for (String oneUrl : exceptUrls) {
                if (url.endsWith("/**")) {
                    if (url.startsWith(oneUrl.substring(0, url.length() - 3))) {
                        return true;
                    }
                } else if (url.startsWith(oneUrl)) {
                    return true;
                }
            }
        }
        if (session.getAttribute(SessionKey.LOGIN_USER_INFO) == null) {
            if (url.equals(request.getContextPath() + "/user/login")) {
                return true;
            } else {
                request.getSession().setAttribute("markUrl", url);
                response.sendRedirect(request.getContextPath() + "/user/login");
                return false;
            }

        } else {
            if (url.equals("/user/login")) {
                response.sendRedirect("/book/get");
                return false;
            } else {
                return true;
            }
        }

    }


}

package com.wsj.tools;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static String getCookieValue(Cookie[] cookies, String key) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }


}

package com.blog.util;

import org.springframework.util.DigestUtils;

//实现密码加密
public class PasswordUtil {
    public static String toMD5(String password)
    {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}

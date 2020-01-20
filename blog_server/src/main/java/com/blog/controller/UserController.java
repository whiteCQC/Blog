package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @param user 包含user的{注册邮箱email，用户名name，密码password}
     * @return 成功则返回新生成的user信息，要包括{用户ID，用户名}以及token信息（参考tell项目），否则返回错误的描述description
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
//        User user= new com.blog.model.User();
//        user.setUname("aaa");
//        user.setPassword("123456");
//        user.setEmail("dxy@12345.com");
//        user.setBirth(new Date());
//        user.setGender("M");
        if(userService.createUser(user))
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("uid",user.getUid());
            map.put("uname",user.getUname());
            return Result.success(map);
        }
        else
        {
            return Result.error("邮箱已被使用");
        }

    }

    /**
     *
     * @param userInfo  登录的信息，包括登录邮箱以及密码
     * @return  返回相应结果，成功则返回包括{用户名，用户ID},以及token信息
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody User userInfo)
    {
        User user = userService.getUserByEmail(userInfo.getEmail());
        if(user == null)
        {
            return Result.error("用户不存在");
        }
        else
        {
            if(user.getPassword().equals(userInfo.getEmail()))
            {
                HashMap < String, Object > map = new HashMap<>();
                map.put("uid", user.getUid());
                map.put("uname",user.getUname());
                return Result.success(map);
            }
            else
            {
                return Result.error("密码不正确");
            }
        }
    }

    /**
     *
     * @param userInfo 包含一个用户的id
     * @return 返回用户的信息
     */
    @PostMapping("/userInfo")
    public Result GetUserInfo(@RequestBody User userInfo){

        User user=userService.getUserById(userInfo.getUid());
        if(user==null){
            return Result.error("用户不存在");
        }else{
            return Result.success(user);
        }

    }
}

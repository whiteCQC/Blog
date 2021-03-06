package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     *
     * @param user 包含user的{注册邮箱email，用户名name，密码password}
     * @return 成功则返回新生成的user信息，要包括{用户ID，用户名}以及token信息（参考tell项目），否则返回错误的描述description
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(userService.createUser(user).equals(true))
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("uid",user.getUid());
            map.put("uname",user.getUname());
            String token = request.getHeader("token");
            map.put("token",token);
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
            if(user.getPassword().equals(PasswordUtil.toMD5(userInfo.getPassword())))
            {
                HashMap < String, Object > map = new HashMap<>();
                map.put("uid", user.getUid());
                map.put("uname",user.getUname());
                String token = request.getHeader("token");
                map.put("token",token);
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
    public Result getUserInfo(@RequestBody User userInfo){

        User user = userService.getUserById(userInfo.getUid());
        if(user == null){
            return Result.error("用户不存在");
        }
        else{
            return Result.success(user);
        }

    }

    /**
     *
     * @param user uid,修改内容包含用户昵称、性别、出生日期
     * @return 修改用户信息
     */
    @PostMapping("/userInfoChange")
    public Result userInfoChange(@RequestBody User user){
        userService.updateUser(user);
        return Result.success("修改成功");
    }

    /**
     *
     * @param user uid,密码
     * @return 修改用户密码
     */
    @PostMapping("/userPasswordChange")
    public Result userPasswordChange(@RequestBody User user){
        userService.updateUser(user);
        return Result.success("密码修改成功");
    }
}

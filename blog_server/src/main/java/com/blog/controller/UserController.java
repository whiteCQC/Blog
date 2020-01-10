package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     *
     * @param user
     * @return 用户的登录信息，成功则返回用户信息，失败返回失败信息
     */
    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody UserInfo user){
        //TODO

        return null;
    }

    /**
     *
     * @param user
     * @return 用户注册的情况，成功/失败（失败原因）
     */
    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody UserInfo user){
        //TODO

        return null;
    }

    /**
     *
     * @param user
     * 用户登出
     */
    @PostMapping("/userLogout")
    public void userLogout(@RequestBody UserInfo user){
        //TODO
    }

    /**
     *
     * @return 修改密码
     */
    @PostMapping("/pwChange")
    public Result passwordChange(@RequestBody UserInfo user){
        //TODO
        return null;
    }

    /**
     *
     * @param user
     * @return 修改个人信息
     */
    @PostMapping("/proChange")
    public Result profileChange(@RequestBody UserInfo user){
        //TODO

        return null;
    }



}

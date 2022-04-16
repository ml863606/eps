package com.mxl.eps.controller;

import com.mxl.eps.model.UsersModel;
import com.mxl.eps.model.common.ResultModel;
import com.mxl.eps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MXL
 * @date 22:13
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/")
    public ResultModel addUser(@RequestBody UsersModel user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ResultModel listUsers(){
        List<UsersModel> users = userService.listUsers();
        return ResultModel.okData(users);
    }

    @GetMapping("/{id}")
    public ResultModel getUserById(@PathVariable(value = "id") int userId){
        UsersModel userById = userService.getUserById(userId);
        return ResultModel.okData(userById);
    }
}

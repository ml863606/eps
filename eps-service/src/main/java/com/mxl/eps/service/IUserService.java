package com.mxl.eps.service;

import com.mxl.eps.model.UsersModel;
import com.mxl.eps.model.common.ResultModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author MXL
 * @date 2022/4/15
 **/
public interface IUserService extends UserDetailsService {
    ResultModel addUser(UsersModel user);

    List<UsersModel> listUsers();

    UsersModel getUserById(int id);
}

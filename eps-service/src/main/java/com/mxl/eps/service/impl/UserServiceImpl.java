package com.mxl.eps.service.impl;

import com.mxl.eps.mapper.UsersMapper;
import com.mxl.eps.model.UsersModel;
import com.mxl.eps.model.common.ResultModel;
import com.mxl.eps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MXL
 * @date 22:14
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails)usersMapper.getUserByUserName(username);
    }

    @Override
    public ResultModel addUser(UsersModel user) {
        usersMapper.insert(user);
        return ResultModel.ok();
    }

    @Override
    public List<UsersModel> listUsers() {
        return usersMapper.selectList(null);
    }

    @Override
    public UsersModel getUserById(int id) {
        return usersMapper.selectById(id);
    }
}

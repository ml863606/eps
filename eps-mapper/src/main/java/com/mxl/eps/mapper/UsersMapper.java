package com.mxl.eps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxl.eps.model.UsersModel;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends BaseMapper<UsersModel> {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return UsersModel
     */
    UsersModel getUserByUserName(@Param("username") String username);

}
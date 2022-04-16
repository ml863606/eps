package com.mxl.eps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@TableName(value = "users")
public class UsersModel implements UserDetails {
    /**
    * 主键(自动自增)
    */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 用户名
    */
    private String username;

    /**
     * 用户姓名
     */
    private String name;

    /**
    * 密码
    */
    private String password;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 账号是否被锁定   1:已锁定   2:未锁定
    */
    private Integer accountLocked;

    /**
    * 账号是否过期    1:已过期   2:未过期
    */
    private Integer accountExpired;

    /**
     * 账号是否被禁用   1:已禁用  2:未禁用
     */
    private Integer enabled;

    /**
    * 账号是否被删除   1:已删除  2:未删除
    */
    private Integer deleted;

    /**
     * 账号是否过期   1:已过期  2:未过期
     */
    private Integer credentialsExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired == 2 ? true : false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked == 2 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpired == 2 ? true : false;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 2 ? true : false;
    }
}
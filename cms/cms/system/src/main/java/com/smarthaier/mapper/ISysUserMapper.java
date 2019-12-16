package com.smarthaier.mapper;

import com.smarthaier.domain.SysUser;
public interface ISysUserMapper {
    SysUser selectUserByLoginName(String username);

    SysUser selectUserByPhoneNumber(String phonenumber);

    SysUser selectUserByEmail(String email);

    void updateUser(SysUser user);
}

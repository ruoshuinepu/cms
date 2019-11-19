package com.smarthaier.service;

import com.smarthaier.domain.SysUser;

public interface ISysUserService {
    SysUser selectUserByLoginName(String username);

    SysUser selectUserByPhoneNumber(String username);

    SysUser selectUserByEmail(String username);

    void updateUserInfo(SysUser user);
}

package com.smarthaier.service.impl;

import com.smarthaier.domain.SysUser;
import com.smarthaier.mapper.ISysUserMapper;
import com.smarthaier.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISysUserServiceImpl implements ISysUserService {

    @Autowired
    ISysUserMapper iSysUserMapper;

    public SysUser selectUserByLoginName(String username) {
        SysUser sysUser = iSysUserMapper.selectUserByLoginName(username);
        return sysUser;
    }

    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        SysUser sysUser = iSysUserMapper.selectUserByPhoneNumber(phoneNumber);
        return sysUser;
    }

    public SysUser selectUserByEmail(String email) {
        SysUser sysUser = iSysUserMapper.selectUserByEmail(email);
        return sysUser;
    }

    public void updateUserInfo(SysUser user) {
        iSysUserMapper.updateUser(user);
    }
}

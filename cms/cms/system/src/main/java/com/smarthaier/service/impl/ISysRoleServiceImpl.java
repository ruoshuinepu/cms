package com.smarthaier.service.impl;

import com.smarthaier.domain.SysRole;
import com.smarthaier.mapper.ISysRoleMapper;
import com.smarthaier.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ISysRoleServiceImpl implements ISysRoleService {

    @Autowired
    ISysRoleMapper iSysRoleMapper;

    public Set<String> selectRoleKeys(long userId) {
        List<SysRole> sysRoleList = iSysRoleMapper.selectRoleKeys(userId);
        Set<String> roleSet = new HashSet<>();
        sysRoleList.forEach(sysRole -> {
            if(sysRole!=null){
                roleSet.addAll(Arrays.asList(sysRole.getRoleKey().trim().split(",")));
            }

        });
        return roleSet;
    }
}

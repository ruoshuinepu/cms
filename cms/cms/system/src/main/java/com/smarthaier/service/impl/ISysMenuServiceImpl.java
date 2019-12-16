package com.smarthaier.service.impl;

import com.smarthaier.common.utils.StringUtils;
import com.smarthaier.domain.SysMenu;
import com.smarthaier.mapper.ISysMenuMapper;
import com.smarthaier.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ISysMenuServiceImpl implements ISysMenuService {
    @Autowired
    ISysMenuMapper iSysMenuMapper;
    public Set<String> selectPermsByUserId(long userId) {
        List<SysMenu> sysMenuList = iSysMenuMapper.selectPermsByUserId(userId);
        Set<String> menuSet = new HashSet<>();
        for(SysMenu sysMenu:sysMenuList){
            if(StringUtils.isNotNull(sysMenu)){
                menuSet.addAll(Arrays.asList(sysMenu.getPerms().trim().split(",")));
            }
        }
        return menuSet;
    }
}

package com.smarthaier.mapper;

import com.smarthaier.domain.SysRole;

import java.util.List;

public interface ISysRoleMapper {
    List<SysRole> selectRoleKeys(long userId);
}

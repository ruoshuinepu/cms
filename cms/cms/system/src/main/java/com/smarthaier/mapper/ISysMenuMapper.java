package com.smarthaier.mapper;

import com.smarthaier.common.annotation.MybatisRepository;
import com.smarthaier.domain.SysMenu;

import java.util.List;
@MybatisRepository
public interface ISysMenuMapper {
    List<SysMenu> selectPermsByUserId(long userId);
}

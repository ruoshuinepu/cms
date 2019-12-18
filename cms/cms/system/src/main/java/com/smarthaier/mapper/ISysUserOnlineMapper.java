package com.smarthaier.mapper;


import com.smarthaier.domain.SysUserOnline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysUserOnlineMapper {

    void deleteOnlineById( @Param("sessionId") String sessionId);

    SysUserOnline selectOnlineById(@Param("sessionId") String sessionId);

    void saveOnline(SysUserOnline online);

    List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);
}

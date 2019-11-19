package com.smarthaier.service;

import com.smarthaier.domain.SysUserOnline;

import java.util.Date;
import java.util.List;

public interface ISysUserOnlineService {
    List<SysUserOnline> selectOnlineByExpired(Date expiredDate);

    void batchDeleteOnline(List<String> needOfflineIdList);
}

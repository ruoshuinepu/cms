package com.smarthaier.service.impl;

import com.smarthaier.domain.SysUserOnline;
import com.smarthaier.service.ISysUserOnlineService;

import java.util.Date;
import java.util.List;

public class ISysUserOnlineServiceImpl implements ISysUserOnlineService {
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        return null;
    }

    public void batchDeleteOnline(List<String> needOfflineIdList) {

    }
}

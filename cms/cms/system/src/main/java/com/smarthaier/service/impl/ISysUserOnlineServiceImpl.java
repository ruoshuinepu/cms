package com.smarthaier.service.impl;

import com.smarthaier.common.utils.DateUtils;
import com.smarthaier.common.utils.StringUtils;
import com.smarthaier.domain.SysUserOnline;
import com.smarthaier.mapper.ISysUserOnlineMapper;
import com.smarthaier.service.ISysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ISysUserOnlineServiceImpl implements ISysUserOnlineService {
    @Autowired
    private ISysUserOnlineMapper userOnlineDao;
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineDao.selectOnlineByExpired(lastAccessTime);
    }

    public void batchDeleteOnline(List<String> needOfflineIdList) {

    }

    @Override
    public void deleteOnlineById(String sessionId) {
        SysUserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline))
        {
            userOnlineDao.deleteOnlineById(sessionId);
        }
    }

    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        SysUserOnline sysUserOnline =  userOnlineDao.selectOnlineById(sessionId);
        return sysUserOnline;
    }

    @Override
    public void saveOnline(SysUserOnline online) {
        userOnlineDao.saveOnline(online);

    }
}

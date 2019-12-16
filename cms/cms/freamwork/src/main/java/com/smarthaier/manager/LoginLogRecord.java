package com.smarthaier.manager;

import com.smarthaier.common.utils.AddressUtils;
import com.smarthaier.common.utils.ServletUtils;
import com.smarthaier.common.utils.SpringUtils;
import com.smarthaier.domain.SysUserOnline;
import com.smarthaier.service.ISysUserOnlineService;
import com.smarthaier.shiro.session.OnlineSession;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class LoginLogRecord {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");
    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args){
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {

            }
        };
    }

    /***
     * session同步到数据库
     * @param session
     * @return
     */
    public static TimerTask syncSessionToDb(OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                SpringUtils.getBean(ISysUserOnlineService.class).saveOnline(online);
            }
        };
    }
}

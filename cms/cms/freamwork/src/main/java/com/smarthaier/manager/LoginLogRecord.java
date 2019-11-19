package com.smarthaier.manager;

import com.smarthaier.common.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class LoginLogRecord {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args){
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {

            }
        };
    }

}

package com.smarthaier.shiro.session;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SpringSessionValidationScheduler implements SessionValidationScheduler {
    public boolean isEnabled() {
        return this.enabled;
    }
    private static final Logger log = LoggerFactory.getLogger(SpringSessionValidationScheduler.class);

    public static final long DEFAULT_SESSION_VALIDATION_INTERVAL = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    /**
     * 定时器，用于处理超时的挂起请求，也用于连接断开时的重连。
     */

    @Autowired
    @Qualifier("scheduledExecutorService")
    private ScheduledExecutorService executorService;

    private volatile boolean enabled = false;
    public void setSessionValidationInterval(long sessionValidationInterval)
    {
        this.sessionValidationInterval = sessionValidationInterval;
    }
    /**
     * 会话验证管理器
     */
    @Autowired
    @Qualifier("sessionManager")
    private ValidatingSessionManager sessionManager;

    // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
    @Value("${shiro.user.session.validationInterval}")
    private long sessionValidationInterval;

    public void enableSessionValidation() {
        enabled = true;

        if (log.isDebugEnabled())
        {
            log.debug("Scheduling session validation job using Spring Scheduler with "
                    + "session validation interval of [" + sessionValidationInterval + "]ms...");
        }

        try
        {
            executorService.scheduleAtFixedRate(new Runnable(){
                @Override
                public void run()
                {
                    if (enabled)
                    {
                        sessionManager.validateSessions();
                    }
                }
            }, 1000, sessionValidationInterval * 60 * 1000, TimeUnit.MILLISECONDS);

            this.enabled = true;

            if (log.isDebugEnabled())
            {
                log.debug("Session validation job successfully scheduled with Spring Scheduler.");
            }

        }
        catch (Exception e)
        {
            if (log.isErrorEnabled())
            {
                log.error("Error starting the Spring Scheduler session validation job.  Session validation may not occur.", e);
            }
        }
    }

    public void disableSessionValidation() {
        if (log.isDebugEnabled())
        {
            log.debug("Stopping Spring Scheduler session validation job...");
        }

        if (this.enabled)
        {
            executorService.shutdown();
        }
        this.enabled = false;
    }
}

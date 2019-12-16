package com.smarthaier.shiro.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthaier.common.constant.ShiroConstants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;

public class KickoutSessionFilter extends AccessControlFilter {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 同一个用户最大会话数
     **/
    private int maxSession = -1;

    /**
     * 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
     **/
    private Boolean kickoutAfter = false;

    /**
     * 踢出后到的地址
     **/
    private String kickoutUrl;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }


    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public Boolean getKickoutAfter() {
        return kickoutAfter;
    }

    public void setKickoutAfter(Boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Cache<String, Deque<Serializable>> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Deque<Serializable>> cache) {
        this.cache = cache;
    }
    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager)
    {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USERCACHE);
    }
}

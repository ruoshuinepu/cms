package com.smarthaier.config.shiro;

import com.smarthaier.common.utils.SpringUtils;
import com.smarthaier.shiro.realm.UserRealm;
import com.smarthaier.shiro.session.OnlineSessionDAO;
import com.smarthaier.shiro.session.OnlineSessionFactory;
import com.smarthaier.shiro.session.SpringSessionValidationScheduler;
import com.smarthaier.shiro.web.OnlineWebSessionManager;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ShiroConfig {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    // Session超时时间，单位为毫秒（默认30分钟）
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    // 同一个用户最大会话数
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    // 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    // 验证码开关
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    // 验证码类型
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    // 设置Cookie的域名
    @Value("${shiro.cookie.domain}")
    private String domain;

    // 设置cookie的有效访问路径
    @Value("${shiro.cookie.path}")
    private String path;

    // 设置HttpOnly属性
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    // 设置Cookie的过期时间，秒为单位
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    // 登录地址
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    // 权限认证失败地址
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    @Bean
    public EhCacheManager getCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("smarthaier");
        EhCacheManager em = new EhCacheManager();
        if (StringUtils.isEmpty(cacheManager)) {
            em.setCacheManager(new CacheManager(getCacheManagerConfigFileInputStream()));
        } else {
            em.setCacheManager(cacheManager);
        }
        return em;
    }

    private InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
    public UserRealm userRealm(EhCacheManager cacheManager){
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(getCacheManager());
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(getRememberMe());
        return securityManager;
    }

    private RememberMeManager getRememberMe() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getRememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    private Cookie getRememberMeCookie() {
        Cookie cookie = new SimpleCookie();
        cookie.setDomain(domain);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge*24*60*60);
        cookie.setPath(path);
        return cookie;
    }

    public OnlineWebSessionManager sessionManager(){
        OnlineWebSessionManager sessionManager = new OnlineWebSessionManager();
        sessionManager.setCacheManager(getCacheManager());
        sessionManager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间
        sessionManager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        // 去掉 JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
        sessionManager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        // 是否定时检查session
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        sessionManager.setSessionDAO(sessionDao());
        // 自定义sessionFactory
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

    private SessionFactory sessionFactory() {
        OnlineSessionFactory onlineSessionFactory = new OnlineSessionFactory();
        return  onlineSessionFactory;
    }

    private SessionDAO sessionDao() {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }


}

package com.smarthaier.shiro.service;

import com.smarthaier.common.constant.Constants;
import com.smarthaier.common.constant.ShiroConstants;
import com.smarthaier.common.constant.UserConstants;
import com.smarthaier.common.utils.DateUtils;
import com.smarthaier.common.utils.ServletUtils;
import com.smarthaier.domain.SysUser;
import com.smarthaier.manager.AsyncManager;
import com.smarthaier.manager.LoginLogRecord;
import com.smarthaier.service.ISysUserService;
import com.smarthaier.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.MessageUtils;

@Component
public class LoginService {
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, "", "user.jcaptcha.error"));

        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, "", "not.null"));

        }


        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null && maybeMobilePhoneNumber(username)) {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && maybeEmail(username)) {
            user = userService.selectUserByEmail(username);
        }

        if (user == null) {
            AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, Constants.LOGIN_FAIL, "user.not.exists"));

        }

        if ("1".equals(user.getDelFlag())) {
            AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, Constants.LOGIN_FAIL, "user.password.delete"));

        }

        if ("1".equals(user.getStatus())) {
            AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, Constants.LOGIN_FAIL, "user.blocked", user.getRemark()));

        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(LoginLogRecord.recordLogininfor(username, Constants.LOGIN_SUCCESS, "user.login.success"));
        recordLoginInfo(user);
        return user;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(UserConstants.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}


package com.smarthaier.shiro.realm;

import com.smarthaier.domain.SysUser;
import com.smarthaier.service.ISysMenuService;
import com.smarthaier.service.ISysRoleService;
import com.smarthaier.shiro.service.LoginService;
import com.smarthaier.shiro.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LoginService loginService;
    @Autowired
    ISysRoleService iSysRoleService;
    @Autowired
    ISysMenuService iSysMenuService;

    /***
     * 授权  权限验证
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SysUser sysUser = ShiroUtils.getSysUser();
         // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (sysUser.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = iSysRoleService.selectRoleKeys(sysUser.getUserId());
            menus = iSysMenuService.selectPermsByUserId(sysUser.getUserId());
            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;
    }

    /****
     * 登陆验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        SysUser user = null;
        try {
            user = loginService.login(username, password);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, username, password);

        return simpleAuthenticationInfo;
    }
    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}

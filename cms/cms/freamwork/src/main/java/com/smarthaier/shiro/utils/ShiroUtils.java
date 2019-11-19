package com.smarthaier.shiro.utils;

import com.smarthaier.common.utils.BeanUtils;
import com.smarthaier.common.utils.StringUtils;
import com.smarthaier.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
    public static String getIp() {
        return getSubject().getSession().getHost();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getSysUser() {
        SysUser user = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj)) {
            user = new SysUser();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;

    }
}

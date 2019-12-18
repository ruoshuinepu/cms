package com.smarthaier.web.controller.system;

import com.smarthaier.common.core.controller.BaseController;
import com.smarthaier.domain.SysMenu;
import com.smarthaier.domain.SysUser;
import com.smarthaier.service.ISysMenuService;
import com.smarthaier.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName SysIndexController
 * @Description: TODO
 * @Author 01472754
 * @Date 2019/12/17
 * @Version V1.0
 **/

@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
     /*   // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());*/
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        //mmap.put("version", Global.getVersion());
        return "main";
    }
}

package com.smarthaier.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView test (){
        ModelAndView modelAndView = new ModelAndView("login"); //设置对应JSP的模板文件
        modelAndView.addObject("hi", "Hello,Cat"); //设置${hi}标签的值为Hello,Cat
        return modelAndView;

    }
}

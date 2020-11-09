package com.zhangyu.test.optional.controller;

import com.zhangyu.test.optional.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2020/11/9 10:36
 **/
@Controller
@RequestMapping("test")
public class AdminController {


    @Resource
    private AdminService adminService;

    /**
        JAVA8       Optional
     */
    @RequestMapping("optional")
    @ResponseBody
    public Object   testOptional(){
        HashMap<String, Object> map = adminService.optionalMethod();

        return map;
    }



}

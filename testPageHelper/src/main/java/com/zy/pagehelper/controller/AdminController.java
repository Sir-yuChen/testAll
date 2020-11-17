package com.zy.pagehelper.controller;

import com.github.pagehelper.PageInfo;
import com.zy.pagehelper.VO.PageVO;
import com.zy.pagehelper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @date 2020/11/16 11:27
 **/

@Controller
@RequestMapping("test")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @RequestMapping("/getList")
    @ResponseBody
    public PageInfo<Object> admin(Integer pageNum,Integer pageSize,String name ) {

        PageVO pageVO = new PageVO();
        //创建接收实体 分页参数
        if ( pageNum !=null && pageSize!= null) {
            pageVO.setPageNum(pageNum);
            pageVO.setPageSize(pageSize);
        }
        //调用数据
        PageInfo<Object> roleList =  adminService.getlist(pageVO,name);

        return roleList;
    }



}

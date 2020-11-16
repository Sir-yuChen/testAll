package com.zy.pagehelper.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.pagehelper.VO.PageVO;
import com.zy.pagehelper.dao.AdminMapper;
import com.zy.pagehelper.dao.RoleMapper;
import com.zy.pagehelper.model.Admin;
import com.zy.pagehelper.model.Role;
import com.zy.pagehelper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/11/16 13:40
 **/
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Object> getlist(PageVO pageVO, String name) {


        //条件查询 通过名字进行查 符合的id
       List<Admin> adminlist =  adminMapper.selectByName(name);

       //根据符合条件的id查object表角色表 并实现分页
        /** 1. 组装返回数据
         *  2.根据符合条件的Id查admin_role表得到角色Id
         *  3.查角色表返回对象，将对应的role放到admin中，返回
         *  4.实现分页
         */
        List<Long> longs = new ArrayList<>();

        for (int i = 0; i < adminlist.size(); i++) {
           Long  roleId = adminMapper.selectRoleId(adminlist.get(i).getId());
            longs.add(roleId);
        }
        //组装返回参数           实现分页
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<Role> roles = roleMapper.selectByIds(longs);

        //将返回参数，存到pageHelper  list中
        PageInfo<Object> pageInfo = new PageInfo(roles);
        List<Object> list = new ArrayList<>();

        /**
         * lombok里的Builder注解 简单使用
         * 类似于链式调用，进行给对象赋值
         */
        for (int i = 0; i <roles.size() ; i++) {
            Admin admin = adminlist.get(i);
            Admin admins =  Admin.builder()
                                .id(admin.getId())
                                .name(admin.getName())
                                .email(admin.getEmail())
                                .department(admin.getDepartment())
                                .username(admin.getUsername())
                                .build();
            HashMap<String, Object> map = new HashMap<>();
            map.put(adminlist.get(i).getName(),admins);
            map.put(roles.get(i).getName(),roles.get(i));
            list.add(map);
        }
        pageInfo.setList(list);
        return pageInfo;
    }
}

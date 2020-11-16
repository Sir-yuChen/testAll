package com.zy.pagehelper.service;

import com.github.pagehelper.PageInfo;
import com.zy.pagehelper.VO.PageVO;

/**
 * @author Administrator
 * @date 2020/11/16 11:28
 **/

public interface AdminService {


    PageInfo<Object> getlist(PageVO pageVO, String name);


}

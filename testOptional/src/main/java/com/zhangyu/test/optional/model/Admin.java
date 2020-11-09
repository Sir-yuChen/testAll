package com.zhangyu.test.optional.model;

import lombok.Data;

import java.util.Date;

/**
 * Table: t_cms_admin
 */
@Data
public class Admin {
    /**
     * 主键
     *
     * Table:     t_cms_admin
     * Column:    f_id
     */
    private Integer id;

    /**
     * 登录名
     *
     * Table:     t_cms_admin
     * Column:    f_login_name
     */
    private String loginName;

    /**
     * 密码
     *
     * Table:     t_cms_admin
     * Column:    f_password
     */
    private String password;

    /**
     * 邮箱
     *
     * Table:     t_cms_admin
     * Column:    f_email
     */
    private String email;

    /**
     * 姓名
     *
     * Table:     t_cms_admin
     * Column:    f_name
     */
    private String name;

    /**
     * 电话
     *
     * Table:     t_cms_admin
     * Column:    f_mobile
     */
    private String mobile;

    /**
     * 所属业务部门id
     *
     * Table:     t_cms_admin
     * Column:    f_department_id
     */
    private String departmentId;

    /**
     * 注册日期
     *
     * Table:     t_cms_admin
     * Column:    f_register_date
     */
    private Date registerDate;

    /**
     * 最后更新时间
     *
     * Table:     t_cms_admin
     * Column:    f_last_login_date
     */
    private Date lastLoginDate;

    /**
     * 状态： 0:锁定，1：正常
     *
     * Table:     t_cms_admin
     * Column:    f_status
     */
    private Integer status;

    /**
     * 删除标识： 0：删除，1:正常
     *
     * Table:     t_cms_admin
     * Column:    f_del_flag
     */
    private Integer delFlag;
}
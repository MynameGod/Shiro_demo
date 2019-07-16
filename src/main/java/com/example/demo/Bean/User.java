package com.example.demo.Bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program:demo
 * @description: 用户实体类
 * @author: whh
 * @create: 2019-07-15 15:42
 */
@Data
public class User implements Serializable {
    /*
     *用户ID
     */
    private String id;
    /*
     *用户名
     */
    private String username;
    /*
     *用户邮箱
     */
    private String email;
    /**
     *用户电话
     */
    private String phone;
    /**
    *用户密码
     */
    private String password;
    /*
     *角色
     */
    private Role role;
    /**
     *角色状态
     */
    private Integer status;
    /**
     *创建时间
     */
    private String createData;
    /**
     *更新时间
     */
    private String upTime;
}

package com.example.demo.Bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program:demo
 * @description: 权限实体类
 * @author: whh
 * @create: 2019-07-15 15:53
 */
@Data
public class Permission implements Serializable {
    /**
     * 权限ID
     */
    private String pId;
    /**
     * 权限名称
     */
    private String pName;
    /**
     * 权限别名
     */
    private String pAlias;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String upTime;
}

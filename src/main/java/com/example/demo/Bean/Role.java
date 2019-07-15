package com.example.demo.Bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @program:demo
 * @description: 角色实体类
 * @author: whh
 * @create: 2019-07-15 15:48
 */
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private String rId;
    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String rName;
    /**
     * 角色别称
     */
    @NotNull(message = "角色别名不能为空")
    private String rAlias;
    /**
     * 角色权限列表
     */
    private List<Permission> permissions;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String upDate;
}

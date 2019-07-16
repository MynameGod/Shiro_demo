package com.example.demo.Service.Impl;

import com.example.demo.Bean.Permission;
import com.example.demo.Mapper.PermissionMapper;
import com.example.demo.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:demo
 * @description: 权限实现类
 * @author: whh
 * @create: 2019-07-16 11:16
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission add(Permission permission) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public Permission updata(Permission permission) {
        return null;
    }

    @Override
    public List<Permission> selectAll() {
        return null;
    }
}

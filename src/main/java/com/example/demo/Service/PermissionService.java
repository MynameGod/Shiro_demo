package com.example.demo.Service;

import com.example.demo.Bean.Permission;

import java.util.List;

public interface PermissionService {
    Permission add(Permission permission);

    String delete(String id);

    Permission updata(Permission permission);

    List<Permission> selectAll();
}

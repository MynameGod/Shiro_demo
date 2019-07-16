package com.example.demo.Mapper;


import com.example.demo.Bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:demo
 * @description:
 * @author: whh
 * @create: 2019-07-16 11:03
 */
@Repository
public interface  PermissionMapper {
    Permission add(Permission permission);

    String delete(String id);

    Permission updata(Permission permission);

    List<Permission> selectAll();
}

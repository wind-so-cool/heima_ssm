package com.cool.ssm.service;

import com.cool.ssm.domain.Permission;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 23:01
 */
public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);

    void deleteById(String id);
}

package com.cool.ssm.service;

import com.cool.ssm.domain.Permission;
import com.cool.ssm.domain.Role;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 21:58
 */
public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteById(String id);

    List<Permission> getOtherPermissionsByRoleId(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}

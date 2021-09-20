package com.cool.ssm.service.impl;

import com.cool.ssm.dao.IRoleDao;
import com.cool.ssm.domain.Permission;
import com.cool.ssm.domain.Role;
import com.cool.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 21:58
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        //删除user_role中的记录
        roleDao.deleteUserRoleByRoleId(id);
        //删除role_permission中的记录
        roleDao.deleteRolePermissionByRoleId(id);
        //删除role中的记录
        roleDao.deleteRoleById(id);
    }

    @Override
    public List<Permission> getOtherPermissionsByRoleId(String roleId) {
        return roleDao.getOtherPermissionsByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        roleDao.addPermissionToRole(roleId,permissionIds);
    }
}

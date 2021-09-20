package com.cool.ssm.service.impl;

import com.cool.ssm.dao.IPermissionDao;
import com.cool.ssm.domain.Permission;
import com.cool.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 23:01
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;


    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Transactional
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);


    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        //删除role_permission中的记录
        permissionDao.deleteRolePermissionByPermissionId(id);
        //删除permission中的记录
        permissionDao.deleteById(id);
    }
}

package com.cool.ssm.dao;

import com.cool.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 18:04
 */
public interface IPermissionDao {

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id=#{id}")
    Permission findById(String id);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteRolePermissionByPermissionId(String id);

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id);
}

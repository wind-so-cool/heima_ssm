package com.cool.ssm.dao;

import com.cool.ssm.domain.Permission;
import com.cool.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 22:37
 */
public interface IRoleDao {

    @Select("select * from role where id in(select roleid from users_role where userid=#{userId})")
    @Results(value={
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many=@Many(select="com.cool.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findRolesByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{id}")
    @Results(value={
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many=@Many(select="com.cool.ssm.dao.IPermissionDao.findByRoleId"))
    })
    Role findById(String id);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteUserRoleByRoleId(String id);

    @Delete("delete from role_permission where roleid=#{id}")
    void deleteRolePermissionByRoleId(String id);


    @Delete("delete from role where id=#{id}")
    void deleteRoleById(String id);

    @Select("select * from permission where id not in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> getOtherPermissionsByRoleId(String roleId);

    @Insert("<script>insert all  <foreach separator=' ' item='permissionId' collection='permissionIds'>into role_permission values(#{permissionId},#{roleId})</foreach> SELECT 1 FROM DUAL</script>")
    void addPermissionToRole(@Param("roleId")String roleId, @Param("permissionIds")String[] permissionIds);
}

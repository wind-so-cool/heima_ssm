package com.cool.ssm.dao;

import com.cool.ssm.domain.Role;
import com.cool.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 20:45
 */
public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results(value={
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many=@Many(select="com.cool.ssm.dao.IRoleDao.findRolesByUserId")),
    })
    UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Results(value={
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many=@Many(select="com.cool.ssm.dao.IRoleDao.findRolesByUserId"))
    })
    @Select("select * from users where id=#{id}")
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleid from users_role where userid=#{userId})")
    List<Role> getOtherRolesByUserId(String userId);

    @Insert("<script>insert all  <foreach separator=' ' item='roleId' collection='roleIds'>into users_role values(#{userId},#{roleId})</foreach> SELECT 1 FROM DUAL</script>")
    void addRoleToUser(@Param("userId")String userId, @Param("roleIds")String[] roleIds);
}

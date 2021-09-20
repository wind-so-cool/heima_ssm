package com.cool.ssm.service;

import com.cool.ssm.domain.Role;
import com.cool.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 15:25
 */
public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> getOtherRolesByUserId(String id);

    void addRoleToUser(String userId, String[] roleIds);
}

package com.cool.ssm.service.impl;

import com.cool.ssm.dao.IUserDao;
import com.cool.ssm.domain.Role;
import com.cool.ssm.domain.UserInfo;
import com.cool.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 15:26
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        userInfo=userDao.findByUsername(username);
        return new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    public void save(UserInfo userInfo){
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> getOtherRolesByUserId(String id) {
        return userDao.getOtherRolesByUserId(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
         userDao.addRoleToUser(userId,roleIds);
    }


}

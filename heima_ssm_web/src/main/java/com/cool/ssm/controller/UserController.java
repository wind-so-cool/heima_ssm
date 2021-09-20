package com.cool.ssm.controller;

import com.cool.ssm.domain.Role;
import com.cool.ssm.domain.UserInfo;
import com.cool.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-14 15:04
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(ModelAndView mv){
        List<UserInfo> userList=userService.findAll();
        mv.setViewName("user-list");
        mv.addObject("userList",userList);
       return mv;
    }

    @PostMapping("save")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }
    @PostMapping("addRoleToUser")
    public String addRoleToUser(String userId, @RequestParam("ids")String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }

    @GetMapping("findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userService.findById(id));
        mv.setViewName("user-show");
        return mv;
    }
    @GetMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userService.findById(id));
        List<Role> otherRoles=userService.getOtherRolesByUserId(id);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }




}

package com.cool.ssm.controller;

import com.cool.ssm.domain.Permission;
import com.cool.ssm.domain.Role;
import com.cool.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 21:56
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("findAll")
    public ModelAndView findAll(ModelAndView mv){
        mv.addObject("roleList",roleService.findAll());
        mv.setViewName("role-list");
        return mv;
    }

    @PostMapping("save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    @GetMapping("findById")
    public ModelAndView findById(String id,ModelAndView mv){
        Role role=roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @GetMapping("deleteById")
    public  String deleteById(String id){
        roleService.deleteById(id);
        return "redirect:findAll";
    }

    @GetMapping("findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String roleId){
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",roleService.findById(roleId));
        List<Permission> otherPermissions=roleService.getOtherPermissionsByRoleId(roleId);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @PostMapping("addPermissionToRole")
    public String addPermissionToRole(String roleId, @RequestParam("permissionIds")String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }
}

package com.cool.ssm.controller;

import com.cool.ssm.domain.Permission;
import com.cool.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 许俊青
 * @Date: 2021-08-15 23:00
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("findAll")
    public ModelAndView findAll(ModelAndView mv){
        mv.addObject("permissionList",permissionService.findAll());
        mv.setViewName("permission-list");
        return mv;
    }

    @PostMapping("save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }
    @GetMapping("deleteById")
    public String deleteById(String id){
        permissionService.deleteById(id);
        return "redirect:findAll";
    }

    @GetMapping("findById")
    public ModelAndView findById(String id,ModelAndView mv){
        Permission permission=permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;

    }



}

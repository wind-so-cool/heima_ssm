package com.cool.ssm.controller;

import com.cool.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 许俊青
 * @Date: 2021-09-20 14:15
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @GetMapping("findAll")
    public ModelAndView findAll(ModelAndView mv){
        mv.addObject("sysLogs",sysLogService.findAll());
        mv.setViewName("syslog-list");
        return mv;
    }


}

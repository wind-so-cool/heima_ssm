package com.cool.ssm.controller;

import com.cool.ssm.domain.Orders;
import com.cool.ssm.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-25 20:04
 */
@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @GetMapping("findAll")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(ModelAndView mv,Integer page,Integer size,Integer selected){
        List<Orders> ordersList=ordersService.findAll(page,size);
        PageInfo<Orders> pageInfo=new PageInfo<>(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("selected",selected);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @GetMapping("findById")
    public ModelAndView findById(String id,ModelAndView mv){
        mv.addObject("orders",ordersService.findById(id));
        mv.setViewName("orders-show");
        return mv;
    }

}

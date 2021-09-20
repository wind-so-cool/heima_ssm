package com.cool.ssm.controller;

import com.cool.ssm.domain.Product;
import com.cool.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-24 13:40
 */
@RequestMapping("product")
@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("findAll")
    @RolesAllowed("ROLE_ADMIN")
    public ModelAndView findAll(ModelAndView mv){
        List<Product> productList=productService.findAll();
        mv.addObject("productList",productList);
        mv.setViewName("product-list");
        return mv;
    }

    @GetMapping("toAdd")
    public String toAdd(){
        return "product-add";
    }

    @PostMapping("save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }
}

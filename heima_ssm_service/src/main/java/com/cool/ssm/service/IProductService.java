package com.cool.ssm.service;

import com.cool.ssm.domain.Product;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-24 11:44
 */
public interface IProductService {

    List<Product> findAll();

    void save(Product product);
}

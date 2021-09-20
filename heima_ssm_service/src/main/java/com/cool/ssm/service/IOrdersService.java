package com.cool.ssm.service;

import com.cool.ssm.domain.Orders;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-25 20:01
 */
public interface IOrdersService {

    List<Orders> findAll(int page, int size);

    Orders findById(String id);
}

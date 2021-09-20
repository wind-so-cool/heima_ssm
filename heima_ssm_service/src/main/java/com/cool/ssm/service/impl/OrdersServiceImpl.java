package com.cool.ssm.service.impl;

import com.cool.ssm.dao.IOrdersDao;
import com.cool.ssm.domain.Orders;
import com.cool.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-25 20:02
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Transactional(readOnly = true)
    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}

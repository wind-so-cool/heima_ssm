package com.cool.ssm.service.impl;

import com.cool.ssm.dao.IProductDao;
import com.cool.ssm.domain.Product;
import com.cool.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-24 11:46
 */
@Service
@Transactional
public class ProductServiceImpl  implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}

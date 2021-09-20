package com.cool.ssm.dao;

import com.cool.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-24 11:40
 */
public interface IProductDao {

    @Select("select * from product where id=#{id}")
    Product findById(String id);

    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}

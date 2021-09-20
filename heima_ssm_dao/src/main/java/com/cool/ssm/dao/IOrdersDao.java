package com.cool.ssm.dao;

import com.cool.ssm.domain.Member;
import com.cool.ssm.domain.Orders;
import com.cool.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-07-25 20:01
 */
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType=Product.class,one=@One(select="com.cool.ssm.dao.IProductDao.findById"))

    })
    public List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType=Product.class,one=@One(select="com.cool.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType= Member.class,one=@One(select="com.cool.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType= List.class,many=@Many(select="com.cool.ssm.dao.ITravellerDao.findByOrderId"))

    })
    Orders findById(String id);
}

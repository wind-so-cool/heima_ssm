package com.cool.ssm.dao;

import com.cool.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 11:57
 */
public interface ITravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
}

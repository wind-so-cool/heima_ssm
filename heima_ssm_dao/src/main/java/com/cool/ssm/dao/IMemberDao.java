package com.cool.ssm.dao;

import com.cool.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 许俊青
 * @Date: 2021-08-01 11:50
 */
public interface IMemberDao {

    @Select("select * from member where id=#{memberId}")
    Member findById(String memberId);
}

package com.cool.ssm.service;

import com.cool.ssm.domain.SysLog;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-09-19 17:46
 */
public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}

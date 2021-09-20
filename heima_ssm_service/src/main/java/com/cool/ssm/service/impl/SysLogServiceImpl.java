package com.cool.ssm.service.impl;

import com.cool.ssm.dao.ISysLogDao;
import com.cool.ssm.domain.SysLog;
import com.cool.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 许俊青
 * @Date: 2021-09-19 17:46
 */
@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Transactional
    public void save(SysLog sysLog){
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}

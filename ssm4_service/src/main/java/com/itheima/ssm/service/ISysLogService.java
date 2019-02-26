package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    List<SysLog> findAll(Integer page, Integer size) throws Exception;

    void save(SysLog sysLog) throws Exception;
}

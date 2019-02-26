package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogDao {

    List<SysLog> findAll() throws Exception;

    void save(SysLog sysLog) throws Exception;
}

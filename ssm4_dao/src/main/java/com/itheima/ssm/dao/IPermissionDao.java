package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    List<Permission> findPermissionByRoleId(String roleId);

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}

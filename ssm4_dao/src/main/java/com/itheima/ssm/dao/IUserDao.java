package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IUserDao {

    public UserInfo findByUsername(String username);


    List<UserInfo> findAll() throws Exception;


    void save(UserInfo userInfo) throws Exception;


    UserInfo findById(String userId) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}

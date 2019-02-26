package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    List<Role> findByUserId(String userId);

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;


    Role findById(String roleId);

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(@Param("roleId") String roleId, @Param("perId") String perId) throws Exception;

  /* @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(String roleId);

   @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;*/
}

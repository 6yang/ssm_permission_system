package cn.yang.dao;

import cn.yang.domain.Permission;
import cn.yang.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id",javaType = java.util.List.class,
            many = @Many(select = "cn.yang.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in " +
            " ( select permissionId from role_permission where roleId = #{roleId}) ")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission values(#{permissionId},#{roleId}) ")
    void addPermissionsToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}

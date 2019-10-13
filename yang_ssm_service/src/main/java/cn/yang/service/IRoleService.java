package cn.yang.service;

import cn.yang.domain.Permission;
import cn.yang.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionsToRole(String roleId, String[] permissionIds);
}

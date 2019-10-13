package cn.yang.service.impl;


import cn.yang.dao.IRoleDao;
import cn.yang.domain.Permission;
import cn.yang.domain.Role;
import cn.yang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @ClassName: RoleServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1215:41
 * @version : V1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return iRoleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return iRoleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionsToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            iRoleDao.addPermissionsToRole(roleId,permissionId);
        }
    }
}

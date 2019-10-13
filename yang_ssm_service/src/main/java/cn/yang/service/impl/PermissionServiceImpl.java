package cn.yang.service.impl;

import cn.yang.dao.IPermissionDao;
import cn.yang.domain.Permission;
import cn.yang.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @ClassName: PermissionServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1216:01
 * @version : V1.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public List<Permission> findAll() {
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        iPermissionDao.save(permission);
    }
}

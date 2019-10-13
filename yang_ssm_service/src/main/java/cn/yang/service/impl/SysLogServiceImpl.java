package cn.yang.service.impl;

import cn.yang.dao.ISysLogDao;
import cn.yang.domain.SysLog;
import cn.yang.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @ClassName: SysLogServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1317:04
 * @version : V1.0
 */
@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}

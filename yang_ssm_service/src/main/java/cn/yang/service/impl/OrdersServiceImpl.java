package cn.yang.service.impl;

import cn.yang.dao.IOrdersDao;
import cn.yang.domain.Orders;
import cn.yang.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @ClassName: OrdersServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/915:28
 * @version : V1.0
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }
}

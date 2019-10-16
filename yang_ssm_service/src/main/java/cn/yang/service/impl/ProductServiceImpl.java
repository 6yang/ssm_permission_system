package cn.yang.service.impl;

import cn.yang.dao.IProductDao;
import cn.yang.domain.Product;
import cn.yang.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/***
 * @ClassName: ProductServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/817:22
 * @version : V1.0
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            iProductDao.delete(id);
        }
    }

}

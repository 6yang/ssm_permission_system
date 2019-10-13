package cn.yang.service.impl;

import cn.yang.dao.IUserDao;
import cn.yang.domain.Role;
import cn.yang.domain.UserInfo;
import cn.yang.service.IUserService;
import cn.yang.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/***
 * @ClassName: UserServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1112:11
 * @version : V1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = iUserDao.findByUsername(username);

        //处理自己的对象用户封装成userdetails
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0? false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    /*
    * 作用：返回出这个一个权限集合
    * */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String userId) {
        return iUserDao.findById(userId);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return iUserDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            iUserDao.addRoleToUser(userId,roleId);
        }
    }
}

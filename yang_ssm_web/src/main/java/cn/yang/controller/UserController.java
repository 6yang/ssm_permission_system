package cn.yang.controller;

import cn.yang.domain.Role;
import cn.yang.domain.UserInfo;
import cn.yang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/***
 * @ClassName: UserController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1211:21
 * @version : V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/findAll")
    @RolesAllowed({"ADMIN"})
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = iUserService.findAll();
        mv.addObject("userList",userInfoList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String  save(UserInfo userInfo){
        iUserService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo  = iUserService.findById(userId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /*
    * 查询用户以及用户可以添加的角色
    * */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId ){
        ModelAndView mv = new ModelAndView();

        //1.查询用户
        UserInfo userInfo = iUserService.findById(userId);
        //2 查询用户可以添加的角色
        List<Role> roleList =  iUserService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    /*
    * 给用户添加角色
    * */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name="userId",required = true) String userId,
                                @RequestParam(name = "ids",required = true) String[] roleIds){

        iUserService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }


}

package cn.yang.controller;

import cn.yang.domain.Permission;
import cn.yang.domain.Role;
import cn.yang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/***
 * @ClassName: RoleController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1215:40
 * @version : V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    /*
    * 更具roleId查询role 并且查询可以添加的权限
    * */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId){
        ModelAndView mv = new ModelAndView();
        //1 .根据roleid 查询 role
        Role role =  roleService.findById(roleId);
        //2 . 根据roleid查询可以添加的权限
        List<Permission> permissionList = roleService.findOtherPermission(roleId);

        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true) String roleId,
                                      @RequestParam(name = "ids",required = true) String[] permissionIds){
        roleService.addPermissionsToRole(roleId,permissionIds);
        return "redirect:findAll";
    }

}

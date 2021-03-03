package com.dgut.blog.controller.admin;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.Role;
import com.dgut.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 角色管理
 * @createDate: 2021/3/3
 */

@RestController
@RequestMapping("/admin")
public class RoleController {


    @Autowired
    RoleService roleService;

    /**
     * 获取所有用户角色
     * @return
     */
    @RequestMapping(value = "/role/all", method = RequestMethod.GET)
    public List<Role> getAllCategories() {
        return roleService.getAllRole();
    }



    /**
     * 增加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseDTO addNewCate(Role role) {
        if ("".equals(role.getRoleName()) || role.getRoleName() == null) {
            return new ResponseDTO("error", "请输入角色名!");
        }
        System.out.println("要新增的角色为： " + role);
        if (roleService.addRole(role)) {
            return new ResponseDTO("success", "添加成功!");
        }
        return new ResponseDTO("error", "添加失败!");
    }


    /**
     * 修改角色名
     * @param role
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public ResponseDTO updateCate(Role role) {
        if(roleService.getRoleByRoleName(role.getRoleName()) != null){
            return new ResponseDTO("error", "角色名重复!");
        }
        if (roleService.updateRoleById(role)) {
            return new ResponseDTO("success", "修改成功!");
        }
        return new ResponseDTO("error", "修改失败!");
    }
}

package cn.itcast.controller;

import cn.itcast.POJO.Role;
import cn.itcast.service.RoleService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public List<Role> findAllRole(){
        List<Role> roles = roleService.findAllRole();
        return roles;
    }

}

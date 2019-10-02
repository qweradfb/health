package cn.itcast.controller;

import cn.itcast.POJO.Menu;
import cn.itcast.service.MenuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @RequestMapping("/getMenu")
    public List<Menu> getMenu(){
         List<Menu> list = menuService.getMenu();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/getUserMenu")
    public List<Menu> getUserMenu(){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        List<Menu> list = menuService.getUserMenu(username);
        return list;
    }
}

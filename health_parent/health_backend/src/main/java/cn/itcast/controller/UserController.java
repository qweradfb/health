package cn.itcast.controller;

import cn.itcast.POJO.User;
import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;
import cn.itcast.service.UserService;
import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/getUserName")
    public String getUserName(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @RequestMapping("/findUsers")
    public PageResult getUsers(@RequestBody QueryPageBean queryPageBean){
            PageResult users = userService.getUsers(queryPageBean);
            return users;
    }

    @RequestMapping("/addUser")
    public Result addUser(Integer[] ids,@RequestBody User user){
        try {
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            userService.addUser(ids,user);
            return new Result(true,MessageConstant.ADD_USER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_USER_FAIL);
        }
    }
    @RequestMapping("/echoUser")
    public User echoUser(Integer id){
        User user = userService.echoUser(id);
        user.setPassword("");
        return user;
    }

    @RequestMapping("/echoRole")
    public List<Integer> echoRole(Integer id){
        List<Integer> ids = userService.echoRole(id);
        return ids;
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] ids,@RequestBody User user){
        try {
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            userService.edit(ids,user);
            return new Result(true,MessageConstant.UPDATE_USER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.UPDATE_USER_FAIL);
        }
    }
    @RequestMapping("/deleteUser")
    public Result deleteUser(int id){
        try {
            userService.deleteUserById(id);
            return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_USER_FAIL);
        }
    }

}

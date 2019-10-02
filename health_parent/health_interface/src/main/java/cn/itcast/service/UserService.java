package cn.itcast.service;

import cn.itcast.POJO.User;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;

import java.util.List;

public interface UserService {
    User findUserByName(String username);

    PageResult getUsers(QueryPageBean queryPageBean);

    void addUser(Integer[] ids, User user);

    User echoUser(Integer id);

    List<Integer> echoRole(Integer id);

    void edit(Integer[] ids, User user);

    void deleteUserById(int id);
}

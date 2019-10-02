package cn.itcast.service;

import cn.itcast.POJO.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenu();

    List<Menu> getUserMenu(String username);
}

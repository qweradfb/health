package cn.itcast.service;

import cn.itcast.POJO.Setmeal;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;

import java.util.List;

public interface SetmealService {

    void saveSetmeal(Setmeal formData, Integer[] checkgroupIds);

    PageResult findPage(QueryPageBean queryPageBean);

    Setmeal echoSetmeal(int id);

    List<Integer> echoGroups(int id);

    void editSetmeal(Setmeal setmeal, Integer[] ids);

    void deleteSetmeal(int id);

    List<Setmeal> findAllSetmeals();

    Setmeal findSetmealDetailById(int id);
}

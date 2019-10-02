package cn.itcast.service;

import cn.itcast.POJO.CheckItem;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    void deleteSelected(Integer[] ids);

    List<CheckItem> findAllCheckItems();
}

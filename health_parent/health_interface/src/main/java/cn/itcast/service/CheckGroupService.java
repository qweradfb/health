package cn.itcast.service;

import cn.itcast.POJO.CheckGroup;
import cn.itcast.POJO.CheckItem;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;

import java.util.List;

public interface CheckGroupService {
    void saveCheckGroup(CheckGroup formData, Integer[] checkitemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findGroupById(int id);

    List<Integer> findItemsById(int id);

    void edit(CheckGroup formData, Integer[] ids);

    void deleteGroup(int id);

    List<CheckItem> findItemsPage(String queryItemsString);

    List<CheckGroup> findAllGroup();
}

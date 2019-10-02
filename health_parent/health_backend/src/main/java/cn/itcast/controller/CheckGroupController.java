package cn.itcast.controller;

import cn.itcast.POJO.CheckGroup;
import cn.itcast.POJO.CheckItem;
import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;
import cn.itcast.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/addCheckGroup")
    public Result addCheckGroup(@RequestBody CheckGroup formData, Integer[] checkitemIds){
        try {
            checkGroupService.saveCheckGroup(formData,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.findPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/echoGroup")
    public CheckGroup echoGroup(int id){
        CheckGroup checkGroup = checkGroupService.findGroupById(id);
        return checkGroup;
    }

    @RequestMapping("/echoItems")
    public List<Integer> echoItems(int id){
        List<Integer> ids = checkGroupService.findItemsById(id);
        return ids;
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup formData, Integer[] ids){
        try {
            checkGroupService.edit(formData,ids);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/deleteGroup")
    public Result deleteGroup(int id){
        try {
            checkGroupService.deleteGroup(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findItemsPage")
    public List<CheckItem> findItemsPage(String queryItemsString){
        List<CheckItem> checkItems = checkGroupService.findItemsPage(queryItemsString);
        return checkItems;
    }

    @RequestMapping("/findAllGroup")
    public List<CheckGroup> findAllGroup(){
        List<CheckGroup> allGroup = checkGroupService.findAllGroup();
        return allGroup;
    }
}

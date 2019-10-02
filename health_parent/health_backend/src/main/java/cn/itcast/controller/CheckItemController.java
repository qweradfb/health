package cn.itcast.controller;

import cn.itcast.POJO.CheckItem;
import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;
import cn.itcast.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.wrapper.CK_SSL3_KEY_MAT_OUT;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    //添加检查项
    @RequestMapping("/add")
    public Result add(CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
            return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    //分页查询检查项
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.findPage(queryPageBean);
        return pageResult;
    }

    //根据id删除检查项
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id")Integer id){
        try {
            checkItemService.delete(id);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    //回显编辑检查项
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑表单项
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    //删除所选的检查项
    @RequestMapping("/deleteSelected")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result deleteSelected(Integer[] ids){
        try {
            checkItemService.deleteSelected(ids);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    //查询所有表单项
    @RequestMapping("/findAllCheckItems")
    public List<CheckItem> findAllItems(){
        List<CheckItem> items = checkItemService.findAllCheckItems();
        return items;
    }
}

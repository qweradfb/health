package cn.itcast.controller;

import cn.itcast.POJO.Setmeal;
import cn.itcast.constant.MessageConstant;
import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;
import cn.itcast.service.SetmealService;
import cn.itcast.utils.QiniuUtils;
import cn.itcast.utils.UUIDUtils;
import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    //上传图片
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        try {
            String fileName = imgFile.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            String imgName = UUIDUtils.getUUID()+fileSuffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),imgName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,imgName);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,imgName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

    }

    //添加套餐
    @RequestMapping("/addSetmeal")
    public Result addSetmeal(@RequestBody Setmeal formData, Integer[] checkgroupIds){
        try {
            setmealService.saveSetmeal(formData,checkgroupIds);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,formData.getImg());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);

    }

    //分页条件查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(queryPageBean);
        return pageResult;
    }

    //回显setmeal
    @RequestMapping("/echoSetmeal")
    public Setmeal echoSetmeal(int id){
        Setmeal setmeal = setmealService.echoSetmeal(id);
        return setmeal;
    }

    //回显setmeal对应的检查组
    @RequestMapping("/echoGroups")
    public List<Integer> echoGroups(int id){
        List<Integer> ids = setmealService.echoGroups(id);
        return ids;
    }

    //编辑setmeal
    @RequestMapping("/editSetmeal")
    public Result editSetmeal(@RequestBody Setmeal setmeal,Integer[] ids){
        try {
            setmealService.editSetmeal(setmeal,ids);
            return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    //删除setmeal以及关系表
    @RequestMapping("deleteSetmeal")
    public Result deleteSetmeal(int id){
        try{
            setmealService.deleteSetmeal(id);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
    }


}

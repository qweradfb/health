package cn.itcast.controller;

import cn.itcast.POJO.Setmeal;
import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.SetmealService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;


    @RequestMapping("/getSetmeals")
    public Result getSetmeals(){
        try {
            List<Setmeal> setmeals = setmealService.findAllSetmeals();
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,setmeals);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEALLIST_FAIL);
        }

    }

    @RequestMapping("/getSetmealById")
    public Result getSetmealById(int id){
        try{
            Setmeal setmeal = setmealService.findSetmealDetailById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }



}

package cn.itcast.controller;

import cn.itcast.POJO.OrderSetting;
import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.OrderSettingService;
import cn.itcast.utils.POIUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    //上传excel表格
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile file){
        try {
            List<String[]> strings = POIUtils.readExcel(file);
            if (strings!=null&&strings.size()>0){
                orderSettingService.saveOrderSetting(strings);
            }
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }

    //显示数据
    @RequestMapping("/findPage")
    public List<Map<String,Object>> findPage(@RequestBody Map<String,String> data){
        List<Map<String,Object>> map = orderSettingService.findPage(data.get("currentYear"),data.get("currentMonth"));
        return map;
    }

    //设置最大预约数
    @RequestMapping("/setNumber")
    public Result setNumber(@RequestBody OrderSetting orderSetting){
        System.out.println(orderSetting);
        try {
            orderSettingService.setNumber(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}

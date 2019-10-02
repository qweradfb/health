package cn.itcast.controller;

import cn.itcast.POJO.Order;
import cn.itcast.constant.MessageConstant;
import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.OrderService;
import cn.itcast.utils.SMSUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/submit")
    public Result submit(@RequestBody Map map){
        if (map==null||map.size()==0){
            return new Result(false,MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }
        String validateCode = (String) map.get("validateCode");
        String phone = (String) map.get("telephone");
        if (validateCode!=null&&validateCode!=""&&phone!=null){
            String code = jedisPool.getResource().get(phone+ RedisConstant.SENDTYPE_ORDER);
            if (code==null||!code.equals(validateCode)){
                return new Result(false,MessageConstant.VALIDATECODE_ERROR);
            }else {
                Result result = null;
                try {
                    map.put("orderType", Order.ORDERTYPE_WEIXIN);
                    result = orderService.submit(map);
                    if (result.isFlag()){
                        //发送通知短信
                        String orderDate = (String) map.get("orderDate");
                        SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,phone,orderDate);
                    }
                    return result;
                }catch (Exception e){
                    e.printStackTrace();
                    return result;
                }
            }
        }else{
            return new Result(false,MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }

    }

    @RequestMapping("/findById")
    public Map findById(int id){
        Map map = orderService.findById(id);
        return map;
    }

}

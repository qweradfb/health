package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.Result;
import cn.itcast.utils.SMSUtils;
import cn.itcast.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/sendNode")
    public Result getValidateCode(String telephone){
        try {
            String code = ValidateCodeUtils.generateValidateCode4String(4);
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code);
            System.out.println(code);
            jedisPool.getResource().setex(telephone+ RedisConstant.SENDTYPE_ORDER,5*60,code);
            System.out.println(telephone);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @RequestMapping("/loginCode")
    public Result getLoginCode(String telephone){
        try {
            String code = ValidateCodeUtils.generateValidateCode4String(6);
//            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code);
            System.out.println(code);
            jedisPool.getResource().setex(telephone+RedisConstant.SENDTYPE_LOGIN,5*60,code);
            System.out.println(telephone);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }


}

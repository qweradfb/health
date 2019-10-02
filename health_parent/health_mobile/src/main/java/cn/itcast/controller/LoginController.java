package cn.itcast.controller;

import cn.itcast.POJO.Member;
import cn.itcast.constant.MessageConstant;
import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.LoginService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private LoginService loginService;

    @RequestMapping("/check")
    public Result check(@RequestBody Map map, HttpServletResponse response){
//        System.out.println(map);
        String validateCode = (String) map.get("validateCode");
        String telephone = (String) map.get("telephone");
        String realCode = jedisPool.getResource().get(telephone + RedisConstant.SENDTYPE_LOGIN);
        if (validateCode==null||telephone==null||"".equals(validateCode)||"".equals(telephone)){
            return new Result(false,MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }
        if(realCode==null||!realCode.equals(validateCode)){
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }
        //判断是否是会员不是完成自动注册
        Member member = loginService.checkMember(telephone);

        Cookie cookie = new Cookie("login_member_phone",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);

        String json = JSON.toJSON(member).toString();
        jedisPool.getResource().setex(telephone,60*30,json);


        return new Result(true, MessageConstant.LOGIN_SUCCESS);
    }
}

package cn.itcast.service;

import cn.itcast.POJO.Order;
import cn.itcast.entity.Result;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface OrderService {
    Result submit(Map map) throws Exception;

    Map findById(int id);
}

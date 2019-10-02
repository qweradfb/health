package cn.itcast.service;

import cn.itcast.POJO.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void saveOrderSetting(List<String[]> strings);

    List<Map<String,Object>> findPage(String currentYear, String currentMonth);

    void setNumber(OrderSetting orderSetting);
}

package cn.itcast.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}

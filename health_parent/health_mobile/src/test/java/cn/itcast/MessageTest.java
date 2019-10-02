package cn.itcast;

import cn.itcast.utils.SMSUtils;
import com.aliyuncs.exceptions.ClientException;

import javax.xml.crypto.Data;

public class MessageTest {
    public static void main(String[] args) throws ClientException {
//        SMSUtils.sendShortMessage("SMS_172357338","18338661287","5869");
//        SMSUtils.sendShortMessage("SMS_172357338","13569583627","5869");
//        SMSUtils.sendShortMessage("SMS_172357338","18538432423","5869");
        SMSUtils.sendShortMessage("SMS_172600828","18538432423","8817");
    }
}

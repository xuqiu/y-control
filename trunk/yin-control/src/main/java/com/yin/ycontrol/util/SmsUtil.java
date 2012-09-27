package com.yin.ycontrol.util;

import com.yin.common.util.ConfigUtil;
import com.yin.ycontrol.client.sms.SmsClient;

public class SmsUtil {
    public static void send(String message) {
        String mobile=ConfigUtil.get("mobile");
        if(mobile!=null&&mobile.length()>0){
        	SmsClient.send(mobile, message);
        }
    }
}

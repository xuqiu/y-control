package com.yin.ycontrol.client.sms;

import java.net.MalformedURLException;
import com.greenline.message.biz.service.share.sms.SMSParamDTO;
import com.greenline.message.biz.service.share.sms.SMSResultDTO;
import com.greenline.message.biz.service.share.sms.SmsService;



/**
 * @Type TestHessian
 * @Desc 
 * @author john
 * @date 2012-4-26
 * @Version V1.0
 */
public class SmsClient {
    static SmsService service;
    static {
        String url = "http://211.144.79.37/message/hessian/sms";
        SmsHessianProxyFactory proxy = new SmsHessianProxyFactory();
        proxy.setClientid("07");
        proxy.setPassword("635698");
        proxy.setCaller("95169");
        try {
            service =  (SmsService) proxy.create(SmsService.class, url,ClassLoader.getSystemClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void send(String mobile,String message) {
        SMSParamDTO param = new SMSParamDTO(mobile, message);
        SMSResultDTO ret = service.send(param);
        if(!ret.isSuccess()){
        	System.out.println("##############·¢ËÍ¶ÌÐÅ³ö´í£º"+ret.getMessage());
        }
    }

}

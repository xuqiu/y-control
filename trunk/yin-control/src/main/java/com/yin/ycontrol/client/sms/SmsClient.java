package com.yin.ycontrol.client.sms;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import com.greenline.message.biz.service.share.sms.SMSParamDTO;
import com.greenline.message.biz.service.share.sms.SMSResultDTO;
import com.greenline.message.biz.service.share.sms.SmsService;
import com.yin.common.util.ConfigUtil;



/**
 * @Type TestHessian
 * @Desc 
 * @author john
 * @date 2012-4-26
 * @Version V1.0
 */
public class SmsClient {
    static SmsService service;
    static Properties p;
    static {
    	try {
			InputStream in = ConfigUtil.class.getClassLoader()
					.getResourceAsStream("sms.properties");
			p = new Properties();
			p.load(in);

	        String url = p.getProperty("url");
	        SmsHessianProxyFactory proxy = new SmsHessianProxyFactory();
	        proxy.setClientid(p.getProperty("clientId"));
	        proxy.setPassword(p.getProperty("password"));
	        proxy.setCaller("95169");

            service =  (SmsService) proxy.create(SmsService.class, url,ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
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

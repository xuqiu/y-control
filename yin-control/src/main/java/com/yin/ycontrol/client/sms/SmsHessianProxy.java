package com.yin.ycontrol.client.sms;

import java.net.URL;
import java.util.Date;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import com.yin.common.util.DateUtil;
import com.yin.common.util.MD5Util;

public class SmsHessianProxy extends HessianProxy  {
	private String clientid;
	private String password;
	private String caller;
	
    private static final long serialVersionUID = -5745806908150418780L;

    /**
     * @param url
     * @param factory
     */
    protected SmsHessianProxy(URL url, HessianProxyFactory factory) {
        super(url, factory);
    }
    
    /**
     * Protected constructor for subclassing
     */
    protected SmsHessianProxy(URL url,
                           HessianProxyFactory factory, 
                           Class<?> type)
    {
       super(url,factory,type);
    }
    
    /**
     * 设置公用的请求头
     */
    @Override
    protected void addRequestHeaders(HessianConnection conn) {        
        Date date = new Date();        
        String requestseqnbr = DateUtil.getDateString(date, "yyyyMMddHHmmssMs");
        String requesttime = DateUtil.getDateString(date, "yyyyMMddHHmmssZ");        
        String signdata = MD5Util.getMD5Format(clientid + requestseqnbr + requesttime + password);

        conn.addHeader("clientid",clientid);
        conn.addHeader("requestseqnbr",requestseqnbr);
        conn.addHeader("requesttime",requesttime);
        conn.addHeader("signdata",signdata); 
        conn.addHeader("caller",caller);
        
        //调用hessian的处理
        super.addRequestHeaders(conn);
    }

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}
}

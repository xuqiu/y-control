package com.yin.ycontrol.client.sms;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

public class SmsHessianProxyFactory extends HessianProxyFactory {
	private String clientid;
	private String password;
	private String caller;
	
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

	@Override
	public Object create(Class<?> api, URL url, ClassLoader loader) {
        if (api == null){
            throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
        }
        SmsHessianProxy smsproxy = new SmsHessianProxy(url, this, api);
        smsproxy.setCaller(caller);
        smsproxy.setClientid(clientid);
        smsproxy.setPassword(password);
        InvocationHandler handler = smsproxy;
        return Proxy.newProxyInstance(loader, new Class[] { api, HessianRemoteObject.class }, handler);
    }
}

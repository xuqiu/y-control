package com.yin.ycontrol.util;

public class NetUtil {
	public static void restartNet(){
		try{
		      Runtime.getRuntime().exec("netsh interface set interface name=\"��������\" admin=DISABLED");
		      Thread.sleep(20000);
		      Runtime.getRuntime().exec("netsh interface set interface name=\"��������\" admin=ENABLED");
		}catch(Exception   e){
		    System.out.println(e.getMessage());
		} 
	}
	public static void main(String[] args){
		restartNet();
	}
}

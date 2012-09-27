package com.yin.ycontrol.task;

import java.util.TimerTask;
import javax.servlet.ServletContext;

import jetty.JettyServer;

import com.yin.ycontrol.util.MailUtils;
import com.yin.ycontrol.util.SmsUtil;
import com.yin.ycontrol.util.UrlUtil;

public class SendEmailTask extends TimerTask {
	public static boolean isRunning = false;
	private ServletContext context = null;
	private static String IP="";

	public SendEmailTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning&&!UpdateMacTask.isRunning) {
			isRunning = true;
			// -------------------开始保存当日历史记录
			int port = JettyServer.getPort();
			String newIP=UrlUtil.getUrl();
			if(newIP!=null&&!newIP.equals(IP)){
				IP=newIP;
				String message="http://"+IP+":"+port;
				MailUtils.send("ycontrol-server",message);
				SmsUtil.send(message);
			}
			// -------------------结束
			isRunning = false;
		}else {
			context.log("上一次任务执行还未结束");
		}
	}

}

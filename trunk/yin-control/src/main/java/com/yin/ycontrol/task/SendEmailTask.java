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
			// -------------------��ʼ���浱����ʷ��¼
			int port = JettyServer.getPort();
			String newIP=UrlUtil.getUrl();
			if(newIP!=null&&!newIP.equals(IP)){
				IP=newIP;
				String message="http://"+IP+":"+port;
				MailUtils.send("ycontrol-server",message);
				SmsUtil.send(message);
			}
			// -------------------����
			isRunning = false;
		}else {
			context.log("��һ������ִ�л�δ����");
		}
	}

}

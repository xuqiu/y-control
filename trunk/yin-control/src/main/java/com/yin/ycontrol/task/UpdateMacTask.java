package com.yin.ycontrol.task;

import java.util.TimerTask;
import javax.servlet.ServletContext;

//import com.yin.ycontrol.util.NetUtil;
//import com.yin.ycontrol.util.RegistryUtil;

public class UpdateMacTask extends TimerTask {
	public static boolean isRunning = false;
	private ServletContext context = null;

	public UpdateMacTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		if (!isRunning) {
			isRunning = true;
			// -------------------��ʼ���浱����ʷ��¼

			//RegistryUtil.updateMac();
			//NetUtil.restartNet();
			// -------------------����
			isRunning = false;
		}else {
			context.log("��һ������ִ�л�δ����");
		}
	}

}

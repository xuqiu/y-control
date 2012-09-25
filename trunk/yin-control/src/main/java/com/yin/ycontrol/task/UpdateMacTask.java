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
			// -------------------开始保存当日历史记录

			//RegistryUtil.updateMac();
			//NetUtil.restartNet();
			// -------------------结束
			isRunning = false;
		}else {
			context.log("上一次任务执行还未结束");
		}
	}

}

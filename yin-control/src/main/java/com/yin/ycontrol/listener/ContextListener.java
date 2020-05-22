package com.yin.ycontrol.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {
	private Timer timer = null;
	private Timer mac_timer = null;
	/**
	 * @方法名：contextDestroyed
	 * @功能描述：应用关闭时执行此方法
	 * @参数：@param arg0
	 * @throws
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * @方法名：contextInitialized
	 * @功能描述：当应用启动时执行此方法
	 * @参数：@param event
	 * @throws
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			timer = new Timer(true);
//			event.getServletContext().log("定时器已启动");//添加日志，可在tomcat日志中查看到
//			timer.schedule(new SendEmailTask(event.getServletContext()),0,60*1000);
//			Thread.sleep(1000);
//			while(SendEmailTask.isRunning){
//				Thread.sleep(1000);
//			}
//			mac_timer = new Timer(true);
//			mac_timer.schedule(new UpdateMacTask(event.getServletContext()),0,23*60*60*1000);
//			event.getServletContext().log("已经添加任务");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
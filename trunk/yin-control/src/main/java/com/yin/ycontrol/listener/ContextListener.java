package com.yin.ycontrol.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yin.ycontrol.task.SendEmailTask;
import com.yin.ycontrol.task.UpdateMacTask;


public class ContextListener implements ServletContextListener {
	private Timer timer = null;
	private Timer mac_timer = null;
	/**
	 * @��������contextDestroyed
	 * @����������Ӧ�ùر�ʱִ�д˷���
	 * @������@param arg0
	 * @throws
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * @��������contextInitialized
	 * @������������Ӧ������ʱִ�д˷���
	 * @������@param event
	 * @throws
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			timer = new Timer(true);
			event.getServletContext().log("��ʱ��������");//�����־������tomcat��־�в鿴��
			timer.schedule(new SendEmailTask(event.getServletContext()),0,60*1000);
//			Thread.sleep(1000);
//			while(SendEmailTask.isRunning){
//				Thread.sleep(1000);
//			}
//			mac_timer = new Timer(true);
//			mac_timer.schedule(new UpdateMacTask(event.getServletContext()),0,23*60*60*1000);
//			event.getServletContext().log("�Ѿ��������");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
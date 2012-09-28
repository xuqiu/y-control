package com.yin.ycontrol.screenShotter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ControlServlet extends HttpServlet {
	/**
	 * ִ�е�½��ҵ����
	 * @param request:��������������
	 * @return destJsp��Ŀ��URL
	 */
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		//����ҳ�治����
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//���ڴ��д���ͼ��
		
		ServletOutputStream output;
		try {
			String x = request.getParameter("x");
			String y = request.getParameter("y");
			String e = request.getParameter("e");
			String k = request.getParameter("k");
			if(e==null){
				
			}else if(e.equalsIgnoreCase("mousedown")){
				Controler.mousedown(Integer.parseInt(x)-8, Integer.parseInt(y)-8);
			}else if(e.equalsIgnoreCase("mouseup")){
				Controler.mouseup(Integer.parseInt(x)-8, Integer.parseInt(y)-8);
			}else if(e.equalsIgnoreCase("rightclick")){
				Controler.rightClickPosition(Integer.parseInt(x)-8, Integer.parseInt(y)-8);
			}else if(e.equalsIgnoreCase("keydown")){
				Controler.keydown(Integer.parseInt(k));
			}else if(e.equalsIgnoreCase("keyup")){
				Controler.keyup(Integer.parseInt(k));
			}
			output = response.getOutputStream();
			
	    	  output.flush(); 
	    	  output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
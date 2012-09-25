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

public class VedioServlet extends HttpServlet {
	/**
	 * 执行登陆的业务处理
	 * @param request:发送上来的请求
	 * @return destJsp：目标URL
	 */
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		//设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//在内存中创建图象
		

		ServletOutputStream output;
		output = response.getOutputStream();
		try {
			while(true){
				BufferedImage image = GuiCamera.screenShot();
				JPEGImageEncoder   encoder   =   JPEGCodec.createJPEGEncoder(   output   ); 
		    	  JPEGEncodeParam   jpegEP   =   JPEGCodec.getDefaultJPEGEncodeParam(   image   ); 
		    	  jpegEP.setQuality(   (float)   0.3   ,   true   ); 
		    	  encoder.encode(   image   ,   jpegEP   ); 
				// 输出图象到页面
		    	  output.flush(); 
		    	  try{
		    	  Thread.sleep(200);
		    	  }catch(InterruptedException e){
		    		  
		    	  }
			}
			//ImageIO.write(image, "PNG", output);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
}
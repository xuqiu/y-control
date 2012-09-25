package com.yin.ycontrol.screenShotter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageWriterSpi;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.awt.image.PNGImageDecoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.png.PNGImageWriter;

public class ImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7241394871084993707L;
	/**
	 * 执行登陆的业务处理
	 * @param request:发送上来的请求
	 * @return destJsp：目标URL
	 */
	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException,ServletException{
		//设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//获取最新帧数
		if(request.getParameter("lastframe")!=null){
			response.getWriter().write(ImageUtil.getFrame()+"");
			response.getWriter().close();
			return;
		}
		//获取图片
		ServletOutputStream output;
		try {
			output = response.getOutputStream();
			
			Long min=0l;
			Long max=0l;
			try{
				String minStr=request.getParameter("min");
				String maxStr=request.getParameter("max");
				min=Long.parseLong(minStr);
				max=Long.parseLong(maxStr);
			}catch(Exception ex){}
				BufferedImage image = ImageUtil.getImage(min, max);
				//ImageIO.write(image, "gif", new File("C://test"+new Date().getTime()+".gif"));
		    	ImageIO.write(image, "gif", output);
	    	 // 输出图象到页面
	    	  output.flush(); 
	    	  output.close();
			//ImageIO.write(image, "PNG", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
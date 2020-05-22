package com.yin.ycontrol.screenShotter;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideoServlet extends HttpServlet {
    private static final long serialVersionUID = 8193565819750469797L;

    /**
     * 执行登陆的业务处理
     * @param request:发送上来的请求
     * destJsp：目标URL
     */
    public void service(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
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
                // 输出图象到页面
                ImageOutputStream ios = ImageIO.createImageOutputStream(output);
                ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("png").next();
                imageWriter.setOutput(ios);
                imageWriter.write(image);

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
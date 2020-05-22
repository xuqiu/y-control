package com.yin.ycontrol.screenShotter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 主页
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2020-05-22 15:39
 */
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 8193565819750469797L;

    /**
     * 执行登陆的业务处理
     *
     * @param request:发送上来的请求 destJsp：目标URL
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Content-Type", "text/html; charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream output = response.getOutputStream();
        try {
            final InputStream resourceAsStream = IndexServlet.class.getResourceAsStream("/webapp/index.vm");
            int i = resourceAsStream.available();
            byte[] content = new byte[i];
            resourceAsStream.read(content);
            String indexHTML = new String(content, StandardCharsets.UTF_8);
            indexHTML = indexHTML.replaceAll("SCREEN_WIDTH", ""+GuiCamera.getWidth());
            indexHTML = indexHTML.replaceAll("SCREEN_HEIGHT", ""+GuiCamera.getHeight());
            output.write(indexHTML.getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            output.close();
        }
    }
}
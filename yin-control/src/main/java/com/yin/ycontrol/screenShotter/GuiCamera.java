package com.yin.ycontrol.screenShotter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/*******************************************************************
 * This JavaBean is used to snapshot the GUI in a
 * Java application! You can embeded
 * it in to your java application source code, and us
 * it to snapshot the right GUI of the application
 *
 *****************************************************/

public class GuiCamera
{
    private String fileName;
    private String defaultName = "GuiCamera";
    static int serialNum=0;
    private String imageFormat;
    private String defaultImageFormat="png";
    static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    static BufferedImage screenshot;
    static Date lasttime=new Date(new Date().getTime()-10000);

    /****************************************************************
     * The default construct will use the default
     * Image file surname "GuiCamera",
     * and default image format "png"
     ****************************************************************/
    public GuiCamera() {
        fileName = defaultName;
        imageFormat=defaultImageFormat;

    }

    /****************************************************************
     * @param s the surname of the snapshot file
     * @param format the format of the  image file,
     * it can be "jpg" or "png"
     ****************************************************************/
    public GuiCamera(String s,String format) {

        fileName = s;
        imageFormat=format;
    }

    /****************************************************************
     * snapShot the Gui once
     ****************************************************************/
    public void snapShot(BufferedImage screenshot,String format) {

        try {
            Date start=new Date();
            String name=fileName+String.valueOf(serialNum)+"."+format;
            File f = new File(name);
            OutputStream   jos   =   new   FileOutputStream(   f  );
            System.out.print("Save File "+name);
//            JPEGImageEncoder   encoder   =   JPEGCodec.createJPEGEncoder(   jos   );
//            JPEGEncodeParam   jpegEP   =   JPEGCodec.getDefaultJPEGEncodeParam(   screenshot   );
//            jpegEP.setQuality(   (float)   0.1   ,   true   );
//            encoder.encode(   screenshot   ,   jpegEP   );

            ImageOutputStream ios = ImageIO.createImageOutputStream(jos);
            ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("png").next();
            imageWriter.setOutput(ios);
            imageWriter.write(screenshot);
            jos.close();
            imageWriter.dispose();
            serialNum++;


            //ImageIO.write(screenshot, format, f);
            Date end=new Date();
            System.out.print("..Finished! Cost "+(end.getTime()-start.getTime())+"\n");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    public static BufferedImage screenShot() {

        try {
            Date now=new Date();
            if(now.getTime()-lasttime.getTime()>100){
                lasttime=now;
                Robot robot =new Robot();
                screenshot = robot
                    .createScreenCapture(new Rectangle(0, 0,
                        (int) d.getWidth(), (int) d.getHeight()));
                Point p=java.awt.MouseInfo.getPointerInfo().getLocation();
                Graphics g = screenshot.getGraphics();
                g.setColor(Color.red);
                g.fillRoundRect(p.x-10, p.y-10, 20, 20,40,40);
                g.dispose();
                screenshot=getConvertedImage(screenshot);
            }
            return screenshot;
        } catch (Exception ex) {
            System.out.println(ex);
            BufferedImage error = new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_RGB);
            // Get drawing context
            Graphics g = error.getGraphics();
            // Fill background
            g.setColor(Color.blue);
            g.drawString("��ȡ��Ļͼ�����", 10, 10);
            g.dispose();
            return error;
        }

    }
    private static BufferedImage getConvertedImage(BufferedImage image){
        int width=image.getWidth();
        int height=image.getHeight();
        BufferedImage convertedImage;
        Graphics2D g2D;
        convertedImage=new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB);
        g2D = (Graphics2D) convertedImage.getGraphics();
        g2D.drawImage(image, 0, 0, null);
        g2D.drawImage(convertedImage, 0, 0, null);
        g2D.dispose();
        return convertedImage;
    }

    public static void main(String[] args)
    {
        GuiCamera cam= new GuiCamera("d:\\111\\Hello", "png");//
        BufferedImage screenshot = screenShot();
        cam.snapShot(screenshot,"JPG");
    }
    public static int getWidth(){
        return (int)d.getWidth();
    }
    public static int getHeight(){
        return (int)d.getHeight();
    }
}
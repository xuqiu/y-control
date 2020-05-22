package com.yin.ycontrol.screenShotter;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * 创建透明的GIF图像
 * @author Winter Lau (javayou@gmail.com)
 * @url http://www.oschina.net
 * @date 2009-8-10 上午09:58:16
 */
public class GifExample {
    public static void main(String[] args) throws IOException {
        boolean ok = ImageIO.write(createImage(), "gif", new File("C://test.gif"));
        System.out.println("success=" + ok);
    }
 
    static BufferedImage createImage() {
        IndexColorModel cm = createIndexColorModel();;
        BufferedImage im = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_INDEXED, cm);
        Graphics2D g = im.createGraphics();
        g.setColor(new Color(0,0,0,0)); //transparent
        g.fillRect(0, 0, 100, 100);
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.GREEN);
        g.fillRect(50, 50, 50, 50);
        g.dispose();
        return im;
    }
 
    static IndexColorModel createIndexColorModel() {
        BufferedImage ex = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
        IndexColorModel icm = (IndexColorModel) ex.getColorModel();
        int SIZE = 256;
        byte[] r = new byte[SIZE];
        byte[] g = new byte[SIZE];
        byte[] b = new byte[SIZE];
        byte[] a = new byte[SIZE];
        icm.getReds(r);
        icm.getGreens(g);
        icm.getBlues(b);
        java.util.Arrays.fill(a, (byte)255);
        r[0] = g[0] = b[0] = a[0] = 0; //transparent
        return  new IndexColorModel(8, SIZE, r, g, b, a);
    }
}
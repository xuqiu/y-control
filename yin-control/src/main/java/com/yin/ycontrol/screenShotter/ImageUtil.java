package com.yin.ycontrol.screenShotter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
	private static final int CATCH_SIZE = 20;
	private static final int INTERVAL = 200;
	private static Long frame = 1L;
	static BufferedImage lastImage = null;
	static BufferedImage currentImage = null;
	private static Map<Long, BufferedImage> imageCache = new HashMap<Long, BufferedImage>();
	static Date last = null;
	
	/**
	 * 
	 * @param min 客户端最后一帧的编号
	 * @param max 客户端刚刚获取的最新一帧的编号(在请求图像前调用接口获取)
	 * @return
	 */
	public static BufferedImage getImage(Long min,Long max){

		Date now = new Date();
		//时间间隔大于INTERVAL时才刷新图像(
		//优点:1没有请求时服务端不会自己刷寻图像
		//	   2请求数量再多,也不会造成内存压力,大家共享缓存图片资源
		if(last==null||now.getTime()-last.getTime()>INTERVAL){
			currentImage = GuiCamera.screenShot();
			BufferedImage cache = ImageUtil.subImage(lastImage, currentImage);
			//缓存变化部分
			if(lastImage!=null){
				catchImage(cache);
			}
			lastImage=currentImage;
			last=now;
		}
		
		//获取需要的图像
		//如果时间过长,返回整张图像. 否则只返回变化的不分
		if(imageCache.get(min)==null){
			return currentImage;
		}else if(min>=max){//也许是客户端请求过快)
			return empty();
		}else{
			return getImageCatch(min,max);
		}
	}
	
	private static void catchImage(BufferedImage image){
		if(frame>=9223372036854775806L){//防止越界
			frame=0L;
			imageCache=new HashMap<Long, BufferedImage>();
		}
		imageCache.put(frame, image);
		if(imageCache.size()>CATCH_SIZE){
			imageCache.remove(new Long(frame-CATCH_SIZE));
		}
		frame+=1;
	}
	private static BufferedImage getImageCatch(Long min,Long max){
		BufferedImage result = null;
		for(Long i=min+1;i<=max;i++){
			result=drawCache(result,imageCache.get(i));
		}
		return result;
	}

	public static BufferedImage drawCache(BufferedImage image,BufferedImage cache){
		if(image==null){
			return cache;
		}
		int width = cache.getWidth();
		int height = cache.getHeight();
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				int p=cache.getRGB(x, y);
				Color c=new Color(p);
				if(c.getAlpha()==0){
					image.setRGB(x, y, p);
				}
				
			}
		}
		return image;
	}
	/**
	 * 返回新旧图片的差异
	 * @param image1 旧图片
	 * @param image2 新图片
	 * @return 返回新旧图片的差异
	 */
	public static BufferedImage subImage(BufferedImage image1, BufferedImage image2){
		if(image1==null){
			return image2;
		}
		int width = image1.getWidth();
		int height = image1.getHeight();
	     //采用带1 字节alpha的TYPE_4BYTE_ABGR，可以修改像素的布尔透明  
	    
		IndexColorModel cm = createIndexColorModel();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED,cm);
		

		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				int p1=image1.getRGB(x, y);
				int p2=image2.getRGB(x, y);
				if(p1!=p2){
					result.setRGB(x, y, p2);
				}
				
			}
		}
		
		return result;
	}
	public static BufferedImage empty(){
		int width = lastImage.getWidth();
		int height = lastImage.getHeight();
	     //采用带1 字节alpha的TYPE_4BYTE_ABGR，可以修改像素的布尔透明  
	    
		IndexColorModel cm = createIndexColorModel();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED,cm);
		return result;
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
    public static Long getFrame(){
    	return frame;
    }
}

package com.yin.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public final class ConfigUtil {

	static Properties p = null;   

	static void init() {
		try {
			InputStream in = ConfigUtil.class.getClassLoader()
					.getResourceAsStream("config.properties");
			p = new Properties();
			p.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static String get(String key){
		if(p==null){
			init();
		}else{
			return p.getProperty(key);
		}
		return null;
	}
}

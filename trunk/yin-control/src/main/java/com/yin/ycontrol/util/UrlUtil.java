package com.yin.ycontrol.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {
    public static String getUrl() {
        InputStream in = null;
        OutputStream out = null;
        try {
            // ��������в���
            if (false)
                throw new IllegalArgumentException("Wrong number of args");
             // URL url = new URL("http://www.ip138.com/ip2city.asp"); //���� URL
            // �õ�һ��������
            // URL url = new URL("http://202.105.81.135/showip/"); //���� URL
             //URL url = new URL("http://www.ip.cn/"); //���� URL
            URL url = new URL("http://www.118114ok.com/resin-admin/"); // ���� URL
            in = url.openStream(); // �򿪵����URL����
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String inputLine = "";
            String pageStr="";
            while ((inputLine = reader.readLine()) != null)   
             {
                
                pageStr+=inputLine;
             }
           String regex = "\\d*\\.\\d*\\.\\d*\\.\\d*";  
           Pattern p = Pattern.compile(regex);  
           Matcher m = p.matcher(pageStr);  
           String val = null;
           if (m.find()){  
             val = m.group(); 
           }
           return val;
        }catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java GetURL <URL> [<filename>]");
        } finally { // ������ζ�Ҫ�ر���
            try {
                in.close();
                out.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
    public static void main(String[] args){
    	String result = getUrl();
    	System.err.println(result);
    }
}
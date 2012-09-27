package com.yin.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @Type MD5Util
 * @Desc MD5������
 * @author hongxia.huhx
 * @date 2012-3-4
 * @Version V1.0
 */
public class MD5Util {

	private static ThreadLocal<MessageDigest> messageDigestHolder = new ThreadLocal<MessageDigest>();

	// �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static {
		try {
			MessageDigest message = java.security.MessageDigest.getInstance("MD5");
			messageDigestHolder.set(message);
		} catch (NoSuchAlgorithmException e) {
		}
	}

	/***
	 * 
	 * @Title: getMD5Format
	 * @Description: ����MD5��ת��Ϊ32�ֽ�������ʾ��
	 * @author wujl
	 * @param data
	 * @return String ��������
	 */
	public static String getMD5Format(String data) {
		try {
			MessageDigest message = messageDigestHolder.get();
			if (message == null) {
				message = java.security.MessageDigest.getInstance("MD5");
				messageDigestHolder.set(message);
			}
			message.update(data.getBytes("UTF-8"));
			byte[] b = message.digest();

			String digestHexStr = "";
			for (int i = 0; i < 16; i++) {
				digestHexStr += byteHEX(b[i]);
			}

			return digestHexStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ����MD5��ת��Ϊ32�ֽ�������ʾ��
	 * @param data
	 * @return
	 */
	   public static String getMD5Format(String[] data) {
	        try {
	            MessageDigest message = messageDigestHolder.get();
	            if (message == null) {
	                message = java.security.MessageDigest.getInstance("MD5");
	                messageDigestHolder.set(message);
	            }
	            for (int i = 0; i < data.length; i++) {
	                message.update(data[i].getBytes("UTF-8"));
                }
	            byte[] b = message.digest();

	            String digestHexStr = "";
	            for (int i = 0; i < 16; i++) {
	                digestHexStr += byteHEX(b[i]);
	            }

	            return digestHexStr;
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
	

	/***
	 * 
	 * @Title: byteHEX
	 * @Description:
	 * @author wujl
	 * @param ib
	 * @return String ��������
	 */
	private static String byteHEX(byte ib) {
		char[] ob = new char[2];
		ob[0] = hexDigits[(ib >>> 4) & 0X0F];
		ob[1] = hexDigits[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}

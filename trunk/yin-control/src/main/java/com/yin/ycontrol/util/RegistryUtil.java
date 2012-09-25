package com.yin.ycontrol.util;

import java.util.Enumeration;

//import com.ice.jni.registry.*;

public class RegistryUtil {
	/*public static void updateMac(){
		try{
			RegistryKey r = Registry.HKEY_LOCAL_MACHINE.
			openSubKey("SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002bE10318}");
			String realRegName=null;
			Enumeration<?> enums = r.keyElements();
	        while (enums.hasMoreElements()) {
	        	 String childRKName = (String)enums.nextElement();
	        	 RegistryKey childMac = r.openSubKey(childRKName);
	        	 String driverDesc = null;
	        	 try{
	        		 driverDesc = childMac.getStringValue("DriverDesc");
	        	 }catch(Exception ex){}
	        	 if(driverDesc!=null&&driverDesc.indexOf("Realtek")>-1){
	        		 realRegName=childRKName;
	        		 break;
	        	 }
	        }
			RegistryKey macKey = r.openSubKey(realRegName);
			String v=macKey.getStringValue("NetworkAddress");
			String last4s=v.substring(8);
			int last4=1000;
			try{
				last4=Integer.parseInt(last4s);
			}catch(Exception ex){}
			last4++;
			RegistryKey subKey = r.createSubKey(realRegName, "");
//			if(v!=null) {  
//			   System.out.println(v.toString());//  
//			} 
			subKey.setValue(new RegStringValue(subKey, "NetworkAddress",
	           "000C0F0F"+last4));
			subKey.closeKey();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args){
		updateMac();
	}
	*/
}
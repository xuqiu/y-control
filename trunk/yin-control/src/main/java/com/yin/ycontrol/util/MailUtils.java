package com.yin.ycontrol.util;

import java.util.Date;  
import java.util.Enumeration;  
import java.util.Properties;  
import java.util.Vector;  
  
import javax.activation.*;  
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.Multipart;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
import javax.mail.internet.MimeUtility;  
  
/** 
 * <p> 
 * Title: ʹ��javamail�����ʼ� 
 * </p> 
 */  
public class MailUtils {  
  
    String to = "";// �ռ���  
    String from = "";// ������  
    String host = "";// smtp����  
    String username = "";  
    String password = "";  
    String filename = "";// �����ļ���  
    String subject = "";// �ʼ�����  
    String content = "";// �ʼ�����  
    Vector file = new Vector();// �����ļ�����  
  
    /** 
     * <br> 
     * ����˵����Ĭ�Ϲ����� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public MailUtils() {  
    }  
  
    /** 
     * <br> 
     * ����˵�������������ṩֱ�ӵĲ������� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public MailUtils(String to, String from, String smtpServer,  
            String username, String password, String subject, String content) {  
        this.to = to;  
        this.from = from;  
        this.host = smtpServer;  
        this.username = username;  
        this.password = password;  
        this.subject = subject;  
        this.content = content;  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ���������ַ <br> 
     * ���������String host �ʼ���������ַ���� <br> 
     * �������ͣ� 
     */  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    /** 
     * <br> 
     * ����˵�������õ�¼������У������ <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setPassWord(String pwd) {  
        this.password = pwd;  
    }  
  
    /** 
     * <br> 
     * ����˵�������õ�¼������У���û� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setUserName(String usn) {  
        this.username = usn;  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ�����Ŀ������ <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setTo(String to) {  
        this.to = to;  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ�����Դ���� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setFrom(String from) {  
        this.from = from;  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ����� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ����� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void setContent(String content) {  
        this.content = content;  
    }  
  
    /** 
     * <br> 
     * ����˵����������ת��Ϊ���� <br> 
     * ���������String strText <br> 
     * �������ͣ� 
     */  
    public String transferChinese(String strText) {  
        try {  
            strText = MimeUtility.encodeText(new String(strText.getBytes(),  
                    "GB2312"), "GB2312", "B");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return strText;  
    }  
  
    /** 
     * <br> 
     * ����˵�����������������Ӹ��� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public void attachfile(String fname) {  
        file.addElement(fname);  
    }  
  
    /** 
     * <br> 
     * ����˵���������ʼ� <br> 
     * ��������� <br> 
     * �������ͣ�boolean �ɹ�Ϊtrue����֮Ϊfalse 
     */  
    public boolean sendMail() {  
  
        // ����mail session  
        Properties props = new Properties() ;  
        if(this.host.indexOf("smtp.gmail.com")>=0)
        {
        	props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
        	props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
        	props.setProperty("mail.smtp.port", "465"); 
        	props.setProperty("mail.smtp.socketFactory.port", "465"); 
        }
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true");  
        Session session = Session.getDefaultInstance(props,  
                new Authenticator() {  
                    public PasswordAuthentication getPasswordAuthentication() {  
                        return new PasswordAuthentication(username, password);  
                    }  
                });  
        //Session session = Session.getDefaultInstance(props);  
//      Session session = Session.getDefaultInstance(props, null);  
  
        try {  
            // ����MimeMessage ���趨������ֵ  
            MimeMessage msg = new MimeMessage(session);  
            //MimeMessage msg = new MimeMessage();  
            msg.setFrom(new InternetAddress(from));  
           
              
            //msg.addRecipients(Message.RecipientType.TO, address); //���ֻ���Ǹ�һ���˷���email  
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to)) ;  
            subject = transferChinese(subject);  
            msg.setSubject(subject);  
  
            // ����Multipart  
            Multipart mp = new MimeMultipart();  
  
            // ��Multipart�������  
            MimeBodyPart mbpContent = new MimeBodyPart();  
            mbpContent.setContent(content, "text/html;charset=gb2312");  
              
            // ��MimeMessage��ӣ�Multipart�������ģ�  
            mp.addBodyPart(mbpContent);  
  
            // ��Multipart��Ӹ���  
            Enumeration efile = file.elements();  
            while (efile.hasMoreElements()) {  
  
                MimeBodyPart mbpFile = new MimeBodyPart();  
                filename = efile.nextElement().toString();  
                FileDataSource fds = new FileDataSource(filename);  
                mbpFile.setDataHandler(new DataHandler(fds));  
                //����������Խ�������������⡣
                String filename= new String(fds.getName().getBytes(),"ISO-8859-1");  
  
                mbpFile.setFileName(filename);  
                // ��MimeMessage��ӣ�Multipart��������  
                mp.addBodyPart(mbpFile);  
  
            }  
  
            file.removeAllElements();  
            // ��Multipart���MimeMessage  
            msg.setContent(mp);  
            msg.setSentDate(new Date());  
            msg.saveChanges() ;  
            // �����ʼ�  
              
            Transport transport = session.getTransport("smtp");  
            transport.connect(host, username, password);  
            transport.sendMessage(msg, msg.getAllRecipients());  
            transport.close();  
        } catch (Exception mex) {  
            mex.printStackTrace();  
//          Exception ex = null;  
//          if ((ex = mex.getNextException()) != null) {  
//              ex.printStackTrace();  
//          }  
            return false;  
        }  
        return true;  
    }  
  
  
      
    /** 
     * <br> 
     * ����˵���������������ڲ��� <br> 
     * ��������� <br> 
     * �������ͣ� 
     */  
    public static void send(String title,String content) {  
        MailUtils sendmail = new MailUtils();  
          
        sendmail.setHost("smtp.gmail.com");  
        sendmail.setUserName("yinzhennan123");  
        sendmail.setPassWord("123qweqwe");  
        sendmail.setTo("yinzhennan123@gmail.com");  
        sendmail.setFrom("yinzhennan123@gmail.com");  
        sendmail.setSubject(title);  
        sendmail.setContent(content);  
        // Mail sendmail = new  
        // Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","���","θ�������");  
//        sendmail.attachfile("d:\\news.rar");  
//        sendmail.attachfile("d:\\jhjl.rar");  
          
        System.out.println(sendmail.sendMail());  
  
    }  
}  
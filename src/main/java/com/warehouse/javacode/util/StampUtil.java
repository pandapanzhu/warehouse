package com.warehouse.javacode.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class StampUtil {
	
	Session session;
	
	public static void sendEmailToUser(String keyCode){
		if ("salaryupdate" .equals(keyCode)){
			String subHead="更新员工工资情况出错记录";
			String timeDate=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String content="Hi Sir<br> something wrong when update stuff`s salary,need your check"
					+"<br>now date is:"+timeDate;
			String receiptUser="375160958@qq.com";
			sendEmail(subHead, content, receiptUser);
		}
	}	
	
	
	public static boolean sendEmail(String MailHead,String MailContent,String receiptUser){
		try {
		Properties props=new Properties();
		//配置发送和接受的协议
		 props.put("username", "18108190650@163.com");   
         props.put("password", "ZSlaoyao1");
         props.put("mail.transport.protocol", "smtp" );  
         props.put("mail.smtp.host", "smtp.163.com");  
         props.put("mail.smtp.port", "25" );
		props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress("18108190650@163.com"));
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiptUser));
		message.setSubject(MailHead);//主题
		message.setContent(MailContent, "text/html;charset=utf-8");
		message.saveChanges();
		Transport transport=session.getTransport("smtp");
		transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("username"), props.getProperty("password"));
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

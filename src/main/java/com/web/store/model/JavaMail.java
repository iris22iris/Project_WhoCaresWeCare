package com.web.store.model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
	// --------------- 基本資料
	private String userName = "java016030924@gmail.com"; // 寄件者email
	private String passWord = "odoayywtzmaexumf"; // 寄件者密碼 到自己的google帳號去設定2階段驗證
	private String customer = "收件者名稱@gmail.com"; // 收件者email
	private String subject = "測試信件"; // 信件標題
	private String txt = "嗨華晏 請問您您您有看到嘛! This is a test Mail!"; // 內容

	public JavaMail(String userName, String passWord, String customer, String subject, String txt) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.customer = customer;
		this.subject = subject;
		this.txt = txt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	@SuppressWarnings("static-access")
	public void sendMail() throws MessagingException {
		// --------------- 連線設定
		Properties prop = new Properties();

		prop.setProperty("mail.transport.protocol", "smtp");
		// host : smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");
		// host port 465
		prop.put("mail.smtp.port", "465");
		// 寄件者帳號 需要驗證：是
		prop.put("mail.smtp.auth", "true");
		// 需要安全資料傳輸層 (SSL)：是
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// 安全資料傳輸層 (SSL) 通訊埠：465
		prop.put("mail.smtp.socketFactory.port", "465");
		
		//顯示連線資訊
		prop.put("mail.debug", "true");
		
		// --------------- 帳號驗證
		// --------------- Session javamail api 默認設定屬性
		// 匿名者類別
		// Session session = Session.getDefaultInstance(prop, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userName, passWord);
//			}
//		});
		// 一般class
		Auth auth = new Auth(userName, passWord);
		Session session = Session.getDefaultInstance(prop, auth);

		// --------------- Message 放入基本資料

		MimeMessage message = new MimeMessage(session);

		try {
			// 寄件者
			// 匿名類別
//			message.setSender(new InternetAddress(userName));
			// 一般class
			InternetAddress sender = new InternetAddress(userName);
			message.setSender(sender);

			// 收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));

			// 標題
			message.setSubject(subject);

			// 內容/格式
			message.setContent(txt, "text/html;charset=UTF-8");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// --------------- Transport將Message傳出去
		Transport transport = session.getTransport();
		transport.send(message);
		transport.close();
	}

}

class Auth extends Authenticator {
	private String userName = "java016030924@gmail.com"; // 寄件者email
	private String passWord = "odoayywtzmaexumf"; // 寄件者密碼

	public Auth(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {

		PasswordAuthentication pa = new PasswordAuthentication(userName, passWord);
		return pa;
	}

}

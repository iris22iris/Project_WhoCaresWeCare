package com.web.store.repository.impl;

import javax.mail.MessagingException;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.JavaMail;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.repository.MailSender;

//@Repository
public class MailSenderImpl implements MailSender {

	JavaMail javamail;			
	
//	@Autowired
	public MailSenderImpl(JavaMail javamail) {		
		this.javamail = javamail;
	}

	@Override
	public void sendRservationInformMail(JavaMail mail) throws MessagingException {
		
		javamail.sendMail();
	}
//	傳入信箱資料
	
	
	


}
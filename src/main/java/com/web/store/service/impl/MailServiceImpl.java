package com.web.store.service.impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.JavaMail;
import com.web.store.repository.MailSender;
import com.web.store.service.MailService;


//@Transactional
//@Service
public class MailServiceImpl implements MailService{

	MailSender mailSender;
	
	
	
//	@Autowired
	public MailServiceImpl(MailSender mailSender) {
		
		this.mailSender = mailSender;
	}


	@Override
	public void sendRservationInformMail(JavaMail mail) throws MessagingException {
		mailSender.sendRservationInformMail(mail);
		
	}

	
	

	
}
package com.web.store.service;

import javax.mail.MessagingException;

import com.web.store.model.JavaMail;

public interface MailService {

	//	傳入信箱資料
	void sendRservationInformMail(JavaMail mail) throws MessagingException;
}
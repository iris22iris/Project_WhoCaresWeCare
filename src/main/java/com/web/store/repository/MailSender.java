package com.web.store.repository;

import javax.mail.MessagingException;

import com.web.store.model.JavaMail;
import com.web.store.model._03_rent.ReservationBean;

public interface MailSender {

//	傳入信箱資料
	public void sendRservationInformMail(JavaMail mail) throws MessagingException ;

}
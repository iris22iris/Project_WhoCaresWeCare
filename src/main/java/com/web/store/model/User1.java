package com.web.store.model;

import javax.mail.MessagingException;

public class User1 {

	public static void main(String[] args) throws MessagingException {
		JavaMail mail = new JavaMail("java016030924@gmail.com","odoayywtzmaexumf",
				"使用者名稱@gmail.com","1測試信件","嗨華晏 請問您您您有看到嘛! This is a test Mail!");
		mail.sendMail();
		
	}

}

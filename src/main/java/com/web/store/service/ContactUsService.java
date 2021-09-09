package com.web.store.service;

import com.web.store.model._02_customerService.ProblemBean;



public interface ContactUsService {
	
	Object save(ProblemBean bean);  //新增一筆聯絡我們記錄
	ProblemBean get(Integer id);
}

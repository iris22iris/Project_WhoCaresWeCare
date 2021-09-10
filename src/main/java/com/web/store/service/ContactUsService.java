package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.ProblemBean;



public interface ContactUsService {
	
	List<ProblemBean> getProblems(); //讀取多筆聯絡我們記錄
	Object save(ProblemBean bean);  //新增一筆聯絡我們記錄
	ProblemBean get(Integer id);
}

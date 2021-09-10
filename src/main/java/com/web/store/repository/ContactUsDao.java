package com.web.store.repository;

import java.util.List;

import com.web.store.model._02_customerService.ProblemBean;



public interface ContactUsDao {
	
	List<ProblemBean> getProblems();
	Object save(ProblemBean bean);  //新增一筆聯絡我們記錄
	ProblemBean get(Integer id);
	
	
	
}

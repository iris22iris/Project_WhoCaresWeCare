package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._02_customerService.ProblemSelectBean;



public interface ContactUsService {
	ProblemBean getProblemById(Integer usId);        //依主鍵找客服編號
	List<ProblemBean> getProblems(); //讀取多筆聯絡我們記錄
	Object save(ProblemBean bean);  //新增一筆聯絡我們記錄
//	ProblemBean get(Integer id);
	List<ProblemSelectBean> queryProblemSelect(String problemType);
	ProblemBean getProblemById(int usId);
}

package com.web.store.repository;

import java.util.List;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._02_customerService.ProblemSelectBean;



public interface ContactUsDao {
	
	
	List<ProblemBean> getProblems();
	Object save(ProblemBean bean);  //新增一筆聯絡我們記錄
//	ProblemBean get(Integer id);
	
	List<ProblemSelectBean> queryProblemSelect(String problemType);
//	ProblemBean findProblemById(int usId);
//	ProblemBean findProblemById(Integer usId);
	
	ProblemBean getProblemById(Integer usId);
	List<ProblemBean> getProblemsById(Integer usId);
	
	
}

package com.web.store.repository;


import java.util.List;
import com.web.store.model._02_customerService.ProblemSelectBean;

public interface ProblemSelectDao {
//	List<ProblemSelectBean> getAllProblemTypes(String problemType);
	
	List<ProblemSelectBean> getAllProblemTypes(String problemType);
	ProblemSelectBean getAllProblemType(Integer id);
	
}

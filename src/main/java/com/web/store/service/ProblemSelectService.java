package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.ProblemSelectBean;


public interface ProblemSelectService {

	List<ProblemSelectBean> getAllProblemTypes(String problemType);
	ProblemSelectBean getAllProblemType(Integer id);

}


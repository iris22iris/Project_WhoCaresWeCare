package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.repository.ProblemSelectDao;
import com.web.store.service.ProblemSelectService;


@Service
@Transactional
public class ProblemSelectServiceImpl implements ProblemSelectService {

	@Autowired
	ProblemSelectDao problemSelectDao;
	
	

//	@Override
//	public List<ProblemSelectBean> getAllProblemTypes(String problemType){
//		return problemSelectDao.getAllProblemTypes(problemType);
//		}
	
	@Override
	public List<ProblemSelectBean> getAllProblemTypes(String problemType){
		return problemSelectDao.getAllProblemTypes(problemType);
		}

	@Override
	public ProblemSelectBean getAllProblemType(Integer id){
	return problemSelectDao.getAllProblemType(id);
	}

}
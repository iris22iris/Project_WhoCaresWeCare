package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.repository.ContactUsDao;
import com.web.store.service.ContactUsService;





@Transactional
@Service
public class ContactUsServiceImpl implements ContactUsService {
	
	ContactUsDao contactUsDao;
	
	@Autowired
	public void setContactUsDao(ContactUsDao contactUsDao) {
		this.contactUsDao = contactUsDao;
	}
	
	@Override
	public List<ProblemBean> getProblems() {
		return contactUsDao.getProblems();
	}
	
	@Override
	public Object save(ProblemBean bean) {
		return contactUsDao.save(bean);
	}

	@Override
	public List<ProblemSelectBean> queryProblemSelect(String problemType) {
		return contactUsDao.queryProblemSelect(problemType) ;
	}

//	@Override
//	public ProblemBean getProblemById(int usId) {
//		return contactUsDao.findProblemById(usId);   
//	}

	@Override
	public ProblemBean getProblemById(Integer replyId) {
		return contactUsDao.getProblemById(replyId);   
	}

	@Override
	public List<ProblemBean> getProblemsById(Integer replyId) {
		return contactUsDao.getProblemsById(replyId);   
	}

//	@Override
//	public ProblemBean get(Integer id) {
//
//		return contactUsDao.get(id);
//	}


	

}
package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._06_order.OrdBean;
import com.web.store.repository.ContactUsOrdDao;
import com.web.store.service.ContactUsOrdService;


@Service
@Transactional
public class ContactUsOrdServiceImpl implements ContactUsOrdService {
	
	@Autowired
	ContactUsOrdDao contactUsOrdDao;

	@Override
	public List<OrdBean> findOrdBeanById(Integer ordId) {
		
		return contactUsOrdDao.findOrdBeanById(ordId);
	}

	



}
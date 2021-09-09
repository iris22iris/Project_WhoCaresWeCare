package com.web.store.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.repository.ContactUsDao;

@Repository
public class ContactUsDaoImpl implements ContactUsDao {

	SessionFactory factory;
	
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public ContactUsDaoImpl() {
		
	}
	
	@Override
	public Object save(ProblemBean bean) {
		
		Session session = factory.getCurrentSession();
		return session.save(bean);
	}

	@Override
	public ProblemBean get(Integer id) {
		return factory.getCurrentSession().get(ProblemBean.class,id);
	}

	
}

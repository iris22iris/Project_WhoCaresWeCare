package com.web.store.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._05_customer.CustomerBean;
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProblemBean> getProblems() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProblemBean";
		List<ProblemBean> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
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

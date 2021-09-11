package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.repository.ProblemSelectDao;

@Repository
public class ProblemSelectDaoImpl implements ProblemSelectDao{
	
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProblemSelectBean> getAllProblemTypes(String problemType) {
		String hql = "FROM ProblemSelect";
		Session session = getSession();
		return session.createQuery(hql).getResultList();
	}
		

	private Session getSession() {
		return factory.getCurrentSession();
	}



	@Override
	public ProblemSelectBean getAllProblemType(Integer id) {
		Session session = getSession();
		return session.get(ProblemSelectBean.class,id);
	}
	
}
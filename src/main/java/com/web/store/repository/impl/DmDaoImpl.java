package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.DmBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.repository.DmDao;
@Repository
public class DmDaoImpl implements DmDao {

	SessionFactory factory;
	
	@Autowired
	public DmDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<DmBean> getAllDms() {
		Session session = factory.getCurrentSession();
		String hql = " FROM DmBean d ";
		return session.createQuery(hql, DmBean.class).getResultList();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<DmBean> getDmid(Integer dmId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM DmBean d WHERE d.dmId = :did ";
		List<DmBean> list = session.createQuery(hql)
										.setParameter("did", dmId)
										.getResultList();
		return list;
	}
	
}

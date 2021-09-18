package com.web.store.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.repository.RentItemDao;

@Repository
public class RentItemDaoImpl implements RentItemDao {
	
	SessionFactory factory;

	@Autowired
	public RentItemDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public RentItemBean findRentItemByPK(RentItemPK rentItemPK) {
		Session session = factory.getCurrentSession();
		return session.get(RentItemBean.class , rentItemPK);
	}
	
	@Override
	public void updateRentItem(RentItemBean rentItemBean) {
		Session session = factory.getCurrentSession();
		session.update(rentItemBean);
	}
	
}
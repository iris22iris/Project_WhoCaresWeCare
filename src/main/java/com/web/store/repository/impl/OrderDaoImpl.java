package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.repository.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	SessionFactory factory;

	public OrderDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}


	@Override
	public PromotionBean findbyDiscountCode(String discountCode) {
		Session session = factory.getCurrentSession();
		String hql = " FROM PromotionBean pb WHERE pb.discountCode = :dc ";
		
		return session.createQuery(hql,PromotionBean.class)
					  .setParameter("dc", discountCode)
					  .getSingleResult();
	}

}

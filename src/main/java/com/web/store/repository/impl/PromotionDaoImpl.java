package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.repository.PromotionDao;

@Repository
public class PromotionDaoImpl implements PromotionDao {

	SessionFactory factory;

	@Autowired
	public PromotionDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

//	取得所有優惠類別
	@Override
	public List<PromotionBean> getAllPromotions() {
		Session session = factory.getCurrentSession();
		String hql = " FROM PromotionBean ";

		return session.createQuery(hql, PromotionBean.class).getResultList();
	}
	
//	依主鍵查詢產品類別
	@Override
	public PromotionBean findPromotionBeanById(Integer promoteId) {
		Session session = factory.getCurrentSession();
		if (promoteId != null) {
			return session.get(PromotionBean.class, promoteId);
		} else {
			return null;
		}
	}
	
}
package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.OrderManageDao;

@Repository
public class OrderManageDaoImpl implements OrderManageDao {

	SessionFactory factory;

	@Autowired
	public OrderManageDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

//	管理者查詢所有訂單
	@Override
	public List<OrdBean> findOrdBeansByCategory(String category) {
		Session session = factory.getCurrentSession();
		String hql = " FROM OrdBean ob  WHERE ob.ordPK.category = :category ";
		if (session.createQuery(hql, OrdBean.class).setParameter("category", category).getResultList().size() > 0) {
			return session.createQuery(hql, OrdBean.class).setParameter("category", category).getResultList();
		} else {
			return null;
		}
	}

//	管理者使用複合主鍵查詢訂單
	@Override
	public OrdBean findOrdBeanById(String category, Integer ordId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM OrdBean ob WHERE ob.ordPK = :pk ";
		if (session.createQuery(hql, OrdBean.class).setParameter("pk", new OrdPK(category, ordId)).getResultList()
				.size() > 0) {
			return session.createQuery(hql, OrdBean.class).setParameter("pk", new OrdPK(category, ordId))
					.getSingleResult();
		} else {
			return null;
		}
	}

}
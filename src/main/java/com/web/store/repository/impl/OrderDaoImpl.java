package com.web.store.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.controller.BuyCheckoutController;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao {
	public static final int orderOffset = 0;
	public static final int lastOrder = 1;
	private static Logger log = LoggerFactory.getLogger(BuyCheckoutController.class);
	SessionFactory factory;
	
	@Autowired
	public OrderDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	
	@Override
	public PromotionBean findByDiscountCode(String discountCode) {
		Session session = factory.getCurrentSession();
		String hql = " FROM PromotionBean pb WHERE pb.discountCode = :dc ";
		
		return session.createQuery(hql,PromotionBean.class)
					  .setParameter("dc", discountCode)
					  .getSingleResult();
	}


	@Override
	public void save(OrdBean ordBean) {
		Session session = factory.getCurrentSession();
		session.save(ordBean);
	}


	@Override
	public OrdPK getCurrentOrdId() {
		Session session = factory.getCurrentSession();
		log.info("準備HQL查詢id:");
		String hql = "SELECT ob.ordPK FROM OrdBean ob ORDER BY ob.orderDate DESC "; 
		OrdPK ord = session.createQuery(hql,OrdPK.class)
							 .setFirstResult(orderOffset)
							 .setMaxResults(lastOrder)
							 .getSingleResult();
		log.info("HQL查詢id:"+ ord);
		return  ord;
	}



	
	

}

package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.OrderQueryDao;

@Repository
public class OrderQueryDaoImpl implements OrderQueryDao {
	
	SessionFactory factory;
	
	@Autowired
	public OrderQueryDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
//	使用複合主鍵查詢訂單
	@Override
	public OrdBean findOrdBeanById(Integer custId, String category, Integer ordId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM OrdBean ob WHERE ob.ordPK = :pk AND ob.customerBean.custId = :cid ";
//		OrdBean ordBean = session.get(OrdBean.class, new OrdPK(category, ordId)); 
		if (session.createQuery(hql, OrdBean.class).setParameter("pk", new OrdPK(category, ordId))
				  .setParameter("cid", custId).getResultList().size() > 0) {
			return session.createQuery(hql, OrdBean.class)
						  .setParameter("pk", new OrdPK(category, ordId))
						  .setParameter("cid", custId)
						  .getSingleResult();
		} else {
			return null;
		}
		
	}
	
//	使用訂單編號查詢購買細項
	@Override
	public List<BuyItemBean> findBuyItemByOrdId(Integer ordId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM BuyItemBean WHERE ordBean = :ob ";

		return session.createQuery(hql, BuyItemBean.class)
					  .setParameter("ob", new OrdBean(new OrdPK("B", ordId)))
				   	  .getResultList();
	}
	
//	使用訂單編號查詢租賃細項
	@Override
	public List<RentItemBean> findRentItemByOrdId(Integer ordId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentItemBean WHERE ordBean = :ob ";

		return session.createQuery(hql, RentItemBean.class)
					  .setParameter("ob", new OrdBean(new OrdPK("R", ordId)))
				   	  .getResultList();
	}
	
}
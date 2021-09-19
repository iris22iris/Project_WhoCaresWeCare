package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.repository.ContactUsOrdDao;

@Repository
public class ContactUsOrdImpl implements ContactUsOrdDao {
	
	SessionFactory factory;
	
	@Autowired
	public ContactUsOrdImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
//	使用複合主鍵查詢訂單
//	@Override
//	public OrdBean findOrdBeanById(String category, Integer ordId) {
//		Session session = factory.getCurrentSession();
//		OrdBean ordBean = session.get(OrdBean.class, new OrdPK(category, ordId)); 
//		return ordBean;
//	}
//	使用複合主鍵查詢訂單custid
	@Override
	public List<OrdBean> findOrdBeanById(Integer custid) {
		Session session = factory.getCurrentSession();
		String hql = " FROM OrdBean WHERE  ord_custid_fk = :ob ";
		
		return session.createQuery(hql, OrdBean.class)
				.setParameter("ob", custid)
			   	  .getResultList();
	}
	
//	使用訂單編號查詢購買細項
//	@Override
//	public List<BuyItemBean> findBuyItemByOrdId(Integer ordId) {
//		Session session = factory.getCurrentSession();
//		String hql = " FROM BuyItemBean WHERE ordBean = :ob ";
//
//		return session.createQuery(hql, BuyItemBean.class)
//					  .setParameter("ob", new OrdBean(new OrdPK("B", ordId)))
//				   	  .getResultList();
//	}
	
//	使用訂單編號查詢租賃細項
//	@Override
//	public List<RentItemBean> findRentItemByOrdId(Integer ordId) {
//		Session session = factory.getCurrentSession();
//		String hql = " FROM RentItemBean WHERE ordBean = :ob ";
//
//		return session.createQuery(hql, RentItemBean.class)
//					  .setParameter("ob", new OrdBean(new OrdPK("R", ordId)))
//				   	  .getResultList();
//	}
	
}
package com.web.store.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.repository.BuyItemDao;

@Repository
public class BuyItemDaoImpl implements BuyItemDao {
	
	SessionFactory factory;

	@Autowired
	public BuyItemDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
//	使用複合主鍵查詢購買細項
	@Override
	public BuyItemBean findBuyItemByPK(BuyItemPK buyItemPK) {
		Session session = factory.getCurrentSession();
		return session.get(BuyItemBean.class , buyItemPK);
	}
	
//	更新購買細項
	@Override
	public void updateBuyItem(BuyItemBean buyItemBean) {
		Session session = factory.getCurrentSession();
		session.update(buyItemBean);
	}
	
}
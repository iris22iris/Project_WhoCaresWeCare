package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.ProductTypeDao;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {

	SessionFactory factory;

	@Autowired
	public ProductTypeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
//	取得所有產品類別
	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductTypeBean";
		return session.createQuery(hql, ProductTypeBean.class).getResultList();
	}
	
//	依主鍵查詢產品類別
	@Override
	public ProductTypeBean findProductTypeBeanById(String prodType) {
		Session session = factory.getCurrentSession();
		if (prodType != null) {
			return session.get(ProductTypeBean.class, prodType);
		} else {
			return null;
		}
	}

}
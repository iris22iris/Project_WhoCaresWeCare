package com.web.store.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.RentProductDao;
import com.web.store.model._03_rent.RentProductBean;

@Repository
public class RentProductDaoImpl implements RentProductDao {

	SessionFactory factory;

	@Autowired
	public RentProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<RentProductBean> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean";
		return session.createQuery(hql, RentProductBean.class)
					  .getResultList();
	}

	@Override
	public List<RentProductBean> getProductsByProdType(String prodtype) {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean rp WHERE rp.classify = :pt";
		return session.createQuery(hql, RentProductBean.class).setParameter("pt", prodtype).getResultList();
	}

	@Override
	public List<String> getAllCategories() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT pt.prodName FROM ProductTypeBean pt";
		return session.createQuery(hql, String.class).getResultList();
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
	}

	@Override
	public RentProductBean getProductById(int productId) {
		return null;
	}

	@Override
	public void addProduct(RentProductBean product) {
	}

}
package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.RentProductDao;

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
		String hql = " FROM RentProductBean rp "
				   + " GROUP BY rp.prodId ";
		
		return session.createQuery(hql, RentProductBean.class)
				      .getResultList();
	}

	@Override
	public List<RentProductBean> getProductsByProdType(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean rp WHERE rp.productTypeBean = :ptb ";
		List<RentProductBean> list = session.createQuery(hql, RentProductBean.class).setParameter("ptb", prodTypeBean).getResultList();
		return list;
	}

	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductTypeBean";
		return session.createQuery(hql, ProductTypeBean.class).getResultList();
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
	}

	@Override
	public RentProductBean getProductById(int prodId) {
		Session session = factory.getCurrentSession();
		RentProductBean rpb = session.get(RentProductBean.class,prodId);
		return rpb;
	}
	
	@Override
	public void addProduct(RentProductBean product) {
	}

}
package com.web.store.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.ProductDao;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

@Repository
public class ProductDaoImpl implements ProductDao {

	SessionFactory factory;

	@Autowired
	public ProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<ProductBean> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		return session.createQuery(hql, ProductBean.class)
				      .getResultList();
	}

	@Override
	public List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb";
		List<ProductBean> list = session.createQuery(hql, ProductBean.class).setParameter("ptb", prodTypeBean).getResultList();
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
	public ProductBean getProductById(int productId) {
		return null;
	}

	@Override
	public void addProduct(ProductBean product) {
	}

}
package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.ProductDao;

import _01_init.util.SystemUtils;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	public static final int recordsPerPage = SystemUtils.RECORDS_PER_PAGE; // 預設值：每頁十二筆
	private int totalPages = -1;
	
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
	public List<ProductBean> getAllProductsByPage(int pageNo) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		return session.createQuery(hql, ProductBean.class)
				      .setFirstResult(startRecordNo)
				      .setMaxResults(recordsPerPage)
					  .getResultList();
	}

	@Override
	public List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		if (sortType != null && sortType != "") {
					hql += " ORDER BY " + sortType + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		return session.createQuery(hql, ProductBean.class)
				.setFirstResult(startRecordNo)
				.setMaxResults(recordsPerPage)
				.getResultList();
	}
	
	@Override
	public int getTotalPages() {
		Session session = factory.getCurrentSession();
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM ProductBean ";
		List<Long> list = session.createQuery(hql, Long.class)
								 .getResultList();
		if (list.size() > 0) {
			count = list.get(0);
		}

		totalPages = (int) (Math.ceil(count / (double) recordsPerPage));
		return totalPages;
	}
	
//	@Override
//	public List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean) {
//		Session session = factory.getCurrentSession();
//		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb ";
//		List<ProductBean> list = session.createQuery(hql, ProductBean.class)
//										.setParameter("ptb", prodTypeBean)
//										.getResultList();
//		return list;
//	}
	
	
	@Override
	public List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<ProductBean> list = session.createQuery(hql, ProductBean.class)
										.setParameter("ptb", prodTypeBean)
										.setFirstResult(startRecordNo)
										.setMaxResults(recordsPerPage)
										.getResultList();
		return list;
	}

	@Override
	public List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo, String sortType) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb ";
		if (sortType != null && sortType != "") {
			hql += " ORDER BY " + sortType + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<ProductBean> list = session.createQuery(hql, ProductBean.class)
				.setParameter("ptb", prodTypeBean)
				.setFirstResult(startRecordNo)
				.setMaxResults(recordsPerPage)
				.getResultList();
		return list;
	}
	
	@Override
	public int getTotalPagesByProdType(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*)  FROM ProductBean p WHERE p.productTypeBean = :ptb ";
		List<Long> list = session.createQuery(hql, Long.class)
								 .setParameter("ptb", prodTypeBean)
								 .getResultList();
		if (list.size() > 0) {
			count = list.get(0);
		}

		totalPages = (int) (Math.ceil(count / (double) recordsPerPage));
		return totalPages;
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
	public ProductBean getProductById(int prodId) {
		Session session = factory.getCurrentSession();
		ProductBean pb = session.get(ProductBean.class,prodId);
		return pb;
	}

	@Override
	public void addProduct(ProductBean product) {
	}
	
}
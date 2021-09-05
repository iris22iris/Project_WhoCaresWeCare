package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._03_rent.pkClass.RentProductPK;
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
		String hql = " FROM RentProductBean rp ";
		
		return session.createQuery(hql, RentProductBean.class)
				      .getResultList();
	}

	@Override
	public List<RentProductBean> getAllGroupedProducts() {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean rp "
				+ " GROUP BY rp.prodId ";
		
		return session.createQuery(hql, RentProductBean.class)
				.getResultList();
	}

	@Override
	public List<Long> getAllStockSum() {
		Session session = factory.getCurrentSession();
		String hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				   + " GROUP BY rp.prodId ";
		
		return session.createQuery(hql, Long.class)
					  .getResultList();
	}

	@Override
	public List<RentProductBean> getGroupedProductsByProdType(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean rp "
				   + " WHERE rp.productTypeBean = :ptb "
				   + " GROUP BY rp.prodId ";
		
		return session.createQuery(hql, RentProductBean.class)
					  .setParameter("ptb", prodTypeBean)
					  .getResultList();
	}
	
	@Override
	public List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				   + " WHERE rp.productTypeBean = :ptb "
				   + " GROUP BY rp.prodId ";
		
		return session.createQuery(hql, Long.class)
					  .setParameter("ptb", prodTypeBean)
					  .getResultList();
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
	//讀取 單筆租賃設備頁面資料  利用prodId
	@Override
	public RentProductBean getProductById(int prodId) {
		Session session = factory.getCurrentSession();
		RentProductBean rpb = session.get(RentProductBean.class, new RentProductPK(prodId,"1"));
		return rpb;
	}
		
	// 依porId(與serialNumber)讀取單筆預約設備資料 抓現在幾人預約
	@Override
	public ReservationBean getReservationBeanByprodId(int prodId) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT r FROM ReservationBean  r"
				
				  + " WHERE r.prodId = :pid AND r.waitNum = (SELECT MAX(r.waitNum)"
				   + "FROM ReservationBean r "
				   + "GROUP BY r.prodId "
				   + "HAVING r.prodId = :pid) ";
		return session.createQuery(hql, ReservationBean.class)
				.setParameter("pid", prodId)
				.getSingleResult();
	}

	@Override
	public void addProduct(RentProductBean product) {
	}

}
package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._03_rent.pkClass.RentProductPK;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.RentProductDao;

import _01_init.util.SystemUtils;

@Repository
public class RentProductDaoImpl implements RentProductDao {

	public static final int recordsPerPage = SystemUtils.RECORDS_PER_PAGE; // 預設值：每頁十二筆
	private int totalPages = -1;
	
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
	public List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo,
			String sortType) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (prodTypeBean.getProdType() != null && prodTypeBean.getProdType() != "" ) {
			hql = " FROM RentProductBean rp "
				+ " WHERE rp.productTypeBean = :ptb "
				+ " GROUP BY rp.prodId ";
		} else {
			hql = " FROM RentProductBean rp "
				+ " GROUP BY rp.prodId ";
		}
		if (sortType != null && sortType != "") {
			String[] token = sortType.split(" ");
			hql += " ORDER BY SUM(" + token[0] + ") " + token[1] + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		if (prodTypeBean.getProdType() != null && prodTypeBean.getProdType() != "" ) {
		return session.createQuery(hql, RentProductBean.class)
					  .setParameter("ptb", prodTypeBean)
			      	  .setFirstResult(startRecordNo)
			      	  .setMaxResults(recordsPerPage)
					  .getResultList();
		} else {
			return session.createQuery(hql, RentProductBean.class)
			      	  .setFirstResult(startRecordNo)
			      	  .setMaxResults(recordsPerPage)
					  .getResultList();
		}
	}

	@Override
	public List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (prodTypeBean.getProdType() != null && prodTypeBean.getProdType() != "" ) {
			hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				+ " WHERE rp.productTypeBean = :ptb "
				+ " GROUP BY rp.prodId ";
		} else {
			hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				+ " GROUP BY rp.prodId ";
		}
		if (sortType != null && sortType != "") {
			String[] token = sortType.split(" ");
			hql += " ORDER BY SUM(" + token[0] + ") " + token[1] + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		if (prodTypeBean.getProdType() != null && prodTypeBean.getProdType() != "" ) {
			return session.createQuery(hql, Long.class)
					  	  .setParameter("ptb", prodTypeBean)
					  	  .setFirstResult(startRecordNo)
					  	  .setMaxResults(recordsPerPage)
					  	  .getResultList();
		} else {
			return session.createQuery(hql, Long.class)
				  	  	  .setFirstResult(startRecordNo)
				  	  	  .setMaxResults(recordsPerPage)
				  	  	  .getResultList();
		}
		
	}
	
	@Override
	public int getGroupedPages(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = "";
		List<Long> list = null;
		long count = 0; // 必須使用 long 型態
		if (prodTypeBean.getProdType() != null && prodTypeBean.getProdType() != "" ) {
			hql = " SELECT count(DISTINCT prodId) FROM RentProductBean rp WHERE rp.productTypeBean = :ptb ";
			list = session.createQuery(hql, Long.class)
					 	  .setParameter("ptb", prodTypeBean)
					 	  .getResultList();
		} else {
			hql = " SELECT count(DISTINCT prodId) FROM RentProductBean rp ";
			list = session.createQuery(hql, Long.class)
						  .getResultList();
		}

		if (list.size() > 0) {
			count = list.get(0);
		}

		totalPages = (int) (Math.ceil(count / (double) recordsPerPage));
		return totalPages;
	}
	
	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductTypeBean ";
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

	//抓取該商品目前租賃評論資料
	@Override
	public List<CommentBean> getCommentBeanByprodId(int prodId) {
		Session session = factory.getCurrentSession();
		String hql =" SELECT c FROM CommentBean c"
//				+" WHERE c.productBean.prodId = :pid ";
				+" WHERE c.rentProductBean.prodId = :pid and c.rentProductBean.serialNumber = 1";
		return session.createQuery(hql,CommentBean.class)
					.setParameter("pid", prodId)
					.getResultList();
	}
	
	@Override
	public void addProduct(RentProductBean product) {
	}

	

}
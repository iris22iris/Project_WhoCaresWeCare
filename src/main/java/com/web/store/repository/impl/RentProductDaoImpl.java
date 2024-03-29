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
import com.web.store.model._05_customer.CustomerBean;
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
	
//	取得所有租賃設備
	@Override
	public List<RentProductBean> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = " FROM RentProductBean rp ";
		
		return session.createQuery(hql, RentProductBean.class)
				      .getResultList();
	}
	
//	取得所有租賃設備並群組同產品不同項次之設備(含頁碼及排序判斷)
	@Override
	public List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo,
			String sortType) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (prodTypeBean != null) {
			hql = " FROM RentProductBean rp "
				+ " WHERE rp.productTypeBean = :ptb "
				+ " GROUP BY rp.prodId ";
		} else {
			hql = " FROM RentProductBean rp "
				+ " GROUP BY rp.prodId ";
		}
		if (sortType != null) {
			String[] token = sortType.split(" ");
			hql += " ORDER BY SUM(" + token[0] + ") " + token[1] + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		if (prodTypeBean != null) {
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
	
//	取得所有租賃設備的總庫存
	@Override
	public List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (prodTypeBean != null) {
			hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				+ " WHERE rp.productTypeBean = :ptb "
				+ " GROUP BY rp.prodId ";
		} else {
			hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
				+ " GROUP BY rp.prodId ";
		}
		if (sortType != null) {
			String[] token = sortType.split(" ");
			hql += " ORDER BY SUM(" + token[0] + ") " + token[1] + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		if (prodTypeBean != null) {
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
	
//	取得租賃設備群組後的總頁數
	@Override
	public int getGroupedPages(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		String hql = "";
		List<Long> list = null;
		long count = 0; // 必須使用 long 型態
		if (prodTypeBean != null) {
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
	
	// 依主鍵讀取租賃設備總庫存
	@Override
	public int getTotalStockByProdId(int prodId) {
		Session session = factory.getCurrentSession();
		String 	hql = " SELECT SUM(rp.stock) FROM RentProductBean rp "
					+ " WHERE rp.prodId = :pid ";
		
		return session.createQuery(hql, Long.class)
					  .setParameter("pid", prodId)
					  .getSingleResult()
					  .intValue();
	}
	
	// 依主鍵+serianumber讀取租賃設備
	@Override
	public RentProductBean getRentProductBeanByProdIdAndSeriaNumber(int prodId , String serialNumber) {
		Session session = factory.getCurrentSession();
		String 	hql = " FROM RentProductBean rp "
					+ " WHERE rp.prodId = :pid "
					+ " AND rp.serialNumber = :sn";
		
		return session.createQuery(hql, RentProductBean.class)
					  .setParameter("pid", prodId)
					  .setParameter("sn", serialNumber)
					  .getSingleResult();
	}

//	更新租賃設備
	@Override
	public void updateRentProduct(RentProductBean rentProductBean) {
		Session session = factory.getCurrentSession();
		session.update(rentProductBean);
	}
	
	//讀取 單筆租賃設備頁面資料  利用prodId
	@Override
	public RentProductBean getProductById(int prodId) {
		Session session = factory.getCurrentSession();
		RentProductBean rpb = session.get(RentProductBean.class, new RentProductPK(prodId,"1"));
		return rpb;
	}
	
	
	// 依prodId取出所有該品項的List
		@Override
		public List<ReservationBean> getReservationBeanByprodId(int prodId) {
			
			Session session = factory.getCurrentSession();
			String hql = " FROM ReservationBean  r"
					
					  + " WHERE r.rentProductBean.prodId = :pid ";
			
			return session.createQuery(hql, ReservationBean.class)
					.setParameter("pid", prodId)
					.getResultList();
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

	@Override
	public void addReservation(ReservationBean reservation) {
		Session session = factory.getCurrentSession();
//	    ReservationBean rb = getCompanyById(product.getCompanyId());
//	    reservation.setCompanyBean(cb);
		session.save(reservation);   
		
	}
	// 依session中使用者帳號讀取目前使用者資料
	@Override
	public  List<CustomerBean> getCustomerInfoByLoginAccount(String account) {
		Session session = factory.getCurrentSession();
		
		String hql =" SELECT c FROM CustomerBean c"
//				+" WHERE c.productBean.prodId = :pid ";
				+" WHERE c.account = :account ";
		return session.createQuery(hql,CustomerBean.class)
					.setParameter("account", account)
					.getResultList();
			
		
	}

	@Override
	public List<ReservationBean> getMyReservationByCustId(int mycustId) {
		Session session = factory.getCurrentSession();
		
		String hql =" SELECT rb FROM ReservationBean rb"
				+" WHERE rb.customerBean.custId = :cid ";
		return session.createQuery(hql,ReservationBean.class)
				.setParameter("cid",  mycustId)
				.getResultList();
	}

	@Override
	public List<ProductTypeBean> getProductTypeBeanBymaincategory(String maincategory) {
		
		Session session = factory.getCurrentSession();
			
		String hql =" SELECT ptb FROM ProductTypeBean ptb"	
					+" WHERE ptb.prodType = :mc ";
		return session.createQuery(hql,ProductTypeBean.class)
					.setParameter("mc",  maincategory)					
					.getResultList();
		
	}
	
	//用商品編號取得該產品及其項次庫存資料
	@Override
	public List<RentProductBean> getAllSerialStocksByprodId(int prodId) {
		Session session = factory.getCurrentSession();
		
		String hql ="  FROM RentProductBean rpb"	
					+" WHERE rpb.prodId = :id ";
		return session.createQuery(hql,RentProductBean.class)
					.setParameter("id",  prodId)					
					.getResultList();
		
	}

}
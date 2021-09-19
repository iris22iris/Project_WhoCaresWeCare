package com.web.store.repository.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._04_shop.FavoriteBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.ProductDao;
import _01_init.util.SystemUtils;

@Repository
public class ProductDaoImpl implements ProductDao {

	public static final int recordsPerPage = SystemUtils.RECORDS_PER_PAGE; // 預設值：每頁十二筆
	private int totalPages = -1;

	SessionFactory factory;

	FavoriteBean favoriteBean;

	@Autowired
	public FavoriteBean getFavoriteBean() {
		return favoriteBean;
	}

	public void setFavoriteBean(FavoriteBean favoriteBean) {
		this.favoriteBean = favoriteBean;
	}

	@Autowired
	public ProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}
	
//	取得所有商品
	@Override
	public List<ProductBean> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		return session.createQuery(hql, ProductBean.class).getResultList();
	}
	
//	透過頁碼取得商品
	@Override
	public List<ProductBean> getAllProductsByPage(int pageNo) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		return session.createQuery(hql, ProductBean.class).setFirstResult(startRecordNo).setMaxResults(recordsPerPage)
				.getResultList();
	}
	
//	透過頁碼取得商品並排序
	@Override
	public List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p ";
		if (sortType != null) {
			hql += " ORDER BY " + sortType + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		return session.createQuery(hql, ProductBean.class).setFirstResult(startRecordNo).setMaxResults(recordsPerPage)
				.getResultList();
	}
	
//	取得所有商品的總頁數
	@Override
	public int getTotalPages() {
		Session session = factory.getCurrentSession();
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM ProductBean ";
		List<Long> list = session.createQuery(hql, Long.class).getResultList();
		if (list.size() > 0) {
			count = list.get(0);
		}

		totalPages = (int) (Math.ceil(count / (double) recordsPerPage));
		return totalPages;
	}
	
//	透過頁碼以及產品類別取得商品
	@Override
	public List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb";
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<ProductBean> list = session.createQuery(hql, ProductBean.class).setParameter("ptb", prodTypeBean)
				.setFirstResult(startRecordNo).setMaxResults(recordsPerPage).getResultList();
		return list;
	}
	
//	透過頁碼以及產品類別取得商品並排序
	@Override
	public List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo,
			String sortType) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ProductBean p WHERE p.productTypeBean = :ptb ";
		if (sortType != null) {
			hql += " ORDER BY " + sortType + " ";
		}
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<ProductBean> list = session.createQuery(hql, ProductBean.class).setParameter("ptb", prodTypeBean)
				.setFirstResult(startRecordNo).setMaxResults(recordsPerPage).getResultList();
		return list;
	}
	
//	透過產品類別取得總頁數
	@Override
	public int getTotalPagesByProdType(ProductTypeBean prodTypeBean) {
		Session session = factory.getCurrentSession();
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*)  FROM ProductBean p WHERE p.productTypeBean = :ptb ";
		List<Long> list = session.createQuery(hql, Long.class).setParameter("ptb", prodTypeBean).getResultList();
		if (list.size() > 0) {
			count = list.get(0);
		}

		totalPages = (int) (Math.ceil(count / (double) recordsPerPage));
		return totalPages;
	}
	
//	取得所有產品類別
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
		ProductBean pb = session.get(ProductBean.class, prodId);
		return pb;
	}

	@Override
	public List<CommentBean> getCommentBeanByprodId(int prodId) {
		Session session = factory.getCurrentSession();
		String hql = " SELECT c FROM CommentBean c" + " WHERE c.productBean.prodId = :pid ";

		return session.createQuery(hql, CommentBean.class).setParameter("pid", prodId).getResultList();

	}

	@Override
	public void addProduct(ProductBean product) {

	}

	@Override
	public Object addFavorite(FavoriteBean favoriteBean) {
		Session session = factory.getCurrentSession();
		return session.save(favoriteBean);
	}

	@Override
	public List<FavoriteBean> queryFavorite(int custId, int productId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM FavoriteBean WHERE FK_Customer_ID = :custId and FK_Product_ID = :productId";
		return session.createQuery(hql, FavoriteBean.class).setParameter("custId", custId)
				.setParameter("productId", productId).getResultList();
	}

	@Override
	public void deleteFavorite(FavoriteBean favoriteBean) {
		Session session = getSession();
		String hql = "FROM FavoriteBean WHERE FK_Customer_ID = :custId and FK_Product_ID = :productId";
		List<FavoriteBean> fb = session.createQuery(hql, FavoriteBean.class)
				.setParameter("custId", favoriteBean.getFK_Customer_ID())
				.setParameter("productId", favoriteBean.getFK_Product_ID()).getResultList();
		if (fb.size() != 0) {
			for (FavoriteBean bean : fb) {
				session.delete(bean);
			}
		}
	}

	@Override
	public List<FavoriteBean> getFavorite(Integer FK_Customer_ID) {
		Session session = getSession();
		String hql = "FROM FavoriteBean WHERE FK_Customer_ID = :custId";
		List<FavoriteBean> fb = session.createQuery(hql, FavoriteBean.class).setParameter("custId", FK_Customer_ID)
				.getResultList();
		return fb;
	}

	// 查詢產品ID 名稱 及 圖片名稱
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Query> queryFavoriteProduct(Integer FK_Customer_ID) {
		List<FavoriteBean> prodId = getFavorite(FK_Customer_ID);
		String FK_Product_ID ="";
		for (FavoriteBean favoriteBean : prodId) {
			FK_Product_ID += "," + favoriteBean.getFK_Product_ID() ;
		}
		String hql = "SELECT prodId,prodName,price,fileName FROM ProductBean WHERE prodId IN(" + FK_Product_ID.substring(1) + ") ORDER BY prodId";
		
		Session session = factory.getCurrentSession();
		List<Query> dataList = session.createQuery(hql).getResultList();
		return dataList;
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
}
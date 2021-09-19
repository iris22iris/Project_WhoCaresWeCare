package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.RentProductDao;
import com.web.store.service.RentProductService;

@Transactional
@Service
public class RentProductServiceImpl implements RentProductService {

	RentProductDao rentProductDao;
//	ReservationDao reservationDao;
	
	@Autowired
	public RentProductServiceImpl(RentProductDao rentProductDao) {
		this.rentProductDao = rentProductDao;
	}
//	@Autowired
//	public RentProductServiceImpl(RentProductDao rentProductDao, ReservationDao reservationDao) {
//		super();
//		this.rentProductDao = rentProductDao;
//		this.reservationDao = reservationDao;
//	}

//	取得所有租賃設備
	@Override
	public List<RentProductBean> getAllProducts() {
		return rentProductDao.getAllProducts();
	}

//	取得所有租賃設備並群組同產品不同項次之設備(含頁碼及排序判斷)
	@Override
	public List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo,
																	 String sortType) {
		return rentProductDao.getGroupedProducts(prodTypeBean, pageNo, sortType);
	}
	
//	取得所有租賃設備的總庫存
	@Override
	public List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType) {
		return rentProductDao.getGroupedStockSum(prodTypeBean, pageNo, sortType);
	}

//	取得租賃設備群組後的總頁數
	@Override
	public int getGroupedPages(ProductTypeBean prodTypeBean) {
		return rentProductDao.getGroupedPages(prodTypeBean);
	}

//	取得所有產品類別
	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		return rentProductDao.getAllProdTypes();
	}

	@Override
	public RentProductBean getProductById(int prodId) {
		return rentProductDao.getProductById(prodId);
	}

	//抓取該商品目前預約資料
	@Override
	public List<ReservationBean> getReservationBeanByprodId(int prodId) {
		
		return rentProductDao.getReservationBeanByprodId(prodId);
	}

	//抓取該商品目前租賃評論資料
	@Override
	public List<CommentBean> getCommentBeanByprodId(int prodId) {
	
		return rentProductDao.getCommentBeanByprodId(prodId);
	}
	
	
	@Override
	public void addProduct(RentProductBean product) {
	}

	

	@Override
	public void updateStock(int productId, int newQuantity) {
	}

	

	//新增單筆預約資料
	@Override
	public void addReservation(ReservationBean reservation) {
		rentProductDao.addReservation(reservation);
		
	}
	// 依cookie中使用者帳號讀取目前使用者資料
	@Override
	public List<CustomerBean> getCustomerInfoBycookieaccount(String account) {
	
		return rentProductDao.getCustomerInfoBycookieaccount(account);
	}

	// 依會員編號取出該會員所有預約的設備資料
	@Override
	public List<ReservationBean> getMyReservationByCustId(int mycustId) {
		
		return rentProductDao.getMyReservationByCustId(mycustId);
	}

	// 依傳入大分類代號取出該分類代號之名稱	
	@Override
	public List<ProductTypeBean> getProductTypeBeanBymaincategory(String maincategory) {
		
		return rentProductDao.getProductTypeBeanBymaincategory(maincategory);
	}

	@Override
	public List<RentProductBean> getAllSerialStocksByprodId(int prodId) {
		
		return rentProductDao.getAllSerialStocksByprodId(prodId);
	}
	
}
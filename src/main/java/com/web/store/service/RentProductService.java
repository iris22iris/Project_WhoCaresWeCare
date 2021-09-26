package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface RentProductService {

	// 讀取多筆租賃設備資料
	List<RentProductBean> getAllProducts();

	// 依ProdType表格主鍵 載入群組租賃設備並分頁、排序
	List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo, String sortType);

	// 依ProdType表格主鍵分類 載入群組租賃設備庫存總和資料
	List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType);
	
	// 計算群組租賃設備總共有幾頁
	int getGroupedPages(ProductTypeBean prodTypeBean);
	
	// 依主鍵讀取單筆租賃設備資料
	public RentProductBean getProductById(int prodId);
	
	// 依主鍵讀取租賃設備總庫存
	public int getTotalStockByProdId(int prodId);
	
	//抓取該商品目前預約資料
	public List<ReservationBean> getReservationBeanByprodId(int prodId) ;
	
	//抓取該商品目前租賃評論資料
	public List<CommentBean> getCommentBeanByprodId(int prodId) ;

	// 新增單筆租賃設備資料
	void addProduct(RentProductBean product);

	// 更新租賃設備
	void updateRentProduct(RentProductBean rentProductBean);
	
	//新增單筆預約資料
	void addReservation(ReservationBean reservation);
	
	// 依session中使用者帳號讀取目前使用者資料
		public List<CustomerBean> getCustomerInfoByLoginAccount(String account);
		
	// 依會員編號取出該會員所有預約的設備資料
		public List<ReservationBean> getMyReservationByCustId(int mycustId); 

	// 依傳入大分類代號取出該分類代號之名稱	
		public List<ProductTypeBean> getProductTypeBeanBymaincategory(String maincategory);
	//用商品編號取得該產品及其項次庫存資料
		public	List<RentProductBean> getAllSerialStocksByprodId(int prodId);
}
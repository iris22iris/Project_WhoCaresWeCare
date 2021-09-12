package com.web.store.repository;

import java.util.List;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface RentProductDao {

	// 讀取多筆租賃產品資料
	List<RentProductBean> getAllProducts();

	// 依ProdType表格主鍵 載入分類的群組設備並分頁、排序
	List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo,
			String sortType);
	
	// 依ProdType表格主鍵分類 載入產品庫存總和資料
	List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType);
	
	// 計算群組設備總共有幾頁
	int getGroupedPages(ProductTypeBean prodTypeBean);
	
	// 讀取全部租賃產品分類
	List<ProductTypeBean> getAllProdTypes();

	// 更新租賃產品庫存
	void updateStock(int productId, int newQuantity);

	// 依主鍵讀取單筆租賃產品資料
	public RentProductBean getProductById(int prodId);

	// 依porId(與serialNumber)讀取單筆預約設備資料 抓現在幾人預約
	public  List<ReservationBean> getReservationBeanByprodId(int prodId);
	
	//抓取該商品目前租賃評論資料
	public List<CommentBean> getCommentBeanByprodId(int prodId);
			
	
	// 新增單筆租賃產品資料
	void addProduct(RentProductBean product);

	//新增單筆預約資料
		void addReservation(ReservationBean reservation);
	
	// 依cookie中使用者帳號讀取目前使用者資料	
	public List<CustomerBean> getCustomerInfoBycookieaccount(String account);
}
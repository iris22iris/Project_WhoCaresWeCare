package com.web.store.service;

import java.util.List;

import com.web.store.model._03_rent.RentProductBean;
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
	
	// 讀取全部租賃設備分類
	List<ProductTypeBean> getAllProdTypes();

	// 依主鍵讀取單筆租賃設備資料
	public RentProductBean getProductById(int prodId);

	// 新增單筆租賃設備資料
	void addProduct(RentProductBean product);

	// 更新租賃設備庫存
	void updateStock(int productId, int newQuantity);

}
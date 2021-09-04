package com.web.store.service;

import java.util.List;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface ProductService {

	// 讀取多筆產品資料
	List<ProductBean> getAllProducts();

	// 依分頁讀取多筆產品資料
	List<ProductBean> getAllProductsByPage(int pageNo);

	// 依分頁讀取多筆產品資料並排序
	List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType);
	
	// 計算販售的商品總共有幾頁
	int getTotalPages();

	// 依ProdType表格主鍵 載入產品的分類
	List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean);

	// 依ProdType表格主鍵 載入產品的分類並分頁
	List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo);

	// 依ProdType表格主鍵 載入產品的分類並分頁且排序
	List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo, String sortType);
	
	// 計算分類的商品總共有幾頁
	int getTotalPagesByProdType(ProductTypeBean prodTypeBean);
	
	// 讀取全部產品分類
	List<ProductTypeBean> getAllProdTypes();

	// 更新庫存
	void updateStock(int productId, int newQuantity);

	// 依主鍵讀取單筆產品資料
	public ProductBean getProductById(int prodId);

	// 新增單筆產品資料
	void addProduct(ProductBean product);

}
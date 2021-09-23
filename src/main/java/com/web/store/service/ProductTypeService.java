package com.web.store.service;

import java.util.List;

import com.web.store.model._07_productType.ProductTypeBean;

public interface ProductTypeService {

	// 讀取全部產品分類
	List<ProductTypeBean> getAllProdTypes();
	
	//	依主鍵查詢產品類別
	ProductTypeBean findProductTypeBeanById(String prodType);

}
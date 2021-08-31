package com.web.store.service;

import java.util.List;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface RentProductService {

	List<RentProductBean> getAllProducts();		//讀取多筆租賃產品資料

	List<RentProductBean> getProductsByProdType(ProductTypeBean prodTypeBean);	//依ProdType表格主鍵 載入產品的分類

	List<ProductTypeBean> getAllProdTypes();	//讀取全部租賃產品分類

	void updateStock(int productId, int newQuantity);	//更新租賃產品庫存

	public RentProductBean getProductById(int prodId);	//依主鍵讀取單筆租賃產品資料

	void addProduct(RentProductBean product);			//新增單筆租賃產品資料

}
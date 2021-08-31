package com.web.store.repository;

import java.util.List;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface ProductDao {

	List<ProductBean> getAllProducts();				//讀取多筆產品資料

	List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean); //依ProdType表格主鍵 載入產品的分類 

	List<ProductTypeBean> getAllProdTypes(); 			//讀取全部產品分類

	void updateStock(int productId, int newQuantity);	//更新庫存

	public ProductBean getProductById(int prodId); 		//依主鍵讀取單筆產品資料

	void addProduct(ProductBean product);				//新增單筆產品資料

}
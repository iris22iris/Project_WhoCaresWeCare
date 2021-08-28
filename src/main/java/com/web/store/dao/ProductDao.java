package com.web.store.dao;

import java.util.List;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface ProductDao {

	List<ProductBean> getAllProducts();

	List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean);

	List<ProductTypeBean> getAllProdTypes();

	void updateStock(int productId, int newQuantity);

	public ProductBean getProductById(int productId);

	void addProduct(ProductBean product);

}
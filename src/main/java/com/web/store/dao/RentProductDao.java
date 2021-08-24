package com.web.store.dao;

import java.util.List;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface RentProductDao {

	List<RentProductBean> getAllProducts();

	List<RentProductBean> getProductsByProdType(String prodtype);

	List<ProductTypeBean> getAllProdTypes();

	void updateStock(int productId, int newQuantity);

	public RentProductBean getProductById(int productId);

	void addProduct(RentProductBean product);

}
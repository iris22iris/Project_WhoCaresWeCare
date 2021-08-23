package com.web.store.dao;

import java.util.List;

import com.web.store.model._03_rent.RentProductBean;

public interface RentProductDao {

	List<RentProductBean> getAllProducts();

	List<RentProductBean> getProductsByProdType(String prodtype);

	List<String> getAllCategories();

	void updateStock(int productId, int newQuantity);

	public RentProductBean getProductById(int productId);

	void addProduct(RentProductBean product);

}
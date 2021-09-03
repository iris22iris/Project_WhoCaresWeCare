package com.web.store.model._04_shop;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCart {

	private Map<Integer, ProductBean> productsMap = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	@Autowired
	public ShoppingCart(Map<Integer, ProductBean> productsMap) {
		this.productsMap = productsMap;
	}

	public Map<Integer, ProductBean> getContent() {
		return productsMap;
	}

	public void addToCart(Integer prodId, ProductBean productBean) {
		if (productBean.getStock() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if (productsMap.get(prodId) == null) {
			productsMap.put(prodId, productBean);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			ProductBean pBean = productsMap.get(prodId);
			// 加購的數量：productBean.getStock()
			// 原有的數量：pBean.getStock()
			pBean.setStock(pBean.getStock() + productBean.getStock());
		}
	}

}
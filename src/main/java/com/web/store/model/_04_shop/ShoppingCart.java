package com.web.store.model._04_shop;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCart {

	private Map<Integer, BuyItemBean> productsMap = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	@Autowired
	public ShoppingCart(Map<Integer, BuyItemBean> productsMap) {
		this.productsMap = productsMap;
	}

	public Map<Integer, BuyItemBean> getContent() {
		return productsMap;
	}

	public void addProductToCart(Integer prodId, BuyItemBean buyItemBean) {
		if (buyItemBean.getProdQTY() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if (productsMap.get(prodId) == null) {
			productsMap.put(prodId, buyItemBean);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			BuyItemBean biBean = productsMap.get(prodId);
			// 加購的數量：buyItemBean.getProdQTY()
			// 原有的數量：biBean.setProdQTY()
			biBean.setProdQTY(biBean.getProdQTY() + buyItemBean.getProdQTY());
		}
	}

}
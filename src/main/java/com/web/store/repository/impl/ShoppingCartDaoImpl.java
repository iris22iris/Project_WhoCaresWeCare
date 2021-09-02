package com.web.store.repository.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.repository.ShoppingCartDao;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private Map<String, Integer> cart = new LinkedHashMap<>();

	@Autowired
	public ShoppingCartDaoImpl(Map<String, Integer> cart) {
		this.cart = cart;
	}

	@Override
	public Map<String, Integer> getContent() {
		return cart;
	}

	@Override
	public void addToCart(String prodId, Integer amount) {
		if (amount <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if (cart.get(prodId) == null) {
			cart.put(prodId, amount);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			Integer count = cart.get(prodId);
			// 加購的數量：amount
			// 原有的數量：count
			count += amount;
		}
	}

}
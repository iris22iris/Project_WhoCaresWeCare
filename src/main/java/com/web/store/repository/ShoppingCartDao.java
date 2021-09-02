package com.web.store.repository;

import java.util.Map;

public interface ShoppingCartDao {

	Map<String, Integer> getContent();

	void addToCart(String prodId, Integer amount);

}
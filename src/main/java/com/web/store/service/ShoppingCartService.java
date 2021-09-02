package com.web.store.service;

import java.util.Map;

public interface ShoppingCartService {

	Map<String, Integer> getContent();

	void addToCart(String prodId, Integer amount);

}
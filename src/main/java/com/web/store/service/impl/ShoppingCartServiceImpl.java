package com.web.store.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.repository.ShoppingCartDao;
import com.web.store.service.ShoppingCartService;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	ShoppingCartDao shoppingCartDao;

	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}

	@Override
	public Map<String, Integer> getContent() {
		return shoppingCartDao.getContent();
	}

	@Override
	public void addToCart(String prodId, Integer amount) {
		shoppingCartDao.addToCart(prodId, amount);
	}
	
}
package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.PromotionBean;

public interface OrderService {
	
	public PromotionBean findbyDiscountCode(String discountCode);
	
}

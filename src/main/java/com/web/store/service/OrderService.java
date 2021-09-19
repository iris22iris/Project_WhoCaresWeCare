package com.web.store.service;

import com.web.store.model._02_customerService.PromotionBean;

public interface OrderService {
	
	public PromotionBean findByDiscountCode(String discountCode);
	

}

package com.web.store.repository;

import com.web.store.model._02_customerService.PromotionBean;


public interface OrderDao {
	
	//使用discountCode找到優惠
	public PromotionBean findByDiscountCode(String discountCode) ;
	
	

	
}

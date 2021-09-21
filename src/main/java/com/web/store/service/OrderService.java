package com.web.store.service;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._06_order.OrdBean;

public interface OrderService {
	
	
	//使用discountCode找到優惠
	public PromotionBean findByDiscountCode(String discountCode);
	
	//儲存訂單
		public void save(OrdBean ordBean);
}

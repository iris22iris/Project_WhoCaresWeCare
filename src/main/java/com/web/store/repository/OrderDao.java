package com.web.store.repository;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._06_order.OrdBean;

public interface OrderDao {
	
	//使用discountCode找到優惠
	public PromotionBean findByDiscountCode(String discountCode) ;
	
	//儲存訂單
	public void save(OrdBean ordBean);
	
	//找尋目前訂單編號
	public Integer findCurrentOrdId();
	
}

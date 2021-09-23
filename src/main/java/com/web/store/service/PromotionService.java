package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.PromotionBean;

public interface PromotionService {

	// 取得所有優惠類別
	List<PromotionBean> getAllPromotions();
	
	//	依主鍵查詢產品類別
	PromotionBean findPromotionBeanById(Integer promoteId);

}
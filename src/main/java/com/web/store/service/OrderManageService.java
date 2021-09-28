package com.web.store.service;

import java.util.List;

import com.web.store.model._06_order.OrdBean;

public interface OrderManageService {
	
	//	管理者查詢所有訂單
	List<OrdBean> findOrdBeansByCategory(String category);
	
	//	管理者使用複合主鍵查詢訂單
	OrdBean findOrdBeanById(String category, Integer ordId);

}
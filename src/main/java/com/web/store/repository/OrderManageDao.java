package com.web.store.repository;

import java.util.List;

import com.web.store.model._06_order.OrdBean;

public interface OrderManageDao {
	
	//	管理者依訂單分類查詢所有訂單
	List<OrdBean> findOrdBeansByCategory(String category);

	//	管理者使用複合主鍵查詢訂單
	OrdBean findOrdBeanById(String category, Integer ordId);

}
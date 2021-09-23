package com.web.store.repository;

import java.util.List;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._06_order.OrdBean;

public interface OrderQueryDao {

	OrdBean findOrdBeanById(Integer custId, String category, Integer ordId);

	List<BuyItemBean> findBuyItemByOrdId(Integer ordId);

	List<RentItemBean> findRentItemByOrdId(Integer ordId);
	
}
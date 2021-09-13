package com.web.store.service;

import java.util.List;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._06_order.OrdBean;

public interface OrderQueryService {

	OrdBean findOrdBeanById(String category, Integer ordId);

	List<BuyItemBean> findBuyItemByOrdId(Integer ordId);

	List<RentItemBean> findRentItemByOrdId(Integer ordId);

}
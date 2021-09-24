package com.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.OrderDao;
import com.web.store.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	OrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Override
	public PromotionBean findByDiscountCode(String discountCode) {
		return orderDao.findByDiscountCode(discountCode);
	}

	@Override
	public void save(OrdBean ordBean) {
		orderDao.save(ordBean);
	}

	@Override
	public OrdPK getCurrentOrdId() {
		return orderDao.getCurrentOrdId();
	}





}

package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.repository.PromotionDao;
import com.web.store.service.PromotionService;

@Transactional
@Service
public class PromotionServiceImpl implements PromotionService {

	PromotionDao promotionDao;
	
	@Autowired
	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}

	// 取得所有優惠類別
	@Override
	public List<PromotionBean> getAllPromotions() {
		return promotionDao.getAllPromotions();
	}
	
//	依主鍵查詢產品類別
	@Override
	public PromotionBean findPromotionBeanById(Integer promoteId) {
		return promotionDao.findPromotionBeanById(promoteId);
	}
	
}
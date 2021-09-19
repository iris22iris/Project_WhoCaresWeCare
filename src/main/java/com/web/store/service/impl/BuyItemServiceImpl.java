package com.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.repository.BuyItemDao;
import com.web.store.service.BuyItemService;

@Transactional
@Service
public class BuyItemServiceImpl implements BuyItemService {
	
	BuyItemDao buyItemDao;
	
	@Autowired
	public BuyItemServiceImpl(BuyItemDao buyItemDao) {
		this.buyItemDao = buyItemDao;
	}

//	使用複合主鍵查詢購買細項
	@Override
	public BuyItemBean findBuyItemByPK(BuyItemPK buyItemPK) {
		return buyItemDao.findBuyItemByPK(buyItemPK);
	}

//	更新購買細項
	@Override
	public void updateBuyItem(BuyItemBean buyItemBean) {
		buyItemDao.updateBuyItem(buyItemBean);
	}
	
}
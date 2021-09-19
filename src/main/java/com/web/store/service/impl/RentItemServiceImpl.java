package com.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.repository.RentItemDao;
import com.web.store.service.RentItemService;

@Transactional
@Service
public class RentItemServiceImpl implements RentItemService {
	
	RentItemDao rentItemDao;
	
	@Autowired
	public RentItemServiceImpl(RentItemDao rentItemDao) {
		this.rentItemDao = rentItemDao;
	}

//	使用複合主鍵查詢租賃細項
	@Override
	public RentItemBean findRentItemByPK(RentItemPK rentItemPK) {
		return rentItemDao.findRentItemByPK(rentItemPK);
	}
	
//	更新租賃細項
	@Override
	public void updateRentItem(RentItemBean rentItemBean) {
		rentItemDao.updateRentItem(rentItemBean);
	}
	
}
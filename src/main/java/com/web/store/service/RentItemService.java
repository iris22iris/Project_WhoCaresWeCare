package com.web.store.service;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;

public interface RentItemService {

	RentItemBean findRentItemByPK(RentItemPK rentItemPK);

	void updateRentItem(RentItemBean rentItemBean);

}
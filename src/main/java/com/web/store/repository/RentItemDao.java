package com.web.store.repository;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;

public interface RentItemDao {

	RentItemBean findRentItemByPK(RentItemPK rentItemPK);

	void updateRentItem(RentItemBean rentItemBean);

}
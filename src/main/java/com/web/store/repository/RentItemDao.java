package com.web.store.repository;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;

public interface RentItemDao {
//	使用複合主鍵查詢租賃細項
	RentItemBean findRentItemByPK(RentItemPK rentItemPK);
	
//	更新租賃細項
	void updateRentItem(RentItemBean rentItemBean);

//	更新設備庫存
	void updateRentProductStock(RentItemBean rentItemBean);

}
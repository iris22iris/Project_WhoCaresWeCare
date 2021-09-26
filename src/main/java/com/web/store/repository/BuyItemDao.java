package com.web.store.repository;


import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;


public interface BuyItemDao {
	
	//找到訂單明細
	BuyItemBean findBuyItemByPK(BuyItemPK buyItemPK);
	
	void updateBuyItem(BuyItemBean buyItemBean);
	
	//更新商品庫存量
	public void updateProductStock(BuyItemBean buyItemBean);
}
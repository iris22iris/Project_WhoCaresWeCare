package com.web.store.repository;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;

public interface BuyItemDao {

	BuyItemBean findBuyItemByPK(BuyItemPK buyItemPK);

	void updateBuyItem(BuyItemBean buyItemBean);

}
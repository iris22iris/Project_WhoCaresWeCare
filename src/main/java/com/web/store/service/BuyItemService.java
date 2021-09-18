package com.web.store.service;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;

public interface BuyItemService {

	BuyItemBean findBuyItemByPK(BuyItemPK buyItemPK);

	void updateBuyItem(BuyItemBean buyItemBean);

}
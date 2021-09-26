package com.web.store.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.BuyItemDao;
import com.web.store.repository.OrderDao;
import com.web.store.repository.ProductDao;
import com.web.store.service.OrderService;


@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	BuyItemDao buyItemDao;
	ProductDao productDao;
	OrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(BuyItemDao buyItemDao, ProductDao productDao, OrderDao orderDao) {
		this.buyItemDao = buyItemDao;
		this.productDao = productDao;
		this.orderDao = orderDao;
	}
	
	@Override
	public PromotionBean findByDiscountCode(String discountCode) {
		return orderDao.findByDiscountCode(discountCode);
	}


	@Override
	public void save(OrdBean ordBean) {
		log.info("處理訂單之Service:檢查交易庫存量");
		checkStock(ordBean);
		log.info("處理訂單之Service:準備儲存訂單");
		orderDao.save(ordBean);
		
//		orderDao.save(ordBean);
	}

	@Override
	public OrdPK getCurrentOrdId() {
		return orderDao.getCurrentOrdId();
	}

	@Override
	public void checkStock(OrdBean ordBean) {
		Set<BuyItemBean> items = ordBean.getBuyItems();
		log.info("處理訂單之Service: 2. 準備再次檢查並調整每項商品的庫存量");
		for(BuyItemBean bib : items) {
			int stock = productDao.getProductById(bib.getProductBean().getProdId()).getStock();
			if(stock < bib.getProdQTY()) {
				log.info("處理訂單之Service: 商品編號: " + bib.getProductBean().getProdId() + " 庫存不足");

			}else {
				log.info("處理訂單之Service: 準備檢查並調整每項商品的庫存量, 商品編號: " + bib.getProductBean().getProdId()  + " 庫存足夠");
				buyItemDao.updateProductStock(bib);
			}
		}
	}





}

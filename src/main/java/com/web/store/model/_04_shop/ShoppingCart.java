package com.web.store.model._04_shop;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ShoppingCart {

	private Map<Integer, BuyItemBean> cart = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	@Autowired
	public ShoppingCart(Map<Integer, BuyItemBean> cart) {
		this.cart = cart;
	}

	// ${ShoppingCart.content}
	public Map<Integer, BuyItemBean> getContent() {
		return cart;
	}

	// 加入購物車
	public void addProductToCart(Integer prodId, BuyItemBean buyItemBean) {
		if (buyItemBean.getProdQTY() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if (cart.get(prodId) == null) {
			cart.put(prodId, buyItemBean);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			BuyItemBean biBean = cart.get(prodId);
			// 加購的數量：buyItemBean.getProdQTY()
			// 原有的數量：biBean.setProdQTY()
			biBean.setProdQTY(biBean.getProdQTY() + buyItemBean.getProdQTY());
		}
	}

	// 修改購物車內商品數量
//	public boolean modifyQty(int prodId, int newQty) {
//		if (cart.get(prodId) != null) {
//			BuyItemBean bean = cart.get(prodId);
//			bean.setProdQTY(newQty);
//			return true;
//		} else {
//			return false;
//		}
//	}

	
	// 刪除勾選商品
	public void deleteProducts(int productId) {
		if ( cart.get(productId) != null ) {
		       cart.remove(productId);  // Map介面的remove()方法
			} 
	}
	
//	public BigDecimal getSuntotal() {
//		BigDecimal subTotal = 0;
//		Set<Integer> set = cart.keySet();
//		for(int n : set) {
//			BuyItemBean bib = cart.get(n);
//			BigDecimal price = bib.getProductBean().getPrice();
//			BigDecimal qty = new BigDecimal(bib.getProdQTY());
//			subTotal = price.multiply(qty);
//		}
//		
//		
//		return subTotal;
//	}



}
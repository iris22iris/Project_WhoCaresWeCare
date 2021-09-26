package com.web.store.model._04_shop;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.RentItemBean;

@Repository
public class RentCart {

	private Map<Integer, RentItemBean> rentcart = new LinkedHashMap<>();

	public RentCart() {
	}

	@Autowired
	public RentCart(Map<Integer, RentItemBean> rentcart) {
		this.rentcart = rentcart;
	}

	// ${RentCart.content}
	public Map<Integer, RentItemBean> getContent() {
		return rentcart;
	}

	// 加入購物車
	public void addProductToCart(Integer prodId, RentItemBean rentItemBean) {
		if (rentItemBean.getProdQty() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if (rentcart.get(prodId) == null) {
			rentcart.put(prodId, rentItemBean);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			RentItemBean rentBean = rentcart.get(prodId);
			// 加購的數量：buyItemBean.getProdQTY()
			// 原有的數量：biBean.setProdQTY()
			rentBean.setProdQty(rentBean.getProdQty() + rentItemBean.getProdQty());
		}
	}

	// 刪除勾選商品
	public void deleteProducts(int productId) {
		if (rentcart.get(productId) != null) {
			rentcart.remove(productId); // Map介面的remove()方法
		}
	}

	// 計算購物車內所有商品的合計金額
	public double getSubtotal() {
		double subTotal = 0;
		Set<Integer> set = rentcart.keySet();
		for (int n : set) {
			RentItemBean rib = rentcart.get(n);
			double price = rib.getRentProductBean().getPrice();
			int qty = rib.getProdQty();
			double discount = 0;
			if (rib.getRentProductBean().getPromotionBean() != null) {
				discount = rib.getRentProductBean().getPromotionBean().getDiscount();

			}
			subTotal += price * qty - discount;
		}

		return subTotal;
	}

}

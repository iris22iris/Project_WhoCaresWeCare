package com.web.store.controller;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderService;

@Controller
@SessionAttributes({ "LoginOK", "ShoppingCart","OrdBean" })
public class BuyCheckoutController {

	private static Logger log = LoggerFactory.getLogger(BuyCheckoutController.class);
	
	OrderService orderService;
	HttpSession httpSession;
	CustomerService customerService;

	
	@Autowired
	public BuyCheckoutController(OrderService orderService, HttpSession httpSession, CustomerService customerService) {
		super();
		this.orderService = orderService;
		this.httpSession = httpSession;
		this.customerService = customerService;
	}


	//顯示結帳頁面
	@GetMapping("/BuyCheckout/{custId}")
	public String buyCheckout(@PathVariable Integer custId,Model model) {
//		判斷是否登入
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		log.info("結帳前，會員登入確認。");
		if (customerBean == null) {
			return "redirect:/_05_login";
		}
		
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (cart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		
		//取得session內的ordbean物件
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");
		log.info("取得OrdBean物件:"+ordBean);
		
		Map<Integer, BuyItemBean> cartContent = cart.getContent();
		//存成Set物件轉換為OrdBean
				Set<BuyItemBean> buyItems = new LinkedHashSet<>();
				Set<Integer> set = cartContent.keySet();
				for(Integer i : set) {
					BuyItemBean bib = cartContent.get(i);
					bib.setOrdBean(ordBean);
					buyItems.add(bib);
				}
				ordBean.setBuyItems(buyItems);
//				
		model.addAttribute("buyItems",buyItems);
		model.addAttribute("OrdBean",ordBean);
		log.info("傳回OrdBean的Map物件:"+ordBean);
		
		return "_04_buyCheckout";
	}
}

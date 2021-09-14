package com.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderQueryService;

@Controller
public class OrderQueryController {

	CustomerService customerService;
	OrderQueryService orderQueryService;
	HttpSession httpSession;

	@Autowired
	public OrderQueryController(CustomerService customerService, OrderQueryService orderQueryService,
			HttpSession httpSession) {
		this.customerService = customerService;
		this.orderQueryService = orderQueryService;
		this.httpSession = httpSession;
	}
	
//	進入商城訂單查詢頁(含查詢字串)
	@GetMapping("/orderQuery/{custId}")
	public String orderQuery (
			@PathVariable Integer custId,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "ordId", required = false) Integer ordId,
			Model model
	) {
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		if (category != null && ordId != 0) {
			OrdBean ordBean = orderQueryService.findOrdBeanById(category, ordId);
			List<BuyItemBean> buyItems = orderQueryService.findBuyItemByOrdId(ordId);
			model.addAttribute(ordBean);
			model.addAttribute("buyItems", buyItems);
		}

		return "_06_orderQuery";
	}

}
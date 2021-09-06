package com.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

	@GetMapping("/_01_searchResult")
	public String searchResult(Model model) {
		return "_01_searchResult";
	}
	
	@GetMapping("/_02_q_a")
	public String q_a(Model model) {
		return "_02_q_a";
	}
	
	@GetMapping("/_02_contactUs")
	public String contackUs(Model model) {
		return "_02_contactUs";
	}
	
	@GetMapping("/_02_onlineDM")
	public String onlineDM(Model model) {
		return "_02_onlineDM";
	}
	
//	@GetMapping("/_03_rentProduct")
//	public String rentProduct(Model model) {
//	return "_03_rentProduct";
//	}
	
	@GetMapping("/_03_rentItemList")
	public String rentItemList(Model model) {
	return "_03_rentItemList";
	}
	
	@GetMapping("/_04_favoriteList")
	public String favoriteList(Model model) {
	return "_04_favoriteList";
	}
	
	@GetMapping("/_04_shoppingCart")
	public String shoppingCart(Model model) {
	return "_04_shoppingCart";
	}

	@GetMapping("/_05_loginPopup")
	public String loginPopup(Model model) {
	return "_05_loginPopup";
	}
	
	@GetMapping("/_05_member_management")
	public String member_management(Model model) {
	return "_05_member_management";
	}
	
	@GetMapping("/_05_memberProfile")
	public String memberProfile(Model model) {
	return "_05_memberProfile";
	}
	
	@GetMapping("/_06_orderQuery")
	public String orderQuery(Model model) {
	return "_06_orderQuery";
	}
	
	@GetMapping("/_06_problemReply")
	public String problemReply(Model model) {
	return "_06_problemReply";
	}
	
	@GetMapping("/_06_rentOrderQuery")
	public String rentOrderQuery(Model model) {
	return "_06_rentOrderQuery";
	}
	
	@GetMapping("/_06_reservationQuery")
	public String reservationQuery(Model model) {
	return "_06_reservationQuery";
	}
	
}

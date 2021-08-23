package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.service.RentProductService;

@Controller

public class RentMenuController {

	RentProductService rentProductService;
	ServletContext servletContext;
	
	@Autowired
	public RentMenuController(RentProductService rentProductService, ServletContext servletContext) {
		this.rentProductService = rentProductService;
		this.servletContext = servletContext;
	}

	@RequestMapping("/rentMenu")
	public String rentProductMenu(Model model) {
		List<RentProductBean> rentProductBeans = rentProductService.getAllProducts();
		model.addAttribute("rentProductBeans", rentProductBeans);
		return "_03_rentProductMenu";
	}

//	@RequestMapping("/rentMenu/{prodtype}")
//	public String getProductsByCategory(@PathVariable("prodtype") String prodtype, Model model) {
//		List<RentProductBean> rentProducts = rentProductService.getProductsByProdType(prodtype);
//		model.addAttribute("rentProducts", rentProducts);
//		return "_03_rentProductMenu";
//	}

}
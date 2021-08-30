package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
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
		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);
		return "_03_rentProductMenu";
	}

	@RequestMapping("/rentMenu/{productType.prodType}")
	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
		List<RentProductBean> rentProducts = rentProductService.getProductsByProdType(new ProductTypeBean(prodtype));
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);
		return "_03_rentProductMenu";
	}
	
	//商品頁面的sideMenu抓取資料 ----------暫時註解未來到RentProductPageController合併
//	@RequestMapping("/_03_rentProduct")
//	public String rentProduct(Model model) {
//		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
//		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
//		model.addAttribute("rentProducts", rentProducts);
//		model.addAttribute("productTypes", productTypes);
//		return "_03_rentProduct";
//	}


	
	
}
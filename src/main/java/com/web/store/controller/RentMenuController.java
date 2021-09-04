package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping("/rentMenuO")
	public String rentProductMenu(Model model) {
		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);
		
		return "_03_rentProductMenu";
	}

	@GetMapping("/rentMenu")
	public String rentProductMenuGrouped(Model model) {
		List<RentProductBean> rentProducts = rentProductService.getAllGroupedProducts();
		List<Long> productStocks = rentProductService.getAllStockSum();
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productStocks", productStocks);
		model.addAttribute("productTypes", productTypes);
		
		return "_03_rentProductMenu";
	}

	@GetMapping("/rentMenu/{productType.prodType}")
	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
		List<RentProductBean> rentProducts = rentProductService.getGroupedProductsByProdType(new ProductTypeBean(prodtype));
		List<Long> productStocks = rentProductService.getGroupedStockSum(new ProductTypeBean(prodtype));
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productStocks", productStocks);
		model.addAttribute("productTypes", productTypes);
		
		return "_03_rentProductMenu";
	}
	
}
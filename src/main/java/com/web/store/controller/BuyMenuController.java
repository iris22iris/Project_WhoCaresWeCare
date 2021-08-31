package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductService;

@Controller
public class BuyMenuController {

	ProductService productService;
	ServletContext servletContext;
	
	@Autowired
	public BuyMenuController(ProductService productService, ServletContext servletContext) {
		this.productService = productService;
		this.servletContext = servletContext;
	}

	@RequestMapping("/buyMenu")
	public String buyProductMenu(Model model) {
		List<ProductBean> products = productService.getAllProducts();
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		return "_04_buyProductMenu";
	}

	@RequestMapping("/buyMenu/{productType.prodType}")
	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
		List<ProductBean> products = productService.getProductsByProdType(new ProductTypeBean(prodtype));
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		return "_04_buyProductMenu";
	}


	
}
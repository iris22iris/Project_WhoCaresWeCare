package com.web.store.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.service.ProductService;
import com.web.store.service.RentProductService;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.web.store.model._03_rent.RentProductBean;

@Controller
public class RentProductPageController {

	
	@RequestMapping("/_03_rentProduct")
	public String getProductById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("rentproduct", rentProductService.getProductById(id));
		return "_03_rentProduct";
	};
	
	
	RentProductService rentProductService;
	ServletContext servletContext;
	
	@Autowired
	public RentProductPageController(RentProductService rentProductService, ServletContext servletContext) {
		this.rentProductService = rentProductService;
		
		this.servletContext = servletContext;
	}
//	@RequestMapping("/_03_rentProduct")
//		public String list(Model model) {
//		RentProductBean rb = new RentProductBean(
//				) ;
//		return null;
//	}
	
	
	
	
//	ProductService productService;
//	ServletContext servletContext;
	
//	@Autowired
//	public RentProductPageController(ProductService productService, ServletContext servletContext) {
//		this.productService = productService;
//		this.servletContext = servletContext;
//	}

//	@RequestMapping("/buyMenu")
//	public String buyProductMenu(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_buyProductMenu";
//	}

//	@RequestMapping("/buyMenu/{productType.prodType}")
//	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
//		List<ProductBean> products = productService.getProductsByProdType(new ProductTypeBean(prodtype));
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_buyProductMenu";
//	}
//
//	@RequestMapping("/_04_productPage")
//	public String buyProduct(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_productPage";
//	}
	
}
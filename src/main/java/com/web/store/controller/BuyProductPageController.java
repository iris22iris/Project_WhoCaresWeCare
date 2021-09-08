package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductService;

@Controller
public class BuyProductPageController {

	@RequestMapping("/_04_productPage")
	public String getProductById(@RequestParam("id") Integer id, Model model) {
		
		List<ProductBean> products = productService.getAllProducts();
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		List<CommentBean> comments = productService.getCommentBeanByprodId(id);
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("comments", comments);
		return "_04_productPage";
	};
	
//	@RequestMapping("/_04_productPage")
//	public String buyProduct(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_productPage";
//	}
	
//	@RequestMapping("/_03_rentProduct")
//		public String list(Model model) {
//		RentProductBean rb = new RentProductBean(
//				) ;
//		return null;
//	}
	
	
	
	
	ProductService productService;
	ServletContext servletContext;
	
	@Autowired
	public BuyProductPageController(ProductService productService, ServletContext servletContext) {
		this.productService = productService;
		this.servletContext = servletContext;
	}

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
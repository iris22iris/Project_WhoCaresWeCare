package com.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductService;

@Controller
public class BuyMenuController {
	
	
	HttpSession httpSession;
	ProductService productService;

	@Autowired
	public BuyMenuController(ProductService productService, HttpSession httpSession) {
		this.productService = productService;
		this.httpSession = httpSession;
	}
	
	@GetMapping("/buyMenu")
	public String buyProductMenu(Model model) {
		List<ProductBean> products = productService.getAllProducts();
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}
		
		return "_04_buyProductMenu";
	}
	
	
//	@GetMapping("/buyMenu")
//	public String buyProductMenu(
//			@PathVariable(value = "sortby", required = false) String sort
//			, Model model
//	) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		if (sort != null) {
//			if (sort.startsWith("stock")) {
//				if (sort.endsWith("asc")) {
//					products.sort((ProductBean pb1, ProductBean pb2) -> pb1.getStock() - pb2.getStock());
//					model.addAttribute("products", products);
//				} else if (sort.endsWith("desc")) {
//					products.sort((ProductBean pb1, ProductBean pb2) -> pb2.getStock() - pb1.getStock());
//					model.addAttribute("products", products);
//				}
//			} else if (sort.startsWith("price")) {
//				if (sort.endsWith("asc")) {
//					products.sort((ProductBean pb1, ProductBean pb2) -> 
//									pb1.getPrice().intValue() - pb2.getPrice().intValue());
//					model.addAttribute("products", products);
//				} else if (sort.endsWith("desc")) {
//					products.sort((ProductBean pb1, ProductBean pb2) -> 
//					pb2.getPrice().intValue() - pb1.getPrice().intValue());
//					model.addAttribute("products", products);
//				}
//			}
//		} else {
//			model.addAttribute("products", products);
//		}
//		model.addAttribute("productTypes", productTypes);
//		return "_04_buyProductMenu";
//	}

	@GetMapping("/buyMenu/{productType.prodType}")
	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
		List<ProductBean> products = productService.getProductsByProdType(new ProductTypeBean(prodtype));
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		return "_04_buyProductMenu";
	}

}
package com.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductService;

@Controller
public class BuyMenuController {

	ProductService productService;
	HttpSession httpSession;

	@Autowired
	public BuyMenuController(ProductService productService, HttpSession httpSession) {
		this.productService = productService;
		this.httpSession = httpSession;
	}

	@GetMapping({ "/buyMenu", "/buyMenu/{prodType}" })
	public String buyProductMenu (
			@PathVariable(name = "prodType", required = false) String prodType,
			@RequestParam(name = "sortType", required = false) String sortType,
			@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
			Model model
	) {

		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}

		List<ProductBean> products = null;
		int totalPages = 0;

		if (prodType != null) {
			if (sortType != null) {
				products = productService.getProductsByProdTypeAndPageSort(new ProductTypeBean(prodType), pageNo,
						sortType);
				String[] token = sortType.split(" ");
				model.addAttribute("sortType", token[0] + "+" + token[1]);
			} else {
				products = productService.getProductsByProdTypeAndPage(new ProductTypeBean(prodType), pageNo);
				model.addAttribute("sortType", null);
			}
			totalPages = productService.getTotalPagesByProdType(new ProductTypeBean(prodType));
		} else {
			if (sortType != null) {
				products = productService.getAllProductsByPageSort(pageNo, sortType);
				model.addAttribute("sortType", sortType);
			} else {
				products = productService.getAllProductsByPage(pageNo);
				model.addAttribute("sortType", null);
			}
			totalPages = productService.getTotalPages();
		}

		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPages", totalPages);

		return "_04_buyProductMenu";
	}

}
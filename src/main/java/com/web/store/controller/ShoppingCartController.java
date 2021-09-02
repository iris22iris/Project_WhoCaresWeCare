package com.web.store.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.store.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
	HttpServletRequest request;
	
	HttpServletResponse response;
			
	ShoppingCartService shoppingCartService;
	
	ServletContext servletContext;
	
	@Autowired
	public ShoppingCartController(HttpServletRequest request, HttpServletResponse response,
			ShoppingCartService shoppingCartService, ServletContext servletContext) {
		this.request = request;
		this.response = response;
		this.shoppingCartService = shoppingCartService;
		this.servletContext = servletContext;
	}
	
	@GetMapping("buyMenu/addCart/{product.prodId}")
	public String AddProductToCart(
			@PathVariable("product.prodId") String prodId,
			@PathVariable("amount") Integer amount
			, Model model
	) {
//		HttpSession httpSession = request.getSession();
		Map<String, Integer> cart = shoppingCartService.getContent();
		if (cart != null) {
			cart.put("prodId", amount);
		}
		else {
			cart = new LinkedHashMap<>();
			cart.put("prodId", amount);
		}
		
		return "_04_buyProductMenu";
	}
	
}
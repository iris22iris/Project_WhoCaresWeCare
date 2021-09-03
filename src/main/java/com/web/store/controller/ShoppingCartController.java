package com.web.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;

@Controller
public class ShoppingCartController {
	
	
	HttpSession httpSession;
	
	@Autowired
	public ShoppingCartController(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	@PostMapping("/buyMenu/addCart/{product.prodId}")
	public String AddProductToCart(
			@PathVariable("product.prodId") Integer prodId,
			@RequestParam("amount") Integer amount,
			Model model
	) {	
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}
		
		ProductBean productBean = new ProductBean();
		productBean.setProdId(prodId);
		productBean.setStock(amount);
		shoppingCart.addToCart(prodId, productBean);
		
		return "redirect:/buyMenu";
	}
	
}
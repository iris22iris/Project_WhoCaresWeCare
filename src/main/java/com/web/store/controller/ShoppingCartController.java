package com.web.store.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.service.ProductService;
import com.web.store.service.impl.ProductServiceImpl;


@Controller
@SessionAttributes({ "LoginOK", "products", "ShoppingCart" })
public class ShoppingCartController {

	private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	ProductService productService;
	
	HttpSession httpSession;

	@Autowired
	public ShoppingCartController(HttpSession httpSession , ProductService productService) {
		this.httpSession = httpSession;
		this.productService = productService;
	}

	// 加入購物車
	@PostMapping("/buyMenu/addCart/{prodId}")
	public String addProductToCart(@PathVariable("prodId") Integer prodId,
			@RequestParam(name = "prodQTY", required = false) Integer prodQTY, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
			log.info("建立新的shoppingCart放進session");
		}
		

		String prodIdStr = request.getParameter("prodId");
		int prodcuctId    = Integer.parseInt(prodIdStr.trim());
		
		ProductBean productBean  = new ProductBean();
		productBean = productService.getProductById(prodcuctId);
		
		BigDecimal productQTY = new BigDecimal(request.getParameter("prodQTY"));
		BigDecimal itemSum = productQTY.multiply(productBean.getPrice());
		
		// 將資料封裝到buyItemBean
		BuyItemBean buyItemBean = new BuyItemBean(prodQTY,itemSum,
												productBean.getPromotionBean(),productBean);
		shoppingCart.addProductToCart(prodId, buyItemBean);
		log.info("將buyItem資料封裝放進session");
		
		return "redirect:/buyMenu";
	}

}
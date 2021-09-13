package com.web.store.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.web.store.model._05_customer.CustomerBean;

@Controller
@SessionAttributes({ "LoginOK", "products", "ShoppingCart"})
public class ShoppingCartController {
	
	HttpSession httpSession;
	
	@Autowired
	public ShoppingCartController(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	//加入購物車
	@SuppressWarnings("unchecked")
	@PostMapping("/buyMenu/addCart/{prodId}")
	public String addProductToCart(
			@PathVariable("prodId") Integer prodId,
			@RequestParam(name = "prodQTY", required = false) Integer prodQTY,
			Model model, HttpServletRequest request, HttpServletResponse response
	) throws ServletException , IOException{	
		
		CustomerBean customerBean = (CustomerBean) model.getAttribute("LoginOK");
		if(customerBean == null) {
			return "redirect:/_05_login";
		}
		
		HttpSession session = request.getSession(false); 
		if (session == null) {
			return "redirect:/_05_login";
		}
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart shoppingCart = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			model.addAttribute("ShoppingCart", shoppingCart);
		}
		
		String prodIdString = request.getParameter("prodId");
		int productId = Integer.parseInt(prodIdString.trim());
		
		String qryString = request.getParameter("prodQTY");
		Integer qty = 0;
		
		Map<Integer,ProductBean> productMap = (Map<Integer,ProductBean>)session.getAttribute("products");
		ProductBean bean = productMap.get(productId);
		
		try{
			// 進行資料型態的轉換
			qty = Integer.parseInt(qryString.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		
		
		BigDecimal qty0 = new BigDecimal(qty.toString());
		BigDecimal itemSum = qty0.multiply(bean.getPrice());
		//將資料封裝到buyItemBean
		BuyItemBean buyItemBean = new BuyItemBean(productId,qty,itemSum,
													bean.getPromotionBean().getDiscountCode(),
													bean.getPromotionBean().getDiscount());
//		buyItemBean.setProductBean(new ProductBean(prodId));
//		buyItemBean.setProdQTY(prodQTY);
		shoppingCart.addProductToCart(productId, buyItemBean);
		return "redirect:/buyMenu";
	}
	
	
	//購物車內容
	
	
}
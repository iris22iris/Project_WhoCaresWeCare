package com.web.store.controller;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.service.ProductService;


@Controller
@SessionAttributes({ "LoginOK", "ShoppingCart" })
public class ShoppingCartController {

	private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	ProductService productService;

	HttpSession httpSession;

	@Autowired
	public ShoppingCartController(HttpSession httpSession, ProductService productService) {
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
		int prodcuctId = Integer.parseInt(prodIdStr.trim());

		ProductBean productBean = new ProductBean();
		productBean = productService.getProductById(prodcuctId);

		BigDecimal productQTY = new BigDecimal(request.getParameter("prodQTY"));
		BigDecimal itemSum = productQTY.multiply(productBean.getPrice());

		// 將資料封裝到buyItemBean
		BuyItemBean buyItemBean = new BuyItemBean(prodQTY, itemSum, productBean.getPromotionBean(), productBean);
		shoppingCart.addProductToCart(prodId, buyItemBean);
		log.info("將buyItemBean資料封裝放進shoppingCart");
		return "redirect:/buyMenu";
	}

	//購物車頁面
	@GetMapping("/_04_shoppingCart")
	public String shoppingCart(Model model) 
	{
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (cart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		
		Map<Integer, BuyItemBean> cartContent = cart.getContent();
		Set<BuyItemBean> buyItems = new LinkedHashSet<>();
		Set<Integer> set = cartContent.keySet();
		for(Integer i : set) {
			BuyItemBean bib = cartContent.get(i);
			buyItems.add(bib);
		}
		model.addAttribute("buyItems",buyItems);

		return "_04_shoppingCart";
	}
	
	//移除購物車商品
	@GetMapping("/UpdateItem.do")
	protected String updateItem(@RequestParam(value = "prodId", required = false) String prodId,
			 Model model) {
			
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (cart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		
		String ids = (String) model.getAttribute("prodId");
		String[] productId = ids.split("\\,");
		int itemsNum = productId.length;
		
		for(int i=0 ; i < itemsNum ; i++) {
			cart.deleteProducts(Integer.parseInt(productId[i]));
		}
		log.info("總共刪除了購物車內"+ itemsNum +"項商品。");
//		log.info("cmd=" + cmd);
//		if (cmd.equalsIgnoreCase("DEL")) {
//			cart.deleteProduct(prodId); // 刪除購物車內的某項商品
//			return "shoppingCart";
//		} else if (cmd.equalsIgnoreCase("MOD")) {
//			sc.modifyQty(bookId, newQty); // 修改某項商品的數項
//			return SHOW_CART_CONTENT;
//		} else {
//			return "shoppingCart";
//		}
		return "_04_shoppingCart";
	}

}
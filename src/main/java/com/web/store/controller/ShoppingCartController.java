package com.web.store.controller;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._06_order.OrdBean;
import com.web.store.service.OrderService;
import com.web.store.service.ProductService;


@Controller
@SessionAttributes({ "LoginOK", "ShoppingCart","OrdBean" })
public class ShoppingCartController {

	private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	ProductService productService;
	OrderService orderService;
	HttpSession httpSession;

	@Autowired
	public ShoppingCartController(HttpSession httpSession, ProductService productService,
			OrderService orderService) {
		this.httpSession = httpSession;
		this.productService = productService; 
		this.orderService = orderService;
	}
	
	
	public void shoppingCartFun(Integer prodId, Integer prodQTY) {
		// 取出存放在session物件內的ShoppingCart物件
				ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
				if (shoppingCart == null) {
					shoppingCart = new ShoppingCart();
					httpSession.setAttribute("ShoppingCart", shoppingCart);
					log.info("建立新的shoppingCart放進session");
				}

				int prodcuctId = Integer.parseInt(prodId.toString().trim());

				ProductBean productBean = new ProductBean();
				productBean = productService.getProductById(prodcuctId);
				Double itemSum = prodQTY* productBean.getPrice();

				// 將資料封裝到buyItemBean
				BuyItemBean buyItemBean = new BuyItemBean(prodQTY, itemSum, productBean.getPromotionBean(), productBean);
				shoppingCart.addProductToCart(prodId, buyItemBean);
				log.info("將buyItemBean資料封裝放進shoppingCart");
	}

	// 加入購物車
	@PostMapping("/buyMenu/addCart/{prodId}")
	public String addProductToCart(
			@PathVariable("prodId") Integer prodId,
			@RequestParam(name = "prodQTY") Integer prodQTY, 
			@RequestParam Integer flag, 
			Model model) {
		shoppingCartFun(prodId, prodQTY);
		
		if (flag == 1) {
			return "redirect:/buyMenu";
		} else {
			return "index";
		}
		
	}

	//顯示購物車內容
	@GetMapping("/_04_shoppingCart")
	public String ShoppingCart(Model model) 
	{
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (cart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");
		if(ordBean == null) {
			ordBean = new OrdBean();
			httpSession.setAttribute("OrdBean", ordBean);
		}
		log.info("建立OrdBean:"+ordBean);
		
		//取出存在購物車的商品放入Map物件
		Map<Integer, BuyItemBean> cartContent = cart.getContent();	
		//存成Set物件轉換為OrdBean
		Set<BuyItemBean> buyItems = new LinkedHashSet<>();
		Set<Integer> set = cartContent.keySet();
		for(Integer i : set) {
			BuyItemBean bib = cartContent.get(i);
			bib.setOrdBean(ordBean);
			buyItems.add(bib);
		}
		
		ordBean.setBuyItems(buyItems);
		
		model.addAttribute("buyItems",buyItems);
		model.addAttribute("ordBean",ordBean);

		return "_04_shoppingCart";
	}
	
	//移除購物車商品
	@PostMapping("_04_shoppingCart/updateItem.do")
	protected String updateItem(@RequestParam(value = "prodId", required = false) String prodId,
			 Model model) {
			
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");

		String[] productId = prodId.split("\\,");
		int itemsNum = productId.length;
		
		for(int i=0 ; i < itemsNum ; i++) {
			cart.deleteProducts(Integer.parseInt(productId[i]));
		}
		log.info("總共刪除了購物車內"+ itemsNum +"項商品。");
		return "_04_shoppingCart";
	}
	
	//輸入折扣代碼
	@PostMapping("/inputCode.do")
	@ResponseBody
	protected OrdBean inputDiscountCode(
			@ModelAttribute("promotion") PromotionBean promotion,
			@RequestParam(value = "discountCode", required = false) String discountCode,Model model){		
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");

		promotion = orderService.findByDiscountCode(discountCode);
		log.info("折扣碼:"+discountCode+"可使用，可折抵:"+promotion.getDiscount());
		
		ordBean.setDiscountCode(discountCode);
		ordBean.setDiscount(promotion.getDiscount());
		
		log.info("把discountCode & discode資訊放進orderBean");
	
		return ordBean;
		}
		
}
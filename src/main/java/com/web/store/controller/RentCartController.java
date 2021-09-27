package com.web.store.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.RentCart;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._06_order.OrdBean;
import com.web.store.repository.RentProductDao;
import com.web.store.service.OrderService;
import com.web.store.service.ProductService;
import com.web.store.service.RentProductService;


@Controller
@SessionAttributes({ "LoginOK", "RentCart", "OrdBean" })
public class RentCartController {

	private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	RentProductService rentProductService;
	ProductService productService;
	OrderService orderService;
	HttpSession httpSession;

	@Autowired
	public RentCartController(RentProductService rentProductService, OrderService orderService,
			ProductService productService,HttpSession httpSession) {
		this.rentProductService = rentProductService;
		this.productService = productService;
		this.orderService = orderService;
		this.httpSession = httpSession;
	}

	// 加入購物車
	@PostMapping("/rentMenu/addCart/{prodId}")
	public String addProductToCart(@PathVariable("prodId") Integer prodId,
			@RequestParam(name = "prodQty") Integer prodQty,
			@RequestParam(name = "rentPeriod", defaultValue = "1") Integer rentPeriod, Model model) {

		// 取出存放在session物件內的RentCart物件
		RentCart rentCart = (RentCart) httpSession.getAttribute("RentCart");
		if (rentCart == null) {
			rentCart = new RentCart();
			httpSession.setAttribute("RentCart", rentCart);
			log.info("建立新的rentCart放進session");
		}
		
		//指定商品序號
		int prodcuctId = Integer.parseInt(prodId.toString().trim());
		RentProductBean rentProductBean = new RentProductBean();// 商品資訊
		rentProductBean = rentProductService.getProductById(prodcuctId);
		List<RentProductBean> rpb =  rentProductService.getAllSerialStocksByprodId(prodcuctId);
		int serialnumSize = rpb.size();
		for(int n = 1; n <= serialnumSize ; n++) {
			if(rpb.get(n-1).getStock() != 0) {
				rentProductBean.setSerialNumber(rpb.get(n-1).getSerialNumber());
				break;
			}
		}
		
		Double itemSum = rentPeriod * rentProductBean.getPrice(); // 商品小計
		String status = "租賃中"; // 商品狀態

		// 租賃起始日
		Date orderDate = new Date();
		Calendar shippingDate = Calendar.getInstance();
		shippingDate.setTime(orderDate);
		shippingDate.add(Calendar.DAY_OF_YEAR, 7);
		Date startDate = shippingDate.getTime();
		log.info("租賃起始日:" + startDate);

		// 設備歸還日
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(orderDate);
		endDate.add(Calendar.DAY_OF_YEAR, (7 + rentPeriod));
		Date returnDate = endDate.getTime();
		log.info("租賃歸還日:" + returnDate);
		
		ProductBean productBean = productService.getProductById(prodId);
		
		// 將資料封裝到rentItemBean
		RentItemBean rentItemBean = new RentItemBean(rentPeriod, prodQty, startDate, returnDate, itemSum, status,
				rentProductBean.getPromotionBean(), rentProductBean,productBean);
		rentCart.addProductToCart(prodId, rentItemBean);
		log.info("將rentItemBean資料封裝放進rentCart");
		return "redirect:/rentMenu";

	}

	// 顯示購物車內容
	@GetMapping("/_03_rentItemList")
	public String RentCart(Model model, SessionStatus status) {

		RentCart rentCart = (RentCart) httpSession.getAttribute("RentCart");
		if (rentCart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/rentMenu";
		}
		
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");
		if(ordBean == null) {
			ordBean = new OrdBean();
			httpSession.setAttribute("OrdBean", ordBean);
		}
		log.info("建立OrdBean:"+ordBean);
		
		Map<Integer,RentItemBean> cartContent = rentCart.getContent();
		//存成Set物件轉換為OrdBean
		Set<RentItemBean> rentItems = new LinkedHashSet<>();
		Set<Integer> set = cartContent.keySet();
		for(Integer i : set) {
			RentItemBean rib = cartContent.get(i);
			rib.setOrdBean(ordBean);
			rentItems.add(rib);
		}
		ordBean.setRentItems(rentItems);
		
		model.addAttribute("rentItems", rentItems);
		model.addAttribute("ordBean",ordBean);
		
		return "/_03_rentItemList";
	}
	
	//移除購物車商品
	@PostMapping("/_03_rentCart/updateItem.do")
	protected String updateItem(@RequestParam(value = "prodId", required = false) String prodId,
			 Model model) {
			
		RentCart rentCart = (RentCart) httpSession.getAttribute("RentCart");

		String[] productId = prodId.split("\\,");
		int itemsNum = productId.length;
		
		for(int i=0 ; i < itemsNum ; i++) {
			rentCart.deleteProducts(Integer.parseInt(productId[i]));
		}
		log.info("總共刪除了購物車內"+ itemsNum +"項商品。");
		return "_03_rentCart";
	}

}

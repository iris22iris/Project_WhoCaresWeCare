package com.web.store.controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderService;

@Controller
@SessionAttributes({ "LoginOK", "ShoppingCart","OrdBean" })
public class BuyCheckoutController {

	private static Logger log = LoggerFactory.getLogger(BuyCheckoutController.class);
	
	OrderService orderService;
	HttpSession httpSession;
	CustomerService customerService;

	
	@Autowired
	public BuyCheckoutController(OrderService orderService, HttpSession httpSession, CustomerService customerService) {
		super();
		this.orderService = orderService;
		this.httpSession = httpSession;
		this.customerService = customerService;
	}


	//顯示結帳頁面
	@GetMapping("/BuyCheckout/{custId}")
	public String buyCheckout(@PathVariable Integer custId,Model model,
			@RequestParam(value = "discountCode", required = false) String discountCode) {
		//判斷是否登入
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		log.info("結帳前，會員登入確認。");
		if (customerBean == null) {
			return "redirect:/_05_login";
		}
		
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (cart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		
		//取得session內的ordbean物件
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");
		if (ordBean == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/buyMenu";
		}
		log.info("取得OrdBean物件:"+ordBean);
		
		//購物商品明細
		Map<Integer, BuyItemBean> cartContent = cart.getContent();
		//存成Set物件轉換為OrdBean
				Set<BuyItemBean> buyItems = new LinkedHashSet<>();
				Set<Integer> set = cartContent.keySet();
				for(Integer i : set) {
					BuyItemBean bib = cartContent.get(i);
					bib.setOrdBean(ordBean);
					buyItems.add(bib);
				}
				
		//購物商品總金額
		double total = cart.getSubtotal();
		
		ordBean.setOrdTotal(total);
		ordBean.setBuyItems(buyItems);
		ordBean.setCustomerBean(customerBean);
			
		model.addAttribute("buyItems",buyItems);
		model.addAttribute("OrdBean",ordBean);
		log.info("傳回OrdBean的Map物件:"+ordBean);
		
		return "_04_buyCheckout";
	}
	
	//送出訂單
	@PostMapping("/orderSubmit/{custId}")
	protected String orderComfirm(
			@PathVariable Integer custId,
			@ModelAttribute("OrdBean") OrdBean ordBean,
			@RequestParam(value = "reciName", required = false) String reciName,
//			@RequestParam(value = "reciName", required = false) String reciCity,
			@RequestParam(value = "reciAddress", required = false) String reciAddress,
			@RequestParam(value = "reciPhone", required = false) String reciPhone,
			@RequestParam(value = "delivery", required = false) String delivery,
//			@RequestParam(name = "orderMark", required = false) Clob orderMark,
			@RequestParam(value = "discountCode", required = false) String discountCode,
			Model model,SessionStatus status) {
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		log.info("訂單完成前，會員登入確認。");
		if (customerBean == null) {
			return "redirect:/_05_login";
		}


		//訂單備註
//		Clob omark = null;
//		try {
//			omark = new SerialClob(orderMark.toCharArray());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//訂單狀態:預設訂單成立
		String orderStatus = "orderStatus1";
		//訂單時間
		Timestamp time = new Timestamp(System.currentTimeMillis());  
		//訂單總金額
		Double discount = ordBean.getDiscount();
		Double total = ordBean.getOrdTotal();
		Double shippingFee = 0.00;
		if(delivery == "宅配") {
			shippingFee += 270;
		}
		Double orderSum = total - discount + shippingFee;
		
//		OrdBean(Timestamp orderDate, String reciName, String reciCity,String reciAddress,
//		String reciPhone, Double ordTotal, String delivery,
//		String discountCode, Double discount, String orderStatus, Timestamp shipDate,
//		Clob orderMark, Set<RentItemBean> rentItems, CustomerBean customerBean, Set<BuyItemBean> buyItems)
		OrdBean order = new OrdBean(time,reciName,null,reciAddress,
									reciPhone,orderSum,delivery,
									discountCode,discount,orderStatus,null,
									null,null,customerBean,ordBean.getBuyItems());
		//設定訂單種類
		OrdPK pk = new OrdPK();
		pk.setCategory("B");
		order.setOrdPK(pk);
		
		model.addAttribute("OrdBean",order);
		try {
			orderService.save(order);
			log.info("訂單已經成功寫入表格");
			return "forward:" + "/_04_payPayment";
		}catch (RuntimeException ex) {
			String message = ex.getMessage();
			String shortMsg = "" ;   
			System.out.println("message=" + message);
			shortMsg =  message.substring(message.indexOf(":") + 1);
			log.info("處理訂單時發生異常: " + shortMsg);
			return "redirect:/BuyCheckout/{custId}";
		}
		
		
	}
}

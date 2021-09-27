package com.web.store.controller;

import java.sql.Clob;
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

import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.RentCart;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.repository.OrderQueryDao;
import com.web.store.service.BuyItemService;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderService;
import com.web.store.service.RentItemService;

@Controller
@SessionAttributes({ "LoginOK", "RentCart", "OrdBean" })
public class RentCheckoutController {

	private static Logger log = LoggerFactory.getLogger(RentCheckoutController.class);

	OrderService orderService;
	HttpSession httpSession;
	CustomerService customerService;
	RentItemService rentItemService;
	OrderQueryDao orderQueryDao;

	@Autowired
	public RentCheckoutController(OrderService orderService, HttpSession httpSession, CustomerService customerService,
			RentItemService rentItemService, OrderQueryDao orderQueryDao) {
		super();
		this.orderService = orderService;
		this.httpSession = httpSession;
		this.customerService = customerService;
		this.rentItemService = rentItemService;
		this.orderQueryDao = orderQueryDao;
	}

	// 顯示結帳頁面
	@GetMapping("/RentCheckout/{custId}")
	public String RentCheckout(@PathVariable Integer custId, Model model) {
		// 判斷是否登入
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		log.info("結帳前，會員登入確認。");
		if (customerBean == null) {
			return "redirect:/_05_login";
		}

		RentCart rentCart = (RentCart) httpSession.getAttribute("RentCart");
		if (rentCart == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/rentMenu";
		}

		// 取得session內的ordbean物件
		OrdBean ordBean = (OrdBean) httpSession.getAttribute("OrdBean");
		if (ordBean == null) {
			// 如果購物車內沒有商品就導回商品menu
			return "redirect:/rentMenu";
		}
		log.info("取得OrdBean物件:" + ordBean);

		// 購物商品明細
		Map<Integer, RentItemBean> cartContent = rentCart.getContent();
		// 存成Set物件轉換為OrdBean
		Set<RentItemBean> rentItems = new LinkedHashSet<>();
		Set<Integer> set = cartContent.keySet();
		for (Integer i : set) {
			RentItemBean rib = cartContent.get(i);
			rib.setOrdBean(ordBean);
			rentItems.add(rib);
		}

		// 購物商品總金額
		double total = rentCart.getSubtotal();

		ordBean.setOrdTotal(total);
		ordBean.setRentItems(rentItems);
		ordBean.setCustomerBean(customerBean);

		model.addAttribute("rentItems", rentItems);
		model.addAttribute("OrdBean", ordBean);
		log.info("傳回OrdBean的Map物件:" + ordBean);

		return "_03_rentCheckout";
	}

	//送出訂單
	@PostMapping("/rentOrderSubmit")
	protected String OrderComfirm(
			@ModelAttribute("OrdBean") OrdBean ordBean,
			@RequestParam(value = "custId") Integer custId,
			@RequestParam(value = "reciName", required = false) String reciName,
//			@RequestParam(value = "reciCity", required = false) String reciCity,
			@RequestParam(value = "reciAddress", required = false) String reciAddress,
			@RequestParam(value = "reciPhone", required = false) String reciPhone,
			@RequestParam(value = "delivery") String delivery,
			@RequestParam(value = "discountCode", required = false) String discountCode,
			@RequestParam(value = "payment", defaultValue = "線上刷卡") String payment,
			@RequestParam(value = "oMark", defaultValue = "") String oMark, Model model, SessionStatus status) {

		log.info("準備開始處理訂單");
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute(customerBean);
		log.info("會員編號:" + custId);
		// 訂單備註
		Clob omark = null;
		try {
			omark = new SerialClob(oMark.toCharArray());

		} catch (Exception e) {
			System.out.println("clob寫入異常:" + e);
		}

		// 訂單狀態:預設訂單成立
		String orderStatus = "confirmed";

		// 訂單時間
		Timestamp time = new Timestamp(System.currentTimeMillis());

		// 訂單金額計算
		Double discount = ordBean.getDiscount();
		if (ordBean.getDiscount() == null) {
			discount = 0.0;
		}
		log.info("優惠折抵:" + discount);
		Double total = ordBean.getOrdTotal();
		log.info("商品金額:" + total);
		Double shippingFee = 0.00;
		if (delivery == "宅配") {
			shippingFee += 270;
		}
		log.info("運費:" + shippingFee);
		Double orderSum = total - discount + shippingFee;
		log.info("訂單總金額:" + orderSum);
		
		// 設定訂單pk
		OrdPK lastPk = orderService.getCurrentOrdId();
		int id = lastPk.getOrdId() + 1;
		OrdPK newPk = new OrdPK("R", id);
		log.info("設定訂單編號:" + newPk);

		// 商品明細 & 設定租賃設備狀態
		RentCart rentCart = (RentCart) httpSession.getAttribute("RentCart");
		Map<Integer, RentItemBean> cartContent = rentCart.getContent();
		Set<RentItemBean> rentItems = new LinkedHashSet<>();
		Set<Integer> set = cartContent.keySet();
		int num = 1;
		for (Integer i : set) {
			RentItemBean rib = cartContent.get(i);
			rib.setRentItemPK(new RentItemPK(newPk, num));
			num++;
			rentItems.add(rib);
		}
		log.info("設定rentItems主鍵編號");

		ordBean.setOrdPK(newPk);
		ordBean.setDiscount(discount);
		ordBean.setOrderDate(time);
		ordBean.setOrderStatus(orderStatus);
		ordBean.setOrdTotal(orderSum);
		ordBean.setOrderMark(omark);
		ordBean.setRentItems(rentItems);

		log.info("準備訂單物件order");
		model.addAttribute("OrdBean", ordBean);
		try {
			orderService.save(ordBean);
			log.info("訂單已經成功寫入表格");

		} catch (RuntimeException ex) {
			String message = ex.getMessage();
			String shortMsg = "";
			System.out.println("message=" + message);
			shortMsg = message.substring(message.indexOf(":") + 1);
			log.info("處理訂單時發生異常: " + shortMsg);
			return "redirect:/_03_rentCart";
		}

		return "_04_orderSuccess";
	}

}

package com.web.store.controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.JavaMail;
import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderManageService;
import com.web.store.service.OrderQueryService;
import com.web.store.service.RentItemService;
import com.web.store.service.RentProductService;

@Controller
public class OrderManageController {

	CustomerService customerService;
	OrderManageService orderManageService;
	OrderQueryService orderQueryService;
	RentProductService rentProductService;
	RentItemService rentItemService;
	HttpSession httpSession;
	HttpServletRequest request;

//	MailService mailservice;
//	JavaMail mail;
	
	
	@Autowired
	public OrderManageController(CustomerService customerService, OrderManageService orderManageService,
			OrderQueryService orderQueryService, RentProductService rentProductService, RentItemService rentItemService,
			HttpSession httpSession, HttpServletRequest request/*, MailService mailservice ,JavaMail mail*/
	) {
		this.customerService = customerService;
		this.orderManageService = orderManageService;
		this.orderQueryService = orderQueryService;
		this.rentProductService = rentProductService;
		this.rentItemService = rentItemService;
		this.httpSession = httpSession;
		this.request = request;
		
//		this.mailservice = mailservice;
//		this.mail = mail;
	}

	// 進入租賃訂單查詢頁(含查詢字串)
	@GetMapping("/rentOrderManage/{custId}")
	public String rentOrderQuery(@PathVariable Integer custId,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "ordId", defaultValue = "0", required = false) Integer ordId, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		CustomerBean customerBean = customerService.getCustomerById(custId);
		List<OrdBean> ordBeans = orderManageService.findOrdBeansByCategory("R");
		model.addAttribute(customerBean);
		model.addAttribute(ordBeans);
		if (custId != null && category != null && ordId != null) {
			OrdBean ordBean = orderManageService.findOrdBeanById(category, ordId);
			List<RentItemBean> rentItems = orderQueryService.findRentItemByOrdId(ordId);
			if (ordBean != null) {
				model.addAttribute(ordBean);
				try {
					Clob orderMarkClob = ordBean.getOrderMark();
					String orderMark = orderMarkClob.getSubString(1L, (int) orderMarkClob.length());
					model.addAttribute("orderMark", orderMark);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				model.addAttribute("rentItems", rentItems);
				String[] commentList = new String[rentItems.size()];
				for (int i = 0; i < rentItems.size(); i++) {
					if (rentItems.get(i).getCommentBean() != null) {
						Clob tempComment = rentItems.get(i).getCommentBean().getComment();
						try {
							commentList[i] = tempComment.getSubString(1L, (int) tempComment.length());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				if (commentList != null) {
					model.addAttribute("commentList", commentList);
				}
			} else {
				model.addAttribute("orderNotFound", "查無此訂單，請重新輸入正確的訂單編號");
			}
		}

		return "_06_rentOrderManage";
	}

//	歸還租賃設備
	@PostMapping("/rentOrderManage/returnProduct")
	public String rentOrderQueryComment(@RequestParam("custId") Integer custId,
			@RequestParam(name = "category", defaultValue = "R", required = false) String category,
			@RequestParam(name = "ordId", required = false) Integer ordId,
			@RequestParam(name = "prodSerialNum", required = false) Integer prodSerialNum, Model model) throws MessagingException   {
		RentItemBean rentItemBean = rentItemService
				.findRentItemByPK(new RentItemPK(new OrdPK(category, ordId), prodSerialNum));
		RentProductBean rentProductBean = rentItemBean.getRentProductBean();
		rentProductBean.setStock(rentProductBean.getStock() + 1);
		rentItemBean.setReturnDate(new Timestamp(System.currentTimeMillis()));
		rentItemBean.setRentStatus("已歸還");
		rentItemService.updateRentItem(rentItemBean);
		rentProductService.updateRentProduct(rentProductBean);

		//用商品編號取得該產品預約資料(排隊人列表)
		List<ReservationBean> reservations = rentProductService.getReservationBeanByprodId(rentItemBean.getRentProductBean().getProdId());
			 boolean ans = reservations.isEmpty();
        if (ans == true) {}
        	
        else {
        	
		JavaMail resInformMail = new JavaMail								
				("java016030924@gmail.com","odoayywtzmaexumf",
				"使用者名稱@gmail.com","測試信件","嗨華晏 請問您您您有看到嘛! This is a test Mail!");
//		mailservice.sendRservationInformMail(mail);
		resInformMail.setCustomer(reservations.get(0).getCustomerBean().getEmail());
		resInformMail.setSubject("Whocares租賃通知");
		resInformMail.setTxt(reservations.get(0).getCustomerBean().getCustName()
							+"先生/小姐您好!"
							+"<br>"
							+"您預約的設備:"
							+"<br>"
							+reservations.get(0).getRentProductBean().getProdName()
							+"<br>"
							+"已經準備好了。"
							+"<br>"
							+"請您到以下連結進行租賃感謝!"
							+"<br>"
							+"http://localhost:8080/Whocares/_03_rentProduct?id="
							+reservations.get(0).getRentProductBean().getProdId());
		resInformMail.sendMail();
        };
		
		
		
		
		

		return "redirect:/rentOrderManage/" + custId + "/?category=" + category + "&ordId=" + ordId;
	}

}

package com.web.store.controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductTypeService;
import com.web.store.service.PromotionService;
import com.web.store.service.RentProductService;


@Controller
public class RentProductPageController {
	
	PromotionService promotionService;
	RentProductService rentProductService;
	ProductTypeService productTypeService;
	ServletContext servletContext;
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	public RentProductPageController(RentProductService rentProductService, ProductTypeService productTypeService,
			PromotionService promotionService,ServletContext servletContext) {
		this.rentProductService = rentProductService;
		this.productTypeService = productTypeService;
		this.servletContext = servletContext;
		this.promotionService = promotionService;
	}

	@GetMapping("/_03_rentProduct")
	public String getProductById(@RequestParam("id") Integer id ,Model model) {
		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
		List<ProductTypeBean> productTypes = productTypeService.getAllProdTypes();
		List<PromotionBean> promotions = promotionService.getAllPromotions();
		
		//用商品編號取得該產品及其項次庫存資料
		List<RentProductBean> AllSerialStocks = rentProductService.getAllSerialStocksByprodId(id);
		//用商品編號取得該產品評論資料
		List<CommentBean> comments = rentProductService.getCommentBeanByprodId(id);
		//用商品編號取得該產品預約資料
		List<ReservationBean> reservations = rentProductService.getReservationBeanByprodId(id);
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("rentProduct", rentProductService.getProductById(id));
		model.addAttribute("allserialstocks", AllSerialStocks);
		model.addAttribute("promotions", promotions);

		
		
		//用商品小分類取得該產品大分類
		String productType = rentProductService.getProductById(id).getProductTypeBean().getProdType();
		String maincategory = productType.substring(0,1);
		List<ProductTypeBean> maincategorys = rentProductService.getProductTypeBeanBymaincategory(maincategory);
		model.addAttribute("maincategorys", maincategorys);
		
		model.addAttribute("comments", comments);
		model.addAttribute("reservations", reservations);
		
		ReservationBean rb = new ReservationBean();		
		model.addAttribute("reservation", rb);
		
		//商品詳細clob轉文字
		RentProductBean rentProductBean = rentProductService.getProductById(id);
		if (rentProductBean != null) {
			model.addAttribute(rentProductBean);
			try {
				Clob rentProductDescriptionClob = rentProductBean.getDescription();
				String rentProductDescription = rentProductDescriptionClob.getSubString(1L, (int) rentProductDescriptionClob.length());
				model.addAttribute("rentProductDescription", rentProductDescription);
				} catch (SQLException e) {
				e.printStackTrace();	  
					}
		}
		//評論內容clob轉文字
		if (comments != null) {			
			try {
				String rentProductComment = "";
				for (int i = 0; i < comments.size(); i++) {
					Clob rentProductCommentClob = comments.get(i).getComment();
					rentProductComment += (rentProductCommentClob.getSubString(1L, (int) rentProductCommentClob.length())
											+"|");										
				}
				
			    List<String> rentProductComments = java.util.Arrays.asList(rentProductComment.split("\\|")); 			  
				model.addAttribute("rentProductComments", rentProductComments);				
				} catch (SQLException e) {
				e.printStackTrace();	  
					}
		}
		return "_03_rentProduct";
	};
	
	
	//預約popout點擊預約畫面把資料送到資料庫
	
	@PostMapping("/_03_rentProduct")
	public String processAddNewProductForm	(@RequestParam("id") Integer id 
			,@ModelAttribute("reservation") ReservationBean rb,Model model) {
		

		List<ReservationBean> reservations = rentProductService.getReservationBeanByprodId(id);

		model.addAttribute("rentProduct", rentProductService.getProductById(id));

								
		rb.setCategory("RES");
		rb.setClassify("R");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		rb.setReserveDate(timestamp);
		//先前沒有被人預約的情況要排除掉
		 boolean ans = reservations.isEmpty();
	        if (ans == true)
	        	rb.setWaitNum(1);
	        else
	        	rb.setWaitNum(reservations.get(reservations.size()-1).getWaitNum()+1);
	        
		
	        HttpSession session = request.getSession();

			
		List<CustomerBean> customerinfo = rentProductService.getCustomerInfoByLoginAccount((String) session.getAttribute("LoginAccount"));
		

		model.addAttribute("customerinfo", customerinfo);
		rb.setCustomerBean(customerinfo.get(0));
		rb.setRentProductBean(rentProductService.getProductById(id));
		rentProductService.addReservation(rb);	
		
	
		
		return "redirect:/_03_reservationSuccess" ;
	}
	
	@GetMapping("/_03_reservationSuccess")
		public String showAddedReservationdata(Model model){
		HttpSession session = request.getSession();

		
		List<CustomerBean> customerinfo = rentProductService.getCustomerInfoByLoginAccount((String) session.getAttribute("LoginAccount"));
		model.addAttribute("customerinfo", customerinfo);
		int mycustId = customerinfo.get(0).getCustId();
		List<ReservationBean> myreservations = rentProductService.getMyReservationByCustId(mycustId);
		model.addAttribute("myreservations", myreservations);
		return "_03_reservationSuccess"  ;
	}
	
	


	
}
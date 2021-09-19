package com.web.store.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.RentProductService;


@Controller
public class RentProductPageController {

	RentProductService rentProductService;
	ServletContext servletContext;
	
	@Autowired
	public RentProductPageController(RentProductService rentProductService, ServletContext servletContext) {
		this.rentProductService = rentProductService;
		
		this.servletContext = servletContext;
	}
	

	@RequestMapping("/_03_rentProduct")
	public String getProductById(@RequestParam("id") Integer id ,Model model) {
		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
		List<ProductTypeBean> productTypes = rentProductService.getAllProdTypes();
		
		List<CommentBean> comments = rentProductService.getCommentBeanByprodId(id);
		List<ReservationBean> reservations = rentProductService.getReservationBeanByprodId(id);
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("rentProduct", rentProductService.getProductById(id));

		
		String productType = rentProductService.getProductById(id).getProductTypeBean().getProdType();
		String maincategory = productType.substring(0,1);
		List<ProductTypeBean> maincategorys = rentProductService.getProductTypeBeanBymaincategory(maincategory);
		model.addAttribute("maincategorys", maincategorys);
		
		model.addAttribute("comments", comments);
		model.addAttribute("reservations", reservations);
		
		ReservationBean rb = new ReservationBean();		
		model.addAttribute("reservation", rb);
		return "_03_rentProduct";
	};
	
	
	//預約popout點擊預約畫面把資料送到資料庫
	
	@PostMapping("/_03_rentProduct")
	public String processAddNewProductForm	(@RequestParam("id") Integer id ,@CookieValue(value = "user") String user
			,@ModelAttribute("reservation") ReservationBean rb,Model model) {
		

		List<ReservationBean> reservations = rentProductService.getReservationBeanByprodId(id);

		model.addAttribute("rentProduct", rentProductService.getProductById(id));

								
		rb.setCategory("RES");
		rb.setClassify("R");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		rb.setReserveDate(timestamp);
		
		rb.setWaitNum(reservations.get(reservations.size()-1).getWaitNum()+1);
		rb.setProdId(id);
		rb.setSerialNumber("1");
		
		
		List<CustomerBean> customerinfo = rentProductService.getCustomerInfoBycookieaccount(user);
		model.addAttribute("customerinfo", customerinfo);
		rb.setCustomerBean(new CustomerBean(customerinfo.get(0).getCustId(), null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		rb.setRentProductBean(new RentProductBean(id,"1", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		
		rentProductService.addReservation(rb);	
		
		
		
		return "redirect:/_03_reservationSuccess" ;
	}
	
	@GetMapping("/_03_reservationSuccess")
		public String showAddedReservationdata(Model model,@CookieValue(value = "user") String user){
		List<CustomerBean> customerinfo = rentProductService.getCustomerInfoBycookieaccount(user);
		model.addAttribute("customerinfo", customerinfo);
		int mycustId = customerinfo.get(0).getCustId();
		List<ReservationBean> myreservations = rentProductService.getMyReservationByCustId(mycustId);
		model.addAttribute("myreservations", myreservations);
		return "_03_reservationSuccess"  ;
	}
	
	


	
}
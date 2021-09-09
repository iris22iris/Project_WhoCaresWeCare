package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._03_rent.ReservationBean;
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
//		model.addAttribute("reservation", rentProductService.getReservationBeanByprodId(id));
		model.addAttribute("comments", comments);
		model.addAttribute("reservations", reservations);
		return "_03_rentProduct";
	};

	



	
}
package com.web.store.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.ProblemBean;

@Controller
public class ContactUsController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ServletContext context;
	
	
	

	
	// 進入聯絡我們頁/WhoCares/src/main/webapp/WEB-INF/views/_02_contactUs.jsp
//		@GetMapping(value ="/_02_contactUs")
//		public String toContactUs(Model model) {
//			return "_02_contactUs";
//		}
	
		//
	
	
	
		@PostMapping(value ="/_02_contactUs")
		public String insertContactUs(@ModelAttribute("Problem") /* @Valid */ ProblemBean pb, BindingResult result,
				Model model,HttpServletRequest request) {
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			System.out.println("555555 :" + email);
			System.out.println("電話 :" + phone);
			System.out.println("留言 :" + content);
			
			
			return "_02_contactUs";
	
}
		
}


package com.web.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PagesController {

	@Autowired
	HttpServletRequest request;

	@GetMapping("/_02_q_a")
	public String q_a(Model model) {
		return "_02_q_a";
	}

	@GetMapping("/_02_contactUs")
	public String contackUs(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_02_contactUs";
	}

	@GetMapping("/_02_onlineDM")
	public String onlineDM(Model model) {
		return "_02_onlineDM";
	}


	@GetMapping("/_04_favoriteList")
	public String favoriteList(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_04_favoriteList";
	}

	@GetMapping("/_05_loginPopup")
	public String loginPopup(Model model) {
		return "_05_loginPopup";
	}

	@GetMapping("/_05_member_management")
	public String member_management(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "/_05_login";
		}
		return "_05_member_management";
	}

	@GetMapping("/_05_memberProfile")
	public String memberProfile(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_05_memberProfile";
	}

	@GetMapping("/_06_orderQuery")
	public String orderQuery(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_06_orderQuery";
	}

	@GetMapping("/_06_problemReply")
	public String problemReply(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_06_problemReply";
	}

	@GetMapping("/_06_rentOrderQuery")
	public String rentOrderQuery(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_06_rentOrderQuery";
	}

	@GetMapping("/_06_reservationQuery")
	public String reservationQuery(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_06_reservationQuery";
	}

	
	
	@GetMapping("/_04_orderSuccess")
	public String orderSuccess(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_04_orderSuccess";
	}
	
	@GetMapping("/_04_orderConfirm")
	public String OrderConfirm(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "_04_orderConfirm";
	}
	
	@GetMapping("/adminBoss")
	public String adminBoss(Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		return "adminBoss";
	}
	


}

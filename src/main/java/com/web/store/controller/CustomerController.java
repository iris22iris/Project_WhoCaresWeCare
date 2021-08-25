package com.web.store.controller;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ServletContext context;
	@Autowired
	CustomerService customerService;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	@GetMapping("/_05_login")
	public String toLogin(Model model) {
		return "_05_login";
	}

	@PostMapping("/login")
	public String login(Model model) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		try {
			CustomerBean customerBean = customerService.checkIDPassword(account, password);

			if (account == null || account.trim().length() == 0) {
				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度不能小於八個字元");
			} else if (password == null || password.trim().length() == 0 || password.length() < 8) {
				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度不能小於八個字元");
			} else if (customerBean != null) {
				model.addAttribute("LoginOK", customerBean);
			} else {
				errorMsgMap.put("Error", "帳號或密碼有誤，密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"");
			}
		} catch (RuntimeException e) {
			errorMsgMap.put("LoginErrorMsg", e.getMessage());
		}

		if (!errorMsgMap.isEmpty()) {
			model.addAttribute("ErrorMsgKey", errorMsgMap);
			return "_05_login";
		}

		errorMsgMap.put("noError", "index");

		// **********Remember Me****************************
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		if (rememberMe != null) {
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			cookiePassword = new Cookie("password", password);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());

			cookiePassword = new Cookie("password", password);
			cookiePassword.setMaxAge(0);// 代表瀏覽器關掉及刪掉
			cookiePassword.setPath(request.getContextPath());// 告訴主機要刪的Path

			cookieRememberMe = new Cookie("rm", "false");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);

		// ********************************************
		return "index";
	}

	@RequestMapping("/_05_logout")
	public String loginout() {
		return "_05_logout";
	}
	
	
	
	

}

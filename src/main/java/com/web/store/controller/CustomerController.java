package com.web.store.controller;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CustomerService;
import com.web.store.validators.CustomerValidator;

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

	// 進入登入頁
	@GetMapping("/_05_login")
	public String toLogin(Model model) {
		return "_05_login";
	}

	// 進行登入
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

	//登出
	@RequestMapping("/_05_logout")
	public String loginout() {
		return "_05_logout";
	}

	//進入此Controller是先啟用，載入註冊會員資料，目前為空不載入任何資料
	@ModelAttribute
	public void getMember(@PathVariable(value = "custId", required = false) Integer custId, Model model) {
		if (custId != null) {
			CustomerBean customer = customerService.get(custId);
			model.addAttribute("customer", customer);
		} else {
			CustomerBean cb = new CustomerBean();
//			cb.setCustName("Rascal");
//			cb.setEmail("aaa@gmail.com");
//			cb.setBirthday(java.sql.Date.valueOf("1991-08-15"));
//			cb.setAccount("jk951230");
//			Hobby hobby = new Hobby();
//			hobby.setId(2);
//			member.setHobby(hobby);
			model.addAttribute("customer", cb);
		}
	}

	// 新增
	// 當使用者填妥資料按下Submit按鈕後，本方法接收將瀏覽器送來的會員資料，新進行資料的檢查，
	// 若資料有誤，轉向寫入錯誤訊息的網頁，若資料無誤，則呼叫Service元件寫入資料庫
	@PostMapping(value = "/_05_login")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	public String insertCustomer(@ModelAttribute("customer") /* @Valid */ CustomerBean cb, BindingResult result,
			Model model, HttpServletRequest request) {
		String account = request.getParameter("account");
		Map<String, String>  columnErrorMsg = new HashMap<String, String>();
		
		if(customerService.idExists(account)) {
			columnErrorMsg.put("accountError", "帳號以重複，請重新輸入");
		}
		if (!columnErrorMsg.isEmpty()) {
			model.addAttribute("columnErrorMsg", columnErrorMsg);
			return "_05_login";
		}
		CustomerValidator validator = new CustomerValidator();
		// 呼叫Validate進行資料檢查
		validator.validate(cb, result);
		if (result.hasErrors()) {
//	          下列敘述可以理解Spring MVC如何處理錯誤			
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println("有錯誤：" + error);
//			}
			return "_05_login";
		} 
			MultipartFile picture = cb.getImage();
			String originalFilename = picture.getOriginalFilename();
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				cb.setFileName(originalFilename);
			}
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (picture != null && !picture.isEmpty()) {
				try {
					byte[] b = picture.getBytes();
					Blob blob = new SerialBlob(b);
					cb.setCustomerImage(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
//			// 必須要找出對應的Hobby物件
//			Hobby hobby = hobbyService.getHobby(member.getHobby().getId());
//			member.setHobby(hobby);
//			
//			// 必須要找出對應的Category物件
//			Category category = categoryService.getCategory(member.getCategory().getId());
//			member.setCategory(category);

//			Timestamp adminTime = new Timestamp(System.currentTimeMillis());
//			member.setAdmissionTime(adminTime);

		try {
			customerService.save(cb);
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
			result.rejectValue("account", "", "註冊失敗，請通知系統人員...");
			return "_05_login";
		}

		return "index";
	}

	// 本方法可對WebDataBinder進行組態設定。除了表單資料外，絕大多數可以傳入控制器方法的
	// 參數都可以傳入以@InitBinder修飾的方法。本方法最常使用的參數為WebDataBinder。
	//
	// org.springframework.web.bind.WebDataBinder
	// 為 org.springframework.validation.DataBinder 的子類別，它將夾帶在請求物件內
	// 的請求參數綁定在JavaBean內。
	// registerCustomEditor(): 可對JavaBean內之特定型態自定該型態的屬性編輯器(PropertyEditor)
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}

package com.web.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CityService;
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
	@Autowired
	CityService cityService;

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
				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度至少八個字元且小於十二個字元");
			} else if (password == null || password.trim().length() == 0 || password.length() < 8) {
				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度大於八個字元");

			} else if (customerBean != null) {
				model.addAttribute("LoginOK", customerBean);
				HttpSession session = request.getSession();
				session.setAttribute("LoginOK", customerBean.getCustId());
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

	// 進行登入
	@PostMapping("/login_api")
	@ResponseBody
	public String login_api(Model model) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println("555555" + account);
		String rememberMe = request.getParameter("rememberMe");
//		Map<String, String> errorMsgMap = new HashMap<String, String>();
//		try {
//			CustomerBean customerBean = customerService.checkIDPassword(account, password);
//
//			if (account == null || account.trim().length() == 0) {
//				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度不能小於八個字元");
//			} else if (password == null || password.trim().length() == 0 || password.length() < 8) {
//				errorMsgMap.put("accountError", "帳號與密碼欄必須輸入，密碼長度不能小於八個字元");
//			} else if (customerBean != null) {
//				model.addAttribute("LoginOK", customerBean);
//				HttpSession session = request.getSession();
//				session.setAttribute("LoginOK", customerBean.getCustId());
//			} else {
//				errorMsgMap.put("Error", "帳號或密碼有誤，密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"");
//			}
//		} catch (RuntimeException e) {
//			errorMsgMap.put("LoginErrorMsg", e.getMessage());
//		}
//
//		if (!errorMsgMap.isEmpty()) {
//			model.addAttribute("ErrorMsgKey", errorMsgMap);
//			return "error";
//		}
//
//		errorMsgMap.put("noError", "index");
//
//		// **********Remember Me****************************
//		Cookie cookieUser = null;
//		Cookie cookiePassword = null;
//		Cookie cookieRememberMe = null;
//		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
//		if (rememberMe != null) {
//			cookieUser = new Cookie("user", account);
//			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
//			cookieUser.setPath(request.getContextPath());
//
//			cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
//			cookiePassword.setPath(request.getContextPath());
//
//			cookieRememberMe = new Cookie("rm", "true");
//			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
//			cookieRememberMe.setPath(request.getContextPath());
//		} else { // 使用者沒有對 RememberMe 打勾
//			cookieUser = new Cookie("user", account);
//			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
//			cookieUser.setPath(request.getContextPath());
//
//			cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(0);// 代表瀏覽器關掉及刪掉
//			cookiePassword.setPath(request.getContextPath());// 告訴主機要刪的Path
//
//			cookieRememberMe = new Cookie("rm", "false");
//			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
//			cookieRememberMe.setPath(request.getContextPath());
//		}
//		response.addCookie(cookieUser);
//		response.addCookie(cookiePassword);
//		response.addCookie(cookieRememberMe);

		// ********************************************
		return "error";
	}

	// 登出
	@RequestMapping("/_05_logout")
	public String loginout() {
		return "_05_logout";
	}

	// 進入此Controller是先啟用，載入註冊會員資料，目前為空不載入任何資料
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
//			CitySelect hobby = new CitySelect();
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
			Model model, HttpServletRequest request, @RequestParam("account") String account) {
//		String account = request.getParameter("account");
		Map<String, String> columnErrorMsg = new HashMap<String, String>();

		if (customerService.idExists(account)) {
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
//			CitySelect hobby = hobbyService.getHobby(member.getHobby().getId());
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

	// 修改會員
	// 當使用者需要修改時，本方法送回含有會員資料的表單，讓使用者進行修改
	// 由這個方法送回修改記錄的表單...
//	@GetMapping("/_05_memberProfile/{id}")
//	public String modifyCustomer(@PathVariable("id") Integer id, Model model) {
//		CustomerBean modifyCust = customerService.get(id);
//		model.addAttribute("modifyCust", modifyCust);
//		return "/_05_memberProfile";
//	}

	// 傳回能夠編輯單筆會員資料之視圖的邏輯名稱
	@GetMapping(value = "/_05_memberProfile/{id}", produces = { "text/html" })
	public String editMemberFindView(@PathVariable Integer id, Model model) {
		CustomerBean customer = customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("id", id);
		return "_05_memberProfile";
	}


	// 修改單筆會員資料
	@PutMapping(value = "/_05_EditmemberProfile/{key}", consumes = { "application/json" }, produces = {
			"application/json" })
	public @ResponseBody Map<String, String> updateCustomer(@RequestBody CustomerBean customer,
			@PathVariable Integer key) {

		Map<String, String> errorMsgColumn = new HashMap<String, String>();
		try {
			CustomerBean member0 = null;
			final String CUSTNAME_PATTERN = "\\pP|\\pS|\\s+";
			final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,12})";
			String custName = customer.getCustName();
			String nickName = customer.getNickName();
			String idNumber = customer.getIdNumber();
			String email = customer.getEmail();
			String account = customer.getAccount();
			Date birthday = customer.getBirthday();
			String password = customer.getPassword();
			if (key != null) {
				member0 = customerService.getCustomerById(key);
				if (member0 == null) {
					throw new RuntimeException("鍵值不存在, key=" + key);
				}
			} else {
				throw new RuntimeException("鍵值不存在, key=" + key);
			}

			customer.setAccount(member0.getAccount());
//			cityService.getAllCitys(groupCity);

			// 姓名檢核
			if (custName == null || custName.trim().length() == 0) {
				errorMsgColumn.put("custNameError", "會員姓名不能為空");
			}

//			Pattern custNameP = Pattern.compile(CUSTNAME_PATTERN);
//			Matcher cm = custNameP.matcher(custName);
//			if (!cm.matches() && custName.length() > 0 && !custName.contains(CUSTNAME_PATTERN)) {
//				errorMsgColumn.put("custNameError", "不允許有特殊字元");
//			}

			// 密碼檢核
			if (password == null || password.trim().length() == 0) {
				errorMsgColumn.put("passWordError", "密碼欄必須輸入");
			}

			Pattern passWordp = Pattern.compile(PASSWORD_PATTERN);
			Matcher pm = passWordp.matcher(password);
			if (!pm.matches() && password.length() > 0 && password.length() < 12) {
				errorMsgColumn.put("passWordError", "密碼至少含各一個大小寫字母、數字與!@#$%!^'\\\"，且長度至少等於八個字元");
			} else {
				if (!pm.matches() && password.length() > 0 && password.length() > 8) {
					errorMsgColumn.put("passWordError", "密碼至少含各一個大小寫字母、數字與!@#$%!^'\\\"，且長度不能大於十二個字元");
				} else {
					if (pm.matches()) {
						errorMsgColumn.put("passWordError", "密碼格式正確");
					}
				}
			}
			// 身分證檢核
			String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO"; // 字母代號對照表

			if (idNumber == null || idNumber.length() != 10) {
				errorMsgColumn.put("idNumberError", "長度不合法");
			} else {
				if (idNumber != null || idNumber.length() >= 10) {
					errorMsgColumn.put("idNumberError", "長度合法");
				}
			}
			if (idNumber.length() == 10 && idNumber != null) {
				char[] c = idNumber.toUpperCase().toCharArray(); // 建立 c 陣列，同時將s字串轉大寫後，轉成字元陣列放入 c 陣列
				int[] ID = new int[c.length]; // 建立一個運算用的整數陣列，空間為 c 的字元個數
				// 驗證首位字母是否合法 (該字元是否能在checkHead[]找到), 驗證第一位是否為 1 or 2 (1=男生, 2=女生)
				if (checkHead.indexOf(c[0]) == -1 || (c[1] != '1' && c[1] != '2')) {
					errorMsgColumn.put("idNumberError", "格式不合法");
				} else {
					int sum = 0;
					ID[0] = checkHead.indexOf(c[0]) + 10; // 第一個英文字運算
					sum += ID[0] / 10; // .. 將商數加總 sum += ID[0]/10
					ID[0] %= 10; // .. 取餘數放回 ID[0] 以便之後的運算
					for (int i = 1; i < 10; i++) { // 將身分證後9碼轉成整數型態 (ASCII碼-48)
						ID[i] = (int) c[i] - 48;
					}
					for (int i = 0; i < 9; i++) { // 代入公式:
						ID[i] *= (9 - i); // 總和 sum += (ID[0])*9 + ID[1]*8 + ID[2]*7 + ... + ID[9]*1
						sum += ID[i];
					}
					// 檢查(10-sum%10)是否相等於檢查碼，且 sum%10(餘數)為0時，檢查碼為0 => (10-sum%10)%10
					if ((10 - sum % 10) % 10 == ID[9]) {
					} else {
						errorMsgColumn.put("idNumberError", "不合法");
					}
				}
			}
			customerService.updateCustomer(customer);
			errorMsgColumn.put("success", "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgColumn.put("fail", "更新失敗");
		}
		return errorMsgColumn;
	}

//	@ModelAttribute
//	public void commonData(Model model) {
//		Map<String, String> genderMap = new HashMap<>();
//		genderMap.put("M", "Male");
//		genderMap.put("F", "Female");
//		model.addAttribute("genderMap", genderMap);
//	}
	
	//傳回會員圖片
		@GetMapping("/getMemberImg")
		public ResponseEntity<byte[]> getMemberImg(@RequestParam("custId") Integer custId) {
			InputStream is = null;
			OutputStream os = null;
			String fileName = null;
			String mimeType = null;
			byte[] media = null;
			ResponseEntity<byte[]> responseEntity = null;
			HttpHeaders headers = new HttpHeaders();
			MediaType mediaType = null;
			Blob blob = null;
			try {
				CustomerBean bean = customerService.getCustomerById(custId);
				if (bean != null) {
					blob = bean.getCustomerImage();
					if (blob != null) {
						is = blob.getBinaryStream();
					}
					fileName = bean.getFileName();
				}
				// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
				if (is == null) {
					fileName = "member.jpg";
					is = context.getResourceAsStream("/images/" + fileName);
				}

				// 由圖片檔的檔名來得到檔案的MIME型態
				mimeType = context.getMimeType(fileName);
				if (mimeType == null) {
					if (fileName.endsWith("jfif")) {
						mimeType = "image/jfif";
					}
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 由InputStream讀取位元組，然後由OutputStream寫出
				int len = 0;
				byte[] bytes = new byte[8192];

				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				media = baos.toByteArray();
				mediaType = MediaType.valueOf(mimeType);
				headers.setCacheControl(CacheControl.noCache().getHeaderValue());
				headers.setContentType(mediaType);
				responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("/getMemberImg#doGet()發生Exception: " + ex.getMessage());
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
					;
				}
				try {
					if (os != null)
						os.close();
				} catch (IOException e) {
					;
				}
			}
			return responseEntity;
		}
	

}

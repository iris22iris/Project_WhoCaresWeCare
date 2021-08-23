package com.web.store.controller;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class userAccountController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ServletContext context;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	
//	@GetMapping("/_05_login")
//	public String login(Model model) {
//	return "_05_login";
//	}
	@PostMapping("/_05_login")
	public String login(Model model) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");

		Map<String, String> errorMsgMap = new HashMap<String, String>();

		if (account == null || account.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}

//		// **********Remember Me****************************
//		Cookie cookieUser = null;
//		Cookie cookiePassword = null;
//		Cookie cookieRememberMe = null;
//		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
//		if (rememberMe != null) { 
//			cookieUser = new Cookie("user", account);
//			cookieUser.setMaxAge(7 * 24 * 60 * 60);     // Cookie的存活期: 七天
//			cookieUser.setPath(request.getContextPath());
//			
//			
//			cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
//			cookiePassword.setPath(request.getContextPath());
//			
//			cookieRememberMe = new Cookie("rm", "true");
//			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
//			cookieRememberMe.setPath(request.getContextPath());
//		} else {   // 使用者沒有對 RememberMe 打勾
//			cookieUser = new Cookie("user", account);
//			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
//			cookieUser.setPath(request.getContextPath());
//			
//			
//			cookiePassword = new Cookie("password", password);
//			cookiePassword.setMaxAge(0);//代表瀏覽器關掉及刪掉
//			cookiePassword.setPath(request.getContextPath());//告訴主機要刪的Path
//			
//			cookieRememberMe = new Cookie("rm", "false");
//			cookieRememberMe.setMaxAge(7 * 24  * 60 * 60);
//			cookieRememberMe.setPath(request.getContextPath());
//		}
//		response.addCookie(cookieUser);
//		response.addCookie(cookiePassword);
//		response.addCookie(cookieRememberMe);
//		// ********************************************
//		
//		userAccountBean ua = null;
//		companyBean cb = null;
//		List<classroomBean> clsb = null;
//		List<DemoboardBean> Demoboard = null;
//		List<DemoTeacher> DemoTeacher = null;
//		
//		try {							
//			ua = useraccountservice.checkIDPassword(account, password);
//			if(ua!=null) {
//				model.addAttribute("LoginOK", ua);
//				}else {
//					errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
//				}
//			
//			Integer UA_pl = ua.getUA_PL();
//			Integer UA_Id = ua.getUA_Id();
//			Integer UA_MGR = ua.getUA_MGR();
//			Integer UA_PC = ua.getUA_PC();
//			String UA_VC = ua.getUA_VC();
//			if(UA_pl.equals(0)) {
//				cb = companyservice.loadcompany_boss(account);
//				Demoboard = bulletinboardservice.loadbulletinboard_boss(UA_Id);
//				DemoTeacher = teacherpresentationservice.loadteacher_boss(account);
//				}else if(UA_pl.equals(1)) {
//					cb = companyservice.loadcompany_teacher(account);
//					Demoboard = bulletinboardservice.loadbulletinboard_teacher(UA_MGR);
//					DemoTeacher = teacherpresentationservice.loadteacher_teacher(account);
//					clsb = classroomservice.loadclassroom_teacher(UA_VC);
//				}else if(UA_pl.equals(2)) {
//					cb = companyservice.loadcompany_parent(account);
//					Demoboard = bulletinboardservice.loadbulletinboard_parent(UA_PC);
//					DemoTeacher = teacherpresentationservice.loadteacher_parent(account);
//				}
//			
//			if(cb!=null) {
//				model.addAttribute("LoginCom", cb);
//				}else {
//					System.out.println("沒抓到companyBean的資料");
//				}
//			if(Demoboard!=null) {
//				model.addAttribute("Demoboard", Demoboard);
//				}else {
//					System.out.println("沒抓到boardDemolist的資料");
//				}
//			if(DemoTeacher!=null) {
//				model.addAttribute("DemoTeacher",DemoTeacher);
//				}else {
//					System.out.println("沒抓到DemoTeacher的資料");
//				}
//			if(clsb!=null) {
//				model.addAttribute("loadclassroom", clsb);
//				}else {
//					System.out.println("沒抓到loadclassroom的資料");
//				}
//		}catch(RuntimeException e) {
//			errorMsgMap.put("LoginErrorMsg", e.getMessage());
//		}
//		
//		if(!errorMsgMap.isEmpty()) {		
//			model.addAttribute("ErrorMsgKey",errorMsgMap);
//			return "login";
//		}
		return "_05_login";

	}

//	@RequestMapping(value="/register_boss",method=RequestMethod.POST)
//	public String register_boss(
//			@RequestPart("UA_Avater") MultipartFile UA_Avater
//			,@RequestPart("C_CL") MultipartFile C_CL
//			,@RequestParam("UA_Acu") String UA_Acu
//			,@RequestParam("UA_Psw") String UA_Psw
//			,@RequestParam("UA_Psw2") String UA_Psw2
//			,@RequestParam("UA_Name") String UA_Name
//			,@RequestParam("UA_Phone") String UA_Phone
//			,@RequestParam("UA_PL") String UA_PL
//			,@RequestParam("C_CN") String C_CN
//			,@RequestParam("C_CP") String C_CP
//			,Model model) {
//		
//		// 準備存放錯誤訊息的Map物件
//		Map<String, String> errorMsg = new HashMap<String, String>();
//		// 準備存放註冊成功之訊息的Map物件
//		Map<String, String> msgOK = new HashMap<String, String>();
//		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
//		// session物件來存放共用資料。
//		HttpSession session = request.getSession();
//		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
//		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息
//
//				
//		String UA_VC = null;
//		String fileName = null;		
//		Integer UA_MGR = 0;
//		long sizeInBytes = 0;
//		InputStream is = null;
//										
//			
//			// 3. 檢查使用者輸入資料
//			if (UA_Acu == null || UA_Acu.trim().length() == 0) {
//				errorMsg.put("errorIDEmpty", "帳號欄必須輸入");
//			}
//			if (UA_Psw == null || UA_Psw.trim().length() == 0) {
//				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
//			}
//			if (UA_Psw2 == null || UA_Psw2.trim().length() == 0) {
//				errorMsg.put("errorPassword2Empty", "密碼確認欄必須輸入");
//			}
//			if (UA_Psw.trim().length() > 0 && UA_Psw2.trim().length() > 0) {
//				if (!UA_Psw.trim().equals(UA_Psw2.trim())) {
//					errorMsg.put("errorPassword2Empty", "密碼欄必須與確認欄一致");
//					errorMsg.put("errorPasswordEmpty", "*");
//				}
//			}
//
//			if (UA_Name == null || UA_Name.trim().length() == 0) {
//				errorMsg.put("errorName", "姓名欄必須輸入");
//			}
//			if (UA_Phone == null || UA_Phone.trim().length() == 0) {
//				errorMsg.put("errorTel", "電話號碼欄必須輸入");
//			}
//
//		
//		// 如果都沒有錯誤才去最後檢查密碼欄的格式
//		if (errorMsg.isEmpty()) {
//			pattern = Pattern.compile(PASSWORD_PATTERN);
//			matcher = pattern.matcher(UA_Psw);
//			if (!matcher.matches()) {
//				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
//			}
//		}
//		// 如果有錯誤
//		if (!errorMsg.isEmpty()) {
//			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//			
//			
//		}
//		try {
//			byte[] b  = UA_Avater.getBytes();
//			byte[] c  = C_CL.getBytes();
//			Blob UA_Avaterimage = new SerialBlob(b);
//			Blob C_CLimage = new SerialBlob(c);
//
//			if (useraccountservice.idExists(UA_Acu)) {
//				errorMsg.put("errorIDDup", "此帳號已存在，請換新帳號");
//			} else {
//				if(UA_PL.equals("0")) {   //boss
//					UA_VC = useraccountservice.VerificationCode(UA_PL);
//					Integer UA_pl = 0;
//					UA_pl = UA_pl.parseInt(UA_PL);
//					
//					userAccountBean mem = new userAccountBean(UA_pl, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC, UA_Avaterimage);
//					companyBean com = new companyBean(C_CLimage,C_CN,C_CP,mem);
//					int n = useraccountservice.saveMember(mem);
//					int cc = companyservice.savecompany(com);
//					if (n == 1 && cc ==1 ) {
//						msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
//						
//					} else {
//						errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
//					}
//					
//				}else if(UA_PL.equals("1")){  //teacher
//					userAccountBean bean = useraccountservice.checkMGR(UA_VC);
//					if(bean != null) {
//						UA_MGR = bean.getUA_Id();
//					}else {
//						UA_MGR = null;
//					}
//					if(UA_MGR!=null) {
//						UA_VC = useraccountservice.VerificationCode(UA_PL);
//						Integer UA_pl = 0;
//						UA_pl = UA_pl.parseInt(UA_PL);
//						
//						userAccountBean mem = new userAccountBean(UA_pl, UA_Acu, UA_Psw, UA_Name, UA_Phone, UA_VC,UA_MGR, UA_Avaterimage);
//						int n = useraccountservice.saveMember(mem);
//						if (n == 1) {
//							msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
//							
//						} else {
//							errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
//						}
//					} else {
//						errorMsg.put("errorBossVC", "查無此驗證碼，請重新輸入");
//					}
//				}
//
//			}
//			// 5.依照 Business Logic 運算結果來挑選適當的畫面
//			if (!errorMsg.isEmpty()) {
//				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				if(UA_PL.equals("0")) {
//									
//				} else if(UA_PL.equals("1")) {
//								
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			errorMsg.put("errorIDDup", e.getMessage());
//			System.out.println(e.getMessage());
//			
//		}
//		return "index";	
//	}
//	
//	
//	
//	
//	
//	
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//		
//		
//	@RequestMapping(value="/relogin")
//	public String relogin() {
//		return "login";
//	}	
//		
//		
//	@RequestMapping(value="/logout")
//	public String loginout() {
//		return "logout";
//	}		
//		
//	
//	@RequestMapping(value="/register")
//	public String register() {
//		return "register/register";	}
//	
//	@RequestMapping(value="/register_teacher")
//	public String register_teacher() {
//		return "register/register_teacher";
//	}
//	@RequestMapping(value="/register_boss")
//	public String register_boss() {
//		return "register/register_boss";
//	}

}

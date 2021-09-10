package com.web.store.controller;

import java.sql.Blob;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.service.ContactUsService;

@Controller
public class ContactUsController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ServletContext context;
	@Autowired
	ContactUsService contactUsService;
	
	

	
	// 進入聯絡我們頁/WhoCares/src/main/webapp/WEB-INF/views/_02_contactUs.jsp
//		@GetMapping(value ="/_02_contactUs")
//		public String toContactUs(Model model) {
//			return "_02_contactUs";
//		}
	
		//
	
	
	
		@PostMapping(value ="/_02_contactUs")
		public
		String insertContactUs(@ModelAttribute("problem") /* @Valid */ @RequestBody  ProblemBean pb, BindingResult result,
				Model model,HttpServletRequest request) {
			
			
			Integer usId = pb.getusId();
			String ordId = pb.getOrdId();
			String phone = pb.getPhone();
			String email = pb.getEmail();
			String content = pb.getContent();
			String fileName = pb.getFileName();
			
			System.out.println("555555 :" + email);
			System.out.println("電話 :" + phone);
			System.out.println("會員編號 :" + usId);
			System.out.println("留言 :" + content);
			System.out.println("訂單編號 :" + ordId);
			System.out.println("圖片名稱 :" + fileName);
			
////			MultipartFile picture = pb.getImageUs();
////			String originalFilename = picture.getOriginalFilename();
////			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1 ) {
////				pb.setFileName(originalFilename);
////			}
//////			 
////			if (picture != null && !picture.isEmpty()) {
////				try {
////					byte[] b = picture.getBytes();
////					Blob blob = new SerialBlob(b);
////					pb.setAttachFile(blob);
////				}catch (Exception e) {
////					e.printStackTrace();
////					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
////			}
//				}
//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			String content = request.getParameter("content");
			
			
//			try {
//				ProblemBean pb;
//				contactUsService.save(pb);
//			}catch (Exception ex) {
//				return "_02_contactUs";
//			}
			
		
			
			try {
				contactUsService.save(pb);
			}catch(Exception ex) {
				System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
				
				return "_02_contactUs";
			}
			
			
			
			

			
			
			return "_02_contactUs";

		}
		
		
		
		
		
		}


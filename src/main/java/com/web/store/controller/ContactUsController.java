package com.web.store.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;
import com.web.store.service.ContactUsOrdService;
import com.web.store.service.ContactUsService;
import com.web.store.service.CustomerService;

@Controller
public class ContactUsController {
	CustomerService customerService;
	@Autowired
	ContactUsOrdService contactUsOrdService;
	HttpSession httpSession;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ServletContext context;
	@Autowired
	ContactUsService contactUsService;

	@Autowired
	public ContactUsController(CustomerService customerService, ContactUsService contactUsService,
			ContactUsOrdService contactUsOrdService,
			HttpSession httpSession) {
		this.customerService = customerService;
		this.contactUsService = contactUsService;
		this.httpSession = httpSession;
		this.contactUsOrdService = contactUsOrdService;

	}

//	@Autowired
//	ProblemSelectService problemSelectService;

	// 進入聯絡我們頁/WhoCares/src/main/webapp/WEB-INF/views/_02_contactUs.jsp
//		@GetMapping(value ="/_02_contactUs")
//		public String toContactUs(Model model) {
//			return "_02_contactUs";
//		}

	//

	@GetMapping(value = "/_02_contactUs/{custId}", produces = { "text/html" })
	public String editUsFindView(@PathVariable Integer custId,Integer ordId, Model model) {
		HttpSession session = request.getSession();
		if(session.getAttribute("LoginOK") == null) {
			return "index";
		} 
		CustomerBean customer = customerService.getCustomerById(custId);
		model.addAttribute("customer", customer);
		model.addAttribute("id", custId);
		List<OrdBean> list = new  ArrayList<OrdBean>(); 
		list = contactUsOrdService.findOrdBeanById(00000001);
		model.addAttribute("judyList", list);
		return "_02_contactUs";
	}

	@PostMapping(value = "/_02_contactUs")
	public String insertContactUs(@ModelAttribute("problem") /* @Valid */ @RequestBody ProblemBean pb,
			BindingResult result, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("LoginOK") == null) {
			return "index";
		}
		Integer usId = pb.getusId();
		String ordId = pb.getOrdId();
		String phone = pb.getPhone();
		String email = pb.getEmail();
		String content = pb.getContent();
		String fileName = pb.getFileName();

		MultipartFile picture = pb.getImageUs();
		String originalFilename = picture.getOriginalFilename();
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			pb.setFileName(originalFilename);
		}

		if (picture != null && !picture.isEmpty()) {
			try {
				byte[] b = picture.getBytes();
				Blob blob = new SerialBlob(b);
				pb.setAttachFile(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}

		try {
			contactUsService.save(pb);
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
		}
		return "_02_contactUs";
	}

	@GetMapping("/_06_problemReply/{custId}")
	public String problemReplyQueryViews(@PathVariable Integer custId, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		CustomerBean customerBean = customerService.getCustomerById(custId);
		model.addAttribute("customerBean", customerBean);
		return "_06_problemReply";
	}

	@PostMapping(value = "/_06_problemReply/{custId}")
	public @ResponseBody ProblemBean insertproblemReplyQuery(@PathVariable Integer custId, Model model,
			@RequestParam(value = "usId", required = false) Integer usId) {

		ProblemBean problemBean = contactUsService.getProblemById(usId);

		model.addAttribute("problemBean", problemBean);

		return problemBean;
	}

}

//			ProblemSelectBean problemBean = problemSelectService.getAllProblemType(pb.getProblemType().getId());
//			pb.setProblemType(problemBean);

//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			String content = request.getParameter("content");

//			try {
//				ProblemBean pb;
//				contactUsService.save(pb);
//			}catch (Exception ex) {
//				return "_02_contactUs";
//			}
//			@GetMapping("querySelect/{problemType}")
//			public @ResponseBody List<ProblemSelectBean> querySelect(Model model,@PathVariable String problemType) {
//				List<ProblemSelectBean> queyrselect = problemSelectService.getAllProblemTypes(problemType);
//				return queyrselect;

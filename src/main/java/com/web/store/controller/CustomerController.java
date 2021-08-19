package com.web.store.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CustomerService;


@Controller
@RequestMapping("/_01_customer")
public class CustomerController {

	@Autowired
	ServletContext context;

	@Autowired
	CustomerService service;

	@GetMapping("/customers")
	public String getCustomers(Model model) {
		List<CustomerBean> beans = service.getCustomers();
		model.addAttribute(beans);      
		// 若屬性物件為CustomerBean型別的物件，則預設的識別字串 ==> customerBean
		// 若屬性物件為List<CustomerBean>型別的物件，則預設的識別字串 ==> customerBeanList
		return "_01_customer/ShowCustomers";
	}
	
	
	@GetMapping("/modifyCustomer/{id}")
	public String editCustomerForm(Model model, @PathVariable Integer id) {
		CustomerBean bean = service.getCustomerById(id);
		bean.setPassword((bean.getPassword()));
		model.addAttribute("customerBean", bean);
		
		
		
		
		return "_01_customer/EditCustomerForm";
	}

	

	
	@DeleteMapping(value="/modifyCustomer/{id}")
	public String deleteCustomerData(@PathVariable Integer id) {
		System.out.println(11122233);
		service.deleteCustomerByPrimaryKey(id);	
		return "redirect:../customers";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true); 
		binder.registerCustomEditor(Date.class, ce);
		// java.sql.Date		
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true); 
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}
	@RequestMapping("/index")
	public String home() {
		return "_01_customer/index";
	}
}

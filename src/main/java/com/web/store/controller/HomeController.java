package com.web.store.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.store.model._05_customer.CitySelectBean;
import com.web.store.service.CityService;
import com.web.store.service.CustomerService;


@Controller
public class HomeController {
	
	@Autowired
	CityService cityService;

	@GetMapping({ "/", "/index", "/index.html" })
	public String home(Model model, Map<String, Object> map) {
		return "index"; // \WEB-INF\views\index.jsp
	}

	@GetMapping("querySelect/{groupCity}")
	public @ResponseBody List<CitySelectBean> querySelect(Model model, @PathVariable String groupCity) {
		List<CitySelectBean> queyrselect = cityService.getAllCitys(groupCity);

//		model.addAttribute("citySelect",queyrselect);
		return queyrselect;
	}

}

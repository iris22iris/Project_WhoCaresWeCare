package com.web.store.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/index", "/index.html"}) 
	public String home(Model model, Map<String,Object>map) {
		return "index"; // \WEB-INF\views\index.jsp
	}
	
}

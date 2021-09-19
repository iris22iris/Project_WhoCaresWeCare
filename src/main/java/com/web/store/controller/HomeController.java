package com.web.store.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._05_customer.CitySelectBean;
import com.web.store.service.CityService;
import com.web.store.service.ProblemSelectService;
import com.web.store.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	CityService cityService;
	@Autowired
	ProductService productService;
	@Autowired
	ProblemSelectService problemSelectService;

	@GetMapping({ "/", "/index", "/index.html" })
	public String home(Model model, Map<String, Object> map) {
		return "index"; // \WEB-INF\views\index.jsp
	}

	// 縣市下拉式選單
	@GetMapping("querySelect/{groupCity}")
	public @ResponseBody List<CitySelectBean> querySelect(Model model, @PathVariable String groupCity) {
		List<CitySelectBean> queyrselect = cityService.getAllCitys(groupCity);
		return queyrselect;
	}

	@GetMapping("usquerySelect/{qroupPb}")
	public @ResponseBody List<ProblemSelectBean> usquerySelect(Model model, @PathVariable String qroupPb) {
		List<ProblemSelectBean> usqueyrselect = problemSelectService.getAllProblemTypes(qroupPb);
		return usqueyrselect;
	}

	@GetMapping("/searchProduct")
	public @ResponseBody List<String> searchProduct(Model model) {
		List<ProductBean> allProducts = productService.getAllProducts();
		List<String> prodNameList = new ArrayList<>();
		for (ProductBean product : allProducts) {
			prodNameList.add(product.getProdName());
		}
		HttpSession session = request.getSession();
		session.setAttribute("searchProduct", prodNameList);
		return prodNameList;
	}

	@GetMapping("/_01_searchResult/{searchProduct}")
	public String searchResult(Model model, @PathVariable String searchProduct) {
		List<ProductBean> allProducts = productService.getAllProducts();
		List<ProductBean> productBeanList = new ArrayList<>();
		for (int i = 0; i < searchProduct.length(); i++) {
			String name = searchProduct.substring(0, searchProduct.length() - i);
			Iterator<ProductBean> iterator = allProducts.iterator();
			while(iterator.hasNext()){
				ProductBean productBean = iterator.next();
				if(productBean.getProdName().indexOf(name) != -1) {
					productBeanList.add(productBean);
					iterator.remove();
				}
			}
		}
		model.addAttribute(searchProduct);
		model.addAttribute("productList", productBeanList);
		for(ProductBean test:productBeanList) {
			System.out.println(test.getProdName());
		}
		return "_01_searchResult";
	}

}

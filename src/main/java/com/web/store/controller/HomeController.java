package com.web.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.store.model._02_customerService.ProblemSelectBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._05_customer.CitySelectBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.service.CityService;
import com.web.store.service.CustomerService;
import com.web.store.service.OrderService;
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
	@Autowired
	OrderService orderService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	CustomerService customerService;

	@GetMapping({ "/", "/index", "/index.html" })
	public String home(Model model, Map<String, Object> map) {
		List<ProductBean> indexProducts = productService.getAllProducts();
		List<ProductBean> lowProductsList = new ArrayList<>();;
		for (ProductBean lowStock : indexProducts) {
			if (lowStock.getStock() < 10) {
				lowProductsList.add(lowStock);
			}
		}
		model.addAttribute("lowProductsList", lowProductsList);
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
			while (iterator.hasNext()) {
				ProductBean productBean = iterator.next();
				if (productBean.getProdName().indexOf(name) != -1) {
					productBeanList.add(productBean);
					iterator.remove();
				}
			}
		}
		Gson gson = new Gson();
		String[] arr = new String[productBeanList.size()];
		for (int i = 0; i < productBeanList.size(); i++) {
			arr[i] = String.valueOf(productBeanList.get(i).getProdId());
		}
		model.addAttribute(searchProduct);
		model.addAttribute("cartButton", gson.toJson(arr));
		model.addAttribute("productList", productBeanList);
//		for(ProductBean test:productBeanList) {
//			System.out.println(test.getProdName());
//		}
		return "_01_searchResult";
	}

	@PostMapping("/_01_searchResult/cart")
	@ResponseBody
	public void quereyFavorite(@RequestParam("prodId") Integer prodId,
			@RequestParam(name = "prodQTY", required = false) Integer prodQTY, Model model) {
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}

		int prodcuctId = Integer.parseInt(prodId.toString().trim());

		ProductBean productBean = new ProductBean();
		productBean = productService.getProductById(prodcuctId);
		Double itemSum = prodQTY * productBean.getPrice();

		// 將資料封裝到buyItemBean
		BuyItemBean buyItemBean = new BuyItemBean(prodQTY, itemSum, productBean.getPromotionBean(), productBean);
		shoppingCart.addProductToCart(prodId, buyItemBean);
	}
	
	@PostMapping("/adminBoss01Mark1")
	@ResponseBody
	public List<CustomerBean> adminBoss01(Model model) {
		List<CustomerBean> allCustomer = customerService.getCustomers();
		
		
		return allCustomer;
		
		
	}
	
	@PostMapping("/adminBoss01Mark2")
	@ResponseBody
	public void adminBoss02(Model model) {
		
		
		
	}
	
	@PostMapping("/adminBoss01Mark3")
	@ResponseBody
	public void adminBoss03(Model model) {
		
		
		
	}

}

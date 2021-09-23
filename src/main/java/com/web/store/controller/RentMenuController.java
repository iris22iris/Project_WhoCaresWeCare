package com.web.store.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductTypeService;
import com.web.store.service.PromotionService;
import com.web.store.service.RentProductService;

@Controller
public class RentMenuController {

	RentProductService rentProductService;
	ProductTypeService productTypeService;
	PromotionService promotionService;
	HttpSession httpSession;
	
	@Autowired
	public RentMenuController(RentProductService rentProductService, ProductTypeService productTypeService,
			PromotionService promotionService, HttpSession httpSession) {
		this.rentProductService = rentProductService;
		this.productTypeService = productTypeService;
		this.promotionService = promotionService;
		this.httpSession = httpSession;
	}

//	原租賃目錄(同商品獨立顯示)
	@GetMapping("/rentMenuO")
	public String rentProductMenu(Model model) {
		List<RentProductBean> rentProducts = rentProductService.getAllProducts();
		List<ProductTypeBean> productTypes = productTypeService.getAllProdTypes();
		model.addAttribute("rentProducts", rentProducts);
		model.addAttribute("productTypes", productTypes);

		return "_03_rentProductMenu";
	}

//	進入租賃目錄頁(含分頁及排序)
	@GetMapping({ "/rentMenu", "/rentMenu/{prodType}", "/rentMenu/promote{promoteId}" })
	public String rentProductMenu(
			@PathVariable(name = "prodType", required = false) String prodType,
			@PathVariable(name = "promoteId", required = false) Integer promoteId,
			@RequestParam(name = "sortType", required = false) String sortType,
			@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo, Model model) {
		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}
		
		List<RentProductBean> rentProducts = rentProductService
				.getGroupedProducts(productTypeService.findProductTypeBeanById(prodType), pageNo, sortType);
		List<Long> productStocks = rentProductService
				.getGroupedStockSum(productTypeService.findProductTypeBeanById(prodType), pageNo, sortType);
		int totalPages = rentProductService.getGroupedPages(productTypeService.findProductTypeBeanById(prodType));

		Map<RentProductBean, Long> rentProductsMap = new LinkedHashMap<>();
		for (int i = 0; i < rentProducts.size(); i++) {
			rentProductsMap.put(rentProducts.get(i), productStocks.get(i));
		}

		List<ProductTypeBean> productTypes = productTypeService.getAllProdTypes();
		List<PromotionBean> promotions = promotionService.getAllPromotions();

		model.addAttribute("rentProductsMap", rentProductsMap);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("promotions", promotions);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPages", totalPages);
		if (sortType != null) {
			model.addAttribute("sortType", sortType);
		} else {
			model.addAttribute("sortType", null);
		}

		return "_03_rentProductMenu";
	}

}
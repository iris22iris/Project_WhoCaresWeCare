package com.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.FavoriteBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._04_shop.ShoppingCart;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.CustomerService;
import com.web.store.service.ProductService;
import com.web.store.service.ProductTypeService;
import com.web.store.service.PromotionService;

@Controller
public class BuyMenuController {

	ProductService productService;
	ProductTypeService productTypeService;
	PromotionService promotionService;
	HttpSession httpSession;
	FavoriteBean favoriteBean;

	@Autowired
	CustomerService customerService;
	@Autowired
	HttpServletRequest request;

	@Autowired
	public FavoriteBean getFavoriteBean() {
		return favoriteBean;
	}

	public void setFavoriteBean(FavoriteBean favoriteBean) {
		this.favoriteBean = favoriteBean;
	}

	@Autowired
	public BuyMenuController(ProductService productService, ProductTypeService productTypeService,
			PromotionService promotionService, HttpSession httpSession) {
		this.productService = productService;
		this.productTypeService = productTypeService;
		this.promotionService = promotionService;
		this.httpSession = httpSession;
	}

//	進入商城目錄頁(含分頁及排序)
	@GetMapping({ "/buyMenu", "/buyMenu/{prodType}", "/buyMenu/promote{promoteId}" })
	public String buyProductMenu(@PathVariable(name = "prodType", required = false) String prodType,
			@PathVariable(name = "promoteId", required = false) Integer promoteId,
			@RequestParam(name = "sortType", required = false) String sortType,
			@RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo, Model model) {

		ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			httpSession.setAttribute("ShoppingCart", shoppingCart);
		}

		List<ProductBean> products = null;
		int totalPages = 0;

		if (prodType != null) {
			if (sortType != null) {
				products = productService.getProductsByProdTypeAndPageSort(
						productTypeService.findProductTypeBeanById(prodType), pageNo, sortType);
				String[] token = sortType.split(" ");
				model.addAttribute("sortType", token[0] + "+" + token[1]);
			} else {
				products = productService
						.getProductsByProdTypeAndPage(productTypeService.findProductTypeBeanById(prodType), pageNo);
				model.addAttribute("sortType", null);
			}
			totalPages = productService.getTotalPagesByProdType(productTypeService.findProductTypeBeanById(prodType));
		} else {
			if (sortType != null) {
				products = productService.getAllProductsByPageSort(pageNo, sortType);
				model.addAttribute("sortType", sortType);
			} else {
				products = productService.getAllProductsByPage(pageNo);
				model.addAttribute("sortType", null);
			}
			totalPages = productService.getTotalPages();
		}

		List<ProductTypeBean> productTypes = productTypeService.getAllProdTypes();
		List<PromotionBean> promotions = promotionService.getAllPromotions();

		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("promotions", promotions);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPages", totalPages);

		return "_04_buyProductMenu";
	}

	// 新增追蹤清單
	@PostMapping(value = "/addFavorite")
	@ResponseBody
	public void addFavorite(@ModelAttribute("favorite") FavoriteBean favoriteBean) {
		List<FavoriteBean> allFavorite = productService.queryFavorite(favoriteBean.getFK_Customer_ID(),
				favoriteBean.getFK_Product_ID());
		if (allFavorite.size() == 0)
			productService.addFavorite(favoriteBean);
	}

	// 刪除追蹤清單
	@PostMapping(value = "/deleteFavorite")
	@ResponseBody
	public void deleteFavorite(@ModelAttribute("favorite") FavoriteBean favoriteBean) {
		productService.deleteFavorite(favoriteBean);
	}

	// 搜尋追蹤清單
	@PostMapping(value = "/quereyFavoriteBYCustomerID")
	@ResponseBody
	public List<FavoriteBean> quereyFavoriteBYCustomerID(@ModelAttribute("favorite") FavoriteBean favoriteBean) {
		return productService.getFavorite(favoriteBean.getFK_Customer_ID());
	}

	// 追蹤清單頁面查詢
	@GetMapping(value = "/_04_favoriteList/{id}", produces = { "text/html" })
	public String checkFavoriteFindView(@PathVariable Integer id, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		CustomerBean customer = customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("id", id);
		return "_04_favoriteList";
	}

	// 追蹤清單
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/quereyFavorite")
	@ResponseBody
	public List<Query> quereyFavorite(Integer FK_Customer_ID, Model model) {
		List<Query> fb = productService.queryFavoriteProduct(FK_Customer_ID);
		return fb;
	}

}
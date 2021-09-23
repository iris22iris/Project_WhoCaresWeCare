package com.web.store.controller;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.ProductService;
import com.web.store.service.ProductTypeService;

@Controller
public class BuyProductPageController {



	ProductService productService;
	ProductTypeService productTypeService;
	ServletContext servletContext;

	@Autowired
	public BuyProductPageController(ProductService productService, ProductTypeService productTypeService,
			ServletContext servletContext) {
		this.productService = productService;
		this.productTypeService = productTypeService;
		this.servletContext = servletContext;
	}

	@RequestMapping("/_04_productPage")
	public String getProductById(@RequestParam("id") Integer id, Model model) {

		List<ProductBean> products = productService.getAllProducts();
		List<ProductTypeBean> productTypes = productTypeService.getAllProdTypes();
		List<CommentBean> comments = productService.getCommentBeanByprodId(id);
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("comments", comments);
		
		String productType = productService.getProductById(id).getProductTypeBean().getProdType();
		String maincategory = productType.substring(0,1);
		List<ProductTypeBean> maincategorys = productService.getProductTypeBeanBymaincategory(maincategory);
		model.addAttribute("maincategorys", maincategorys);
		
		//商品詳細clob轉文字
		ProductBean productBean = productService.getProductById(id);
		if (productBean != null) {
			model.addAttribute(productBean);
			try {
				Clob productDescriptionClob = productBean.getDescription();
				String productDescription = productDescriptionClob.getSubString(1L, (int) productDescriptionClob.length());
				model.addAttribute("productDescription", productDescription);
				} catch (SQLException e) {
				e.printStackTrace();	  
					}
		}
		
		//評論內容clob轉文字
				if (comments != null) {			
					try {
						String productComment = "";
						for (int i = 0; i < comments.size(); i++) {
							Clob productCommentClob = comments.get(i).getComment();
							productComment += (productCommentClob.getSubString(1L, (int) productCommentClob.length())
													+"|");										
						}
						
					    List<String> productComments = java.util.Arrays.asList(productComment.split("\\|")); 			  
						model.addAttribute("productComments", productComments);				
						} catch (SQLException e) {
						e.printStackTrace();	  
							}
				}
		
		return "_04_productPage";
	};

//	@RequestMapping("/_04_productPage")
//	public String buyProduct(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_productPage";
//	}

//	@RequestMapping("/_03_rentProduct")
//		public String list(Model model) {
//		RentProductBean rb = new RentProductBean(
//				) ;
//		return null;
//	}

//	@RequestMapping("/buyMenu")
//	public String buyProductMenu(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_buyProductMenu";
//	}

//	@RequestMapping("/buyMenu/{productType.prodType}")
//	public String getProductsByProdType(@PathVariable("productType.prodType") String prodtype, Model model) {
//		List<ProductBean> products = productService.getProductsByProdType(new ProductTypeBean(prodtype));
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_buyProductMenu";
//	}
//
//	@RequestMapping("/_04_productPage")
//	public String buyProduct(Model model) {
//		List<ProductBean> products = productService.getAllProducts();
//		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
//		model.addAttribute("products", products);
//		model.addAttribute("productTypes", productTypes);
//		return "_04_productPage";
//	}
	
	

}
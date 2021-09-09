package com.web.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.service.CustomerService;
import com.web.store.service.ProductService;

@Controller
public class BuyProductPageController {

//	@Autowired
//	ServletContext context;
//
//	@Autowired
//	CustomerService customerService;

	ProductService productService;
	ServletContext servletContext;

	@Autowired
	public BuyProductPageController(ProductService productService, ServletContext servletContext) {
		this.productService = productService;
		this.servletContext = servletContext;
	}

	@RequestMapping("/_04_productPage")
	public String getProductById(@RequestParam("id") Integer id, Model model) {

		List<ProductBean> products = productService.getAllProducts();
		List<ProductTypeBean> productTypes = productService.getAllProdTypes();
		List<CommentBean> comments = productService.getCommentBeanByprodId(id);
		model.addAttribute("products", products);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("comments", comments);
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
	
	//傳回會員圖片
//	@GetMapping("/getMemberImg")
//	public ResponseEntity<byte[]> getMemberImg(@RequestParam("custId") Integer custId) {
//		InputStream is = null;
//		OutputStream os = null;
//		String fileName = null;
//		String mimeType = null;
//		byte[] media = null;
//		ResponseEntity<byte[]> responseEntity = null;
//		HttpHeaders headers = new HttpHeaders();
//		MediaType mediaType = null;
//		Blob blob = null;
//		try {
//			CustomerBean bean = customerService.getCustomerById(custId);
//			if (bean != null) {
//				blob = bean.getCustomerImage();
//				if (blob != null) {
//					is = blob.getBinaryStream();
//				}
//				fileName = bean.getFileName();
//			}
//			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
//			if (is == null) {
//				fileName = "member.jpg";
//				is = context.getResourceAsStream("/images/" + fileName);
//			}
//
//			// 由圖片檔的檔名來得到檔案的MIME型態
//			mimeType = context.getMimeType(fileName);
//			if (mimeType == null) {
//				if (fileName.endsWith("jfif")) {
//					mimeType = "image/jfif";
//				}
//			}
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			// 由InputStream讀取位元組，然後由OutputStream寫出
//			int len = 0;
//			byte[] bytes = new byte[8192];
//
//			while ((len = is.read(bytes)) != -1) {
//				baos.write(bytes, 0, len);
//			}
//			media = baos.toByteArray();
//			mediaType = MediaType.valueOf(mimeType);
//			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//			headers.setContentType(mediaType);
//			responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("/getMemberImg#doGet()發生Exception: " + ex.getMessage());
//		} finally {
//			try {
//				if (is != null)
//					is.close();
//			} catch (IOException e) {
//				;
//			}
//			try {
//				if (os != null)
//					os.close();
//			} catch (IOException e) {
//				;
//			}
//		}
//		return responseEntity;
//	}

}
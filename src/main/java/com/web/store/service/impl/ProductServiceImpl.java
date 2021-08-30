package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.ProductDao;
import com.web.store.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<ProductBean> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean) {
		return productDao.getProductsByProdType(prodTypeBean);
	}

	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		return productDao.getAllProdTypes();
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
	}
	
	@Transactional
	@Override
	public ProductBean getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public void addProduct(ProductBean product) {
	}

}
package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.ProductTypeDao;
import com.web.store.service.ProductTypeService;

@Transactional
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	ProductTypeDao productTypeDao;

	@Autowired
	public ProductTypeServiceImpl(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}

//	取得所有產品類別
	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		return productTypeDao.getAllProdTypes();
	}
	
//	依主鍵查詢產品類別
	@Override
	public ProductTypeBean findProductTypeBeanById(String prodType) {
		return productTypeDao.findProductTypeBeanById(prodType);
	}
	
}
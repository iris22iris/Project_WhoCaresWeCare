package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.RentProductDao;
import com.web.store.model._03_rent.RentProductBean;
import com.web.store.service.RentProductService;

@Transactional
@Service
public class RentProductServiceImpl implements RentProductService {

	RentProductDao rentProductDao;

	@Autowired
	public RentProductServiceImpl(RentProductDao rentProductDao) {
		this.rentProductDao = rentProductDao;
	}

	@Override
	public List<RentProductBean> getAllProducts() {
		return rentProductDao.getAllProducts();
	}

	@Override
	public List<RentProductBean> getProductsByProdType(String prodtype) {
		return rentProductDao.getProductsByProdType(prodtype);
	}

	@Override
	public List<String> getAllCategories() {
		return rentProductDao.getAllCategories();
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
	}

	@Override
	public RentProductBean getProductById(int productId) {
		return null;
	}

	@Override
	public void addProduct(RentProductBean product) {
	}

}
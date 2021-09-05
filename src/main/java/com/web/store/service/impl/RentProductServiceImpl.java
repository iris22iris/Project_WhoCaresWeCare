package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._07_productType.ProductTypeBean;
import com.web.store.repository.RentProductDao;
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
	public List<RentProductBean> getGroupedProducts(ProductTypeBean prodTypeBean, int pageNo,
																	 String sortType) {
		return rentProductDao.getGroupedProducts(prodTypeBean, pageNo, sortType);
	}
	
	@Override
	public List<Long> getGroupedStockSum(ProductTypeBean prodTypeBean, int pageNo, String sortType) {
		return rentProductDao.getGroupedStockSum(prodTypeBean, pageNo, sortType);
	}

	@Override
	public int getGroupedPages(ProductTypeBean prodTypeBean) {
		return rentProductDao.getGroupedPages(prodTypeBean);
	}

	@Override
	public List<ProductTypeBean> getAllProdTypes() {
		return rentProductDao.getAllProdTypes();
	}

	@Override
	public RentProductBean getProductById(int prodId) {
		return rentProductDao.getProductById(prodId);
	}

	@Override
	public void addProduct(RentProductBean product) {
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
	}

}
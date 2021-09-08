package com.web.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.store.model._04_shop.FavoriteBean;
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
	public List<ProductBean> getAllProductsByPage(int pageNo) {
		return productDao.getAllProductsByPage(pageNo);
	}

	@Override
	public List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType) {
		return productDao.getAllProductsByPageSort(pageNo, sortType);
	}

	@Override
	public int getTotalPages() {
		return productDao.getTotalPages();
	}

//	@Override
//	public List<ProductBean> getProductsByProdType(ProductTypeBean prodTypeBean) {
//		return productDao.getProductsByProdType(prodTypeBean);
//	}

	@Override
	public List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo) {
		return productDao.getProductsByProdTypeAndPage(prodTypeBean, pageNo);
	}

	@Override
	public List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo,
			String sortType) {
		return productDao.getProductsByProdTypeAndPageSort(prodTypeBean, pageNo, sortType);
	}

	@Override
	public int getTotalPagesByProdType(ProductTypeBean prodTypeBean) {
		return productDao.getTotalPagesByProdType(prodTypeBean);
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
	public ProductBean getProductById(int prodId) {
		return productDao.getProductById(prodId);
	}

	@Override
	public void addProduct(ProductBean product) {

	}

	@Override
	public Object addFavorite(FavoriteBean favoriteBean) {
		return productDao.addFavorite(favoriteBean);
	}

	@Override
	public List<FavoriteBean> queryFavorite(int productId, int newQuantity) {
		return productDao.queryFavorite(productId, newQuantity);
	}

	@Override
	public void deleteFavorite(FavoriteBean favoriteBean) {
		productDao.deleteFavorite(favoriteBean);
	}

	@Override
	public List<FavoriteBean> get(Integer FK_Customer_ID) {
		return productDao.get(FK_Customer_ID);
	}
}
package com.web.store.service.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.CommentBean;
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

//	取得所有商品
	@Override
	public List<ProductBean> getAllProducts() {
		return productDao.getAllProducts();
	}

//	透過頁碼取得商品
	@Override
	public List<ProductBean> getAllProductsByPage(int pageNo) {
		return productDao.getAllProductsByPage(pageNo);
	}
	
//	透過頁碼取得商品並排序
	@Override
	public List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType) {
		return productDao.getAllProductsByPageSort(pageNo, sortType);
	}

//	取得所有商品的總頁數
	@Override
	public int getTotalPages() {
		return productDao.getTotalPages();
	}
	
//	透過頁碼以及產品類別取得商品
	@Override
	public List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo) {
		return productDao.getProductsByProdTypeAndPage(prodTypeBean, pageNo);
	}

//	透過頁碼以及產品類別取得商品並排序
	@Override
	public List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo,
			String sortType) {
		return productDao.getProductsByProdTypeAndPageSort(prodTypeBean, pageNo, sortType);
	}

//	透過產品類別取得總頁數
	@Override
	public int getTotalPagesByProdType(ProductTypeBean prodTypeBean) {
		return productDao.getTotalPagesByProdType(prodTypeBean);
	}

//	取得所有產品類別
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

	
	//抓取該商品目前租賃評論資料
	@Override
	public List<CommentBean> getCommentBeanByprodId(int prodId) {
		
		return productDao.getCommentBeanByprodId(prodId);
	}

	@Override
	public void addProduct(ProductBean product) {

	}

	// 新增追蹤項目
	@Override
	public Object addFavorite(FavoriteBean favoriteBean) {
		return productDao.addFavorite(favoriteBean);
	}

	// 查詢追蹤項目
	@Override
	public List<FavoriteBean> queryFavorite(int productId, int newQuantity) {
		return productDao.queryFavorite(productId, newQuantity);
	}

	// 刪除追蹤項目
	@Override
	public void deleteFavorite(FavoriteBean favoriteBean) {
		productDao.deleteFavorite(favoriteBean);
	}

	@Override
	public List<FavoriteBean> getFavorite(Integer FK_Customer_ID) {
		return productDao.getFavorite(FK_Customer_ID);
	}

	@Override
	public List<Query> queryFavoriteProduct(Integer FK_Customer_ID) {
		return productDao.queryFavoriteProduct(FK_Customer_ID);
	}
	
}
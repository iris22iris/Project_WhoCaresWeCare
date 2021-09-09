package com.web.store.repository;

import java.util.List;

import org.hibernate.query.Query;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._04_shop.FavoriteBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._07_productType.ProductTypeBean;

public interface ProductDao {

	// 讀取多筆產品資料
	List<ProductBean> getAllProducts();

	// 依分頁讀取多筆產品資料
	List<ProductBean> getAllProductsByPage(int pageNo);

	// 依分頁讀取多筆產品資料並排序
	List<ProductBean> getAllProductsByPageSort(int pageNo, String sortType);
	
	// 計算販售的商品總共有幾頁
	int getTotalPages();
	
	// 依ProdType表格主鍵 載入分類的產品並分頁
	List<ProductBean> getProductsByProdTypeAndPage(ProductTypeBean prodTypeBean, int pageNo);

	// 依ProdType表格主鍵 載入分類的產品並分頁、排序
	List<ProductBean> getProductsByProdTypeAndPageSort(ProductTypeBean prodTypeBean, int pageNo, String sortType);
	
	// 計算分類的商品總共有幾頁
	int getTotalPagesByProdType(ProductTypeBean prodTypeBean);
	
	// 讀取全部產品分類
	List<ProductTypeBean> getAllProdTypes();

	// 更新庫存
	void updateStock(int productId, int newQuantity);

	// 依主鍵讀取單筆產品資料
	public ProductBean getProductById(int prodId);
	
	//抓取該商品目前租賃評論資料
		public List<CommentBean> getCommentBeanByprodId(int prodId);


	// 新增單筆產品資料
	void addProduct(ProductBean product);
	
	// 新增我的最愛
	Object addFavorite(FavoriteBean favoriteBean);
	
	// 讀取多筆我的最愛
	List<FavoriteBean> queryFavorite(int productId, int newQuantity);

	//刪除我的最愛
	void deleteFavorite(FavoriteBean favoriteBean);

	List<FavoriteBean> getFavorite(Integer FK_Customer_ID);

	//依追蹤清單的產品ID 搜尋產品細項
	@SuppressWarnings("rawtypes")
	List<Query> queryFavoriteProduct(Integer FK_Customer_ID);

}
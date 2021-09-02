package com.web.store.model._04_shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._07_productType.ProductTypeBean;

@Entity
@Table(name = "Product")
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodId;// 商品編號
	private String classify;// 租買分類
	private String prodName;// 商品名稱
	private BigDecimal price;// 商品售價
	private Blob coverImage;// 圖片
	private String mimeType;// 圖片類型
	private Integer stock;// 庫存數量
//	private String prodType;// 商品分類代碼 --ProductTypeBean取代此建構子--
	private String fileName;// 圖片名稱
	private Integer promoteId;// 活動編號

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PROMOTEID_FK")
	PromotionBean promotionBean;

	@OneToMany(mappedBy = "productBean", cascade = CascadeType.ALL)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();

	public ProductBean() {
	}

	public ProductBean(String classify, Integer prodId, String prodName, BigDecimal price, Blob coverImage,
			String mimeType, Integer stock, String fileName, Integer promoteId,
			Set<BuyItemBean> buyItems) {
		this.classify = classify;
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
		this.coverImage = coverImage;
		this.mimeType = mimeType;
		this.stock = stock;
//		this.prodType = prodType; --ProductTypeBean取代此建構子--
		this.fileName = fileName;
		this.promoteId = promoteId;
		this.buyItems = buyItems;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

//	public String getProdType() {           --ProductTypeBean取代此建構子--
//		return prodType;
//	}
//
//	public void setProdType(String prodType) {
//		this.prodType = prodType;
//	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

//	雙向多對一productTypeBean之getter、setter 開始
	
	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}
	
	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
	
//	雙向多對一productTypeBean之getter、setter 結束
	
	public PromotionBean getPromotionBean() {
		return promotionBean;
	}

	public void setPromotionBean(PromotionBean promotionBean) {
		this.promotionBean = promotionBean;
	}

	public Set<BuyItemBean> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(Set<BuyItemBean> buyItems) {
		this.buyItems = buyItems;
	}

}
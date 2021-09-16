package com.web.store.model._04_shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._05_customer.CustomerBean;
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
	private Blob coverImage1;// 圖片1
	private Blob coverImage2;// 圖片2
	private Blob coverImage3;// 圖片3
	private String mimeType;// 圖片類型
	private String mimeType2;// 圖片類型
	private String mimeType3;// 圖片類型
	private Integer stock;// 庫存數量
//	private String prodType;// 商品分類代碼 --ProductTypeBean取代此建構子--
	private String fileName;// 圖片名稱
	private String fileName2;// 圖片名稱
	private String fileName3;// 圖片名稱
	private Clob description;// 商品敘述
//	private Integer promoteId;// 活動編號

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PROMOTEID_FK")
	PromotionBean promotionBean;

	@OneToMany(mappedBy = "productBean", cascade = CascadeType.ALL)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "productBean", cascade = CascadeType.ALL)
	Set<CommentBean> comments = new LinkedHashSet<>();

	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "favorite",  
//        joinColumns = {   // 在Join Table中，儲存本類別之主鍵值的外鍵欄位名稱
//            @JoinColumn(name = "FK_Product_ID", referencedColumnName = "prodId") 
//        }, 
//        inverseJoinColumns = { // 在Join Table中，儲存對應對照類別之主鍵值的外鍵欄位名稱
//            @JoinColumn(name = "FK_Customer_ID",    referencedColumnName = "custId") 
//        }
//    )

//	private Set<CustomerBean> custmers = new HashSet<CustomerBean>(0);
	
	public ProductBean() {
	}

	public ProductBean(Integer prodId) {
		this.prodId = prodId;
	}

	public ProductBean(String classify, Integer prodId, String prodName, BigDecimal price, Blob coverImage1,
			 Blob coverImage2,  Blob coverImage3, String mimeType, Integer stock, String fileName,
			 Clob description, Set<BuyItemBean> buyItems,Set<CommentBean> comments) {
		this.classify = classify;
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
		this.coverImage1 = coverImage1;
		this.coverImage2 = coverImage2;
		this.coverImage3 = coverImage3;
		this.mimeType = mimeType;
		this.stock = stock;
//		this.prodType = prodType; --ProductTypeBean取代此建構子--
		this.fileName = fileName;
		this.description = description;
		this.buyItems = buyItems;
		this.comments = comments;
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

	public Blob getCoverImage1() {
		return coverImage1;
	}

	public void setCoverImage1(Blob coverImage1) {
		this.coverImage1 = coverImage1;
	}
	
	public Blob getCoverImage2() {
		return coverImage2;
	}
	
	public void setCoverImage2(Blob coverImage2) {
		this.coverImage2 = coverImage2;
	}
	
	public Blob getCoverImage3() {
		return coverImage3;
	}
	
	public void setCoverImage3(Blob coverImage3) {
		this.coverImage3 = coverImage3;
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
	
	public Clob getDescription() {
		return description;
	}

	public void setDescription(Clob description) {
		this.description = description;
	}
	
//	public Integer getPromoteId() {
//		return promoteId;
//	}
//
//	public void setPromoteId(Integer promoteId) {
//		this.promoteId = promoteId;
//	}

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
	
	
	
	public Set<CommentBean> getComments() {
		return comments;
	}

	public void setComments(Set<CommentBean> comments) {
		this.comments = comments;
	}

	public String getMimeType2() {
		return mimeType2;
	}

	public void setMimeType2(String mimeType2) {
		this.mimeType2 = mimeType2;
	}

	public String getMimeType3() {
		return mimeType3;
	}

	public void setMimeType3(String mimeType3) {
		this.mimeType3 = mimeType3;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

//	public Set<CustomerBean> getCustmers() {
//		return custmers;
//	}
//
//	public void setCustmers(Set<CustomerBean> custmers) {
//		this.custmers = custmers;
//	}
	
	

}
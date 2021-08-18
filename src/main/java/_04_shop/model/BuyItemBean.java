package _04_shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _02_customerService.model.PromotionBean;
import _06_order.model.OrdBean;
import _07_productType.model.ProductTypeBean;




@Entity
@Table(name="BuyItem")
public class BuyItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			prodSerialNum;	//商品項次
	private String  		category;       //訂單類別
//	private String      	prodType;		//商品分類代碼  --ProductTypeBean取代此建構子--
	private Integer     	prodQTY;		//商品數量
	private BigDecimal  	itemSum;		//單項總額
	private BigDecimal  	discount;		//折扣金額
	private BigDecimal		ordTotal;		//訂單總金額
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "BUYTITEM_ORDCID_FK"),
		@JoinColumn(name = "BUYTITEM_ORDID_FK"),
		})
	private OrdBean ordBean;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="BUYTITEM_PROMOTEID_FK", referencedColumnName="promoteId"),
			@JoinColumn(name="BUYTITEM_DISCOUNTCODE_FK", referencedColumnName="discountCode")
	 			})
	private PromotionBean promotionBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PRODID_FK")
	private ProductBean productBean;
	
	public BuyItemBean(Integer prodSerialNum, String category, String prodType, Integer prodQTY, BigDecimal itemSum,
			BigDecimal discount, BigDecimal ordTotal) {
		super();
		this.prodSerialNum = prodSerialNum;
		this.category = category;
//		this.prodType = prodType; --ProductTypeBean取代此建構子--
		this.prodQTY = prodQTY;
		this.itemSum = itemSum;
		this.discount = discount;
		this.ordTotal = ordTotal;
	}

	public Integer getProdSerialNum() {
		return prodSerialNum;
	}

	public void setProdSerialNum(Integer prodSerialNum) {
		this.prodSerialNum = prodSerialNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

//	public String getProdType() { --ProductTypeBean取代此建構子--
//		return prodType;
//	}
//
//	public void setProdType(String prodType) {
//		this.prodType = prodType;
//	}

	public Integer getProdQTY() {
		return prodQTY;
	}

	public void setProdQTY(Integer prodQTY) {
		this.prodQTY = prodQTY;
	}

	public BigDecimal getItemSum() {
		return itemSum;
	}

	public void setItemSum(BigDecimal itemSum) {
		this.itemSum = itemSum;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getOrdTotal() {
		return ordTotal;
	}

	public void setOrdTotal(BigDecimal ordTotal) {
		this.ordTotal = ordTotal;
	}

//	雙向多對一productTypeBean之getter、setter 開始
	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}

	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
//	雙向多對一productTypeBean之getter、setter 結束
	
	public OrdBean getOrdBean() {
		return ordBean;
	}

	public void setOrdBean(OrdBean ordBean) {
		this.ordBean = ordBean;
	}

	public PromotionBean getPromotionBean() {
		return promotionBean;
	}

	public void setPromotionBean(PromotionBean promotionBean) {
		this.promotionBean = promotionBean;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}	
		

	
	
	
	
	
}

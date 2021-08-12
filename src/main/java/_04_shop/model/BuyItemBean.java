package _04_shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _02_customerService.model.PromotionBean;
import _06_order.model.OrdBean;




@Entity
@Table(name="BuyItem")
public class BuyItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer			prodSerialNum;	//商品項次
	private String  		category;       //訂單類別
	private String      	prodType;		//商品分類代碼
	private Integer     	prodQTY;		//商品數量
	private BigDecimal  	itemSum;		//單項總額
	private BigDecimal  	discount;		//折扣金額
	private BigDecimal		ordTotal;		//訂單總金額
	

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYITEM_ORDID_FK")
	private OrdBean ordBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PROMOTEID_FK")
	private PromotionBean promotionBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PRODID_FK")
	private ProductBean productBean;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "BUYTITEM_DISCOUNTCODE_FK")
//	private PromotionBean promotionBean;
	
	public BuyItemBean(Integer prodSerialNum, String category, String prodType, Integer prodQTY, BigDecimal itemSum,
			BigDecimal discount, BigDecimal ordTotal) {
		super();
		this.prodSerialNum = prodSerialNum;
		this.category = category;
		this.prodType = prodType;
		this.prodQTY = prodQTY;
		this.itemSum = itemSum;
		this.discount = discount;
		this.ordTotal = ordTotal;
//		this.ordBean = ordBean;
//		this.promotionBean = promotionBean;
//		this.productBean = productBean;
		
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

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

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

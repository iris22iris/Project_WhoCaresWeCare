package com.web.store.model._04_shop;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.model._07_productType.ProductTypeBean;

@Entity
@Table(name="BuyItem")
public class BuyItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BuyItemPK buyItemPK;
	
	private Integer     	prodQTY;		//商品數量
	private BigDecimal  	itemSum;		//單項總額
	private String 			discountCode;	//折扣碼
	private BigDecimal  	discount;		//折扣金額
	private BigDecimal		ordTotal;		//訂單總金額
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;
	
	@MapsId("OrdPK")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "category", referencedColumnName="category"),
		@JoinColumn(name = "ordId", referencedColumnName="ordId"),
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
	
	public BuyItemBean() {
	}

	public BuyItemBean(Integer prodQTY, BigDecimal itemSum, String discountCode,
			BigDecimal discount, BigDecimal ordTotal) {
		this.prodQTY = prodQTY;
		this.itemSum = itemSum;
		this.discountCode = discountCode;
		this.discount = discount;
		this.ordTotal = ordTotal;
	}
	
	public BuyItemPK getBuyItemPK() {
		return buyItemPK;
	}

	public void setBuyItemPK(BuyItemPK buyItemPK) {
		this.buyItemPK = buyItemPK;
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
	
	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyItemPK == null) ? 0 : buyItemPK.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((discountCode == null) ? 0 : discountCode.hashCode());
		result = prime * result + ((itemSum == null) ? 0 : itemSum.hashCode());
		result = prime * result + ((ordTotal == null) ? 0 : ordTotal.hashCode());
		result = prime * result + ((prodQTY == null) ? 0 : prodQTY.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyItemBean other = (BuyItemBean) obj;
		if (buyItemPK == null) {
			if (other.buyItemPK != null)
				return false;
		} else if (!buyItemPK.equals(other.buyItemPK))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (discountCode == null) {
			if (other.discountCode != null)
				return false;
		} else if (!discountCode.equals(other.discountCode))
			return false;
		if (itemSum == null) {
			if (other.itemSum != null)
				return false;
		} else if (!itemSum.equals(other.itemSum))
			return false;
		if (ordTotal == null) {
			if (other.ordTotal != null)
				return false;
		} else if (!ordTotal.equals(other.ordTotal))
			return false;
		if (prodQTY == null) {
			if (other.prodQTY != null)
				return false;
		} else if (!prodQTY.equals(other.prodQTY))
			return false;
		return true;
	}




}
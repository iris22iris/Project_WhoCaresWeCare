package com.web.store.model._03_rent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._07_productType.ProductTypeBean;


@Entity
@Table(name = "RentItem")
public class RentItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RentItemPK rentItemPK;
	
//	private Integer prodId;
//	private String serialNumber;
	private Integer rentPeriod;
	private Integer prodQty;
	private String discountCode;
	@Column(columnDefinition = "datetime")
	private Timestamp startDate;
	@Column(columnDefinition = "datetime")
	private Timestamp returnDate;
	private BigDecimal discount;
	private BigDecimal prodTotal;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;
	
	@MapsId("OrdPK")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "category", referencedColumnName="category"),
		@JoinColumn(name = "ordId", referencedColumnName="ordId"),
		})
	private OrdBean ordBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_PROMOTEID_FK")
	private PromotionBean promotionBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "RENTITEM_RENTPRODID_FK"),
		@JoinColumn(name = "RENTITEM_RENTPRODSN_FK"),
		})
	private RentProductBean rentProductBean;
	
	public RentItemBean() {
	}

	public RentItemBean(/*Integer prodId, String serialNumber, */Integer rentPeriod, 
			Integer prodQty, String discountCode, Timestamp startDate,
			Timestamp returnDate, BigDecimal discount, BigDecimal prodTotal) {
//		this.prodId = prodId;
//		this.serialNumber = serialNumber;
		this.rentPeriod = rentPeriod;
		this.prodQty = prodQty;
		this.discountCode = discountCode;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.discount = discount;
		this.prodTotal = prodTotal;
	}

	public RentItemPK getRentItemPK() {
		return rentItemPK;
	}

	public void setRentItemPK(RentItemPK rentItemPK) {
		this.rentItemPK = rentItemPK;
	}

//	public Integer getProdId() {
//		return prodId;
//	}
//
//	public void setProdId(Integer prodId) {
//		this.prodId = prodId;
//	}
//
//	public String getSerialNumber() {
//		return serialNumber;
//	}
//
//	public void setSerialNumber(String serialNumber) {
//		this.serialNumber = serialNumber;
//	}

	public Integer getRentPeriod() {
		return rentPeriod;
	}

	public void setRentPeriod(Integer rentPeriod) {
		this.rentPeriod = rentPeriod;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(BigDecimal prodTotal) {
		this.prodTotal = prodTotal;
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

	public RentProductBean getRentProductBean() {
		return rentProductBean;
	}

	public void setRentProductBean(RentProductBean rentProductBean) {
		this.rentProductBean = rentProductBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((discountCode == null) ? 0 : discountCode.hashCode());
//		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
		result = prime * result + ((prodQty == null) ? 0 : prodQty.hashCode());
		result = prime * result + ((prodTotal == null) ? 0 : prodTotal.hashCode());
		result = prime * result + ((rentItemPK == null) ? 0 : rentItemPK.hashCode());
		result = prime * result + ((rentPeriod == null) ? 0 : rentPeriod.hashCode());
//		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
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
		RentItemBean other = (RentItemBean) obj;
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
//		if (prodId == null) {
//			if (other.prodId != null)
//				return false;
//		} else if (!prodId.equals(other.prodId))
//			return false;
		if (prodQty == null) {
			if (other.prodQty != null)
				return false;
		} else if (!prodQty.equals(other.prodQty))
			return false;
		if (prodTotal == null) {
			if (other.prodTotal != null)
				return false;
		} else if (!prodTotal.equals(other.prodTotal))
			return false;
		if (rentItemPK == null) {
			if (other.rentItemPK != null)
				return false;
		} else if (!rentItemPK.equals(other.rentItemPK))
			return false;
		if (rentPeriod == null) {
			if (other.rentPeriod != null)
				return false;
		} else if (!rentPeriod.equals(other.rentPeriod))
			return false;
//		if (serialNumber == null) {
//			if (other.serialNumber != null)
//				return false;
//		} else if (!serialNumber.equals(other.serialNumber))
//			return false;
		return true;
	}
	
}
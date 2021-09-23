package com.web.store.model._03_rent;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._03_rent.pkClass.RentItemPK;
import com.web.store.model._06_order.OrdBean;

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
	@Column(columnDefinition = "datetime")
	private Timestamp startDate;
	@Column(columnDefinition = "datetime")
	private Timestamp returnDate;
	private Double prodTotal;
	private String rentStatus;

//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "RENTITEM_PRODTYPE_FK")
//	private ProductTypeBean productTypeBean;

	@MapsId("OrdPK")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({ @JoinColumn(name = "category", referencedColumnName = "category"),
			@JoinColumn(name = "ordId", referencedColumnName = "ordId"), })
	private OrdBean ordBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_PROMOTEID_FK")
	private PromotionBean promotionBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({ @JoinColumn(name = "RENTITEM_RENTPRODID_FK"), @JoinColumn(name = "RENTITEM_RENTPRODSN_FK"), })
	private RentProductBean rentProductBean;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RENTITEM_COMMENTID_FK")
	private CommentBean commentBean;

	public RentItemBean() {
	}

	public RentItemBean(Integer rentPeriod, Integer prodQty, Timestamp startDate, Timestamp returnDate,
			Double prodTotal, String rentStatus) {
//		this.prodId = prodId;
//		this.serialNumber = serialNumber;
		this.rentPeriod = rentPeriod;
		this.prodQty = prodQty;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.prodTotal = prodTotal;
		this.rentStatus = rentStatus;
	}

	public RentItemPK getRentItemPK() {
		return rentItemPK;
	}

	public void setRentItemPK(RentItemPK rentItemPK) {
		this.rentItemPK = rentItemPK;
	}

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

	public Double getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(Double prodTotal) {
		this.prodTotal = prodTotal;
	}

	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

//	// 雙向多對一productTypeBean之getter、setter 開始
//	public ProductTypeBean getProductTypeBean() {
//		return productTypeBean;
//	}
//
//	public void setProductTypeBean(ProductTypeBean productTypeBean) {
//		this.productTypeBean = productTypeBean;
//	}
////	雙向多對一productTypeBean之getter、setter 結束

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

	public CommentBean getCommentBean() {
		return commentBean;
	}

	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodQty == null) ? 0 : prodQty.hashCode());
		result = prime * result + ((prodTotal == null) ? 0 : prodTotal.hashCode());
		result = prime * result + ((rentItemPK == null) ? 0 : rentItemPK.hashCode());
		result = prime * result + ((rentPeriod == null) ? 0 : rentPeriod.hashCode());
		result = prime * result + ((rentStatus == null) ? 0 : rentStatus.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		if (rentStatus == null) {
			if (other.rentStatus != null)
				return false;
		} else if (!rentStatus.equals(other.rentStatus))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
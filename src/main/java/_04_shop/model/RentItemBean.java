package _04_shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import _02_customerService.model.PromotionBean;
import _03_rent.model.RentProductBean;
import _06_order.model.OrdBean;

@Entity
@Table(name = "RentItem")
public class RentItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodSerialNum;
	private String category;
	private String productType;
	private Integer prodId;
	private String serialNumber;
	private Integer rentPeriod;
	private Integer prodQty;
	private BigDecimal discountCode;
	@Column(columnDefinition = "datetime")
	private Timestamp startDate;
	@Column(columnDefinition = "datetime")
	private Timestamp returnDate;
	private BigDecimal discount;
	private BigDecimal ordTot;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_ORDID_FK")
	private OrdBean ordBean;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_PROMOTEID_FK")
	private PromotionBean promotionBean;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTITEM_PRODID_FK")
	private RentProductBean rentProductBean;

	public RentItemBean(Integer prodSerialNum, String category, String productType, Integer prodId, String serialNumber,
			Integer rentPeriod, Integer prodQty, BigDecimal discountCode, Timestamp startDate, Timestamp returnDate,
			BigDecimal discount, BigDecimal ordTot) {
		super();
		this.prodSerialNum = prodSerialNum;
		this.category = category;
		this.productType = productType;
		this.prodId = prodId;
		this.serialNumber = serialNumber;
		this.rentPeriod = rentPeriod;
		this.prodQty = prodQty;
		this.discountCode = discountCode;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.discount = discount;
		this.ordTot = ordTot;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public BigDecimal getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(BigDecimal discountCode) {
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

	public BigDecimal getOrdTot() {
		return ordTot;
	}

	public void setOrdTot(BigDecimal ordTot) {
		this.ordTot = ordTot;
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

	public RentProductBean getRentProductBean() {
		return rentProductBean;
	}

	public void setRentProductBean(RentProductBean rentProductBean) {
		this.rentProductBean = rentProductBean;
	}

}
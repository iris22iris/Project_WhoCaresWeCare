package _ord;

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

@Entity
@Table(name = "RentItem")
public class RentItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int(8)")
	private Integer prodSerialNum;
	private String category;
	private String promoteId;
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
	@JoinColumn(name = "FK_OrdBean_OrdId")
	private OrdBean ordBean;

	public RentItemBean(Integer prodSerialNum, String category, String promoteId, String productType, Integer prodId,
			String serialNumber, Integer rentPeriod, Integer prodQty, BigDecimal discountCode, Timestamp startDate,
			Timestamp returnDate, BigDecimal discount, BigDecimal ordTot) {
		super();
		this.prodSerialNum = prodSerialNum;
		this.category = category;
		this.promoteId = promoteId;
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

	public OrdBean getOrdBean() {
		return ordBean;
	}

	public void setOrdBean(OrdBean ordBean) {
		this.ordBean = ordBean;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(String promoteId) {
		this.promoteId = promoteId;
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

}
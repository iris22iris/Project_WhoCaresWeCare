package _ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ord")
public class OrdBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ordId;
	private String category;
	private Timestamp orderDate;
	private String custId;
	private String reciName;
	private String reciCity;
	private String reciAddress;
	private String reciPhone;
	private BigDecimal ordTot;
	private String discountType;
	private String payment;
	private String carriage;
	private String discountId;
	private Timestamp shipDate;
//	private Clob orderMark;

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();

	public OrdBean(Integer ordId, String category, Timestamp orderDate, String custId, String reciName, String reciCity,
			String reciAddress, String reciPhone, BigDecimal ordTot, String discountType, String payment,
			String carriage, String discountId, Timestamp shipDate, Set<RentItemBean> rentItems) {
		super();
		this.ordId = ordId;
		this.category = category;
		this.orderDate = orderDate;
		this.custId = custId;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTot = ordTot;
		this.discountType = discountType;
		this.payment = payment;
		this.carriage = carriage;
		this.discountId = discountId;
		this.shipDate = shipDate;
		this.rentItems = rentItems;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getReciName() {
		return reciName;
	}

	public void setReciName(String reciName) {
		this.reciName = reciName;
	}

	public String getReciCity() {
		return reciCity;
	}

	public void setReciCity(String reciCity) {
		this.reciCity = reciCity;
	}

	public String getReciAddress() {
		return reciAddress;
	}

	public void setReciAddress(String reciAddress) {
		this.reciAddress = reciAddress;
	}

	public String getReciPhone() {
		return reciPhone;
	}

	public void setReciPhone(String reciPhone) {
		this.reciPhone = reciPhone;
	}

	public BigDecimal getOrdTot() {
		return ordTot;
	}

	public void setOrdTot(BigDecimal ordTot) {
		this.ordTot = ordTot;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public Timestamp getShipDate() {
		return shipDate;
	}

	public void setShipDate(Timestamp shipDate) {
		this.shipDate = shipDate;
	}

	public Set<RentItemBean> getRentItems() {
		return rentItems;
	}

	public void setRentItems(Set<RentItemBean> rentItems) {
		this.rentItems = rentItems;
	}

}
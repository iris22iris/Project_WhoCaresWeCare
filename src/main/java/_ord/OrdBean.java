package _ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ord")
public class OrdBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) ZEROFILL")
	private Integer ordId;
	private String category;
	@Column(columnDefinition = "datetime")
	private Timestamp orderDate;
	private String reciName;
	private String reciCity;
	private String reciAddress;
	private String reciPhone;
	private BigDecimal ordTot;
	private String discountType;
	private String payment;
	private String carriage;
	private String discountId;
	@Column(columnDefinition = "datetime")
	private Timestamp shipDate;
	private Clob orderMark;
	
	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ORD_CUSTID_FK")
	private CustomerBean customerBean;
	
	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL )
	Set<ProblemBean> problem = new LinkedHashSet<>();

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();
	
	public OrdBean(Integer ordId, String category, Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, BigDecimal ordTot, String discountType, String payment,
			String carriage, String discountId, Timestamp shipDate, Clob orderMark, Set<RentItemBean> rentItems,
			Set<ProblemBean> problem,Set<BuyItemBean> buyItems) {
		super();
		this.ordId = ordId;
		this.category = category;
		this.orderDate = orderDate;
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
		this.orderMark = orderMark;
		this.rentItems = rentItems;
		this.problem = problem;
		this.buyItems = buyItems;
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

	public Clob getOrderMark() {
		return orderMark;
	}

	public void setOrderMark(Clob orderMark) {
		this.orderMark = orderMark;
	}

	public Set<RentItemBean> getRentItems() {
		return rentItems;
	}

	public void setRentItems(Set<RentItemBean> rentItems) {
		this.rentItems = rentItems;
	}
	
	
	

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public Set<ProblemBean> getProblem() {
		return problem;
	}

	public void setProblem(Set<ProblemBean> problem) {
		this.problem = problem;
	}
	
	public Set<BuyItemBean> getBuyItems() {
		return buyItems;
	}
	
	public void setBuyItems(Set<BuyItemBean> buyItems) {
		this.buyItems = buyItems;
	}
	
}
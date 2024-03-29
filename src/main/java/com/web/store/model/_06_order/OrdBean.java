package com.web.store.model._06_order;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.pkClass.OrdPK;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
@Entity
@Table(name = "Ord")
public class OrdBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId	
	private OrdPK ordPK;			//訂單編號(種類/編號)
	
	@Column(columnDefinition = "datetime")
	private Timestamp orderDate;	//訂單日期
	private String reciName;		//收件人姓名
	private String reciCity;		//收件人城市
	private String reciAddress;		//收件人地址
	private String reciPhone;		//收件人電話
	private Double ordTotal;		//訂單金額
	private String delivery;		//宅配方式
	private String payment;			//付款方式
	private String discountCode;	//折扣碼
	private Double discount;		//折扣金額
	private String orderStatus;		//訂單處理狀態
	@Column(columnDefinition = "datetime")
	private Timestamp shipDate;		//宅配到貨日
	private Clob orderMark;			//備註
	private String payPayment;		//金流串接Html
	

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ORD_CUSTID_FK")
	private CustomerBean customerBean;

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<ProblemBean> problem = new LinkedHashSet<>();

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();
	
	

	public OrdBean() {
	}
	
	public OrdBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}
	
	public OrdBean(OrdPK ordPK) {
		this.ordPK = ordPK;
	}

	public OrdBean(Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, Double ordTotal, String delivery,String payment,
			String discountCode, Double discount, String orderStatus, Timestamp shipDate,
			Clob orderMark, Set<RentItemBean> rentItems, CustomerBean customerBean, Set<BuyItemBean> buyItems) {
		this.orderDate = orderDate;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTotal = ordTotal;
		this.delivery = delivery;
		this.payment = payment;
		this.discountCode = discountCode;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.shipDate = shipDate;
		this.orderMark = orderMark;
		this.rentItems = rentItems;
		this.customerBean = customerBean;
		this.buyItems = buyItems;
	}
	
	public OrdBean(Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, Double ordTotal, String delivery,String payment,
			String discountCode, Double discount, String orderStatus, Timestamp shipDate,
			 Set<RentItemBean> rentItems, CustomerBean customerBean, Set<BuyItemBean> buyItems) {
		this.orderDate = orderDate;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTotal = ordTotal;
		this.delivery = delivery;
		this.payment = payment;
		this.discountCode = discountCode;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.shipDate = shipDate;
//		this.orderMark = orderMark;
		this.rentItems = rentItems;
		this.customerBean = customerBean;
		this.buyItems = buyItems;
	}
	
	public OrdPK getOrdPK() {
		return ordPK;
	}

	public void setOrdPK(OrdPK ordPK) {
		this.ordPK = ordPK;
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

	public Double getOrdTotal() {
		return ordTotal;
	}

	public void setOrdTotal(Double ordTotal) {
		this.ordTotal = ordTotal;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDiscount() {
		return discount;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getPayPayment() {
		return payPayment;
	}

	public void setPayPayment(String payPayment) {
		this.payPayment = payPayment;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((discountCode == null) ? 0 : discountCode.hashCode());
		result = prime * result + ((ordPK == null) ? 0 : ordPK.hashCode());
		result = prime * result + ((ordTotal == null) ? 0 : ordTotal.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((reciAddress == null) ? 0 : reciAddress.hashCode());
		result = prime * result + ((reciCity == null) ? 0 : reciCity.hashCode());
		result = prime * result + ((reciName == null) ? 0 : reciName.hashCode());
		result = prime * result + ((reciPhone == null) ? 0 : reciPhone.hashCode());
		result = prime * result + ((shipDate == null) ? 0 : shipDate.hashCode());
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
		OrdBean other = (OrdBean) obj;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
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
		if (ordPK == null) {
			if (other.ordPK != null)
				return false;
		} else if (!ordPK.equals(other.ordPK))
			return false;
		if (ordTotal == null) {
			if (other.ordTotal != null)
				return false;
		} else if (!ordTotal.equals(other.ordTotal))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (reciAddress == null) {
			if (other.reciAddress != null)
				return false;
		} else if (!reciAddress.equals(other.reciAddress))
			return false;
		if (reciCity == null) {
			if (other.reciCity != null)
				return false;
		} else if (!reciCity.equals(other.reciCity))
			return false;
		if (reciName == null) {
			if (other.reciName != null)
				return false;
		} else if (!reciName.equals(other.reciName))
			return false;
		if (reciPhone == null) {
			if (other.reciPhone != null)
				return false;
		} else if (!reciPhone.equals(other.reciPhone))
			return false;
		if (shipDate == null) {
			if (other.shipDate != null)
				return false;
		} else if (!shipDate.equals(other.shipDate))
			return false;
		return true;
	}



}
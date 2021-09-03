package com.web.store.model._06_order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.store.model._02_customerService.ProblemBean;
import com.web.store.model._03_rent.RentItemBean;
import com.web.store.model._04_shop.BuyItemBean;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.pkClass.OrdPK;


@Entity
@Table(name = "Ord")
public class OrdBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdPK ordPK;
	
	@Column(columnDefinition = "datetime")
	private Timestamp orderDate;
	private String reciName;
	private String reciCity;
	private String reciAddress;
	private String reciPhone;
	private BigDecimal ordTotal;
	private String delivery;
	private String payment;
	@Column(columnDefinition = "datetime")
	private Timestamp shipDate;
	private Clob orderMark;

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ORD_CUSTID_FK")
	private CustomerBean customerBean;

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<ProblemBean> problem = new LinkedHashSet<>();

	@OneToMany(mappedBy = "ordBean", cascade = CascadeType.ALL)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();
	
	

	public OrdBean() {
	}

	public OrdBean(Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, BigDecimal ordTotal, String delivery, String payment,
			Timestamp shipDate, Clob orderMark, Set<RentItemBean> rentItems,
			Set<ProblemBean> problem, Set<BuyItemBean> buyItems) {
		this.orderDate = orderDate;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTotal = ordTotal;
		this.delivery = delivery;
		this.payment = payment;
		this.shipDate = shipDate;
		this.orderMark = orderMark;
		this.rentItems = rentItems;
		this.problem = problem;
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

	public BigDecimal getOrdTotal() {
		return ordTotal;
	}

	public void setOrdTotal(BigDecimal ordTotal) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((ordPK == null) ? 0 : ordPK.hashCode());
		result = prime * result + ((ordTotal == null) ? 0 : ordTotal.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
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
		if (payment == null) {
			if (other.payment != null)
				return false;
		} else if (!payment.equals(other.payment))
			return false;
		if (problem == null) {
			if (other.problem != null)
				return false;
		} else if (!problem.equals(other.problem))
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
package com.web.store.model._06_order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(OrdPK.class)
public class OrdBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String category;
	@Id
	@Column(columnDefinition = "INT(8) ZEROFILL")
	private Integer ordId;
	@Column(columnDefinition = "datetime")
	private Timestamp orderDate;
	private String reciName;
	private String reciCity;
	private String reciAddress;
	private String reciPhone;
	private BigDecimal ordTot;
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
	
	

	public OrdBean(String category, Integer ordId, Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, BigDecimal ordTot, String delivery, String payment,
			Timestamp shipDate, Clob orderMark) {
		this.category = category;
		this.ordId = ordId;
		this.orderDate = orderDate;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTot = ordTot;
		this.delivery = delivery;
		this.payment = payment;
		this.shipDate = shipDate;
		this.orderMark = orderMark;
	}



	public OrdBean(String category, Integer ordId, Timestamp orderDate, String reciName, String reciCity,
			String reciAddress, String reciPhone, BigDecimal ordTot, String delivery, String payment,
			Timestamp shipDate, Clob orderMark, Set<RentItemBean> rentItems,
			Set<ProblemBean> problem, Set<BuyItemBean> buyItems) {
		this.category = category;
		this.ordId = ordId;
		this.orderDate = orderDate;
		this.reciName = reciName;
		this.reciCity = reciCity;
		this.reciAddress = reciAddress;
		this.reciPhone = reciPhone;
		this.ordTot = ordTot;
		this.delivery = delivery;
		this.payment = payment;
		this.shipDate = shipDate;
		this.orderMark = orderMark;
		this.rentItems = rentItems;
		this.problem = problem;
		this.buyItems = buyItems;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
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
		result = prime * result + ((buyItems == null) ? 0 : buyItems.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((customerBean == null) ? 0 : customerBean.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
		result = prime * result + ((ordTot == null) ? 0 : ordTot.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderMark == null) ? 0 : orderMark.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
		result = prime * result + ((reciAddress == null) ? 0 : reciAddress.hashCode());
		result = prime * result + ((reciCity == null) ? 0 : reciCity.hashCode());
		result = prime * result + ((reciName == null) ? 0 : reciName.hashCode());
		result = prime * result + ((reciPhone == null) ? 0 : reciPhone.hashCode());
		result = prime * result + ((rentItems == null) ? 0 : rentItems.hashCode());
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
		if (buyItems == null) {
			if (other.buyItems != null)
				return false;
		} else if (!buyItems.equals(other.buyItems))
			return false;

		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (customerBean == null) {
			if (other.customerBean != null)
				return false;
		} else if (!customerBean.equals(other.customerBean))
			return false;
	
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		if (ordId == null) {
			if (other.ordId != null)
				return false;
		} else if (!ordId.equals(other.ordId))
			return false;
		if (ordTot == null) {
			if (other.ordTot != null)
				return false;
		} else if (!ordTot.equals(other.ordTot))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderMark == null) {
			if (other.orderMark != null)
				return false;
		} else if (!orderMark.equals(other.orderMark))
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
		if (rentItems == null) {
			if (other.rentItems != null)
				return false;
		} else if (!rentItems.equals(other.rentItems))
			return false;
		if (shipDate == null) {
			if (other.shipDate != null)
				return false;
		} else if (!shipDate.equals(other.shipDate))
			return false;
		return true;
	}

}
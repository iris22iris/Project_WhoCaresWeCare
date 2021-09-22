package com.web.store.model._03_rent;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.store.model._05_customer.CustomerBean;


@Entity
@Table(name = "Reservation")
public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) ZEROFILL")
	private Integer reservationId;
	private String category;
	private Integer waitNum;
	private String classify;
	private Integer waitType;
	@Column(columnDefinition = "datetime")
	private Timestamp reserveDate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "RESERVE_PRODID_FK"),
		@JoinColumn(name = "RESERVE_PRODSN_FK"),
		})
	private RentProductBean rentProductBean;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RESERVE_CUSTID_FK")
	private CustomerBean customerBean;

	public ReservationBean(Integer reservationId, String category, Integer waitNum, String classify, Integer waitType, Timestamp reserveDate) {
		this.reservationId = reservationId;
		this.category = category;
		this.waitNum = waitNum;
		this.classify = classify;
		this.waitType = waitType;
		this.reserveDate = reserveDate;
	}	

	public ReservationBean() {
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getWaitNum() {
		return waitNum;
	}

	public void setWaitNum(Integer waitNum) {
		this.waitNum = waitNum;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Integer getWaitType() {
		return waitType;
	}

	public void setWaitType(Integer waitType) {
		this.waitType = waitType;
	}

	public Timestamp getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}

	public RentProductBean getRentProductBean() {
		return rentProductBean;
	}

	public void setRentProductBean(RentProductBean rentProductBean) {
		this.rentProductBean = rentProductBean;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

}
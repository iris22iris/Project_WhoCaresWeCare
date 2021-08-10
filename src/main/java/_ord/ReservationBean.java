package _ord;

import java.io.Serializable;
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
@Table(name = "Reservation")
public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;
	private String category;
	private Integer waitNum;
	private String classify;
	private Integer prodId;
	private String serialNumber;
	private Integer waitType;
	@Column(columnDefinition = "datetime")
	private Timestamp reserveDate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_customerBean_custId")
	private CustomerBean customerBean;

	public ReservationBean(Integer reservationId, String category, Integer waitNum, String classify, Integer prodId,
			String serialNumber, Integer waitType, Timestamp reserveDate) {
		super();
		this.reservationId = reservationId;
		this.category = category;
		this.waitNum = waitNum;
		this.classify = classify;
		this.prodId = prodId;
		this.serialNumber = serialNumber;
		this.waitType = waitType;
		this.reserveDate = reserveDate;
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

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

}
package _ord;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer custId; // 會員編號
	String account; // 會員帳號
	String password; // 會員密碼
	String custName; // 會員姓名
	String nickName; // 暱稱
	String gender; // 性別
	String idNumber; // 會員身份證字號
	@Column(columnDefinition = "datetime")
	Timestamp birthday; // 出生日期
	String phone; // 連絡電話
	String city; // 通訊城市
	String address; // 通訊地址
	String email; // 電子信箱
	Blob customerImage; // 會員圖片
	String fileName; // 圖片名稱
	String mineType; // 圖片類型

	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL)
	Set<OrdBean> orders = new LinkedHashSet<>();
	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL)
	Set<ReservationBean> reservations = new LinkedHashSet<>();


	public CustomerBean(Integer custId, String account, String password, String custName, String nickName,
			String gender, String idNumber, Timestamp birthday, String phone, String city, String address, String email,
			Blob customerImage, String fileName, String mineType, Set<OrdBean> orders,
			Set<ReservationBean> reservations) {
		super();
		this.custId = custId;
		this.account = account;
		this.password = password;
		this.custName = custName;
		this.nickName = nickName;
		this.gender = gender;
		this.idNumber = idNumber;
		this.birthday = birthday;
		this.phone = phone;
		this.city = city;
		this.address = address;
		this.email = email;
		this.customerImage = customerImage;
		this.fileName = fileName;
		this.mineType = mineType;
		this.orders = orders;
		this.reservations = reservations;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getCustomerImage() {
		return customerImage;
	}

	public void setCustomerImage(Blob customerImage) {
		this.customerImage = customerImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMineType() {
		return mineType;
	}

	public void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public Set<OrdBean> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdBean> orders) {
		this.orders = orders;
	}

	public Set<ReservationBean> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationBean> reservations) {
		this.reservations = reservations;
	}

}
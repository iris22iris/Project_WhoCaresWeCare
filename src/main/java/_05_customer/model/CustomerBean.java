package _05_customer.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
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

import _02_customerService.model.CommentBean;
import _02_customerService.model.ProblemBean;
import _03_rent.model.ReservationBean;
import _06_order.model.OrdBean;

@Entity
@Table(name = "Customer")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) ZEROFILL")
	Integer custId; // 會員編號
	String account; // 會員帳號
	String password; // 會員密碼
	String custName; // 會員姓名
	String nickName; // 暱稱
	String gender; // 性別
	String idNumber; // 會員身份證字號
	@Column(columnDefinition = "datetime")
	Date birthday; // 出生日期
	String phone; // 連絡電話
	String city; // 通訊城市
	String address; // 通訊地址
	String email; // 電子信箱
	Blob customerImage; // 會員圖片
	String fileName; // 圖片名稱
	String mimeType; // 圖片類型

	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL)
	Set<OrdBean> orders = new LinkedHashSet<>();
	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL)
	Set<ReservationBean> reservations = new LinkedHashSet<>();
	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL)
	Set<CommentBean> comments = new LinkedHashSet<>();
	@OneToMany(mappedBy = "customerBean", cascade = CascadeType.ALL )
	Set<ProblemBean> problem = new LinkedHashSet<>();
	
	
	
	public CustomerBean() {
	}

	public CustomerBean(Integer custId, String account, String password, String custName, String nickName,
			String gender, String idNumber, Date birthday, String phone, String city, String address, String email,
			Blob customerImage, String fileName, String mimeType) {
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
		this.mimeType = mimeType;
	}

	public CustomerBean(Integer custId, String account, String password, String custName, String nickName,
			String gender, String idNumber, Date birthday, String phone, String city, String address, String email,
			Blob customerImage, String fileName, String mimeType, Set<OrdBean> orders,
			Set<ReservationBean> reservations, Set<CommentBean> comments, Set<ProblemBean> problem) {
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
		this.mimeType = mimeType;
		this.orders = orders;
		this.reservations = reservations;
		this.comments = comments;
		this.problem = problem;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public String getMimeType() {
	public String getmimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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

	public Set<CommentBean> getComments() {
		return comments;
	}

	public void setComments(Set<CommentBean> comments) {
		this.comments = comments;
	}

	public Set<ProblemBean> getProblem() {
		return problem;
	}

	public void setProblem(Set<ProblemBean> problem) {
		this.problem = problem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerBean [custId=");
		builder.append(custId);
		builder.append(", account=");
		builder.append(account);
		builder.append(", password=");
		builder.append(password);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", idNumber=");
		builder.append(idNumber);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", city=");
		builder.append(city);
		builder.append(", address=");
		builder.append(address);
		builder.append(", email=");
		builder.append(email);
		builder.append(", customerImage=");
		builder.append(customerImage);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", mimeType=");
		builder.append(mimeType);
		builder.append(", orders=");
		builder.append(orders);
		builder.append(", reservations=");
		builder.append(reservations);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", problem=");
		builder.append(problem);
		builder.append("]");
		return builder.toString();
	}

}

package _ord;

import java.io.Serializable;
import java.sql.Blob;
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
@Table(name = "Problem")
public class ProblemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer custId;
	private Integer ordId;
	private String email;
	private String phone;
	private String problemType;
	private String content;
	@Column(columnDefinition = "datetime")
	private Timestamp formDate;
	private String processState;
	@Column(columnDefinition = "datetime")
	private Timestamp replyDate;
	private String replyContent;
	private Blob attachFile;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PROBLE_CUSTID_FK")
    CustomerBean customerBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PROBLE_ORDID_FK")
	OrdBean ordBean;

	public ProblemBean(Integer custId, Integer ordId, String email, String phone, String problemType, String content,
			Timestamp formDate, String processState, Timestamp replyDate, String replyContent, Blob attachFile) {
		this.custId = custId; // 會員編號
		this.ordId = ordId; // 訂單編號
		this.email = email; // 信箱
		this.phone = phone; // 連絡電話
		this.problemType = problemType;// 問題種類
		this.content = content;// 內容
		this.formDate = formDate;// 表單生成時間
		this.processState = processState;// 處理狀態
		this.replyDate = replyDate;// 客服回應時間
		this.replyContent = replyContent;// 客服回應內容
		this.attachFile = attachFile;// 附加檔案
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getFormDate() {
		return formDate;
	}

	public void setFormDate(Timestamp formDate) {
		this.formDate = formDate;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public Timestamp getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Blob getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(Blob attachFile) {
		this.attachFile = attachFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public OrdBean getOrdBean() {
		return ordBean;
	}

	public void setOrdBean(OrdBean ordBean) {
		this.ordBean = ordBean;
	}
	
}

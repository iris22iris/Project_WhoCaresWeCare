package com.web.store.model._02_customerService;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.web.store.model._02_customerService.usPKClass.ProblemBeanPK;
import com.web.store.model._05_customer.CustomerBean;
import com.web.store.model._06_order.OrdBean;



@Entity
@Table(name = "Problem")
public class ProblemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@EmbeddedId
//	private ProblemBeanPK problemBeanPK;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replyId;
//	private Integer custId;
	private String ordId;
//	private Integer ordId;
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
	String fileName; // 圖片名稱
	String account;  //帳號
	@Transient
	MultipartFile ImageUs;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PROBLEM_CUSTID_FK")
    CustomerBean customerBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "PROBLEM_ORDCID_FK"),
		@JoinColumn(name = "PROBLEM_ORDID_FK"),
		})
	private OrdBean ordBean;


	public ProblemBean(Integer replyId, String ordId, String email, String phone, String problemType, String content,
			Timestamp formDate, String processState, Timestamp replyDate, String replyContent, Blob attachFile,String fileName,String account) {
		this.replyId = replyId; // 會員編號
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
		this.fileName = fileName;
		this.account = account;
	}

	public ProblemBean() {
	}

	public Integer getreplyId() {
		return replyId;
	}

	public void setreplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
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
	
	public MultipartFile getImageUs() {
		return ImageUs;
	}

//	public void setImage(MultipartFile imageUs) {
//		ImageUs = imageUs;
//	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setImageUs(MultipartFile imageUs) {
		ImageUs = imageUs;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((problemBeanPK == null) ? 0 : problemBeanPK.hashCode());
//		result = prime * result + ((account == null) ? 0 : account.hashCode());
//		result = prime * result + ((content == null) ? 0 : content.hashCode());
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
//		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
//		result = prime * result + ((problemType == null) ? 0 : problemType.hashCode());
//		result = prime * result + ((replyContent == null) ? 0 : replyContent.hashCode());
//		result = prime * result + ((replyDate == null) ? 0 : replyDate.hashCode());
//		return result;
//	}
	

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ProblemBean other = (ProblemBean) obj;
//		if(problemBeanPK == null) {
//			if (other.problemBeanPK != null)
//				return false;
//		}else if (!problemBeanPK.equals(other.problemBeanPK))
//			return false;
//		if (account == null) {
//			if (other.account != null)
//				return false;
//		}else if (!account.equals(other.account))
//			return false;
//		if (content == null) {
//			if (other.content != null)
//				return false;
//		}else if (!content.equals(other.content))
//			return false;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		}else if (!email.equals(other.email))
//			return false;
//		if (ordId == null) {
//			if (other.ordId != null)
//				return false;
//		}else if (!ordId.equals(other.ordId))
//			return false;
//		if (phone == null) {
//			if (other.phone != null)
//				return false;
//		}else if (!phone.equals(other.phone))
//			return false;
//		if (problemType == null) {
//			if (other.problemType != null)
//				return false;
//		}else if (!problemType.equals(other.problemType))
//			return false;
//		if (replyContent == null) {
//			if (other.replyContent != null)
//				return false;
//		}else if (!replyContent.equals(other.replyContent))
//			return false;
//		if (replyDate == null) {
//			if (other.replyDate != null)
//				return false;
//		}else if (!replyDate.equals(other.replyDate))
//			return false;
//		return true;
//	}
	
}

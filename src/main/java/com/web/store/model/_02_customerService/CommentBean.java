package com.web.store.model._02_customerService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
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

import com.web.store.model._03_rent.RentProductBean;
import com.web.store.model._04_shop.ProductBean;
import com.web.store.model._05_customer.CustomerBean;


@Entity
@Table(name = "Comment")
public class CommentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) ZEROFILL")
	private Integer commentId;
	private String classify;
	private Integer rate;
	private Integer visits;
	private Timestamp commentDate;
	private Clob comment;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
		@JoinColumn(name = "RENTPRODUCT_PRODCID_FK"),
		@JoinColumn(name = "RENTPRODUCT_PRODID_FK"),
		})
	private RentProductBean rentProductBean;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "COMMENT_CUSTID_FK")
	private CustomerBean customerBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PRODID_FK")
	private ProductBean productBean;

	public CommentBean(Integer commentId, String classify, Integer rate, Integer visits, Timestamp commentDate,
			Clob comment) {
		super();
		this.commentId = commentId;
		this.classify = classify;
		this.rate = rate;
		this.visits = visits;
		this.commentDate = commentDate;
		this.comment = comment;
	}

	public CommentBean() {
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public RentProductBean getRentProductBean() {
		return rentProductBean;
	}

	public void setRentProductBean(RentProductBean rentProductBean) {
		this.rentProductBean = rentProductBean;
	}

		
	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

}
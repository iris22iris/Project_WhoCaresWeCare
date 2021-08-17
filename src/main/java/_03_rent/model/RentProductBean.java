package _03_rent.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import _02_customerService.model.CommentBean;
import _02_customerService.model.PromotionBean;
import _07_productType.model.ProductTypeBean;

@Entity
@Table(name = "RentProduct")
public class RentProductBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodId;
	private String classify;
	private String serialNumber;
	private String prodName;
	private BigDecimal price;
	private Blob coverImage;
	private String mineType;
	private Integer stock;
	private String prodType;
	private String fileName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RENTPROD_PROMOTEID_FK")
	private PromotionBean promotionBean;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "RENTPROD_PRODTYPE_FK")
	private ProductTypeBean productTypeBean;
	
	@OneToMany(mappedBy = "rentProductBean", cascade = CascadeType.ALL)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();
	@OneToMany(mappedBy = "rentProductBean", cascade = CascadeType.ALL)
	Set<ReservationBean> reservations = new LinkedHashSet<>();
	@OneToMany(mappedBy = "rentProductBean", cascade = CascadeType.ALL)
	Set<CommentBean> comments = new LinkedHashSet<>();
	

	public RentProductBean(Integer prodId, String classify, String serialNumber, String prodName, BigDecimal price,
			Blob coverImage, String mineType, Integer stock, String prodType, String fileName,
			Set<RentItemBean> rentItems, Set<ReservationBean> reservations, Set<CommentBean> comments) {
		super();
		this.prodId = prodId;
		this.classify = classify;
		this.serialNumber = serialNumber;
		this.prodName = prodName;
		this.price = price;
		this.coverImage = coverImage;
		this.mineType = mineType;
		this.stock = stock;
		this.prodType = prodType;
		this.fileName = fileName;
		this.rentItems = rentItems;
		this.reservations = reservations;
		this.comments = comments;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public String getMineType() {
		return mineType;
	}

	public void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PromotionBean getPromotionBean() {
		return promotionBean;
	}

	public void setPromotionBean(PromotionBean promotionBean) {
		this.promotionBean = promotionBean;
	}

//	雙向多對一productTypeBean之getter、setter 開始
	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}

	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
//	雙向多對一productTypeBean之getter、setter 結束

	public Set<RentItemBean> getRentItems() {
		return rentItems;
	}

	public void setRentItems(Set<RentItemBean> rentItems) {
		this.rentItems = rentItems;
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

}
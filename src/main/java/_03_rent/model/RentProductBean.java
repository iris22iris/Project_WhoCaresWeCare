package _03_rent.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import _02_customerService.model.CommentBean;
import _02_customerService.model.PromotionBean;
import _03_rent.model.pkClass.RentProductPK;
import _07_productType.model.ProductTypeBean;

@Entity
@Table(name = "RentProduct")
@IdClass(RentProductPK.class)
public class RentProductBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer prodId;
	@Id
	private String serialNumber;
	private String classify;
	private String prodName;
	private BigDecimal price;
	private Blob coverImage;
	private String mimeType;
	private Integer stock;
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

	public RentProductBean() {
	}

	public RentProductBean(Integer prodId, String serialNumber, String classify, String prodName, BigDecimal price,
			Blob coverImage, String mimeType, Integer stock, String prodType, String fileName,
			Set<RentItemBean> rentItems, Set<ReservationBean> reservations, Set<CommentBean> comments) {
		this.prodId = prodId;
		this.serialNumber = serialNumber;
		this.classify = classify;
		this.prodName = prodName;
		this.price = price;
		this.coverImage = coverImage;
		this.mimeType = mimeType;
		this.stock = stock;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
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

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classify == null) ? 0 : classify.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
		result = prime * result + ((prodName == null) ? 0 : prodName.hashCode());
		result = prime * result + ((productTypeBean == null) ? 0 : productTypeBean.hashCode());
		result = prime * result + ((promotionBean == null) ? 0 : promotionBean.hashCode());
		result = prime * result + ((rentItems == null) ? 0 : rentItems.hashCode());
		result = prime * result + ((reservations == null) ? 0 : reservations.hashCode());
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		RentProductBean other = (RentProductBean) obj;
		if (classify == null) {
			if (other.classify != null)
				return false;
		} else if (!classify.equals(other.classify))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (coverImage == null) {
			if (other.coverImage != null)
				return false;
		} else if (!coverImage.equals(other.coverImage))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (prodId == null) {
			if (other.prodId != null)
				return false;
		} else if (!prodId.equals(other.prodId))
			return false;
		if (prodName == null) {
			if (other.prodName != null)
				return false;
		} else if (!prodName.equals(other.prodName))
			return false;
		if (productTypeBean == null) {
			if (other.productTypeBean != null)
				return false;
		} else if (!productTypeBean.equals(other.productTypeBean))
			return false;
		if (promotionBean == null) {
			if (other.promotionBean != null)
				return false;
		} else if (!promotionBean.equals(other.promotionBean))
			return false;
		if (rentItems == null) {
			if (other.rentItems != null)
				return false;
		} else if (!rentItems.equals(other.rentItems))
			return false;
		if (reservations == null) {
			if (other.reservations != null)
				return false;
		} else if (!reservations.equals(other.reservations))
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

}
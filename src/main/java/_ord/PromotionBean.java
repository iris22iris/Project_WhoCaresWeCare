package _ord;

import java.io.Serializable;
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
@Table(name = "Promotion")
public class PromotionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) ZEROFILL")
	private Integer promoteId;
	private String promotion;
	private String promoContent;
	private String promoTag;
	@Column(columnDefinition = "datetime")
	private Timestamp promoStartDate;
	@Column(columnDefinition = "datetime")
	private Timestamp promoEndDate;
	private String discountCode;
	private String discountType;

	@OneToMany(mappedBy = "promotionBean", cascade = CascadeType.ALL)
	Set<RentItemBean> rentItems = new LinkedHashSet<>();
	@OneToMany(mappedBy = "promotionBean", cascade = CascadeType.ALL)
	Set<RentProductBean> rentProducts = new LinkedHashSet<>();

	public PromotionBean(Integer promoteId, String promotion, String promoContent, String promoTag,
			Timestamp promoStartDate, Timestamp promoEndDate, String discountCode, String discountType,
			Set<RentItemBean> rentItems, Set<RentProductBean> rentProducts) {
		super();
		this.promoteId = promoteId;
		this.promotion = promotion;
		this.promoContent = promoContent;
		this.promoTag = promoTag;
		this.promoStartDate = promoStartDate;
		this.promoEndDate = promoEndDate;
		this.discountCode = discountCode;
		this.discountType = discountType;
		this.rentItems = rentItems;
		this.rentProducts = rentProducts;
	}

	public Integer getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getPromoContent() {
		return promoContent;
	}

	public void setPromoContent(String promoContent) {
		this.promoContent = promoContent;
	}

	public String getPromoTag() {
		return promoTag;
	}

	public void setPromoTag(String promoTag) {
		this.promoTag = promoTag;
	}

	public Timestamp getPromoStartDate() {
		return promoStartDate;
	}

	public void setPromoStartDate(Timestamp promoStartDate) {
		this.promoStartDate = promoStartDate;
	}

	public Timestamp getPromoEndDate() {
		return promoEndDate;
	}

	public void setPromoEndDate(Timestamp promoEndDate) {
		this.promoEndDate = promoEndDate;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Set<RentItemBean> getRentItems() {
		return rentItems;
	}

	public void setRentItems(Set<RentItemBean> rentItems) {
		this.rentItems = rentItems;
	}

	public Set<RentProductBean> getRentProducts() {
		return rentProducts;
	}

	public void setRentProducts(Set<RentProductBean> rentProducts) {
		this.rentProducts = rentProducts;
	}

}
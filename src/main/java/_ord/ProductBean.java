package _ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodId;// 商品編號
	private String classify;// 租買分類
	private String prodName;// 商品名稱
	private BigDecimal price;// 商品售價
	private Blob coverImage;// 圖片
	private String mineType;// 圖片類型
	private Integer stock;// 庫存數量
	private String prodType;// 商品分類代碼
	private String fileName;// 圖片名稱
	private Integer promoteId;// 活動編號

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_PROMOTEID_FK")
	PromotionBean promotionBean;
	
	@OneToMany(mappedBy = "ProductBean", cascade = CascadeType.ALL)
	Set<BuyItemBean> buyItems = new LinkedHashSet<>();

	public ProductBean(String classify, Integer prodId, String prodName, BigDecimal price, Blob coverImage,
			String mineType, Integer stock, String prodType, String fileName, Integer promoteId,Set<BuyItemBean> buyItems) {
		this.classify = classify;
		this.prodId = prodId;
		this.prodName = prodName;
		this.price = price;
		this.coverImage = coverImage;
		this.mineType = mineType;
		this.stock = stock;
		this.prodType = prodType;
		this.fileName = fileName;
		this.promoteId = promoteId;
		this.buyItems = buyItems;
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

	public Integer getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

	public PromotionBean getPromotionBean() {
		return promotionBean;
	}

	public void setPromotionBean(PromotionBean promotionBean) {
		this.promotionBean = promotionBean;
	}

	public Set<BuyItemBean> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(Set<BuyItemBean> buyItems) {
		this.buyItems = buyItems;
	}

}

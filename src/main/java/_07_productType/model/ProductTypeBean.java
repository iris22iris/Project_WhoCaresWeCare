package _07_productType.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import _03_rent.model.RentItemBean;
import _03_rent.model.RentProductBean;
import _04_shop.model.BuyItemBean;
import _04_shop.model.ProductBean;

@Entity
@Table(name = "ProductType")
public class ProductTypeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String prodType; // 商品分類代碼 A~H商品種類
	private String prodName; // 分類名稱

	// 雙向一對多
	@OneToMany(mappedBy = "productTypeBean", cascade = { CascadeType.ALL })
	private Set<RentItemBean> rentItems = new LinkedHashSet<>();
	@OneToMany(mappedBy = "productTypeBean", cascade = { CascadeType.ALL })
	private Set<RentProductBean> rentProducts = new LinkedHashSet<>();
	@OneToMany(mappedBy = "productTypeBean", cascade = { CascadeType.ALL })
	private Set<BuyItemBean> buyItems = new LinkedHashSet<>();
	@OneToMany(mappedBy = "productTypeBean", cascade = { CascadeType.ALL })
	private Set<ProductBean> product = new LinkedHashSet<>();

	public ProductTypeBean() {
	}

	public ProductTypeBean(String prodType, String prodName, Set<RentItemBean> rentItems,
			Set<RentProductBean> rentProducts, Set<BuyItemBean> buyItems, Set<ProductBean> product) {
		super();
		this.prodType = prodType;
		this.prodName = prodName;
		this.rentItems = rentItems;
		this.rentProducts = rentProducts;
		this.buyItems = buyItems;
		this.product = product;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Set<RentItemBean> getRentitems() {
		return rentItems;
	}

	public void setRentitems(Set<RentItemBean> rentitems) {
		this.rentItems = rentitems;
	}

	public Set<RentProductBean> getRentproducts() {
		return rentProducts;
	}

	public void setRentproducts(Set<RentProductBean> rentproducts) {
		this.rentProducts = rentproducts;
	}

	public Set<BuyItemBean> getBuyitems() {
		return buyItems;
	}

	public void setBuyitems(Set<BuyItemBean> buyitems) {
		this.buyItems = buyitems;
	}

	public Set<ProductBean> getProducts() {
		return product;
	}

	public void setProducts(Set<ProductBean> products) {
		this.product = products;
	}

}

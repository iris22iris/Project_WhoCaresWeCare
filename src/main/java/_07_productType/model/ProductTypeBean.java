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
	private String prodType; //商品分類代碼 A~H商品種類
	private String prodName; //分類名稱
	
	//雙向一對多
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<RentItemBean> rentitems = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<RentProductBean> rentproducts = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<BuyItemBean> buyitems = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<ProductBean> products = new LinkedHashSet<>();
	
		
	public ProductTypeBean(String prodType, String prodName, Set<RentItemBean> rentitems,
			Set<RentProductBean> rentproducts, Set<BuyItemBean> buyitems, Set<ProductBean> products) {
		super();
		this.prodType = prodType;
		this.prodName = prodName;
		this.rentitems = rentitems;
		this.rentproducts = rentproducts;
		this.buyitems = buyitems;
		this.products = products;
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
		return rentitems;
	}
	public void setRentitems(Set<RentItemBean> rentitems) {
		this.rentitems = rentitems;
	}
	public Set<RentProductBean> getRentproducts() {
		return rentproducts;
	}
	public void setRentproducts(Set<RentProductBean> rentproducts) {
		this.rentproducts = rentproducts;
	}
	public Set<BuyItemBean> getBuyitems() {
		return buyitems;
	}
	public void setBuyitems(Set<BuyItemBean> buyitems) {
		this.buyitems = buyitems;
	}
	public Set<ProductBean> getProducts() {
		return products;
	}
	public void setProducts(Set<ProductBean> products) {
		this.products = products;
	}
	
}

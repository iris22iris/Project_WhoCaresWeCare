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
	private String prodType;
	private String prodName;
	
	//雙向一對多
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<RentItemBean> rentitems = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<RentProductBean> rentproducts = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<BuyItemBean> buyitems = new LinkedHashSet<>();
	@OneToMany(mappedBy="productTypeBean", cascade={CascadeType.ALL}) 
	private Set<ProductBean> products = new LinkedHashSet<>();
	
	public ProductTypeBean(String prodType, String prodName) {
		super();
		this.prodType = prodType; //商品分類代碼 A~H商品種類
		this.prodName = prodName; //分類名稱

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
	
}

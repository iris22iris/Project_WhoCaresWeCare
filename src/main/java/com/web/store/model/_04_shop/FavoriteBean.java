package com.web.store.model._04_shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.model._06_order.OrdBean;
import com.web.store.model._06_order.pkClass.OrdPK;
import com.web.store.model._07_productType.ProductTypeBean;

@Entity
@Table(name="favorite")
public class FavoriteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer FavoriteID;
	private Integer FK_Customer_ID;
	private Integer FK_Product_ID;
	
	
	public FavoriteBean() {
	}


	public Integer getFavoriteID() {
		return FavoriteID;
	}


	public void setFavoriteID(Integer favoriteID) {
		FavoriteID = favoriteID;
	}


	public Integer getFK_Customer_ID() {
		return FK_Customer_ID;
	}


	public void setFK_Customer_ID(Integer fK_Customer_ID) {
		FK_Customer_ID = fK_Customer_ID;
	}


	public Integer getFK_Product_ID() {
		return FK_Product_ID;
	}


	public void setFK_Product_ID(Integer fK_Product_ID) {
		FK_Product_ID = fK_Product_ID;
	}
	
}
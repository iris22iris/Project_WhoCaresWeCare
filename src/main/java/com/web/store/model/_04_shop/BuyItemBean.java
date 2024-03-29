package com.web.store.model._04_shop;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web.store.model._02_customerService.CommentBean;
import com.web.store.model._02_customerService.PromotionBean;
import com.web.store.model._04_shop.pkClass.BuyItemPK;
import com.web.store.model._06_order.OrdBean;

@Entity
@Table(name = "BuyItem")
public class BuyItemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BuyItemPK buyItemPK;

	private Integer prodQTY; // 商品數量
	private Double itemSum; // 單項總額

//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "BUYTITEM_PRODTYPE_FK")
//	private ProductTypeBean productTypeBean;

	@MapsId("OrdPK")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({ @JoinColumn(name = "category", referencedColumnName = "category"),
			@JoinColumn(name = "ordId", referencedColumnName = "ordId"), })
	private OrdBean ordBean;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BUYTITEM_PROMOTEID_FK", referencedColumnName = "promoteId")
	private PromotionBean promotionBean;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BUYTITEM_PRODID_FK")
	private ProductBean productBean;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUYITEM_COMMENTID_FK")
	private CommentBean commentBean;

	public BuyItemBean() {
	}

	public BuyItemBean(Integer prodQTY, Double itemSum, PromotionBean promotionBean, ProductBean productBean) {
		this.prodQTY = prodQTY;
		this.itemSum = itemSum;
		this.promotionBean = promotionBean;
		this.productBean = productBean;

	}

	public BuyItemPK getBuyItemPK() {
		return buyItemPK;
	}

	public void setBuyItemPK(BuyItemPK buyItemPK) {
		this.buyItemPK = buyItemPK;
	}

	public Integer getProdQTY() {
		return prodQTY;
	}

	public void setProdQTY(Integer prodQTY) {
		this.prodQTY = prodQTY;
	}

	public Double getItemSum() {
		return itemSum;
	}

	public void setItemSum(Double itemSum) {
		this.itemSum = itemSum;
	}

////	雙向多對一productTypeBean之getter、setter 開始
//	public ProductTypeBean getProductTypeBean() {
//		return productTypeBean;
//	}
//
//	public void setProductTypeBean(ProductTypeBean productTypeBean) {
//		this.productTypeBean = productTypeBean;
//	}
////	雙向多對一productTypeBean之getter、setter 結束

	public OrdBean getOrdBean() {
		return ordBean;
	}

	public void setOrdBean(OrdBean ordBean) {
		this.ordBean = ordBean;
	}

	public PromotionBean getPromotionBean() {
		return promotionBean;
	}

	public void setPromotionBean(PromotionBean promotionBean) {
		this.promotionBean = promotionBean;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public CommentBean getCommentBean() {
		return commentBean;
	}

	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyItemPK == null) ? 0 : buyItemPK.hashCode());
		result = prime * result + ((itemSum == null) ? 0 : itemSum.hashCode());
		result = prime * result + ((prodQTY == null) ? 0 : prodQTY.hashCode());
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
		BuyItemBean other = (BuyItemBean) obj;
		if (buyItemPK == null) {
			if (other.buyItemPK != null)
				return false;
		} else if (!buyItemPK.equals(other.buyItemPK))
			return false;
		if (itemSum == null) {
			if (other.itemSum != null)
				return false;
		} else if (!itemSum.equals(other.itemSum))
			return false;
		if (prodQTY == null) {
			if (other.prodQTY != null)
				return false;
		} else if (!prodQTY.equals(other.prodQTY))
			return false;
		return true;
	}

}
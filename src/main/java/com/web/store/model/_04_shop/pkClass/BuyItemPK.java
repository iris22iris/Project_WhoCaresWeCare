package com.web.store.model._04_shop.pkClass;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.web.store.model._06_order.pkClass.OrdPK;

@Embeddable
public class BuyItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdPK ordPK;
	private Integer prodSerialNum;

	public BuyItemPK() {
	}

	public BuyItemPK(OrdPK ordPK, Integer prodSerialNum) {
		this.ordPK = ordPK;
		this.prodSerialNum = prodSerialNum;
	}

	public OrdPK getOrdPK() {
		return ordPK;
	}

	public void setOrdPK(OrdPK ordPK) {
		this.ordPK = ordPK;
	}

	public Integer getProdSerialNum() {
		return prodSerialNum;
	}

	public void setProdSerialNum(Integer prodSerialNum) {
		this.prodSerialNum = prodSerialNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ordPK == null) ? 0 : ordPK.hashCode());
		result = prime * result + ((prodSerialNum == null) ? 0 : prodSerialNum.hashCode());
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
		BuyItemPK other = (BuyItemPK) obj;
		if (ordPK == null) {
			if (other.ordPK != null)
				return false;
		} else if (!ordPK.equals(other.ordPK))
			return false;
		if (prodSerialNum == null) {
			if (other.prodSerialNum != null)
				return false;
		} else if (!prodSerialNum.equals(other.prodSerialNum))
			return false;
		return true;
	}

}
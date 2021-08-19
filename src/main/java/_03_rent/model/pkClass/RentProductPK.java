package _03_rent.model.pkClass;

import java.io.Serializable;

public class RentProductPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private String classify;
	private Integer prodId;

	public RentProductPK() {
	}

	public RentProductPK(String classify, Integer prodId) {
		super();
		this.classify = classify;
		this.prodId = prodId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classify == null) ? 0 : classify.hashCode());
		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
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
		RentProductPK other = (RentProductPK) obj;
		if (classify == null) {
			if (other.classify != null)
				return false;
		} else if (!classify.equals(other.classify))
			return false;
		if (prodId == null) {
			if (other.prodId != null)
				return false;
		} else if (!prodId.equals(other.prodId))
			return false;
		return true;
	}

}
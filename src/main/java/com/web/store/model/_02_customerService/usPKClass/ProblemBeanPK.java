package com.web.store.model._02_customerService.usPKClass;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProblemBeanPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer usId;

	public ProblemBeanPK() {
	}

	public ProblemBeanPK(Integer usId) {
	this.usId = usId;
	}
	
	
	

	public Integer getUsId() {
		return usId;
	}

	public void setUsId(Integer usId) {
		this.usId = usId;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usId == null) ? 0 : usId.hashCode());
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
		ProblemBeanPK other = (ProblemBeanPK) obj;
		if (usId == null) {
			if (other.usId != null)
				return false;
		} else if (!usId.equals(other.usId))
			return false;
		return true;
	}

}
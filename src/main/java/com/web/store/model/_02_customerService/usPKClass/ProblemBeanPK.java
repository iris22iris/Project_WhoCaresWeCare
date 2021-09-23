package com.web.store.model._02_customerService.usPKClass;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Embeddable
public class ProblemBeanPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer replyId;

	public ProblemBeanPK() {
	}

	public ProblemBeanPK(Integer replyId) {
	this.replyId = replyId;
	}
	
	
	

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((replyId == null) ? 0 : replyId.hashCode());
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
		if (replyId == null) {
			if (other.replyId != null)
				return false;
		} else if (!replyId.equals(other.replyId))
			return false;
		return true;
	}

}
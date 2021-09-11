package com.web.store.model._02_customerService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProblemSelect")

public class ProblemSelectBean {

	Integer id;
	String problemType;
	String qroupPb;
	String sortPb;
	
	public ProblemSelectBean() {
		super();
	}
	
	@Column(name = "id", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	
	
	public String getQroupPb() {
		return qroupPb;
	}

	public void setQroupPb(String qroupPb) {
		this.qroupPb = qroupPb;
	}

	public String getSortPb() {
		return sortPb;
	}

	public void setSortPb(String sortPb) {
		this.sortPb = sortPb;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProblemSelect [id=");
		builder.append(id);
		builder.append(", problemType=");
		builder.append(problemType);
		builder.append(", qroupPb=");
		builder.append(qroupPb);
		builder.append(", sortPb=");
		builder.append(sortPb);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
}

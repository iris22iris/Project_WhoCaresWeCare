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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProblemSelect [id=");
		builder.append(id);
		builder.append(", problemType=");
		builder.append(problemType);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
	
}

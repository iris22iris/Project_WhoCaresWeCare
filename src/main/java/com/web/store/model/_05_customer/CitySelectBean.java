package com.web.store.model._05_customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CitySelect")
public class CitySelectBean {
	Integer id;
	String groupCity;
	String sortCity;
	String city;

	public CitySelectBean() {
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

	public String getGroupCity() {
		return groupCity;
	}

	public void setGroupCity(String groupCity) {
		this.groupCity = groupCity;
	}

	public String getSortCity() {
		return sortCity;
	}

	public void setSortCity(String sortCity) {
		this.sortCity = sortCity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CitySelect [id=");
		builder.append(id);
		builder.append(", groupCity=");
		builder.append(groupCity);
		builder.append(", sortCity=");
		builder.append(sortCity);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}

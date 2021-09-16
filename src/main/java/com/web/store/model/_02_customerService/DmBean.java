package com.web.store.model._02_customerService;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dm")
public class DmBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dmId;
	private String dmName;
	private String category;
	private Blob dmImage;
	private String mimeType;
	@Column(columnDefinition = "datetime")	
	private Timestamp addDate;
	private String dmdate;
	
	public DmBean() {
		super();
	}

	public DmBean(Integer dmId, String dmName, String category, Blob dmImage, String mimeType, Timestamp addDate,
			String dmdate) {
		super();
		this.dmId = dmId;
		this.dmName = dmName;
		this.category = category;
		this.dmImage = dmImage;
		this.mimeType = mimeType;
		this.addDate = addDate;
		this.dmdate = dmdate;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Blob getDmImage() {
		return dmImage;
	}

	public void setDmImage(Blob dmImage) {
		this.dmImage = dmImage;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public String getDmdate() {
		return dmdate;
	}

	public void setDmdate(String dmdate) {
		this.dmdate = dmdate;
	}
	
}

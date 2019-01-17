package org.penpi.subsys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.penpi.subsys.Global;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "SYS_SMALL_PICT_SETUP")
@Document(indexName = Global.INDEX_NAME, type = "smallPictSetup")
public class SmallPictSetup implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String SMALL_PICT_SETUP_ID = "smallPictSetupId";
	public static final String BUSINESS_CLASS_NM = "businessClassNm";
	public static final String BUSINESS_FIELD_NM = "businessFieldNm";
	public static final String SMALL_PICT_SPEC = "smallPictSpec";

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SMALL_PICT_SETUP_ID")
	private java.lang.Integer smallPictSetupId;

	@Column(name = "BUSINESS_CLASS_NM")
	private java.lang.String businessClassNm;

	@Column(name = "BUSINESS_FIELD_NM")
	private java.lang.String businessFieldNm;

	@Column(name = "SMALL_PICT_SPEC")
	private java.lang.String smallPictSpec;

	public SmallPictSetup() {
	}

	public java.lang.Integer getSmallPictSetupId() {
		return smallPictSetupId;
	}

	public void setSmallPictSetupId(java.lang.Integer smallPictSetupId) {
		this.smallPictSetupId = smallPictSetupId;
	}

	public java.lang.String getBusinessClassNm() {
		return businessClassNm;
	}

	public void setBusinessClassNm(java.lang.String businessClassNm) {
		this.businessClassNm = businessClassNm;
	}

	public java.lang.String getBusinessFieldNm() {
		return businessFieldNm;
	}

	public void setBusinessFieldNm(java.lang.String businessFieldNm) {
		this.businessFieldNm = businessFieldNm;
	}

	public java.lang.String getSmallPictSpec() {
		return smallPictSpec;
	}

	public void setSmallPictSpec(java.lang.String smallPictSpec) {
		this.smallPictSpec = smallPictSpec;
	}

}

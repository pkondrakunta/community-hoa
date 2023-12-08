package com.projects.communityhoa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "fees")
public class Fee {
	@Id
	@Column(name="fee_name")
	String feeName;
	@Column(name="fee_value")
	Double feeValue;
	@Column(name="fee_type")
	String feeType;
	
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public Double getFeeValue() {
		return feeValue;
	}
	public void setFeeValue(Double feeValue) {
		this.feeValue = feeValue;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	
	
}

package com.projects.communityhoa.model;

public class SubscriptionPlan {
	String unitType;
	Float waterMonthlyFee;
	Float trashMonthlyFee;
	Float adminMonthlyFee;
	Float lateDailyFee;
	
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public Float getWaterMonthlyFee() {
		return waterMonthlyFee;
	}
	public void setWaterMonthlyFee(Float waterMonthlyFee) {
		this.waterMonthlyFee = waterMonthlyFee;
	}
	public Float getTrashMonthlyFee() {
		return trashMonthlyFee;
	}
	public void setTrashMonthlyFee(Float trashMonthlyFee) {
		this.trashMonthlyFee = trashMonthlyFee;
	}
	public Float getAdminMonthlyFee() {
		return adminMonthlyFee;
	}
	public void setAdminMonthlyFee(Float adminMonthlyFee) {
		this.adminMonthlyFee = adminMonthlyFee;
	}
	public Float getLateDailyFee() {
		return lateDailyFee;
	}
	public void setLateDailyFee(Float lateDailyFee) {
		this.lateDailyFee = lateDailyFee;
	}
	
	
}

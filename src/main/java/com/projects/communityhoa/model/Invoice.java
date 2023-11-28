package com.projects.communityhoa.model;

import java.util.Date;

public class Invoice {
	String invoiceID;
	String memberID;
	Date date;
	Float waterFee;
	Float trashFee;
	Float adminFee;
	Float lateFee;
	
	public String getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(Float waterFee) {
		this.waterFee = waterFee;
	}
	public Float getTrashFee() {
		return trashFee;
	}
	public void setTrashFee(Float trashFee) {
		this.trashFee = trashFee;
	}
	public Float getLateFee() {
		return lateFee;
	}
	public void setLateFee(Float lateFee) {
		this.lateFee = lateFee;
	}


}

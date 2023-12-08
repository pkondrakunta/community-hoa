/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;

@Component
@Entity
@Table(name = "invoices")
public class Invoice {
	@Id
	@Column(name="invoice_id")
    @GeneratedValue
	String invoiceID;
	@Column(name="member_id")
	String memberID;
	@Column(name="invoice_date")
	LocalDateTime date;
	@Column(name="water_fee")
	Double waterFee;
	@Column(name="trash_fee")
	Double trashFee;
	@Column(name="late_fee")
	Double lateFee;
	@Column(name="request_fee")
	Double additionalRequestFee;
	@Column(name="total")
	Double total;

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
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Double getWaterFee() {
		return waterFee;
	}
	public void setWaterFee(Double waterFee) {
		this.waterFee = waterFee;
	}
	public Double getTrashFee() {
		return trashFee;
	}
	public void setTrashFee(Double trashFee) {
		this.trashFee = trashFee;
	}
	public Double getLateFee() {
		return lateFee;
	}
	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}
	public Double getAdditionalRequestFee() {
		return additionalRequestFee;
	}
	public void setAdditionalRequestFee(Double additionalRequestFee) {
		this.additionalRequestFee = additionalRequestFee;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}

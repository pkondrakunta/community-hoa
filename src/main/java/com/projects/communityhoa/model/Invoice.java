/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;

@Component
@Entity
@Table(name = "invoices")
public class Invoice {
	@Id
	@Column(name="INVOICE_ID")
	String invoiceID;
	@Column(name="MEMBER_ID")
	String memberID;
	@Column(name="DATE")
	LocalDateTime date;
	@Column(name="WATER")
	Double water;
	@Column(name="TRASH")
	Double trash;	
	@Column(name="SNOW_REMOVAL")
	Double snowRemoval;
	@Column(name="LAWN_MOWING")
	Double lawnMowing;
	@Column(name="LANDSCAPING")
	Double landscaping;
	@Column(name="TOTAL")
	Double total;
	@Column(name="NEW_EXPIRY")
	LocalDate newExpiry;
	
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
	
	public Double getWater() {
		return water;
	}
	public void setWater(Double water) {
		this.water = water;
	}
	public Double getTrash() {
		return trash;
	}
	public void setTrash(Double trash) {
		this.trash = trash;
	}
	public Double getSnowRemoval() {
		return snowRemoval;
	}
	public void setSnowRemoval(Double snowRemoval) {
		this.snowRemoval = snowRemoval;
	}
	public Double getLawnMowing() {
		return lawnMowing;
	}
	public void setLawnMowing(Double lawnMowing) {
		this.lawnMowing = lawnMowing;
	}
	public Double getLandscaping() {
		return landscaping;
	}
	public void setLandscaping(Double landscaping) {
		this.landscaping = landscaping;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public LocalDate getNewExpiry() {
		return newExpiry;
	}
	public void setNewExpiry(LocalDate newExpiry) {
		this.newExpiry = newExpiry;
	}
	

}

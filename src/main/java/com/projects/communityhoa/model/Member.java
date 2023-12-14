/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Component
@Entity
@Table(name = "members")
public class Member {
	//Default annotation is Basic for type
	@Id
	@Column(name="MEMBER_ID")
	private String memberID;
	@Column(name="FIRST")
	private String firstName;
	@Column(name="LAST")
	private String lastName;
	private String unit;
	@Column(name="UNIT_TYPE")
	private String unitType;
	private String address;
	private String email;
	private String phone;
	@Column(name="PLAN")
	private String subscriptionPlan;
	@Column(name="EXPIRY")
	private LocalDate subscriptionExpiry;
	@Column(name="LAST_PAID")
	private LocalDateTime lastPaid;
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}
	public void setSubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}
	public LocalDate getSubscriptionExpiry() {
		return subscriptionExpiry;
	}
	public void setSubscriptionExpiry(LocalDate subscriptionExpiry) {
		this.subscriptionExpiry = subscriptionExpiry;
	}
	public LocalDateTime getLastPaid() {
		return lastPaid;
	}
	public void setLastPaid(LocalDateTime lastPaid) {
		this.lastPaid = lastPaid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}



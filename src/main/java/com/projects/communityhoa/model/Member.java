/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.model;

import java.time.LocalDateTime;

public class Member {
	String memberID;
	String firstName;
	String lastName;
	String unit;
	String unitType;
	String address;
	String email;
	String phone;
	String subscriptionPlan;
	LocalDateTime subscriptionExpiry;
	LocalDateTime lastPaid;
	
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
	public LocalDateTime getSubscriptionExpiry() {
		return subscriptionExpiry;
	}
	public void setSubscriptionExpiry(LocalDateTime subscriptionExpiry) {
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



/**
 *
 * @author Pragnya
 */

package com.projects.communityhoa.model;

import java.util.Date;

public class Member {
	String memberID;
	String firstName;
	String lastName;
	String unit;
	String unitType;
	Integer phone;
	Boolean yearly;
	Date subscriptionExpiry;
	Date lastPaid;
	
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
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public Boolean getYearly() {
		return yearly;
	}
	public void setYearly(Boolean yearly) {
		this.yearly = yearly;
	}
	public Date getSubscriptionExpiry() {
		return subscriptionExpiry;
	}
	public void setSubscriptionExpiry(Date subscriptionExpiry) {
		this.subscriptionExpiry = subscriptionExpiry;
	}
	public Date getLastPaid() {
		return lastPaid;
	}
	public void setLastPaid(Date lastPaid) {
		this.lastPaid = lastPaid;
	}

}



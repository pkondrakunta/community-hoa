/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Component
@Entity
@Table(name = "members")
public class Member {
	//Default annotation is Basic for type
	@Id
	@Column(name="member_id")
	private String memberID;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String unit;
	@Column(name="unit_type")
	private String unitType;
	private String address;
	private String email;
	private String phone;
	@Column(name="subscription_plan")
	private String subscriptionPlan;
	@Column(name="subscription_expiry")
	private LocalDateTime subscriptionExpiry;
	@Column(name="last_paid")
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



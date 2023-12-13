package com.projects.communityhoa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "MEMBER_ID")
    private String memberID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUser_type(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
    
    
}

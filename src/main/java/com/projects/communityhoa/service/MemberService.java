/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import com.projects.communityhoa.CommunityHoaApplication;
import com.projects.communityhoa.model.Member;

@Component
public class MemberService {

	Connection conn;
	private long creationTimeMillis;

	public MemberService() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_hoa", "csye_user",
					"csye6220");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public Member addMember(Member member) {
		
		Member addedMember = member;
		String plan = member.getSubscriptionPlan();
		
		String memberID = generateNewMemberId(member);
		
		addedMember.setMemberID(null);
		addedMember.setLastPaid(LocalDateTime.now());
		
		if (plan == "Annually"){
			addedMember.setSubscriptionExpiry(LocalDateTime.now().plusYears(1));
		}
		else {
			addedMember.setSubscriptionExpiry(LocalDateTime.now().plusMonths(1));
		}

		return addedMember;
	}

	private static String generateNewMemberId(Member member) {
	    String creationTimeMillis_4ID = ""+System.currentTimeMillis()/1000;
	    String initials = member.getFirstName().substring(0,1).toUpperCase().concat(member.getLastName().substring(0,1).toUpperCase());
	    
		return creationTimeMillis_4ID.concat(initials);
	}

	public List<Member> searchMembers(String search_text_with_wildcard) {

		try {
			String query = "SELECT * FROM members WHERE member_id LIKE ? OR first_name LIKE ? OR last_name LIKE ?";
			PreparedStatement statement = conn.prepareStatement(query);
			
//			System.out.println(search_text_with_wildcard);
			statement.setString(1, search_text_with_wildcard);
			statement.setString(2, search_text_with_wildcard);
			statement.setString(3, search_text_with_wildcard);


			ResultSet resultSet = statement.executeQuery();

			// Loop through the ResultSet
			List<Member> memberList = new ArrayList<Member>();

			while (resultSet.next()) {

				Member memObj = new Member();

				memObj.setMemberID(resultSet.getString("member_id"));
				memObj.setFirstName(resultSet.getString("first_name"));
				memObj.setLastName(resultSet.getString("last_name"));
				memObj.setUnit(resultSet.getString("unit"));
				memObj.setUnitType(resultSet.getString("unit_type"));
				memObj.setPhone(resultSet.getString("phone"));
				memObj.setSubscriptionPlan(resultSet.getString("subscription_plan"));
				memObj.setSubscriptionExpiry(resultSet.getTimestamp("subscription_expiry").toLocalDateTime());
				memObj.setLastPaid(resultSet.getTimestamp("last_paid").toLocalDateTime());
				memObj.setEmail(resultSet.getString("email"));
				memObj.setAddress(resultSet.getString("address"));

				memberList.add(memObj);
			}

			return memberList;

		} catch (SQLException ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
			return new ArrayList<Member>();
		}
	}

	public List<Member> getAllMembers() {

		try {
			String query = "SELECT * FROM members";
			PreparedStatement statement = conn.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery(query);

			// Loop through the ResultSet
			List<Member> memberList = new ArrayList<Member>();

			while (resultSet.next()) {

				Member memObj = new Member();

				memObj.setMemberID(resultSet.getString("member_id"));
				memObj.setFirstName(resultSet.getString("first_name"));
				memObj.setLastName(resultSet.getString("last_name"));
				memObj.setUnit(resultSet.getString("unit"));
				memObj.setUnitType(resultSet.getString("unit_type"));
				memObj.setPhone(resultSet.getString("phone"));
				memObj.setSubscriptionPlan(resultSet.getString("subscription_plan"));
				memObj.setSubscriptionExpiry(resultSet.getTimestamp("subscription_expiry").toLocalDateTime());
				memObj.setLastPaid(resultSet.getTimestamp("last_paid").toLocalDateTime());
				memObj.setEmail(resultSet.getString("email"));
				memObj.setAddress(resultSet.getString("address"));
				
				memberList.add(memObj);
			}

			return memberList;

		} catch (SQLException ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
			return new ArrayList<Member>();
		}
	}

	public Member getMemberfromID(String ID) {

		try {
			String query = "SELECT * FROM members WHERE member_id = ? LIMIT 1";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, ID);

			ResultSet resultSet = statement.executeQuery();
			
			Member memObj = new Member();

			if (resultSet.next()) {

				memObj.setMemberID(resultSet.getString("member_id"));
				memObj.setFirstName(resultSet.getString("first_name"));
				memObj.setLastName(resultSet.getString("last_name"));
				memObj.setUnit(resultSet.getString("unit"));
				memObj.setUnitType(resultSet.getString("unit_type"));
				memObj.setPhone(resultSet.getString("phone"));
				memObj.setSubscriptionPlan(resultSet.getString("subscription_plan"));
				memObj.setSubscriptionExpiry(resultSet.getTimestamp("subscription_expiry").toLocalDateTime());
				memObj.setLastPaid(resultSet.getTimestamp("last_paid").toLocalDateTime());
				memObj.setEmail(resultSet.getString("email"));
				memObj.setAddress(resultSet.getString("address"));
				
			}
			
			return memObj;


		} catch (SQLException ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
			return new Member();
		}
	}
	
//	public static void main(String[] args) {
//		
//		MemberService ms = new MemberService();
//		Member m = ms.getMemberfromID("00011");
//		System.out.println("Here's the member ID: " + m.getFirstName());
//		
//	    System.out.println(generateNewMemberId(m));
//	}

}

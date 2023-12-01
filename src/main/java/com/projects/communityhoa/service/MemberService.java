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

	public List<Member> searchMembers(String search_text) {

		try {
			String query = "SELECT * FROM members WHERE '%?%' IN member_id";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, search_text);
			statement.setString(2, search_text);

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
//		Member m = ms.getMemberfromID("008");
//		System.out.println("Here's the member ID: " + m.getFirstName());
//	}

}

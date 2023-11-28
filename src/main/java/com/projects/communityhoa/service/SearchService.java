package com.projects.communityhoa.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.projects.communityhoa.model.Invoice;

public class SearchService {

	Connection conn;

	public SearchService() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_hoa", "csye_user",
					"csye6220");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(SearchService.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(SearchService.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public List<Invoice> searchInvoice(String search_text) {

		try {
			String query = "SELECT * FROM invoices WHERE '%?%' IN member_id OR '%?%' IN invoice_id";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, search_text);
			statement.setString(2, search_text);

			ResultSet resultSet = statement.executeQuery(query);

			// Loop through the ResultSet
			List<Invoice> invoiceList = new ArrayList<Invoice>();

			while (resultSet.next()) {

				 Invoice invObj = new Invoice();

				 invObj.setInvoiceID(resultSet.getString("invoice_id"));
				 invObj.setMemberID(resultSet.getString("member_id"));
				 invObj.setDate(resultSet.getDate("invoice_date"));
				 invObj.setWaterFee(resultSet.getFloat("water_fee"));
				 invObj.setTrashFee(resultSet.getFloat("trash_fee"));
				 invObj.setLateFee(resultSet.getFloat("late_fee"));

				 invoiceList.add(invObj);
			}

			return invoiceList;

		} catch (SQLException ex) {
			Logger.getLogger(SearchService.class.getName()).log(Level.SEVERE, null, ex);
			return new ArrayList<Invoice>();
		}
	}

}

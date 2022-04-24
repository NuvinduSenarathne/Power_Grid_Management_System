package com.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.models.PaymentModel;

public class PaymentService {

private Connection connect() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pgms", "root", "");
			System.out.println("Connection successfully established");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}

public ArrayList<PaymentModel> getPayments() {
	
	ArrayList<PaymentModel> data = new ArrayList<PaymentModel>();

	try {
		Connection con = connect();
		if (con == null) {
			System.out.println("Error while connecting to the database for reading.");
		}
		
		String query = "SELECT * FROM usage";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery();
		
		while(rs.next()) {
			PaymentModel paymentModel = new PaymentModel();
			
			paymentModel.setUsageID(rs.getInt("UsageID"));
			paymentModel.setRefNo(rs.getString("RefNo"));
			paymentModel.setUnits(rs.getInt("Units"));
			paymentModel.setMonth(rs.getString("Month"));
			paymentModel.setAmount(rs.getInt("Amount"));
			
			data.add(paymentModel);
		}
		
		con.close();
		
	}
	catch (Exception e) {
		System.out.println("Error while reading the Payments!");
		System.err.println(e.getMessage());
	}
	
	return data;
	
}

public ArrayList<PaymentModel> getPaymentById(int UsageID) {
	
	ArrayList<PaymentModel> data = new ArrayList<PaymentModel>();

	try {
		Connection con = connect();
		if (con == null) {
			System.out.println("Error while connecting to the database for reading.");
		}
		
		String query = "SELECT * FROM usage WHERE UsageID = ?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1, UsageID);
		ResultSet rs = preparedStmt.executeQuery();
		
		while(rs.next()) {
PaymentModel paymentModel = new PaymentModel();
			
			paymentModel.setUsageID(rs.getInt("UsageID"));
			paymentModel.setRefNo(rs.getString("RefNo"));
			paymentModel.setUnits(rs.getInt("Units"));
			paymentModel.setMonth(rs.getString("Month"));
			paymentModel.setAmount(rs.getInt("Amount"));
			
			data.add(paymentModel);
		}
		
		con.close();
		
	}
	catch (Exception e) {
		System.out.println("Error while reading the Payment!");
		System.err.println(e.getMessage());
	}
	
	return data;
	
}

	
	
}

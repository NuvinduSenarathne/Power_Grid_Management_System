package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.models.UsageAdminModel;

public class UsageService {
	
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

public ArrayList<UsageAdminModel> getUsages() {
	
	ArrayList<UsageAdminModel> data = new ArrayList<UsageAdminModel>();

	try {
		Connection con = connect();
		if (con == null) {
			System.out.println("Error while connecting to the database for reading.");
		}
		
		String query = "SELECT * FROM usages";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		ResultSet rs = preparedStmt.executeQuery();
		
		while(rs.next()) {
			UsageAdminModel usageAdminModel = new UsageAdminModel();
			usageAdminModel.setUsageID(rs.getInt("UsageID"));
			usageAdminModel.setRefNo(rs.getString("RefNo"));
			usageAdminModel.setUnits(rs.getInt("Units"));
			usageAdminModel.setMonth(rs.getString("Month"));
			usageAdminModel.setAmount(rs.getString("Amount"));
			//usageAdminModel.calculateAmount(rs.getInt("Units"));
			
			data.add(usageAdminModel);
		}
		
		con.close();
		
	}
	catch (Exception e) {
		System.out.println("Error while reading the Usage1!");
		System.err.println(e.getMessage());
	}
	
	return data;
	
}

public UsageAdminModel getUsageById(int UsageID) {
	
	//ArrayList<UsageAdminModel> data = new ArrayList<UsageAdminModel>();
	UsageAdminModel usageAdminModel = new UsageAdminModel();

	try {
		Connection con = connect();
		if (con == null) {
			System.out.println("Error while connecting to the database for reading.");
		}
		
		String query = "SELECT * FROM usages WHERE UsageID = ?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1, UsageID);
		ResultSet rs = preparedStmt.executeQuery();
		
		while(rs.next()) {
			//UsageAdminModel usageAdminModel = new UsageAdminModel();
			usageAdminModel.setUsageID(rs.getInt("UsageID"));
			usageAdminModel.setRefNo(rs.getString("RefNo"));
			usageAdminModel.setUnits(rs.getInt("Units"));
			usageAdminModel.setMonth(rs.getString("Month"));
			usageAdminModel.setAmount(rs.getString("Amount"));
			//usageAdminModel.calculateAmount(rs.getInt("Units"));
			
			//data.add(usageAdminModel);
		}
		
		con.close();
		
	}
	catch (Exception e) {
		System.out.println("Error while reading the Usage2!");
		System.err.println(e.getMessage());
	}
	
	return usageAdminModel;
	
}
	
	public String insertusage(String RefNo, String units, String month, String Amount) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "INSERT INTO usages(`RefNo`,`Units`,`Month`,`Amount`) VALUES(?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			//preparedStmt.setInt(1, UsageID);
			preparedStmt.setString(1, RefNo);
			preparedStmt.setString(2, units);
			preparedStmt.setString(3, month);
			preparedStmt.setString(4, Amount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the usage.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
public String updateusage(  int UsageID, String RefNo, String Units, String Month, String Amount) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "UPDATE usages SET RefNo = ?, Units = ?, Month = ?, Amount = ? WHERE UsageID = ?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(5, UsageID);
			preparedStmt.setString(1, RefNo);
			preparedStmt.setString(2, Units);
			preparedStmt.setString(3, Month);
			preparedStmt.setString(4, Amount);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e) {
			output = "Error while updating the Usage.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}

public String deleteusage(int UsageID) {
	
	String output = "";
	
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
		
		String query = "DELETE FROM usages WHERE UsageID = ?";
	
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1, UsageID);
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
	}
	catch (Exception e) {
		output = "Error while deleting the Usages.";
		System.err.println(e.getMessage());
	}
	
	return output;
	
}
	
	
}





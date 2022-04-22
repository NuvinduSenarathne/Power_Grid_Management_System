package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.models.AdminModel;

public class AdminService {
	
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
	
	public ArrayList<AdminModel> getAdministrators() {
		
		ArrayList<AdminModel> data = new ArrayList<AdminModel>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}
			
			String query = "SELECT * FROM administrator";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				AdminModel adminModel = new AdminModel();
				adminModel.setAdminID(rs.getInt("adminID"));
				adminModel.setFirstName(rs.getString("firstName"));
				adminModel.setLastName(rs.getString("lastName"));
				adminModel.setNIC(rs.getString("NIC"));
				adminModel.setEmail(rs.getString("email"));
				adminModel.setMobile(rs.getString("mobile"));
				adminModel.setDepartment(rs.getString("department"));
				data.add(adminModel);
			}
			
			con.close();
			
		}
		catch (Exception e) {
			System.out.println("Error while reading the Administrators!");
			System.err.println(e.getMessage());
		}
		
		return data;
		
	}
	
	public ArrayList<AdminModel> getAdministratorById(int adminID) {
		
		ArrayList<AdminModel> data = new ArrayList<AdminModel>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}
			
			String query = "SELECT * FROM administrator WHERE adminID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, adminID);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				AdminModel adminModel = new AdminModel();
				adminModel.setAdminID(rs.getInt("adminID"));
				adminModel.setFirstName(rs.getString("firstName"));
				adminModel.setLastName(rs.getString("lastName"));
				adminModel.setNIC(rs.getString("NIC"));
				adminModel.setEmail(rs.getString("email"));
				adminModel.setMobile(rs.getString("mobile"));
				adminModel.setDepartment(rs.getString("department"));
				data.add(adminModel);
			}
			
			con.close();
			
		}
		catch (Exception e) {
			System.out.println("Error while reading the Administrator!");
			System.err.println(e.getMessage());
		}
		
		return data;
		
	}
	
	public String insertAdministrator(String firstName, String lastName, String NIC, String email, String mobile, String department, String password) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "INSERT INTO administrator(`firstName`,`lastName`,`NIC`,`email`,`mobile`,`department`,`password`) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, NIC);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, mobile);
			preparedStmt.setString(6, department);
			preparedStmt.setString(7, password);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the administrator.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String updateAdministrator(String adminID, String firstName, String lastName, String NIC, String email, String mobile, String department) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "UPDATE administrator SET firstName = ?, lastName = ?, NIC = ?, email = ?, mobile = ?, department = ? WHERE adminID =?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, NIC);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, mobile);
			preparedStmt.setString(6, department);
			preparedStmt.setInt(7, Integer.parseInt(adminID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e) {
			output = "Error while updating the Administrator.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String deleteAdministrator(int adminID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "DELETE FROM administrator WHERE adminID = ?";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, adminID);
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e) {
			output = "Error while deleting the Administrator.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}

}

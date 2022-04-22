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
			
			String query = "SELECT * FROM administrators";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				AdminModel adminModel = new AdminModel();
				adminModel.setUserID(rs.getInt("userID"));
				adminModel.setFirstName(rs.getString("firstName"));
				adminModel.setLastName(rs.getString("lastName"));
				adminModel.setEmail(rs.getString("email"));
				adminModel.setMobile(rs.getString("mobile"));
				adminModel.setServiceNo(rs.getString("serviceNo"));
				adminModel.setDepartment(rs.getString("department"));
				adminModel.setPosition(rs.getString("position"));
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
	
	public ArrayList<AdminModel> getAdministratorById(int userID) {
		
		ArrayList<AdminModel> data = new ArrayList<AdminModel>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}
			
			String query = "SELECT * FROM administrators WHERE userID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, userID);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				AdminModel adminModel = new AdminModel();
				adminModel.setUserID(rs.getInt("userID"));
				adminModel.setFirstName(rs.getString("firstName"));
				adminModel.setLastName(rs.getString("lastName"));
				adminModel.setEmail(rs.getString("email"));
				adminModel.setMobile(rs.getString("mobile"));
				adminModel.setServiceNo(rs.getString("serviceNo"));
				adminModel.setDepartment(rs.getString("department"));
				adminModel.setPosition(rs.getString("position"));
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
	
	public String insertAdministrator(String firstName, String lastName, String email, String mobile, String password, String serviceNo, String department, String position) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "INSERT INTO administrators(`firstName`,`lastName`,`email`,`mobile`,`password`,`serviceNo`,`department`,`position`) VALUES(?, ?, ?, ?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, mobile);
			preparedStmt.setString(5, password);
			preparedStmt.setString(6, serviceNo);
			preparedStmt.setString(7, department);
			preparedStmt.setString(8, position);
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
	
	public String updateAdministrator(String userID, String firstName, String lastName, String email, String mobile, String serviceNo, String department, String position) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "UPDATE administrators SET firstName = ?, lastName = ?, email = ?, mobile = ?, serviceNo = ?, department = ?, position = ? WHERE userID =?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, mobile);
			preparedStmt.setString(5, serviceNo);
			preparedStmt.setString(6, department);
			preparedStmt.setString(7, position);
			preparedStmt.setInt(8, Integer.parseInt(userID));
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
	
	public String deleteAdministrator(int userID) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = "DELETE FROM administrators WHERE userID = ?";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, userID);
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

package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.models.CustomerModel;

public class CustomerService {
	
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
	
	public ArrayList<CustomerModel> getCustomers() {
		
		ArrayList<CustomerModel> data = new ArrayList<CustomerModel>();

		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}
			
			String query = "SELECT * FROM customer";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				CustomerModel customerModel = new CustomerModel();
				customerModel.setUserID(rs.getInt("userID"));
				customerModel.setFirstName(rs.getString("firstName"));
				customerModel.setLastName(rs.getString("lastName"));
				customerModel.setEmail(rs.getString("email"));
				customerModel.setMobile(rs.getString("mobile"));
				customerModel.setAddress(rs.getString("address"));
				customerModel.setPostalCode(rs.getString("postalCode"));
				
				data.add(customerModel);
			}
			
			con.close();
			
		}
		catch (Exception e) {
			System.out.println("Error while reading the Customer!");
			System.err.println(e.getMessage());
		}
		
		return data;
		
	}

	
	
		public CustomerModel getCustomerById(int userID) {
				
			CustomerModel customerModel = new CustomerModel();
		
				try {
					Connection con = connect();
					if (con == null) {
						System.out.println("Error while connecting to the database for reading.");
					}
					
					String query = "SELECT * FROM customer WHERE userID = ?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt(1, userID);
					ResultSet rs = preparedStmt.executeQuery();
					
					while(rs.next()) {
						customerModel.setUserID(rs.getInt("userID"));
						customerModel.setFirstName(rs.getString("firstName"));
						customerModel.setLastName(rs.getString("lastName"));
						customerModel.setEmail(rs.getString("email"));
						customerModel.setMobile(rs.getString("mobile"));
						customerModel.setAddress(rs.getString("address"));
						customerModel.setPostalCode(rs.getString("postalCode"));
						
					}
					
					con.close();
					
				}
				catch (Exception e) {
					System.out.println("Error while reading the CUstomer!");
					System.err.println(e.getMessage());
				}
				
				return customerModel;
				
			}
		
		
		
		public String insertCustomer(String firstName, String lastName, String email, String mobile, String password, String address, String postalCode) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = "INSERT INTO customer(`firstName`,`lastName`,`email`,`mobile`,`password`,`address`,`postalCode`) VALUES(?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values.
				
				preparedStmt.setString(1, firstName);
				preparedStmt.setString(2, lastName);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, mobile);
				preparedStmt.setString(5, password);
				preparedStmt.setString(6, address);
				preparedStmt.setString(7, postalCode);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e) {
				output = "Error while inserting the Customer!";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
		public String updateCustomer(String userID, String firstName, String lastName, String email, String mobile,  String address, String postalCode) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for Updating.";
				}
				// create a prepared statement
				String query = "UPDATE customer SET firstName = ?, lastName = ?, email = ?, mobile = ?, address=? , postalCode=? WHERE userID =?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, firstName);
				preparedStmt.setString(2, lastName);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, mobile);
				preparedStmt.setString(5, address);
				preparedStmt.setString(6, postalCode);
				
				
				preparedStmt.setInt(7, Integer.parseInt(userID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			}
			catch (Exception e) {
				output = "Error while updating the Customer.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
		public String deleteCustomer(int userID) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				
				String query = "DELETE FROM customer WHERE userID = ?";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, userID);
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e) {
				output = "Error while deleting the Customer.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
		public CustomerModel loginCustomer(String email, String password) {
			
			CustomerModel customerModel = new CustomerModel();

			try {
				Connection con = connect();
				if (con == null) {
					System.out.println("Error while connecting to the database for reading.");
				}
				
				String query = "SELECT * FROM customer WHERE email = ? AND password = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, email);
				preparedStmt.setString(2, password);
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next()) {
					customerModel.setUserID(rs.getInt("userID"));
					customerModel.setFirstName(rs.getString("firstName"));
					customerModel.setLastName(rs.getString("lastName"));
					customerModel.setEmail(rs.getString("email"));
					customerModel.setMobile(rs.getString("mobile"));
					customerModel.setAddress(rs.getString("address"));
					customerModel.setPostalCode(rs.getString("postalCode"));
					
				}
				
				con.close();
				
			}
			catch (Exception e) {
				System.out.println("Error while loging the System!");
				System.err.println(e.getMessage());
			}
			
			return customerModel;
			
		}
		
		public	CustomerModel changePasswordById(int userID) {
			
			CustomerModel customerModel = new CustomerModel();

			try {
				Connection con = connect();
				if (con == null) {
					System.out.println("Error while connecting to the database for reading.");
				}
				
				String query = "SELECT * FROM customer WHERE userID = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, userID);
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next()) {
					customerModel.setUserID(rs.getInt("userID"));
					customerModel.setPassword(rs.getString("password"));
				}
				
				con.close();
				
			}
			catch (Exception e) {
				System.out.println("Error while reading the Password!");
				System.err.println(e.getMessage());
			}
			
			return customerModel;
			
		}
		
		public String changePassword(String userID, String password) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// create a prepared statement
				String query = "UPDATE customer SET password = ? WHERE userID =?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, password);
				preparedStmt.setInt(2, Integer.parseInt(userID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Changed Password successfully";
			}
			catch (Exception e) {
				output = "Error while Changing the Password.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
}

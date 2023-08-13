package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminService {
	
	public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "pgms";
        String dbUsername = "root";
        String dbPassword = "";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
	
	//method to insert data
    public String insertAdmin(String firstName, String lastName, String email, String mobile,  String serviceNo, String department, String position) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO administrators(`firstName`,`lastName`,`email`,`mobile`,`serviceNo`,`department`,`position`) VALUES(?, ?, ?, ?, ?, ?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, firstName);
        	preparedStatement.setString(2, lastName);
        	preparedStatement.setString(3, email);
        	preparedStatement.setString(4, mobile);
        	preparedStatement.setString(5, serviceNo);
        	preparedStatement.setString(6, department);
			preparedStatement.setString(7, position);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();

			String newAdmins = readAllAdmins(); 
			Output = "{\"status\":\"success\", \"data\": \"" + newAdmins + "\"}";
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\": \"Failed to insert the Admin!\"}";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    public String updateAdmin(String userID, String firstName, String lastName, String email, String mobile, String serviceNo, String department, String position) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE administrators SET firstName = ?, lastName = ?, email = ?, mobile = ?, serviceNo = ?, department = ?, position = ? WHERE userID =?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, firstName);
        	preparedStatement.setString(2, lastName);
        	preparedStatement.setString(3, email);
        	preparedStatement.setString(4, mobile);
        	preparedStatement.setString(5, serviceNo);
        	preparedStatement.setString(6, department);
        	preparedStatement.setString(7, position);
        	preparedStatement.setInt(8, Integer.parseInt(userID));
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	String newAdmins = readAllAdmins(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newAdmins + "\"}";
        	
    	} catch(Exception e) {
    		Output = "{\"status\":\"error\", \"data\":\"Failed to update the Admin!\"}"; 
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    public String readAllAdmins() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM administrators";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table class='table table-striped'><tr><th>UserID</th>" 
        	+"<th>First Name</th><th>Last Name</th>"
        	+"<th>Email</th><th>Mobile</th>"
        	+"<th>Service No</th><th>Department</th>"
    		+ "<th>Position</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String userID = Integer.toString(resultSet.getInt("userID"));
        		String firstName = resultSet.getString("firstName");
        		String lastName = resultSet.getString("lastName");
        		String email = resultSet.getString("email");
        		String mobile = resultSet.getString("mobile");
        		String serviceNo = resultSet.getString("serviceNo");
        		String department = resultSet.getString("department");
        		String position = resultSet.getString("position");
        		
        		// Add a row into the HTML table
        		Output += "<tr><td>" + userID + "</td>"; 
        		Output += "<td>" + firstName + "</td>"; 
        		Output += "<td>" + lastName + "</td>"; 
        		Output += "<td>" + email + "</td>";
        		Output += "<td>" + mobile + "</td>"; 
        		Output += "<td>" + serviceNo + "</td>"; 
        		Output += "<td>" + department + "</td>";
        		Output += "<td>" + position + "</td>"; 
        		
        		// buttons
        		Output += "<td>"
						+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-userid='" + userID + "'>"
						+ "</td>" 
        				+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-userid='" + userID + "'>"
						+ "</td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the Admins";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
  //method to delete data
    public String deleteAdmin(String userID) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM administrators WHERE userID = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1, Integer.parseInt(userID));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	String newAdmins = readAllAdmins(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newAdmins + "\"}"; 
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\":\"Failed to delete the Admins.\"}";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }

}

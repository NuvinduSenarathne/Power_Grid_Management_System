package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.models.CardModel;

public class CardService {
	
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
	
	public ArrayList<CardModel> getCard() {
			
			ArrayList<CardModel> data = new ArrayList<CardModel>();
	
			try {
				Connection con = connect();
				if (con == null) {
					System.out.println("Error while connecting to the database for reading.");
				}
				
				String query = "SELECT * FROM card";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next()) {
					CardModel cardModel = new CardModel();
					
					cardModel.setCardID(rs.getInt("cardID"));
					cardModel.setCardNo(rs.getInt("cardNo"));
					cardModel.setCardType(rs.getString("cardType"));
					cardModel.setCvv(rs.getInt("cvv"));
					cardModel.setExpDate(rs.getString("expDate"));
					
					data.add(cardModel);
				}
				
				con.close();
				
			}
			catch (Exception e) {
				System.out.println("Error while reading the Card Details!");
				System.err.println(e.getMessage());
			}
			
			return data;
			
		}
	
	public ArrayList<CardModel> getCardById(int cardID) {
		
		ArrayList<CardModel> data = new ArrayList<CardModel>();
	
		try {
			Connection con = connect();
			if (con == null) {
				System.out.println("Error while connecting to the database for reading.");
			}
			
			String query = "SELECT * FROM card WHERE cardID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, cardID);
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				CardModel cardModel = new CardModel();
				cardModel.setCardID(rs.getInt("cardID"));
				cardModel.setCardNo(rs.getInt("cardNo"));
				cardModel.setCardType(rs.getString("cardType"));
				cardModel.setCvv(rs.getInt("cvv"));
				cardModel.setExpDate(rs.getString("expDate"));
				
				data.add(cardModel);
			}
			
			con.close();
			
		}
		catch (Exception e) {
			System.out.println("Error while reading the Card Details!");
			System.err.println(e.getMessage());
		}
		
		return data;
		
	}

	public String insertCard(int cardNo, String cardType, int cvv, String expDate ) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = "INSERT INTO card(`cardNo`,`cardType`,`cvv`,`expDate`) VALUES(?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, cardNo);
				preparedStmt.setString(2, cardType);
				preparedStmt.setInt(3, cvv);
				preparedStmt.setString(4, expDate);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e) {
				output = "Error while inserting the Card Details.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
	
	public String updateCard(int cardID,int cardNo, String cardType, int cvv, String expDate) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for update.";
			}
			// create a prepared statement
			String query = "UPDATE card SET cardNo = ?, cardType = ?, cvv = ?, expDate = ? WHERE cardID =?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, cardNo);
			preparedStmt.setString(2, cardType);
			preparedStmt.setInt(3, cvv);
			preparedStmt.setString(4, expDate);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e) {
			output = "Error while updating the Card Details.";
			System.err.println(e.getMessage());
		}
		
		return output;
	
	}
	
	public String deleteCard(int cardID) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				
				String query = "DELETE FROM card WHERE cardID = ?";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, cardID);
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e) {
				output = "Error while deleting the Card Details.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}

	
	

}

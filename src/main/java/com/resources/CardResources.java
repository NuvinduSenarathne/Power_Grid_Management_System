package com.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.services.CardService;



@Path("/card")
public class CardResources {

CardService cardService = new CardService();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCard() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCards = gson.toJson(cardService.getCards());
		return jsonCards;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCardById(@PathParam("id") int id) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCard = gson.toJson(cardService.getCardById(id));
		return jsonCard;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCard(String card) {
		
		JsonObject cardObject = new JsonParser().parse(card).getAsJsonObject();
		
		String cardNo = cardObject.get("cardNo").getAsString();
		String cardType = cardObject.get("cardType").getAsString();
		String cvv = cardObject.get("cvv").getAsString();
		String expDate = cardObject.get("expDate").getAsString();
		
		String output = cardService.insertCard(cardNo, cardType, cvv, expDate);
		
		return output;
		
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecard(String card) {
		
		JsonObject cardObject = new JsonParser().parse(card).getAsJsonObject();

		String cardID = cardObject.get("cardID").getAsString();
		String cardNo = cardObject.get("cardNo").getAsString();
		String cardType = cardObject.get("cardType").getAsString();
		String cvv = cardObject.get("cvv").getAsString();
		String expDate = cardObject.get("expDate").getAsString();
		
		
		String output = cardService.updateCard(cardID, cardNo, cardType, cvv, expDate);
		
		return output;
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCard(@PathParam("id") int id) {
		String output = cardService.deleteCard(id);
		return output;
	}
	
	
	
}

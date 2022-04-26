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

import com.services.UsageService;


@Path("/usage")

public class UsageResource {
	
	UsageService usageService = new UsageService();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsages() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonUsage = gson.toJson(usageService.getUsages());
		return jsonUsage;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsageById(@PathParam("id") int uid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonUsage = gson.toJson(usageService.getUsageById(uid));
		return jsonUsage;
	}

	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertusage(String usage) {
		
		JsonObject usageObject = new JsonParser().parse(usage).getAsJsonObject();
		//int UsageID = usageObject.get("UsageID").getAsInt();
		String RefNo = usageObject.get("RefNo").getAsString();
		String Units = usageObject.get("Units").getAsString();
		String Month = usageObject.get("Month").getAsString();
		String Amount = usageObject.get("Amount").getAsString();
		
		String output = usageService.insertusage(RefNo, Units, Month, Amount);
		
		return output;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateusage(String usage) {
		
		JsonObject usageObject = new JsonParser().parse(usage).getAsJsonObject();

		int UsageID = usageObject.get("UsageID").getAsInt();
		String RefNo = usageObject.get("RefNo").getAsString();
		String Units = usageObject.get("Units").getAsString();
		String Month = usageObject.get("Month").getAsString();
		String Amount = usageObject.get("Amount").getAsString();
		
		String output = usageService.updateusage(UsageID, RefNo, Units, Month, Amount);
		
		return output;
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteusage(@PathParam("id") int uid) {
		String output = usageService.deleteusage(uid);
		return output;	
	}
}

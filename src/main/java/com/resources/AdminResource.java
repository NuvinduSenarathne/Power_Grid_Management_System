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
import com.services.AdminService;

@Path("/admin")
public class AdminResource {
	
	AdminService adminService = new AdminService();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdministrators() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonAdmins = gson.toJson(adminService.getAdministrators());
		return jsonAdmins;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdministratorById(@PathParam("id") int id) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonAdmin = gson.toJson(adminService.getAdministratorById(id));
		return jsonAdmin;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdministrator(String admin) {
		
		JsonObject adminObject = new JsonParser().parse(admin).getAsJsonObject();
		String firstName = adminObject.get("firstName").getAsString();
		String lastName = adminObject.get("lastName").getAsString();
		String email = adminObject.get("email").getAsString();
		String mobile = adminObject.get("mobile").getAsString();
		String password = adminObject.get("password").getAsString();
		String serviceNo = adminObject.get("serviceNo").getAsString();
		String department = adminObject.get("department").getAsString();
		String position = adminObject.get("position").getAsString();
		String output = adminService.insertAdministrator(firstName, lastName, email, mobile, password, serviceNo, department, position);
		
		return output;
		
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdministrator(String admin) {
		
		JsonObject adminObject = new JsonParser().parse(admin).getAsJsonObject();

		String userID = adminObject.get("userID").getAsString();
		String firstName = adminObject.get("firstName").getAsString();
		String lastName = adminObject.get("lastName").getAsString();
		String email = adminObject.get("email").getAsString();
		String mobile = adminObject.get("mobile").getAsString();
		String serviceNo = adminObject.get("serviceNo").getAsString();
		String department = adminObject.get("department").getAsString();
		String position = adminObject.get("position").getAsString();
		String output = adminService.updateAdministrator(userID, firstName, lastName, email, mobile, serviceNo, department, position);
		
		return output;
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdministrator(@PathParam("id") int id) {
		String output = adminService.deleteAdministrator(id);
		return output;
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginAdministrator(String admin) {
		
		JsonObject adminObject = new JsonParser().parse(admin).getAsJsonObject();
		String serviceNo = adminObject.get("serviceNo").getAsString();
		String password = adminObject.get("password").getAsString();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonAdmin = gson.toJson(adminService.loginAdministrator(serviceNo, password));
		return jsonAdmin;
	}
	
	@PUT
	@Path("/changePwd")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String changePassword(String admin) {
		
		JsonObject adminObject = new JsonParser().parse(admin).getAsJsonObject();

		String userID = adminObject.get("userID").getAsString();
		String password = adminObject.get("password").getAsString();
		String output = adminService.changePassword(userID, password);
		
		return output;
		
	}

}

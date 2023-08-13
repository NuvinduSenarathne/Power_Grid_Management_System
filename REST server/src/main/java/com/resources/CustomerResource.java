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
import com.services.CustomerService;

@Path("/customer")
public class CustomerResource {
	
	CustomerService customerService = new CustomerService();
	
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomers() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCustomer = gson.toJson(customerService.getCustomers());
		return jsonCustomer;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomerById(@PathParam("id") int id) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCustomer = gson.toJson(customerService.getCustomerById(id));
		return jsonCustomer;
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(String customer) {
		
		JsonObject customerObject = new JsonParser().parse(customer).getAsJsonObject();
		
		String firstName = customerObject.get("firstName").getAsString();
		String lastName = customerObject.get("lastName").getAsString();
		String email = customerObject.get("email").getAsString();
		String mobile = customerObject.get("mobile").getAsString();
		String password = customerObject.get("password").getAsString();
		String address = customerObject.get("address").getAsString();
		String postalCode = customerObject.get("postalCode").getAsString();
		
		String output = customerService.insertCustomer(firstName, lastName, email, mobile, password,address, postalCode);
		
		return output;
		
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customer) {
		
		JsonObject customerObject = new JsonParser().parse(customer).getAsJsonObject();

		String userID = customerObject.get("userID").getAsString();
		String firstName = customerObject.get("firstName").getAsString();
		String lastName = customerObject.get("lastName").getAsString();
		String email = customerObject.get("email").getAsString();
		String mobile = customerObject.get("mobile").getAsString();
		String address = customerObject.get("address").getAsString();
		String postalCode = customerObject.get("postalCode").getAsString();
		
		String output = customerService.updateCustomer(userID, firstName, lastName, email, mobile,address,postalCode);
		
		return output;
		
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(@PathParam("id") int id) {
		String output = customerService.deleteCustomer(id);
		return output;
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String loginCustomer(String customer) {
		
		JsonObject customerObject = new JsonParser().parse(customer).getAsJsonObject();
		String email = customerObject.get("email").getAsString();
		String password = customerObject.get("password").getAsString();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCustomer = gson.toJson(customerService.loginCustomer(email, password));
		return jsonCustomer;
	}
	
	
	
	@PUT
	@Path("/changePassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String changePassword(String customer) {
		
		JsonObject customerObject = new JsonParser().parse(customer).getAsJsonObject();

		String userID = customerObject.get("userID").getAsString();
		String password = customerObject.get("password").getAsString();
		String output = customerService.changePassword(userID, password);
		
		return output;
		
	}
	
	@GET
	@Path("changePassword/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String changePasswordById(@PathParam("id") int id) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jsonCustomer = gson.toJson(customerService.changePasswordById(id));
		return jsonCustomer;
	}
	
	
	
	

}

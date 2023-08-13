package com.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;

/**
 * Servlet implementation class AdminAPI
 */
@WebServlet("/AdminAPI")
public class AdminAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminService adminService = new AdminService();
    
  //convert request parameters to a map
  	private static Map getParasMap(HttpServletRequest request) {
  		Map<String, String> map = new HashMap<String, String>();

  		try {
  			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
  			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
  			scanner.close();

  			String[] params = queryString.split("&");

  			for (String param : params) {
  				String[] p = param.split("=");
  				map.put(p[0], p[1]);
  			}
  		} catch (Exception e) {

  		}

  		return map;
  	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sending values to insert function
				String output = adminService.insertAdmin(	request.getParameter("firstName"),
													request.getParameter("lastName"),
													request.getParameter("email"),
													request.getParameter("mobile"),
													request.getParameter("serviceNo"),
													request.getParameter("department"),
													request.getParameter("position"));
													
				//sending the output to client
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);

		//getting values from the map and sending to update function
		String output = adminService.updateAdmin(	paras.get("hidAdminIDSave").toString(),
													paras.get("firstName").toString(),
													paras.get("lastName").toString(),
													paras.get("email").toString(),
													paras.get("mobile").toString(),
													paras.get("serviceNo").toString(),
													paras.get("department").toString(),
													paras.get("position").toString());
													
		//sending the output to client
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);

		//getting values from the map and sending to delete function
		String output = adminService.deleteAdmin(	paras.get("userID").toString());
				
		//sending the output to client
		response.getWriter().write(output);
	}

}

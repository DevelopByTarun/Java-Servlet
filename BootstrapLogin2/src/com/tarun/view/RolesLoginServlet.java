package com.tarun.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tarun.dao.LoginDAO;
import com.tarun.dao.RolesDAO;
import com.tarun.dto.LoginDTO;
import com.tarun.dto.RolesDTO;

/**
 * Servlet implementation class RolesLoginServlet
 */
@WebServlet("/RolesLogin")
public class RolesLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String doComboBox() throws ClassNotFoundException, SQLException {
		String design = "<select>";
		RolesDAO rolesDAO = new RolesDAO();
		String opt = "";
		ArrayList<RolesDTO> rolesList = rolesDAO.getRoles();
		for(RolesDTO rolesDTO: rolesList) {
			System.out.println("Inside Loop "+rolesDTO.getName());
			opt += "<option>"+rolesDTO.getDescr()+"</option>";
		}
		design = design + opt + "</select>";
		return design;
	}
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		LoginDAO loginDAO = new LoginDAO();
		
		out.println("<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'></head><body>");		
		out.println("<div class='container'>");
		out.println("<h2 class='text-center'>Welcome To Bootstrap Login In Servlet</h2>");
		out.println("<br/>");
		out.println("<form action ='RolesLogin' method='get'>");
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		out.println("<label for='id'>Userid ::</label>");
		out.println("<input type='text' class='form-control' id='id' placeholder='Enter Userid' name='uid'>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		out.println("<label for='pd'>Password ::</label>");
		out.println("<input type='password' class='form-control' id='pd' placeholder='Enter Password' name='pwd'>");
		out.println("</div>");
		out.println("</div>");
		
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		try {
			out.println("<label for='sell'>Choose You Role ::</label>"+this.doComboBox());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</div>");
		out.println("</div>");
		
		out.println("<div class='row'>");
		out.println("<div class='col-sm-12'>");
		out.println("<input class='btn btn-danger' type='submit' value='Signin..'>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body></html>");
		
		String userid = request.getParameter("uid");
		String password = request.getParameter("pwd");
		LoginDTO loginDTO = new LoginDTO(userid, password);
		boolean result = false;
		try {
			result = loginDAO.validate(loginDTO);
			if(result == true) {
				response.sendRedirect("result");
			}
			else {
				out.println("<h1>Sorry, You Have Unable To Logged In, Please Try Again</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("RolesLogin");  
		        rd.include(request, response);  
			}
		}
		catch(ClassNotFoundException cnfe) {
			response.sendRedirect("error.html");
			cnfe.printStackTrace();
		}
		catch(SQLException ex) {
			response.sendRedirect("error.html");
			ex.printStackTrace();
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

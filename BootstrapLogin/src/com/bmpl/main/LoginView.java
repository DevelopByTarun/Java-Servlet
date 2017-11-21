package com.bmpl.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bmpl.dto.*;
import com.bmpl.dao.*;

/**
 * Servlet implementation class LoginView
 */
@WebServlet("/View")
public class LoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		boolean status = false;
		/*
		 * create form for input text
		 */
		out.println("<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'></head><body>");		
		out.println("<div class='container'>");
		out.println("<h2 class='text-center'>Welcome To Bootstrap Login In Servlet</h2>");
		out.println("<br/>");
		out.println("<form action ='View' method='get'>");
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		out.println("<label for='exampleInputEmail1'>Username ::</label>");
		out.println("<input type='text' class='form-control' id='exampleInputEmail1' placeholder='Enter Username' name='uname'>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		out.println("<label for='exampleInputPassword1'>Password ::</label>");
		out.println("<input type='password' class='form-control' id='exampleInputPassword1' placeholder='Enter Password' name='pwd'>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='row'>");
		out.println("<div class='col-sm-12'>");
		out.println("<input class='btn btn-danger' type='submit' value='Authenticate'>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</body></html>");
		
		String u = request.getParameter("uname");
		String p = request.getParameter("pwd");
		LoginDTO loginDTO = new LoginDTO(u, p);
		LoginDAO loginDAO = new LoginDAO();

		try {
			status = loginDAO.validate(loginDTO);
			if(status == true) {
				response.sendRedirect("Welcome");
			}
//			else {
//				out.println("<h1>You Have Unable To Logged In</h1>");
//			}
		} catch (ClassNotFoundException e) {
			response.sendRedirect("Error.html");
			e.printStackTrace();
		} catch (SQLException e) {
			response.sendRedirect("Error.html");
			e.printStackTrace();
		}
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package com.tarun.login;

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
import javax.servlet.http.HttpSession;

import com.tarun.dao.LoginDAO;
import com.tarun.dao.RolesDAO;
import com.tarun.dto.LoginDTO;
import com.tarun.dto.RolesDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
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
	
	private String doDesign(boolean ...flag){
		String design = "";
		String message = "";
		if(flag!=null && flag.length>0){
			if(flag[0]==true){
				message = "Invalid Userid or Password";
			}
		}
		try{
		 design = "<!DOCTYPE html><html lang='en'><head>"
				+ "<meta charset='utf-8'><meta http-equiv='X-UA-Compatible' "
				+ "content='IE=edge'><meta name='viewport' "
				+ "content='width=device-width, initial-scale=1'>"
				+ "<meta name='description' content=''>"
				+ "<meta name='author' content=''>"
				+ "<link rel='icon' href='../../favicon.ico'><title>"
				+ "Signin Template for Bootstrap</title><link"
				+ " href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' "
				+ "rel='stylesheet'><link href='css/signin.css' rel='stylesheet'>"
				+" <script src=https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js></script> "
				+ "</head><body><div class='container'>"
				+" <h1> "+message +"</h1>"
				+ "<form action='Login' method='post'  class='form-signin'><h2 class='form-signin-heading'>"
				+ "Please sign in</h2><label for='ud' class='sr-only'>"
				+ "Userid ::</label><input name='uid' type='text' id='ud' class='form-control' placeholder='Enter Userid' required autofocus>"
				+ "<label for='inputPassword' class='sr-only'>Password</label>"
				+ "<input type='password' name='pwd' id='inputPassword' class='form-control' placeholder='Password' required>"
				+ "<label for='role' class='sr-only'>Choose Your Role</label>"+this.doComboBox()
				+ "<button class='btn btn-lg btn-primary btn-block' type='submit'>Sign in</button></form></div></body></html>";
		}
		catch(Exception ee){
			System.out.println("Problem in Design "+ee);
		}
		return design;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(this.doDesign());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginDAO loginDAO = new LoginDAO();
		String userid = request.getParameter("uid");
		String password =request.getParameter("pwd");
		LoginDTO loginDTO = new LoginDTO(userid, password);
		boolean result = false;
		try {
			result = loginDAO.validate(loginDTO);
			if(result == true) {
				HttpSession session = request.getSession(true);
				session.setAttribute("uid", userid);
				RequestDispatcher rd = request.getRequestDispatcher("Welcome");
				rd.forward(request, response);
			}
			else {
				PrintWriter out=response.getWriter();
				out.println(this.doDesign(true));
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
	}
}

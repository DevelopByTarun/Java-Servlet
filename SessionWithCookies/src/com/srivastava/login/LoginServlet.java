package com.srivastava.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srivastava.db.DB;
import com.srivastava.dto.MasterDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private String doCombo() throws ClassNotFoundException, SQLException, NamingException{
		String design = "<select>";
	DB db = new DB();
	String li= "";
	ArrayList<MasterDTO> masterList = db.getRoles();
	for(MasterDTO masterDTO : masterList){
		System.out.println("Inside Loop "+masterDTO.getName());
		li += "<option>"+masterDTO.getName()+"</option>";
	}
	design = design + li +"</select>";
   
	return design;


	}
	private String doDesign(String color, boolean ...flag){
		String design = "";
		String message = "";
		if(flag!=null && flag.length>0){
			if(flag[0]==true){
				message = "Invalid Userid or Password";
			}
		}
		try{
		String script = "<script>if(!navigator.cookieEnabled){"
				+ " document.getElementById('msg').innerHTML='Cookie is Disabled u Cant Login ';"
				+ " } </script>";	
		 design = "<!DOCTYPE html><html lang='en'><head>"
				+ "<meta charset='utf-8'><meta http-equiv='X-UA-Compatible' "
				+ "content='IE=edge'><meta name='viewport' "
				+ "content='width=device-width, initial-scale=1'>"
				+ "<meta name='description' content=''>"
				+ "<meta name='author' content=''>"
				+ "<link rel='icon' href='../../favicon.ico'><title>"
				+ "Signin Template for Bootstrap</title>"
				+" <script src=https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js></script> "
				+ "</head><body bgcolor='"+color+"'> <h2 id='msg'>MESSAGE IS </h2>"
				+" <h1> "+message +"</h1>"
				+ "<form action='login' method='post'  class='form-signin'><h2 class='form-signin-heading'>"
				+ "Please sign in</h2><label for='inputEmail' class='sr-only'>"
				+ "Email address</label><input name='email' type='email' id='inputEmail' class='form-control' placeholder='Email address' required autofocus>"
				+ "<label for='inputPassword' class='sr-only'>Password</label>"
				+"<input type='password' name='pwd' id='inputPassword' class='form-control' placeholder='Password' required>"
				+"<label for='color' class='sr-only'>Color</label>"
				
				+ "<input type='color' name='color' id='color'  >"
				+ "<label for='role' class='sr-only'>Choose Your Role</label>"+this.doCombo()
				+ "<button class='btn btn-lg btn-primary btn-block' type='submit'>Sign in</button></form>"+script+"</body></html>";
		}
		catch(Exception ee){
			System.out.println("Problem in Design "+ee);
		}
		return design;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String color = "white";
		// Read 
		Cookie cookies [] = request.getCookies();
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("mycolor")){
					color = cookie.getValue();
					break;
				}
			}
		}
		out.println(this.doDesign(color));
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("email");
		String password =request.getParameter("pwd");
		String color="white";
		if(userid.equals(password)){
			HttpSession session = request.getSession(true);
			session.setAttribute("uid", userid);
			// Read
			Cookie cookies [] = request.getCookies();
			if(cookies!=null && cookies.length>0){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("mycolor")){
						color = cookie.getValue();
						break;
					}
				}
			}
			// Write Cookie
			if(color.equalsIgnoreCase("white")){
				color = request.getParameter("color");
				Cookie cookie = new Cookie("mycolor",color);
				cookie.setMaxAge(60*60*24*365);
				response.addCookie(cookie);
			}
			//RequestDispatcher rd = request.getRequestDispatcher("welcome");
			//rd.forward(request, response);
			String urlWithSessionId = response.encodeRedirectURL("welcome?uname="+userid);
			response.sendRedirect(urlWithSessionId);
			//response.sendRedirect("welcome?uname="+userid);
		}
		else
		{
			PrintWriter out=response.getWriter();
			out.println(this.doDesign(color,true));
		}
	}

}

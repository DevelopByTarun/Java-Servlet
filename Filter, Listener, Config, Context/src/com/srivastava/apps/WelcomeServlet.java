package com.srivastava.apps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srivastava.db.DB;
import com.srivastava.dto.ProductDTO;
import com.srivastava.listeners.SessionCountListener;

@WebServlet("/welcome.onlineshop")
public class WelcomeServlet extends HttpServlet {
	int counter;
	
	
	public void init(){
		counter = 1;
		System.out.println("Call First Time Only "+counter);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		PrintWriter out = res.getWriter();
		Date date = new Date();
		DB db = new DB();
		
		HttpSession session = req.getSession(false);
		
		out.println("<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'></head><body>");
		RequestDispatcher rd = req.getRequestDispatcher("header");
		rd.include(req, res);
		out.println("<h1>Welcome "+ req.getParameter("email"));
		//session.setMaxInactiveInterval(60*20);
		//out.println("<h1>Welcome "+session.getAttribute("uid")+" UNAME "+req.getParameter("uname"));
		out.println("<h2> Login Users are "+SessionCountListener.getCounter()+"</h2>");
		out.println("<h1>New Products are ::: from "+req.getParameter("branch")+"</h1>");
		out.println("<form action = 'welcome' method='get'><div class='form-group'>");
		out.println("<input class='form-control' type='text' placeholder='Type to Search' name='search'>");
		out.println("<input class='btn btn-primary' type='submit' value='Search'>");
		
		out.println("</form>");
		String searchCriteria = req.getParameter("search");
		if(searchCriteria!=null && searchCriteria.trim().length()>0){
			ArrayList<ProductDTO> productList;
			try {
				productList = db.getProductsByCriteria("N", searchCriteria);
				out.println("<ul>");
				for(ProductDTO product: productList){
					out.println("<li> "+"<img src='"+product.getImage()+"'>"+product.getName()+" Price "+product.getPrice());
				}
				return ;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				res.sendRedirect("error.html");
				e.printStackTrace();
			} catch (Exception e) {
				res.sendRedirect("error.html");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			ArrayList<ProductDTO> productList = db.getProducts("N");
			out.println("<ul>");
			for(ProductDTO product: productList){
				out.println("<li> "+"<img src='"+product.getImage()+"'>"+product.getName()+" Price "+product.getPrice());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			res.sendRedirect("error.html");
			e.printStackTrace();
		} catch (Exception e) {
			res.sendRedirect("error.html");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</body></html>");
		counter++;
		out.close();
	}
}

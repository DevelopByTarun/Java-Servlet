package com.tarun.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tarun.dao.ProductDAO;
import com.tarun.dto.ProductDTO;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		ProductDAO productDAO = new ProductDAO();
		out.println("<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'></head><body>");		
		out.println("<div class='container'>");
		out.println("<h2 class='text-center'>Welcome To Bootstrap Login In Servlet</h2>");
		out.println("<br/>");
		out.println("<form action ='Product' method='get'>");
		out.println("<div class='row'>");
		out.println("<div class='form-group col-sm-12'>");
		out.println("<label for='exampleInputEmail1'>SortByPrice ::</label>");
		out.println("<input type='text' class='form-control' id='exampleInputEmail1' placeholder='Enter Price' name='search'>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='row'>");
		out.println("<div class='col-sm-12'>");
		out.println("<input class='btn btn-danger' type='submit' value='Search..'>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		/*
		 * show products by search criteria
		 */
		String searchCriteria = request.getParameter("search");
		if(searchCriteria != null && searchCriteria.trim().length() > 0) {
			ArrayList<ProductDTO> productList;
			try {
				productList = productDAO.getProductsByCriteria("ok", searchCriteria);
				out.println("<ul>");
				for(ProductDTO products : productList) {
					out.println("<li> "+"<img src='"+products.getImage()+"'>"+" "+products.getName()+" "+products.getDescr()+" "+products.getPrice()+"</li>");
				}
				out.println("</ul>");
				return ;
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
		out.println("<hr/>");
		/*
		 * show products
		 */
		try {
			ArrayList<ProductDTO> productList = productDAO.getProducts("ok");
			out.println("<ul>");
			for(ProductDTO products : productList) {
				out.println("<li> "+"<img src='"+products.getImage()+"'>"+" "+products.getName()+" "+products.getDescr()+" "+products.getPrice()+"</li>");
			}
			out.println("</ul>");
		}
		catch(ClassNotFoundException cnfe) {
			response.sendRedirect("error.html");
			cnfe.printStackTrace();
		}
		catch(SQLException ex) {
			response.sendRedirect("error.html");
			ex.printStackTrace();
		}
		out.println("</body></html>");
		out.close();
	}
}

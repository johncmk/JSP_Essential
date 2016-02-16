package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Do Get");
		
		response.setContentType("text/plain");
		String fullname = request.getParameter("fullname");
		PrintWriter out = response.getWriter();
		out.println("Hello " + fullname);
	}//End Get

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("Do Post");
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		switch(action) {
			case "demo1":
				String full_name = request.getParameter("full_name");
				out.println("Hello " + full_name);
				break;
			case "demo2":
				int num1 = Integer.parseInt(request.getParameter("number1"));
				int num2 = Integer.parseInt(request.getParameter("number2"));
				out.println(num1 + num2);
				break;
		}
		
	}//End Post

}//End Servlet

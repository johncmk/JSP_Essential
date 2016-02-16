package com.javalec.ex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontCon
 */
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get method");
		actionDo(request, response);
	}//End Get

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("Post method");
		actionDo(request, response);
	}//End Post

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ActionDo");
		
		String uri = request.getRequestURI();
		System.out.println("URI = " + uri);
		
		String conPath = request.getContextPath();
		System.out.println("Context Path = " + conPath);
		
		String command = uri.substring(conPath.length());
		System.out.println("Command = " + command);
		
		switch(command) {
			case "/insert.do":
				System.out.println("Insert");
				System.out.println("----------------");
				break;
			case "/update.do":
				System.out.println("Update");
				System.out.println("----------------");
				break;
			case "/select.do":
				System.out.println("Select");
				System.out.println("----------------");
				break;
			case "/delete.do":
				System.out.println("Delete");
				System.out.println("----------------");
				break;
		}
		
	}//End ActionDo
	
}//End Servlet

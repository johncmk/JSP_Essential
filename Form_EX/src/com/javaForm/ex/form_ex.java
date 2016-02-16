package com.javaForm.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class form_ex
 */
@WebServlet("/form_ex")
public class form_ex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public form_ex() {
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		//Able to send back Korean Character
		request.setCharacterEncoding("EUC-KR");
		
		String name = request.getParameter("name_val");
		
		name = !name.isEmpty()? name: "form didn't pass value successfully.";
		
		PrintWriter writer = response.getWriter();
		
		response.setContentType("text/html; charset=EUC-KR");
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<h1>Form value is ÇÑ±Û</h1>");
		writer.println("<i>"+name+"</i>");
		writer.println("<body>");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
	}

}

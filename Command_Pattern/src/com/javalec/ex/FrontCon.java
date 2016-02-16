package com.javalec.ex;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontCon; FrontController
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
		System.out.println("DoGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("doPost");
		actionDo(request, response);
	}//End

	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("actionDo");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		switch(command) {
		
			case "/membersAll.do":
				response.setContentType("text/html; charset=EUC-KR");
				PrintWriter writer = response.getWriter();
				writer.println("<html><head></head><body>");
				
				Service service = new MembersAllService(); //MembersAllService 에 커멘드를 다 옮긴다. 
				ArrayList<MemberDto> dtos = service.execute(request, response);
				
				for (MemberDto dto : dtos) {
					String id = dto.getId();
					String pw = dto.getPw();
					String name = dto.getName();
					String eMail = dto.geteMail();
					Timestamp rDate = dto.getrDate();
					String address = dto.getAddress();
					writer.println(id + ", " + pw + ", " + name + ", " + eMail + ", " + rDate.toLocalDateTime() + ", " + address + "<hr />");
				}
				writer.println("</body></html>");
				break; 
		}
		
	}//End
	
}//End Servlet

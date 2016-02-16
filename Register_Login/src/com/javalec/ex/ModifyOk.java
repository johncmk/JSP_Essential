package com.javalec.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * variable and Connection
	 */
	
	private Connection conn;
	private Statement stmt;
	
	private String name,
					id,
					pw,
					phone1,
					phone2,
					phone3,
					gender;
	
	private String driver ="oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String u_id = "scott";
	private String u_pw = "tiger";
	
	HttpSession httpSession;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOk() {
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
		doGet(request, response);
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		httpSession = request.getSession();
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		
		if(pwConfirm()) {
			System.out.println("OK");
			
			String query = "UPDATE member"
						+ " SET name = '"+name+"',"
							+ " phone1 = '"+phone1+"',"
							+ " phone2 = '"+phone2+"',"
							+ " phone3 = '"+phone3+"',"
							+ " gender = '"+gender+"'"
						+ " WHERE id = '"+id+"'"
							+ " AND pw = '"+pw+"'";
					
			System.out.println("query = " + query);
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,u_id,u_pw);
				stmt = conn.createStatement();
				int i = stmt.executeUpdate(query);
				
				if( i == 1) {
					System.out.println("Update Successfully");
					httpSession.setAttribute("name", name);
					response.sendRedirect("modifyResult.jsp");
				} else {
					System.out.println("update failed");
					response.sendRedirect("modify.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Password confirmation failed");
		}
			
	}//End actionDo
	
	private boolean pwConfirm() {
		boolean rs = false;
		
		String sessionPw = (String)httpSession.getAttribute("pw");
		
		System.out.println("session pw = " + sessionPw);
		System.out.println("pw = " + sessionPw);
		
		return (sessionPw.equals(pw));
	}
	
}//End Servlet

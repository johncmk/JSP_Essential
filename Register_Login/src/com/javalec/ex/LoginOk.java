package com.javalec.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 * variable and Connection
	 */
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private String name,
					id,
					pw,
					phone1,
					phone2,
					phone3,
					gender;
	
//	private String[] hobbys;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) {
		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		
		String query = "SELECT * "
				+ "FROM member "
				+ "WHERE id = '"+id+"' and pw = '"+pw+"'";
		
		String driver ="oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String u_id = "scott";
		String u_pw = "tiger";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,u_id,u_pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while( rs.next()) {
				name = rs.getString("name");
				id = rs.getString("id");
				pw = rs.getString("pw");
				phone1 = rs.getString("phone1");
				phone2 = rs.getString("phone2");
				phone3 = rs.getString("phone3");
				gender = rs.getString("gender");
			}
			
			/*
			 * Store user's ID into session if user's ID exists in DB
			 */
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("name", name);
			httpSession.setAttribute("id", id);
			httpSession.setAttribute("pw", pw);
			
			response.sendRedirect("loginResult.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) 
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch( Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}//End Servlet

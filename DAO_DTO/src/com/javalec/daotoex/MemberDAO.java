package com.javalec.daotoex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * DAO; Data Access Object
 * It access to DB to perform Insert, Delete, Update, Select ...
 */
public class MemberDAO {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "scott";
	private String upw = "tiger";
	private String driver = "oracle.jdbc.OracleDriver";
	
	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<MemberDTO> memberSelect() {
		
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection(url,uid,upw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from member");
			
			while ( rs.next() ) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String phone1 = rs.getString("phone1");
				String phone2 = rs.getString("phone2");
				String phone3 = rs.getString("phone3");
				String gender = rs.getString("gender");
				
				MemberDTO dto = new MemberDTO(name, id, pw, phone1, phone2, phone3, gender);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ( rs != null )
					rs.close();
				if ( stmt != null ) 
					rs.close();
				if ( conn != null ) 
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}//End memberSelect
		
}//End DAO

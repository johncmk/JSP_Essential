package com.javalec.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

/**
 * Data Access Object
 */
public class MemberDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static MemberDao instance = null;
	
	/*
	 * Singleton Instance
	 */
	public static MemberDao getInstance(){
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	/*
	 * SELECT * FROM MEMBERS
	 */
	public ArrayList<MemberDto> membersAll() {
		
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		
		String query = "SELECT * FROM members";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			System.out.println("================");
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.seteMail(rs.getString("eMail"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setAddress(rs.getString("address"));
				dtos.add(dto);
			}
			System.out.println("------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}//End 

	/**
	 * Connect to Oracle DB
	 * @return connected Object
	 */
	private Connection getConnection() {
		Context ct = null;
		DataSource ds = null;
		
		try {
			ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/Oracle11g");
			if(conn == null)
				conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//End
	
}//End DAO

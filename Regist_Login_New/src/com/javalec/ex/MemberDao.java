package com.javalec.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * Data Access Object
 */
public class MemberDao {

	/*
	 * Constant Variable
	 */
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FALL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = 1;
	
	private static MemberDao instance = null;
	
	private MemberDao(){
	}
	
	/*
	 * Singleton Pattern
	 */
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	/**
	 * Insert value to register
	 * @param dto
	 * @return result a/f insert
	 */
	public int insertMember(MemberDto dto) {
		 
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO members VALUES(?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());;
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null) 
					conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	/**
	 * Check if the ID already exists in DB
	 * @param id
	 * @return return 0 or 1
	 */
	public int confirmId(String id) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT id from members where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ri;
	}
	
	/**
	 * Check user's password
	 * @param id
	 * @param pw
	 * @return 0 or 1
	 */
	public int userCheck ( String id, String pw ) {
		int ri = 0;
		String dbPw;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT pw from members where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPw = rs.getString("pw");
				if(dbPw.equals(pw)) {
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; //login success
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; //Password incorrect login failed
				}
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;//Your ID doesn't exists in DB
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ri;
	}
	
	/**
	 * Retrieve User's data from db by id 
	 * @param id
	 * @return dto Object
	 */
	public MemberDto getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM members WHERE id = ?";
		MemberDto dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.seteMail(rs.getString("eMail"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setAddress(rs.getString("address"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	/**
	 * Update user's info
	 * @param dto
	 * @return 0 or 1
	 */
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE members set pw = ?, "
										+ "	eMail = ?, "
										+ "address = ? "
									+ "where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ri;
	}
	
	/**
	 * Connect to DB; DBCP
	 * @return connected connection object
	 */
	private Connection getConnection() {
		Context ct = null;
		DataSource ds = null;
		Connection conn = null;
		
		try {
			ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}//End DAO
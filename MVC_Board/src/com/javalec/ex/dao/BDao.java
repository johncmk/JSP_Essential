package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.dto.BDto;

/*
 * Board Data Access Object
 */
public class BDao {

	private DataSource dataSource;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	public BDao() {
		try {
			Context ctxt = new InitialContext();
			dataSource = (DataSource)ctxt.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Write 
	 */
	public void write(String bName, String bTitle, String bContent) {
		
		try {
			conn = dataSource.getConnection();
			
			sql = "INSERT INTO mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent)"
					+ " VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0 , 0)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int rn = pstmt.executeUpdate();
			
			if(rn == 0)
				System.out.println("Write SQL failed");
			else 
				System.out.println("Write SQL Succeed");
			
			System.out.println("Write: "+sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//End 

	/*
	 * Print the list
	 */
	public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		try {
			conn = dataSource.getConnection();
			
			sql = "SELECT "
						+ " bId, "
						+ " bName, "
						+ " bTitle, "
						+ " bContent, "
						+ " bDate, "
						+ " bHit, "
						+ " bGroup, "
						+ " bStep, "
						+ " bIndent "
					+ " FROM mvc_board "
						+ " ORDER BY bGroup DESC, "
						+ " bStep ASC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("List SQL: " +sql);
			
			while( rs.next() ) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("dtos.size: " + dtos.size());
		return dtos;
	}//End

	/*
	 * Display all column from DB
	 */
	public BDto contentView(String strID) {
		
		upHit(strID);//Increase number of view count
		
		BDto dto = null;

		try {
			conn = dataSource.getConnection();
			
			sql = "SELECT * "
					+ " FROM mvc_board "
					+ " WHERE bId = ?";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				if(dto == null)
					dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
			
			System.out.println("Content View: " + sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}//End
	
	/*
	 * Edit 
	 */
	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		try {
			conn = dataSource.getConnection();
			
			sql = "UPDATE mvc_board"
					+ " SET bName = ?,"
						+ "bTitle = ?,"
						+ "bContent = ?"
					+ " WHERE bId = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
	
			int rn = pstmt.executeUpdate();

			if( rn == 0)
				System.out.println("Update failed");
			else
				System.out.println("Update Succeed");
		
			System.out.println("Modify: " + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//End

	/*
	 * Delete
	 */
	public void delete(String bId) {
		try {
			conn = dataSource.getConnection();
			
			sql = "DELETE FROM mvc_board"
					+ " WHERE bId = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();

			if( rn == 0)
				System.out.println("Delete failed");
			else
				System.out.println("Delete Succeed");
		
			System.out.println("Delete: " + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//End
	
	/*
	 * Reply to view
	 */
	public BDto reply_view(String str) {

		BDto dto = null;
		
		try {
			conn = dataSource.getConnection();
			
			sql = "SELECT *"
				+ " FROM mvc_board "
				+ " WHERE bId = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(str));
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				if(dto == null)
					dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
			
			System.out.println("Reply View: " + sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}//End
	
	/*
	 * Reply 
	 */
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		
		replyShape(bGroup, bStep);
		
		try {
			conn = dataSource.getConnection();
			
			sql = "INSERT INTO mvc_board(bId, bName, bTitle, bContent, bGroup, bStep, bIndent)"
					+ " VALUES(mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 0)
				System.out.println("Write SQL failed");
			else 
				System.out.println("Write SQL Succeed");
			
			System.out.println("Reply: " + sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}//End
	
	/*
	 * update bStep
	 */
	private void replyShape(String strGroup, String strStep) {

		System.out.println("ReplyShape called");
		System.out.println("BGroup: " + strGroup);
		System.out.println("BStep: " + strStep);
		
		try {
			conn = dataSource.getConnection();
			
			sql = "UPDATE mvc_board "
					+ " SET bStep = bStep + 1 "
					+ " WHERE bGroup = ? "
					+ " AND bStep > ?";
			
			System.out.println("BGroup: " + strGroup);
			System.out.println("BStep: " + strStep);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 0)
				System.out.println("Update failed");
			else 
				System.out.println("Update Succeed");
		
			System.out.println("ReplyShape: " + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//End
	
	/*
	 * Increase the number of board hit.Á¶È¸¼ö
	 */
	private void upHit (String bId) {
		
		try {
			conn = dataSource.getConnection();
			
			sql = "UPDATE mvc_board "
					+ " SET bHit = bHit + 1 "
					+ " WHERE bId = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
			
			if(rn == 0)
				System.out.println("Update failed");
			else 
				System.out.println("Update Succeed");
			
			System.out.println("UpHit: "+sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//End
	
}//End 

package com.javalec.ex;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Command Pattern
 */
public class MembersAllService implements Service{

	/*
	 * Default Constructor
	 */
	public MembersAllService() {}

	@Override
	public ArrayList<MemberDto> execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		MemberDao dao = MemberDao.getInstance();
		return dao.membersAll();
	}
	
}//End class

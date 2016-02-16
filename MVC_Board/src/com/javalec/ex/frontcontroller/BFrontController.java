package com.javalec.ex.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.command.BCommand;
import com.javalec.ex.command.BContentCommand;
import com.javalec.ex.command.BDeleteCommand;
import com.javalec.ex.command.BListCommand;
import com.javalec.ex.command.BModifyCommand;
import com.javalec.ex.command.BReplyCommand;
import com.javalec.ex.command.BReplyViewCommand;
import com.javalec.ex.command.BWriteCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Do Get");
		actionDo(request, response);
	}//End

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("Do Post");
		actionDo(request, response);
	}//End

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ActionDo");
		
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());
		
		System.out.println("URI: " + uri);
		System.out.println("CONTEXT PATH: " + conPath);
		System.out.println("COMMAND: " + cmd);
		
		switch(cmd) {
			case "/write_view.do":
				System.out.println("Switch.Command: "+ cmd);
				viewPage = "write_view.jsp";
				break;
			case "/write.do":
				System.out.println("Switch.Command: "+ cmd);
				command = new BWriteCommand();
				command.execute(request, response);
				viewPage = "list.do";
				break;
			case "/list.do": 
				System.out.println("Switch.Command: "+ cmd);
				command = new BListCommand();
				command.execute(request, response);
				viewPage = "list.jsp";
				break;
			case "/content_view.do":
				System.out.println("Switch.Command: "+ cmd);
				command = new BContentCommand();
				command.execute(request, response);
				viewPage = "content_view.jsp";
				break;
			case "/modify.do":
				System.out.println("Switch.Command: "+ cmd);
				command = new BModifyCommand();
				command.execute(request, response);
				viewPage = "list.do";
				break;
			case "/delete.do":
				System.out.println("Switch.Command: "+ cmd);
				command = new BDeleteCommand();
				command.execute(request, response);
				viewPage = "list.do";
				break;
			case "/reply_view.do":
				System.out.println("Switch.Command: "+ cmd);
				command = new BReplyViewCommand();
				command.execute(request, response);
				viewPage = "reply_view.jsp";
				break;
			case "/reply.do": //Fix this
				System.out.println("Switch.Command: "+ cmd);
				command = new BReplyCommand();
				command.execute(request, response);
				viewPage = "list.do";
				break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}//End
	
}//End Servlet

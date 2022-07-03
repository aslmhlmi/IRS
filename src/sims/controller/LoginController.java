package sims.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sims.dao.UserLoginDAO;
import sims.connection.ConnectionManager;
import sims.model.User;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserLoginDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		dao = new UserLoginDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("Logout")) {
			session.setAttribute("roleuser",null);
			session.setAttribute("username",null);
			session.setAttribute("userid",null);
			session.invalidate();
			
			response.sendRedirect("login.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String passwordUser = request.getParameter("passwordUser");
		HttpSession session = request.getSession(true);//declare session
		ConnectionManager cm = new ConnectionManager();
		User user = null;
		
		System.out.println(userID);
		System.out.println(passwordUser);
		
		try {
			user = cm.login(userID, passwordUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user == null) {
			request.setAttribute("fail", "Invalid ID and/or Password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if (user.getRoleUser().equals("admin")){
			
			session.setAttribute("roleuser","admin");
			session.setAttribute("username",user.getUsernameUser());
			session.setAttribute("userid",user.getUserID());
			
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
		}
		else if (user.getRoleUser().equals("staff")){
				
			session.setAttribute("roleuser","staff");
			session.setAttribute("username",user.getUsernameUser());
			session.setAttribute("userid",user.getUserID());
			
			request.getRequestDispatcher("staff/indexS.jsp").forward(request, response);
		}		
	}
}

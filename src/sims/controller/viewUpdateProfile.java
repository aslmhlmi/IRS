package sims.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sims.dao.UserDAO;
import sims.model.User;

/**
 * Servlet implementation class viewUpdateProfile
 */
@WebServlet("/viewUpdateProfile")
public class viewUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String UPDATE = "/profile.jsp";
	private UserDAO dao;  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewUpdateProfile() {
        super();
        dao = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String forward="";
        String action = request.getParameter("action");
        
		if (action.equalsIgnoreCase("viewProfile")){			        	
        	String userID = (request.getParameter("userID"));        	
        	forward = UPDATE;
        	request.setAttribute("i", dao.getUserById(userID));
        }
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.sendRedirect("listUser.jsp");
	}

}

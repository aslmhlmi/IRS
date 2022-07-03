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
 * Servlet implementation class updateProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String UPDATE_USER = "user/updateUser.jsp";
	
	
	String forward="";	
	private UserDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        dao = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("profile/profile")) {
			String id = request.getParameter("id");
			forward = UPDATE_USER;
			
			User user = new User();
        	user = dao.getUserById(id);
        	        	
            request.setAttribute("user", user);
            
			RequestDispatcher view = request.getRequestDispatcher(forward);
	        view.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		User user = new User();
		user.setUserID(request.getParameter("userID"));
		user.setRoleUser(request.getParameter("roleUser"));
		user.setNameUser(request.getParameter("nameUser"));
		user.setEmailUser(request.getParameter("emailUser"));
		user.setTelUser(request.getParameter("telUser"));
		user.setAddressUser(request.getParameter("addressUser"));
		user.setUsernameUser(request.getParameter("usernameUser"));
		user.setPasswordUser(request.getParameter("passwordUser"));
		//System.out.println(request.getParameter("staffID"));
		 dao.updateUser(user);

		    response.sendRedirect("listUser.jsp");
	}

}

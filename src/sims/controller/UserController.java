package sims.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sims.dao.UserDAO;
import sims.dao.UserLoginDAO;
import sims.model.User;



/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String CREATE_USER = "user/createUser.jsp";
	private static String LIST_USER = "user/viewUserList.jsp";
	private static String UPDATE_USER = "user/updateUser.jsp";
	private static String VIEW_USER = "user/viewUser.jsp";
	
	private UserDAO daoUser;
	
	String forward="";	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        daoUser = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("listUser")) {
			
			forward = LIST_USER;
            request.setAttribute("users", daoUser.getAllUser());
					
		} else if (action.equalsIgnoreCase("deleteUser")) {
    	
			String id= request.getParameter("id");
    	
			daoUser.deleteUser(id);
    	
			forward = LIST_USER;
			request.setAttribute("users", daoUser.getAllUser()); 
   
		} else if (action.equalsIgnoreCase("user/updateUser")) {	
        	
        	String id= request.getParameter("id");
        	
        	User user = new User();
        	user = daoUser.getUserById(id);
        	        	
        	forward = UPDATE_USER;
            request.setAttribute("user", user); 
	
        } else if (action.equalsIgnoreCase("user/viewUser")) {
        	
        	String id= request.getParameter("id");
        	
        	User user = new User();
        	user = daoUser.getUserById(id);  
        	
        	User addressUse = new User();
        	addressUse = daoUser.getUserById(user.getAddressUser());
        	user.setAddressUse(addressUse);
        	
        	forward = VIEW_USER;       		
            request.setAttribute("user", user);        	
      
        } else if (action.equalsIgnoreCase("user/viewUserList")) {
        	
        	forward = LIST_USER;       		
        	request.setAttribute("users", daoUser.getAllUser());        	
      
        } else if (action.equalsIgnoreCase("createUser")) {
        	
        	forward = CREATE_USER;
        	request.setAttribute("users", daoUser.getAllUser());
        	
        }
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//handleRequest(request, response);
		String action = request.getParameter("action");
		
		String roleUser = request.getParameter("roleUser");
		String userID = request.getParameter("userID");
		String nameUser = request.getParameter("nameUser");
		String emailUser = request.getParameter("emailUser");
		String telUser = request.getParameter("telUser");
		String addressUser = request.getParameter("addressUser");
		String usernameUser = request.getParameter("usernameUser");
		String passwordUser = request.getParameter("passwordUser");
		
		HttpSession session = request.getSession(true);
		String adminID = (String) session.getAttribute("userid");
		
		User user = new User(adminID, roleUser, nameUser, emailUser, telUser, addressUser, usernameUser, passwordUser);	
		user.setUserID(userID);
		if(action.equalsIgnoreCase("create")) {
			daoUser.add(user);
        	
        	response.sendRedirect("/SIMS/UserController?action=user/createUser");
		}
		else if(action.equalsIgnoreCase("update")) {
			daoUser.updateUser(user);
        	
			// to get the new name of the updated address user
        	User addressUsers = new User();
        	addressUsers = daoUser.getUserById(userID);
        	
        	RequestDispatcher view = request.getRequestDispatcher("user/viewUser.jsp");
        	
			request.setAttribute("user", addressUsers);
			view.forward(request, response);
			
		}
		
		
	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            String str = paramName + " : ";

            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                
                str += paramValue;
                System.out.println(str);
            }

        }
        
        out.close();

    }

}

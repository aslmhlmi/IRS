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

import sims.dao.SupplierDAO;
import sims.model.Supplier;



/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String CREATE_SUPPLIER ="supplier/createSupplier.jsp";
	private static String LIST_SUPPLIER = "supplier/viewSupplierList.jsp";
	private static String UPDATE_SUPPLIER = "supplier/updateSupplier.jsp";
	private static String VIEW_SUPPLIER = "supplier/viewSupplier.jsp";
	
	private SupplierDAO daoSupplier;
	
	String forward="";	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
        daoSupplier = new SupplierDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("listSupplier")) {
			
			forward = LIST_SUPPLIER;
            request.setAttribute("suppliers", daoSupplier.getAllSupplier());
					
		} else if (action.equalsIgnoreCase("deleteSupplier")) {
    	
			String id= request.getParameter("id");
    	
			daoSupplier.deleteSupplier(id);
    	
			forward = LIST_SUPPLIER;
			request.setAttribute("suppliers", daoSupplier.getAllSupplier()); 
   
		} else if (action.equalsIgnoreCase("supplier/updateSupplier")) {	
        	
        	String id= request.getParameter("id");
        	
        	Supplier supplier = new Supplier();
        	supplier = daoSupplier.getSupplierById(id);
        	        	
        	forward = UPDATE_SUPPLIER;
            request.setAttribute("supplier", supplier); 
	
        } else if (action.equalsIgnoreCase("supplier/viewSupplier")) {
        	
        	String id= request.getParameter("id");
        	
        	Supplier supplier = new Supplier();
        	supplier = daoSupplier.getSupplierById(id);  
        	
        	Supplier addressSup = new Supplier();
        	addressSup = daoSupplier.getSupplierById(supplier.getAddressSupplier());
        	supplier.setAddressSup(addressSup);
        	
        	forward = VIEW_SUPPLIER;       		
            request.setAttribute("supplier", supplier);        	
      
        } else if (action.equalsIgnoreCase("supplier/viewSupplierList")) {
        	
        	forward = LIST_SUPPLIER;       		
        	request.setAttribute("suppliers", daoSupplier.getAllSupplier());        	
      
        } else if (action.equalsIgnoreCase("createSupplier")) {
        	
        	forward = CREATE_SUPPLIER;
        	request.setAttribute("users", daoSupplier.getAllSupplier());
		
		
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
		
		String nameSupplier = request.getParameter("nameSupplier");
		String emailSupplier = request.getParameter("emailSupplier");
		String telSupplier = request.getParameter("telSupplier");
		String addressSupplier = request.getParameter("addressSupplier");
		String supplierID = request.getParameter("supplierID");
		

		Supplier supplier = new Supplier(supplierID, nameSupplier, emailSupplier, telSupplier, addressSupplier);				
		
	
		if(action.equalsIgnoreCase("create")) {
			daoSupplier.add(supplier);
        	
        	response.sendRedirect("/SIMS/SupplierController?action=createSupplier");
        }
		
		else if(action.equalsIgnoreCase("update")) {
			daoSupplier.updateSupplier(supplier);
        	
        	// to get the new name of the updated address supplier
        	Supplier addressSuppliers = new Supplier();
        	addressSuppliers = daoSupplier.getSupplierById(supplier.getAddressSupplier());
        	supplier.setAddressSup(addressSuppliers);        	
        	
        	RequestDispatcher view = request.getRequestDispatcher("supplier/viewSupplier.jsp");
        	
			request.setAttribute("supplier", supplier);
			view.forward(request, response);
			response.sendRedirect("/SIMS/SupplierController?action=updateSupplier");
            
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

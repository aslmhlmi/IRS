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

import sims.dao.SaleDAO;
import sims.model.Sale;



/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/SaleController")
public class SaleController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String LIST_SALE = "sale/viewSaleList.jsp";
	private static String UPDATE_SALE = "sale/updateSale.jsp";
	private static String VIEW_SALE = "sale/viewSale.jsp";
	
	private SaleDAO daoSale;
	
	String forward="";	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleController() {
        super();
        daoSale = new SaleDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("listSale")) {
			
			forward = LIST_SALE;
            request.setAttribute("sales", daoSale.getAllSale());
					
		} else if (action.equalsIgnoreCase("deleteSale")) {
    	
			String id= request.getParameter("id");
    	
			daoSale.deleteSale(id);
    	
			forward = LIST_SALE;
			request.setAttribute("sales", daoSale.getAllSale()); 
   
		} else if (action.equalsIgnoreCase("sale/updateSale")) {	
        	
        	String id= request.getParameter("id");
        	
        	Sale sale = new Sale();
        	sale = daoSale.getSaleById(id);
        	        	
        	forward = UPDATE_SALE;
            request.setAttribute("sale", sale); 
	
        } else if (action.equalsIgnoreCase("sale/viewSale")) {
        	
        	String id= request.getParameter("id");
        	
        	Sale sale = new Sale();
        	sale = daoSale.getSaleById(id);  
        	
        	Sale status = new Sale();
        	status = daoSale.getSaleById(sale.getStatusSale());
        	sale.setStatus(status);
        	
        	forward = VIEW_SALE;       		
            request.setAttribute("sale", sale);        	
      
        } else if (action.equalsIgnoreCase("sale/viewSaleList")) {
        	
        	forward = LIST_SALE;       		
        	request.setAttribute("sales", daoSale.getAllSale());        	
      
        }
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//handleRequest(request, response);
		String saleID = request.getParameter("saleID");
		String userID = request.getParameter("userID");
		String totalPayment = request.getParameter("totalPayment");
		String dateSale = request.getParameter("dateSale");
		String statusSale = request.getParameter("statusSale");
		
		System.out.println("getSaleID " + saleID);
		Sale sale = new Sale(saleID, userID, totalPayment, dateSale, statusSale);				
		
		sale = SaleDAO.getSale(sale);
	
		if(!sale.isValid()){
			System.out.println("adding");
        	daoSale.add(sale);
        	
        	response.sendRedirect("sale/createSale.jsp");
        }
		
		else{
        	System.out.println("sale already exist");
        	daoSale.updateSale(sale);
        	
        	// to get the new name of the updated address supplier
        	Sale statusSales = new Sale();
        	statusSales = daoSale.getSaleById(sale.getStatusSale());
        	sale.setStatus(statusSales);        	
        	
        	RequestDispatcher view = request.getRequestDispatcher("sale/viewSale.jsp");
        	
			request.setAttribute("sale", sale);
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

package sims.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

import sims.dao.ArrowDAO;
import sims.dao.BoardDAO;
import sims.dao.BowDAO;
import sims.dao.ProductDAO;
import sims.dao.SupplierDAO;
import sims.dao.UsersProductDAO;
import sims.model.Arrow;
import sims.model.Board;
import sims.model.Bow;
import sims.model.Product;
import sims.model.Users_Product;



/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String LIST_PRODUCT = "product/viewProductList.jsp";
	private static String UPDATE_PRODUCT = "product/updateProduct.jsp";
	private static String VIEW_PRODUCT = "product/viewProduct.jsp";
	private static String CREATE_PRODUCT = "product/createProduct.jsp";
	private static String REPORT = "report/result.jsp";
	
	private ProductDAO daoProduct;
	private SupplierDAO daoSupplier;
	
	String forward="";	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        daoProduct = new ProductDAO();
        daoSupplier = new SupplierDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("listProduct")) {
			
			forward = LIST_PRODUCT;
            request.setAttribute("products", daoProduct.getAllProduct());
					
		} else if (action.equalsIgnoreCase("deleteProduct")) {
    	
			String id= request.getParameter("id");
    	
			daoProduct.deleteProduct(id);
    	
			forward = LIST_PRODUCT;
			request.setAttribute("products", daoProduct.getAllProduct()); 
   
		} else if (action.equalsIgnoreCase("product/updateProduct")) {	
        	
        	String id= request.getParameter("id");
        	
        	Product product = new Product();
        	product = daoProduct.getProductById(id);
        	        	
        	forward = UPDATE_PRODUCT;
            request.setAttribute("product", product); 
	
        } else if (action.equalsIgnoreCase("product/viewProduct")) {
        	
        	String id= request.getParameter("id");
        	
        	Product product = new Product();
        	product = daoProduct.getProductById(id);  
        	
        	Product namePro = new Product();
        	namePro = daoProduct.getProductById(product.getNameProduct());
        	product.setNamePro(namePro);
        	
        	forward = VIEW_PRODUCT;       		
            request.setAttribute("product", product);        	
      
        } else if (action.equalsIgnoreCase("product/viewProductList")) {
        	
        	forward = LIST_PRODUCT;       		
        	request.setAttribute("products", daoProduct.getAllProduct());        	
      
        } else if (action.equalsIgnoreCase("createProduct")) {
        	
        	forward = CREATE_PRODUCT;
        	request.setAttribute("suppliers", daoSupplier.getAllSupplier());
        	
        }
        else if (action.equalsIgnoreCase("report")) {
        	String dateFrom = request.getParameter("start_date");
        	String dateUntil = request.getParameter("end_date");
        	
        	forward = REPORT;       		
        	try {
				request.setAttribute("reports", ProductDAO.generateReport(dateFrom, dateUntil));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
      
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
		
		if(action.equalsIgnoreCase("createbow")) {
			String productID = request.getParameter("productId");
			String codeProduct = request.getParameter("codeProduct");
			String nameProduct = request.getParameter("nameProduct");
			String stringBow = request.getParameter("stringBow");
			String priceProduct = request.getParameter("priceProduct");
			String quantityProduct = request.getParameter("quantityProduct");
			String orderDate = request.getParameter("dateOrder");
			String supplierID = request.getParameter("supplierID");
			
			
			Double priceProduct1 = Double.parseDouble(priceProduct);
			Double quantityProduct1 = Double.parseDouble(quantityProduct);
			
			Double totalPrice = priceProduct1 * quantityProduct1;
			
			HttpSession session = request.getSession(true);
			String userID = (String) session.getAttribute("userid");
			
			System.out.println("getProductID " + productID);
			
			Product product = new Product();
			product.setProductID(productID);
			product.setCodeProduct(codeProduct);
			product.setNameProduct(nameProduct);
			product.setPriceProduct(priceProduct);
			product.setSupplierID(supplierID);
			
			daoProduct.add(product);
			
			Bow bow = new Bow();
			
			bow.setProductID(productID);
			bow.setStringBow(stringBow);
			
			BowDAO.add(bow);
			
			Users_Product userProduct = new Users_Product();
			
			userProduct.setUserID(userID);
			userProduct.setProductID(productID);
			userProduct.setQuantityOrder(quantityProduct);
			userProduct.setTotalPrice(totalPrice);
			userProduct.setOrderDate(orderDate);
			
			try {
				UsersProductDAO.add(userProduct);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	response.sendRedirect("/SIMS/ProductController?action=createProduct");
		}
		else if(action.equalsIgnoreCase("createarrow")) {
			String productID = request.getParameter("productId");
			String codeProduct = request.getParameter("codeProduct");
			String nameProduct = request.getParameter("nameProduct");
			String arrowTips = request.getParameter("arrowTips");
			String arrowShaft = request.getParameter("arrowShaft");
			String arrowSpine = request.getParameter("arrowSpine");
			String priceProduct = request.getParameter("priceProduct");
			String quantityProduct = request.getParameter("quantityProduct");
			String orderDate = request.getParameter("dateOrder");
			String supplierID = request.getParameter("supplierID");
			
			
			Double priceProduct1 = Double.parseDouble(priceProduct);
			Double quantityProduct1 = Double.parseDouble(quantityProduct);
			
			Double totalPrice = priceProduct1 * quantityProduct1;
			
			HttpSession session = request.getSession(true);
			String userID = (String) session.getAttribute("userid");
			
			System.out.println("getProductID " + productID);
			
			Product product = new Product();
			product.setProductID(productID);
			product.setCodeProduct(codeProduct);
			product.setNameProduct(nameProduct);
			product.setPriceProduct(priceProduct);
			product.setSupplierID(supplierID);
			
			daoProduct.add(product);
			
			Arrow arrow = new Arrow();
			
			arrow.setProductID(productID);
			arrow.setTipsArrow(arrowTips);
			arrow.setShaftArrow(arrowShaft);
			arrow.setSpineArrow(arrowSpine);
			
			ArrowDAO.add(arrow);
			
			Users_Product userProduct = new Users_Product();
			
			userProduct.setUserID(userID);
			userProduct.setProductID(productID);
			userProduct.setQuantityOrder(quantityProduct);
			userProduct.setTotalPrice(totalPrice);
			userProduct.setOrderDate(orderDate);
			
			try {
				UsersProductDAO.add(userProduct);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	response.sendRedirect("/SIMS/ProductController?action=createProduct");
		}
		else if(action.equalsIgnoreCase("createboard")) {
			String productID = request.getParameter("productId");
			String codeProduct = request.getParameter("codeProduct");
			String nameProduct = request.getParameter("nameProduct");
			String targetBoard = request.getParameter("targetBoard");
			String priceProduct = request.getParameter("priceProduct");
			String quantityProduct = request.getParameter("quantityProduct");
			String orderDate = request.getParameter("dateOrder");
			String supplierID = request.getParameter("supplierID");
			
			
			Double priceProduct1 = Double.parseDouble(priceProduct);
			Double quantityProduct1 = Double.parseDouble(quantityProduct);
			
			Double totalPrice = priceProduct1 * quantityProduct1;
			
			HttpSession session = request.getSession(true);
			String userID = (String) session.getAttribute("userid");
			
			System.out.println("getProductID " + productID);
			
			Product product = new Product();
			product.setProductID(productID);
			product.setCodeProduct(codeProduct);
			product.setNameProduct(nameProduct);
			product.setPriceProduct(priceProduct);
			product.setSupplierID(supplierID);
			
			daoProduct.add(product);
			
			Board board = new Board();
			
			board.setProductID(productID);
			board.setTargetBoard(targetBoard);;

			
			BoardDAO.add(board);
			
			Users_Product userProduct = new Users_Product();
			
			userProduct.setUserID(userID);
			userProduct.setProductID(productID);
			userProduct.setQuantityOrder(quantityProduct);
			userProduct.setTotalPrice(totalPrice);
			userProduct.setOrderDate(orderDate);
			
			try {
				UsersProductDAO.add(userProduct);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	response.sendRedirect("/SIMS/ProductController?action=createProduct");
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

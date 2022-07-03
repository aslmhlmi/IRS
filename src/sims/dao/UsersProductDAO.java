package sims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import sims.connection.ConnectionManager;
import sims.model.Bow;
import sims.model.Users_Product;

public class UsersProductDAO {
		static Connection currentCon = null;
		static ResultSet rs = null; 
		static PreparedStatement ps= null;
		static Statement stmt= null;
		
		static String userID;
		static String productID;
		static String quantityOrder;
		static Double totalPrice;
		static String orderDate;
		
	public static void add(Users_Product userProduct) throws ParseException {
			
		userID = userProduct.getUserID();
		productID = userProduct.getProductID();
		quantityOrder = userProduct.getQuantityOrder();
		totalPrice = userProduct.getTotalPrice();
		orderDate = userProduct.getOrderDate();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(orderDate);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
	        
	       
	    	try {
	    		currentCon = ConnectionManager.getConnection();
	    		ps=currentCon.prepareStatement("insert into users_product (usersid, productid, quantityorder, totalprice, orderdate) values(?,?,?,?,?)");
	    		ps.setString(1,userID);
	    		ps.setString(2,productID);
	    		ps.setString(3,quantityOrder);
	    		ps.setDouble(4,totalPrice);
	    		ps.setDate(5,sqlStartDate);
	    		ps.executeUpdate();
	    	
	            
	    	}

	    	catch (Exception ex) {
	    		System.out.println("failed: An Exception has occurred! " + ex);
	    	}

	    	finally {
	    		if (ps != null) {
	    			try {
	    				ps.close();
	    			} catch (Exception e) {
	    			}
	    			ps = null;
	    		}
	    		
	    		if (currentCon != null) {
	    			try {
	    				currentCon.close();
	    			} catch (Exception e) {
	    			}
	    			currentCon = null;
	    		}
	    	}
			
			
		}
}

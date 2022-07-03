package sims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import sims.connection.ConnectionManager;
import sims.model.Arrow;
import sims.model.Bow;
import sims.model.Product;

public class ArrowDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	
	static String productID, arrowTips, arrowShaft, arrowSpine;
	static String stringBow;
	
public static void add(Arrow arrow) {
		
	productID = arrow.getProductID();
	arrowShaft = arrow.getShaftArrow();
	arrowTips = arrow.getTipsArrow();
	arrowSpine = arrow.getSpineArrow();
        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into arrow (productid, tipsarrow, shaftarrow, spinearrow)values(?,?,?,?)");
    		ps.setString(1,productID);
    		ps.setString(2,arrowTips);
    		ps.setString(3,arrowShaft);
    		ps.setString(4,arrowSpine);
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

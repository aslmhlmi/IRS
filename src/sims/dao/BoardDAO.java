package sims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import sims.connection.ConnectionManager;
import sims.model.Board;
import sims.model.Bow;
import sims.model.Product;

public class BoardDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	
	static String productID;
	static String targetBoard;
	
public static void add(Board board) {
		
	productID = board.getProductID();
	targetBoard = board.getTargetBoard();
        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into board (productid, targetboard)values(?,?)");
    		ps.setString(1,productID);
    		ps.setString(2,targetBoard);
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

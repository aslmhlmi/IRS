package sims.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sims.connection.ConnectionManager;
import sims.model.Sale;

public class SaleDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	static String saleid, userid, total, date, status;

	public static Sale getSale(Sale sale) {
		saleid = sale.getSaleID();

        String searchQuery = "select * from sales where saleid='" + saleid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if supplier exists set the isValid variable to true
            if (more) {
                sale.setValid(true);
           	}
           
            else if (!more) {
            	sale.setValid(false);
            }
           
        }

        catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return sale;
	}
	
	public void add(Sale sale) {
		
        saleid = sale.getSaleID();
        userid = sale.getUserID();
        total = sale.getTotalPayment();
        date = sale.getDateSale();
        status = sale.getStatusSale();
        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into sales (saleid, userid, totalpayment, datesale, statussale)values(?,?,?,?,?)");
    		ps.setString(1,saleid);
    		ps.setString(2,userid);
    		ps.setString(3,total);
    		ps.setString(4,date);
    		ps.setString(5,status);
    		ps.executeUpdate();
    	
    		System.out.println("Your saleid is " + saleid);
    		System.out.println("Your statussale is " + status);
            
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

	public List<Sale> getAllSale() {
		List<Sale> sales = new ArrayList<Sale>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select * from sale";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		          Sale sale = new Sale();
		          
		          
		          sale.setSaleID(rs.getString("saleID"));
		          sale.setUserID(rs.getString("userID"));
		          sale.setTotalPayment(rs.getString("totalPayment"));
		          sale.setDateSale(rs.getString("dateSale"));
		          sale.setStatusSale(rs.getString("statusSale"));
		          sales.add(sale);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return sales;
	}
	
	public void deleteSale(String saleid) {
		String searchQuery = "delete from sales where saleid=" + "'" + saleid + "'";
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public Sale getSaleById(String saleid) {
		Sale sale = new Sale();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from sales where saleid=?");
	        
	        ps.setString(1, saleid);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	sale.setSaleID(rs.getString("saleID"));
	        	sale.setUserID(rs.getString("userID"));
	        	sale.setTotalPayment(rs.getString("totalPayment"));
	        	sale.setDateSale(rs.getString("dateSale"));
	        	sale.setStatusSale(rs.getString("statusSale"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return sale;
	}

	public void updateSale(Sale sale) {
		
		saleid = sale.getSaleID();
		userid= sale.getUserID();
		total = sale.getTotalPayment();
		date = sale.getDateSale();
		status = sale.getStatusSale();

	    String searchQuery = "UPDATE sales SET userid='" + userid  + "' , totalpayment='" + total + "' , datesale='" + date  + "' , statussale='" + status + "' WHERE saleid= '" + saleid + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
}
package sims.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sims.connection.ConnectionManager;
import sims.model.Supplier;

public class SupplierDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	static String supplierid, namesupplier, emailsupplier, telsupplier, addresssupplier;

	public static Supplier getSupplier(Supplier supplier) {
		supplierid = supplier.getSupplierID();

        String searchQuery = "select * from supplier where supplierid='" + supplierid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if supplier exists set the isValid variable to true
            if (more) {
                supplier.setValid(true);
           	}
           
            else if (!more) {
            	supplier.setValid(false);
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

        return supplier;
	}
	
	public void add(Supplier supplier) {
		
        supplierid = supplier.getSupplierID();
        namesupplier = supplier.getNameSupplier();
        emailsupplier = supplier.getEmailSupplier();
        telsupplier = supplier.getTelSupplier();
        addresssupplier = supplier.getAddressSupplier();
        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into supplier (supplierid, namesupplier, emailsupplier, telsupplier, addresssupplier)values(?,?,?,?,?)");
    		ps.setString(1,supplierid);
    		ps.setString(2,namesupplier);
    		ps.setString(3,emailsupplier);
    		ps.setString(4,telsupplier);
    		ps.setString(5,addresssupplier);
    		ps.executeUpdate();
    	
    		System.out.println("Your id is " + supplierid);
    		System.out.println("Your address is " + addresssupplier);
            
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

	public List<Supplier> getAllSupplier() {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select * from supplier";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		          Supplier supplier = new Supplier();
		          
		          
		          supplier.setSupplierID(rs.getString("supplierID"));
		          supplier.setNameSupplier(rs.getString("nameSupplier"));
		          supplier.setEmailSupplier(rs.getString("emailSupplier"));
		          supplier.setTelSupplier(rs.getString("telSupplier"));
		          supplier.setAddressSupplier(rs.getString("addressSupplier"));
		          suppliers.add(supplier);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return suppliers;
	}
	
	public void deleteSupplier(String supplierid) {
		String searchQuery = "delete from supplier where supplierid=" + "'" + supplierid + "'";
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public Supplier getSupplierById(String id) {
		Supplier supplier = new Supplier();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from supplier where supplierid=?");
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	supplier.setSupplierID(rs.getString("supplierID"));
	        	supplier.setNameSupplier(rs.getString("nameSupplier"));
	        	supplier.setEmailSupplier(rs.getString("emailSupplier"));
	        	supplier.setTelSupplier(rs.getString("telSupplier"));
	        	supplier.setAddressSupplier(rs.getString("addressSupplier"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return supplier;
	}

	public void updateSupplier(Supplier supplier) {
		
		supplierid = supplier.getSupplierID();
		namesupplier = supplier.getNameSupplier();
		emailsupplier = supplier.getEmailSupplier();
		telsupplier = supplier.getTelSupplier();
		addresssupplier = supplier.getAddressSupplier();

	    String searchQuery = "UPDATE supplier SET namesupplier='" + namesupplier  + "' , emailsupplier='" + emailsupplier + "' , telsupplier='" + telsupplier  + "' , addresssupplier='" + addresssupplier + "' WHERE supplierid= '" + supplierid + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
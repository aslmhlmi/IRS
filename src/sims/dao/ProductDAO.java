package sims.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sims.connection.ConnectionManager;
import sims.model.Product;
import sims.model.Report;

public class ProductDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	static String id, code, name, price, quantity, supplierid;

	public static Product getProduct(Product product) {
		id = product.getProductID();

        String searchQuery = "select * from products where productid='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if product exists set the isValid variable to true
            if (more) {
                product.setValid(true);
           	}
           
            else if (!more) {
            	product.setValid(false);
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

        return product;
	}
	
	public void add(Product product) {
		
        id = product.getProductID();
        code = product.getCodeProduct();
        name = product.getNameProduct();
        price = product.getPriceProduct();
        quantity = product.getQuantityProduct();
        supplierid = product.getSupplierID();
        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into products (productid, codeproduct, nameproduct, priceproduct, supplierid)values(?,?,?,?,?)");
    		ps.setString(1,id);
    		ps.setString(2,code);
    		ps.setString(3,name);
    		ps.setString(4,price);
    		ps.setString(5,supplierid);
    		ps.executeUpdate();
    	
    		System.out.println("Your id is " + id);
    		System.out.println("Your supplierid is " + supplierid);
            
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

	public List<Product> getAllProduct() {
		List<Product> products = new ArrayList<Product>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select * from products p join users_product u on (p.productid = u.productid)";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		          Product product = new Product();
		          
		          
		          product.setProductID(rs.getString("productID"));
		          product.setCodeProduct(rs.getString("codeProduct"));
		          product.setNameProduct(rs.getString("nameProduct"));
		          product.setPriceProduct(rs.getString("priceProduct"));
		          product.setQuantityProduct(rs.getString("quantityProduct"));
		          product.setSupplierID(rs.getString("supplierID"));
		          product.setTotalProduct(rs.getString("totalprice"));
		          products.add(product);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return products;
	}
	
	public static List<Report> generateReport(String dateFrom, String dateUntil) throws ParseException {
		
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse(dateFrom);
		java.sql.Date sqlDateFrom = new java.sql.Date(date.getTime());
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date2 = sdf2.parse(dateUntil);
		java.sql.Date sqlDateUntil = new java.sql.Date(date2.getTime());
		
		List<Report> reports = new ArrayList<Report>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select p.codeproduct, p.nameproduct, s.namesupplier, u.quantityorder, u.totalprice, "
		  	  		+ "TO_CHAR(u.orderdate, 'dd-MON-yyyy') from products " + 
		  	  		"p join supplier s on (p.supplierid = s.supplierid) join users_product u on (u.productid = p.productid) " + 
		  	  		"where u.orderdate >= TO_DATE('"+ sqlDateFrom +"', 'yyyy-MM-dd')" +  
		  	  		"AND " + 
		  	  		"u.orderdate <= TO_DATE('"+ sqlDateUntil +"', 'yyyy-MM-dd')";
		  	  System.out.println(q);
		  	  
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		    	  Report report = new Report();
		          
		          
		    	  report.setOrderDateString(rs.getString(6));
		    	  report.setCodeProduct(rs.getString(1));
		    	  report.setNameProduct(rs.getString(2));
		    	  report.setSupplierName(rs.getString(3));
		    	  report.setQuantityOrder(rs.getString(4));
		    	  report.setTotalPrice(rs.getString(5));
		    	  reports.add(report);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return reports;
	}
	
	public void deleteProduct(String id) {
		String searchQuery = "delete from products where productid=" + "'" + id + "'";
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public Product getProductById(String id) {
		Product product = new Product();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from products where productid=?");
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	product.setProductID(rs.getString("productID"));
	        	product.setCodeProduct(rs.getString("codeProduct"));
	        	product.setNameProduct(rs.getString("nameProduct"));
	        	product.setPriceProduct(rs.getString("priceProduct"));
	        	product.setQuantityProduct(rs.getString("quantityProduct"));
	        	product.setSupplierID(rs.getString("supplierID"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return product;
	}

	public void updateProduct(Product product) {
		
		id = product.getProductID();
		code = product.getCodeProduct();
		name = product.getNameProduct();
		price =  product.getPriceProduct();
		quantity = product.getQuantityProduct();
		supplierid = product.getSupplierID();

	    String searchQuery = "UPDATE products SET codeproduct='" + code  + "' , nameproduct='" + name + "' , priceproduct='" + price  + "' , quantityproduct='" + quantity + "' , supplierid='" + supplierid + "' WHERE productid= '" + id + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	

}
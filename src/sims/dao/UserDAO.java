package sims.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sims.connection.ConnectionManager;
import sims.model.User;

public class UserDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	static String userid, adminid, roleuser, name, email, tel, address, username, password;

	public static User getUser(User user) {
		userid = user.getUserID();

		String searchQuery = "select * from users where userid='" + userid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if user exists set the isValid variable to true
            if (more) {
                user.setValid(true);
           	}
           
            else if (!more) {
            	user.setValid(false);
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

        return user;
	}
	
	public void add(User user) {
		
        userid = user.getUserID();
        adminid = user.getAdminID();
        roleuser = user.getRoleUser();
        name = user.getNameUser();
        email = user.getEmailUser();
        tel = user.getTelUser();
        address = user.getAddressUser();
        username = user.getUsernameUser();
        password = user.getPasswordUser();
        
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into users (userid, adminid, roleuser, nameuser, emailuser, teluser, addressuser, usernameuser, passworduser)values(users_seq.nextval,?,?,?,?,?,?,?,?)");
    		ps.setString(1,adminid);
    		ps.setString(2,roleuser);
    		ps.setString(3,name);
    		ps.setString(4,email);
    		ps.setString(5,tel);
    		ps.setString(6,address);
    		ps.setString(7,username);
    		ps.setString(8,password);
    		ps.executeUpdate();
    	
    		System.out.println("Your userid is " + userid);
    		System.out.println("Your adminid is " + adminid);
            
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

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select * from users";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		          User user = new User();
		          
		          
		          user.setUserID(rs.getString("userID"));
		          user.setAdminID(rs.getString("adminID"));
		          user.setRoleUser(rs.getString("roleUser"));
		          user.setNameUser(rs.getString("nameUser"));
		          user.setEmailUser(rs.getString("emailUser"));
		          user.setTelUser(rs.getString("telUser"));
		          user.setAddressUser(rs.getString("addressUser"));
		          user.setUsernameUser(rs.getString("usernameUser"));
		          user.setPasswordUser(rs.getString("passwordUser"));
		          users.add(user);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return users;
	}
	
	public void deleteUser(String userid) {
		String searchQuery = "delete from users where userid=" + "'" + userid + "'";
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public User getUserById(String id) {
		User user = new User();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from users where userid=?");
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	user.setUserID(rs.getString("userID"));
	        	user.setAdminID(rs.getString("adminID"));
	        	user.setRoleUser(rs.getString("roleUser"));
	        	user.setNameUser(rs.getString("nameUser"));
	        	user.setEmailUser(rs.getString("emailUser"));
	        	user.setTelUser(rs.getString("telUser"));
	        	user.setAddressUser(rs.getString("addressUser"));
	        	user.setUsernameUser(rs.getString("usernameUser"));
	        	user.setPasswordUser(rs.getString("passwordUser"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return user;
	}

	public void updateUser(User user) {
		
        userid = user.getUserID();
        adminid = user.getAdminID();
        roleuser = user.getRoleUser();
        name = user.getNameUser();
        email = user.getEmailUser();
        tel = user.getTelUser();
        address = user.getAddressUser();
        username = user.getUsernameUser();
        password = user.getPasswordUser();

	    String searchQuery = "UPDATE users SET adminid='" + adminid + "' , roleuser='" + roleuser + "' , nameuser='" + name  + "' "
	    		+ ", emailuser='" + email + "' , teluser='" + tel  + "' , addressuser='" + address + "' , "
	    				+ "usernameuser='" + username + "' , passworduser='" + password + "' WHERE userid= '" + userid + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
}
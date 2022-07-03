package sims.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.text.*;
import java.util.*;
import java.sql.*;

import sims.connection.ConnectionManager;
import sims.model.Profile;
import sims.model.User;

public class ProfileDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps= null;
	static Statement stmt= null;
	static String userid, roleuser, name, email, tel, address, username, password;

	public static Profile getProfile(Profile profile) {
		userid = profile.getUserID();

		String searchQuery = "select * from users where userid='" + userid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if user exists set the isValid variable to true
            if (more) {
                profile.setValid(true);
           	}
           
            else if (!more) {
            	profile.setValid(false);
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

        return profile;
	}
	
	public void add(Profile profile) {
		
        userid = profile.getUserID();
        roleuser = profile.getRoleUser();
        name = profile.getNameUser();
        email = profile.getEmailUser();
        tel = profile.getTelUser();
        address = profile.getAddressUser();
        username = profile.getUsernameUser();
        password = profile.getPasswordUser();
        
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into users (userid, roleuser, nameuser, emailuser, teluser, addressuser, usernameuser, passworduser)values(?,?,?,?,?,?,?,?)");
    		ps.setString(1,userid);
    		ps.setString(2,roleuser);
    		ps.setString(3,name);
    		ps.setString(4,email);
    		ps.setString(5,tel);
    		ps.setString(6,address);
    		ps.setString(7,username);
    		ps.setString(8,password);
    		ps.executeUpdate();
    	
    		System.out.println("Your userid is " + userid);
    		System.out.println("Your nameuser is " + name);
            
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

	public List<Profile> getAllUser() {
		List<Profile> profiles = new ArrayList<Profile>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select * from users";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		    	  Profile profile = new Profile();
		          
		          
		    	  profile.setUserID(rs.getString("userID"));
		    	  profile.setRoleUser(rs.getString("roleUser"));
		    	  profile.setNameUser(rs.getString("nameUser"));
		    	  profile.setEmailUser(rs.getString("emailUser"));
		    	  profile.setTelUser(rs.getString("telUser"));
		    	  profile.setAddressUser(rs.getString("addressUser"));
		    	  profile.setUsernameUser(rs.getString("usernameUser"));
		    	  profile.setPasswordUser(rs.getString("passwordUser"));
		    	  profiles.add(profile);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return profiles;
	}

	public Profile getUserById(String id) {
		Profile profile = new Profile();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from users where userid=?");
	        
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	profile.setUserID(rs.getString("userID"));
	        	profile.setRoleUser(rs.getString("roleUser"));
	        	profile.setNameUser(rs.getString("nameUser"));
	        	profile.setEmailUser(rs.getString("emailUser"));
	        	profile.setTelUser(rs.getString("telUser"));
	        	profile.setAddressUser(rs.getString("addressUser"));
	        	profile.setUsernameUser(rs.getString("usernameUser"));
	        	profile.setPasswordUser(rs.getString("passwordUser"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return profile;
	}
	
	public void updateProfile(Profile profile) {
		
        userid = profile.getUserID();
        roleuser = profile.getRoleUser();
        name = profile.getNameUser();
        email = profile.getEmailUser();
        tel = profile.getTelUser();
        address = profile.getAddressUser();
        username = profile.getUsernameUser();
        password = profile.getPasswordUser();

	    String searchQuery = "UPDATE users SET roleuser='" + roleuser + "' , nameuser='" + name  + "' , emailuser='" + email + "' , teluser='" + tel  + "' , addressuser='" + address + "' , usernameuser='" + username + "' , passworduser='" + password + "' WHERE userid= '" + userid + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
}
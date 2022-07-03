package sims.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sims.model.User;

public class ConnectionManager {
	
	static Connection con;

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "TEST";
	private static final String DB_PASSWORD = "1234";
	
    public static Connection getConnection() {

        try {

            Class.forName(DB_DRIVER);

            try {
                con = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
                System.out.println("connected");

            }

            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return con;
    }
    
  //staffLogin
  	public User login(String userID, String passwordUser) throws SQLException {
  		ConnectionManager cm = new ConnectionManager();
  		Connection con = cm.getConnection();
  		
  		try {
  			String sql = "select * from USERS where USERID=? and PASSWORDUSER=?";
  			PreparedStatement ps = con.prepareStatement(sql);
  			
  			ps.setString(1, userID);
  			ps.setString(2, passwordUser);
  			ps.executeQuery();
  			
  			ResultSet rs = ps.executeQuery();
  			
  			if(rs.next()) {	
  				User user = new User();
  				user.setUserID(rs.getString("USERID"));	
  				user.setPasswordUser(rs.getString("PASSWORDUSER"));	
  				user.setRoleUser(rs.getString("ROLEUSER"));
  				user.setUsernameUser(rs.getString("USERNAMEUSER"));	
  				return user;
  			}
  		}
  		catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally{
  			con.close();
  		}
  		return null;
  	}


}

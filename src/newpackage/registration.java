/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Owner
 */
public class registration {
  conn con = new conn();
    
    public int register(String username, String pass, String first_name, String last_name,String add){
   
    int x = 0;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);
        String sql = "insert into log_in values(?,md5(?),?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        
        pstmt.setString(1, username);
        pstmt.setString(2, pass);
        pstmt.setString(3, first_name);
        pstmt.setString(4, last_name);
         pstmt.setString(5, add);
        x = pstmt.executeUpdate();
        
       
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    return x;
    }
    
    public int confirmPassword(String password, String confirmpassword){
        int x = 0;
        
        if(password.equals(confirmpassword)){
            x = 1;
        }else{
            x = 0;
        }
        return x;
    }
    
    public int checkUsername(String username){
        int x = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url, con.username, con.password);
        
            String sql = "SELECT username FROM log_in WHERE username = ?;";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                x = 1;
            }else{
                x = 0;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
      
}

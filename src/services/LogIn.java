/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author ferha
 */
public class LogIn {
    
    private Connection connection;
    private JDBCConnection jdbccon;
    private PreparedStatement ps;
    private String sql;
    private String countUsers;
    private ResultSet rs;
    
    
    public LogIn(){
        this.jdbccon = new JDBCConnection();
        this.connection = new JDBCConnection().getConnection();
    }
    
    
    public void authenticateUser(User user){
        
        try {
            
            sql = "SELECT * FROM useraccount WHERE username=? AND password=?";
            
            try {
                ps = connection.prepareStatement(sql);
                
                ps.setString(1, user.getuserName());
                ps.setString(2, user.getPassword());
                
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Signed up");
                } else{
                    JOptionPane.showMessageDialog(null, "Error");
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

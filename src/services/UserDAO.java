
package services;


import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;
import services.JDBCConnection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.UserRole;

public class UserDAO {
    private Connection connection;
    private JDBCConnection jdbccon;
    private PreparedStatement psuser, pscount, pslogin;
    private String sql;
    private String countUsers;
    private ResultSet rs;
    
    
    public UserDAO(){
        this.jdbccon = new JDBCConnection();
        this.connection = new JDBCConnection().getConnection();
    }
    
    
    public void addUser(User user){
        try {
            
            sql = "INSERT INTO users (username, password, role) VALUES(?,?,?)";
            
            try {
                
               psuser = connection.prepareStatement(sql);
                
               String salt = generateSalt();
               
               String saltedPassword = user.getPassword() + salt;
               
               
                psuser.setString(1, user.getuserName());
                psuser.setString(2, saltedPassword);
                
                if (CountExistingUser() == 0) {
                    psuser.setString(3, UserRole.ADMIN.name());
                } else{
                    psuser.setString(3, UserRole.CLIENT.name());
                }
                
                int success = psuser.executeUpdate();
                
                
                if (success > 0) {
                    System.out.println("Added Successfully"); 
                } else{
                    System.out.println("Failed");
                }
                
                
            } catch (Exception e) {
                 e.printStackTrace();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    
    
    private String generateSalt(){
        
        //Use a strong ranmdom number generator
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        
        //convert the byte array to a hexadecimal string
        StringBuilder salt = new StringBuilder();
        
        for (byte b : saltBytes) {
            salt.append(String.format("%02x", b));
        }
        
        return salt.toString();
    }
    
    private int CountExistingUser(){
        int count = 0;
        
        try {
            countUsers = "SELECT COUNT(*) FROM users";
            
            try {
                pscount = connection.prepareStatement(countUsers);
                
                rs = pscount.executeQuery();
                
                if(rs.next()){
                    count = rs.getInt(1);
                }
                
                
                
            } catch (Exception e) {
               e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return count;
    }
    
    
      public void authenticateUser(User user){
        
        try {
            
            sql = "SELECT * FROM users WHERE username=? AND password=?";
            
            try {
                pslogin = connection.prepareStatement(sql);
                
                pslogin.setString(1, user.getuserName());
                pslogin.setString(2, user.getPassword());
                
                rs = pslogin.executeQuery();
                
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

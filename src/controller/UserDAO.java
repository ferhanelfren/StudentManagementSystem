
package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;
import services.JDBCConnection;
import java.sql.ResultSet;
import model.UserRole;

public class UserDAO {
    private Connection connection;
    private JDBCConnection jdbccon;
    private PreparedStatement psuser, pscount;
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
                
                psuser.setString(1, user.getuserName());
                psuser.setString(2, user.getPassword());
                
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
    
}

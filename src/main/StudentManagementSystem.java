
package main;

import java.sql.Connection;
import services.JDBCConnection;

public class StudentManagementSystem {
    
    private static void jdbcConnection(){
        JDBCConnection jdbcCon = new JDBCConnection();
        
        try {
            Connection connection = jdbcCon.getConnection();
            
            if (connection != null) {
                System.out.println("Successfully Connected to Database . . . . ");
            } else{
                System.out.println("Failed to Connect to the Database . . . ");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
       
        Login login = new Login();
        login.setVisible(true);
        
        jdbcConnection();
    }
    
}

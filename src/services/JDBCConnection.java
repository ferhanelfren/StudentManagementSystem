
package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    
   public Connection getConnection(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           return DriverManager.getConnection(
                   "jdbc:mysql://127.0.0.1:3306/studentmanagementsystem",
                   "root",
                   "root");
           
       } catch (Exception e) {
           System.out.println(e);
       }
       return null;
   }
}


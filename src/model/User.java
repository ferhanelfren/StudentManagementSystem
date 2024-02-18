
package model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String role;
    
    public int getuserId(){
        return userId;
    }
    
    public String getuserName(){
        return userName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRole(){
        return role;
    }
    
    //SETTERS - - -- - - - - -- - 
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    
}


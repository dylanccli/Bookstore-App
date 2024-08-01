/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

/**
 *
 * @author Dylan
 */
public class Customer {
    
    private String username; //customer username
    private String password; //customer password
    private int points; //customer points
    
    public Customer() {
        this.username = "";
        this.password = "";
        this.points = 0;
    }
    
    public Customer(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
    }
    
    //setters and getters
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
}

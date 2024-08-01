/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Dylan
 */
public class Book {
    
    /*
        www.youtube.com/watch?v=aE3XwpHOeG8
        the above youtube video helped me understand how CheckBox's work
    */
    
    private String name; //book name
    private double price; //book price
    private CheckBox select; //book checkbox
    
    public Book() { 
        this.name = "";
        this.price = 0.00;
    }
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
    }
    
    //setters and getters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public CheckBox getSelect() {
        return select;
    }
    
    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
}

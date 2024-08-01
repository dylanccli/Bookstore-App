/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class CustomerBuyScreen implements State{
    
    Manager m = new Manager(); //can use this to call methods in the Manager class
    Utils u = new Utils(); //can use this to call methods in the Utils class
    
    Stage stage = m.getStage(); //returns the current stage
    
    GridPane customerBuyStartScreenGridPane = new GridPane(); //creates a new grid pane for the customer-cost-screen gui scene/screen
    Scene customerBuyStartScreenScene = new Scene(customerBuyStartScreenGridPane); //creates a new scene/screen for the customer-cost-screen gui with gridpane customerBuyStartScreenGridPane
    
    //state pattern design
     public void doAction(SetState ss) {
        System.out.println("    -> user is in the customer-buy-screen state");
        ss.setState(this);	
    }
    
     public void buyScreen(int customerID, String username, int points, double price) {
        //updating the customers point total in the customers list
        m.getCustomers().set(customerID, new Customer(m.getCustomers().get(customerID).getUsername(),
                                                      m.getCustomers().get(customerID).getPassword(),
                                                      points));

        //creating a string "status" that returns "Gold" or "Silver" depending on the customer's point total
        //(1000 points or more for Gold, 0-999 points for Silver)
        String status = u.getStatus(points);
        
        //customizing a new gui
        u.customizeGUI(customerBuyStartScreenGridPane, "-fx-background-color: LAVENDER;", 500, 250, 20, 0, Pos.CENTER);
        
        //creating a new text line
        Text totalCostText = u.createTextLine("Your total cost is $" + price + " ", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text pointsAndStatusText = u.createTextLine("Your new point total is " + points + ". Your status is " + status, "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        
        //creating a button
        Button logoutButton = u.createButton("Logout","-fx-background-color: white; -fx-text-fill: black;",100,15);
        
        //putting the created text lines and button in the customerRedeemAndBuyStartScreenGridPane
        customerBuyStartScreenGridPane.add(totalCostText, 0, 0);
        customerBuyStartScreenGridPane.add(pointsAndStatusText, 0, 1);
        customerBuyStartScreenGridPane.add(logoutButton, 2,2);
                
        //setting the scene/screen to customer-start-screen
        stage.setScene(customerBuyStartScreenScene);
        
        //what happens when the user clicks logout
        logoutButton.setOnAction(e -> {

            LoginScreen login = new LoginScreen();
            SetState ss = new SetState();
                
            login.doAction(ss);
            m.getStage().setScene(login.loginScreenScene);
            
        });
    }
     
    
}

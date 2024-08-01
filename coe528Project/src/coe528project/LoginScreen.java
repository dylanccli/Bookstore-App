/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */

/*
    This screen is accessed after starting the program or clicking a logout button
*/

public class LoginScreen implements State {
    
    Manager m = new Manager(); //allows me to call methods in the Manager class
    Utils u = new Utils(); //allows me to call methods in the Utils class
    TextFiles textFiles = new TextFiles(); //allows me to call methods in the TextFiles class
    
    Stage stage = m.getStage(); //returns the current stage
    GridPane loginScreenGridPane = new GridPane(); //creates a new grid pane for this gui scene/screen
    //creates a new scene/screen for this gui with gridpane loginScreenGridPane
    Scene loginScreenScene = new Scene(loginScreenGridPane); 
    
    
    public void doAction(SetState ss) {
        System.out.println("-> user is in the login-screen state");
        ss.setState(this);	
        loginGUI();
        m.getStage().setScene(loginScreenScene);
        stage.show();
    }
    
    //used to determine if a customer has entered a valid username & password combination
    public boolean checkLoginDetails(String enteredUsername, String enteredPassword) {
        //loops through i amount of times with i being the amount of customers in the customers list
        for (int i = 0; i < m.getCustomers().size(); i++) { 
            //if a valid username is entered and its corresponding password is also entered this returns true
            if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(enteredUsername)
                    && m.getCustomers().get(i).getPassword().equals(enteredPassword)) {
                return true;
            }
        }
        return false;
    }
    
    
    public void loginGUI() { //creating and customizing the login-screen
        
        //customizing a new gui
        u.customizeGUI(loginScreenGridPane, "-fx-background-color: LAVENDER;", 500, 250, 5, 0, Pos.CENTER);
        
        //creating a new text line
        Text greetingText = u.createTextLine("Welcome to the BookStore App", "-fx-font: normal bold 17px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text usernameText = u.createTextLine("Username:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text passwordText = u.createTextLine("Password:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text loginErrorText = u.createTextLine("incorrect username and password\ncombination please try again ... ", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        
        
        //creating a new text field
        //creating a new text field
        TextField usernameInput = new TextField();
        //this text field (PasswordField) shows characters as a shape (black opaque circles) instead of the actual character  
        PasswordField passwordInput = new PasswordField();
        
        //creating a new button
        Button loginButton = u.createButton("Login", "-fx-background-color: white; -fx-text-fill: black;", 5, 5);
        
        //putting the created text lines, fields, and button in the loginScreenGridPane
        loginScreenGridPane.add(greetingText, 0, 0);
        loginScreenGridPane.add(usernameText, 0, 4);
        loginScreenGridPane.add(usernameInput, 1, 4);
        loginScreenGridPane.add(passwordText, 0, 5);
        loginScreenGridPane.add(passwordInput, 1, 5);
        loginScreenGridPane.add(loginButton, 1, 6);
        
       
        //what happens when the user clicks login
        loginButton.setOnAction(e -> {
            
            //if the user enters user name and password "admin"
            if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
                //goes to the scene/screen owner-start-screen
                OwnerStartScreen oStart = new OwnerStartScreen();
                oStart.doAction(m.ss);
                
                
                
             //   return;
            //if the user enters a valid customer username and password combination
            } else if (this.checkLoginDetails(usernameInput.getText(), passwordInput.getText())) {
                //goes to the scene/screen customer-start-screen
                
                CustomerStartScreen c = new CustomerStartScreen();
                c.doAction(m.ss);
                c.cStart(usernameInput.getText());
                

                
                
                
            //    return;

            //if the user enters an incorrect username and password combination
            } else { 
                //if the incorrect username and password message is not on the screen
                if (!loginScreenGridPane.getChildren().contains(loginErrorText)) {
                    //display the incorrect username and password message on the screen
                    loginScreenGridPane.add(loginErrorText, 0, 6);
                }
                //remove the username and password inputs from the text fields 
                //usernameInput and passwordInput whenever the user enters an incorrect customer login
                usernameInput.setText("");
                passwordInput.setText("");
            }
        });
    }
    
}

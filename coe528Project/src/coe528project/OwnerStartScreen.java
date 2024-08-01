/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class OwnerStartScreen implements State {

    Manager m = new Manager(); //allows me to call methods in the Manager class
    Utils u = new Utils(); //allows me to call methods in the Utils class

    Stage stage = m.getStage(); //allows me to call methods in the TextFiles
    GridPane ownerStartScreenGridPane = new GridPane(); //creates a new grid pane for this gui scene/screen
    Scene ownerStartScreenScene = new Scene(ownerStartScreenGridPane); //creates a new scene/screen for this gui with gridpane ownerStartScreenGridPane

    //state pattern design
    public void doAction(SetState ss) {
        System.out.println("  -> user is in the owner-start-screen state");
        ss.setState(this);	
        ownerStartScreen();
        m.getStage().setScene(ownerStartScreenScene);
    }
    
    
    public void ownerStartScreen() {
        
        //customizing a new gui
        u.customizeGUI(ownerStartScreenGridPane, "-fx-background-color: LAVENDER;", 500, 250, 20, 0, Pos.CENTER);
        //creating a new button
        Button booksButton = u.createButton("Books", "-fx-background-color: white; -fx-text-fill: black;", 100, 15);
        //creating a new button
        Button customersButton = u.createButton("Customers", "-fx-background-color: white; -fx-text-fill: black;", 100, 15);
        //creating a new button
        Button logoutButton = u.createButton("Logout", "-fx-background-color: white; -fx-text-fill: black;", 100, 15);

        //putting the created buttons in the ownerStartScreenGridPane
        ownerStartScreenGridPane.add(booksButton, 0, 0);
        ownerStartScreenGridPane.add(customersButton, 0, 1);
        ownerStartScreenGridPane.add(logoutButton, 0, 2);

        //setting the scene/screen to owner-start-screen

        //what happens when the user clicks books
        booksButton.setOnAction(e -> {
            //goes to the scene/screen owner-books-screen
           OwnerBooksScreen ownerBooksScreen = new OwnerBooksScreen();
                ownerBooksScreen.doAction(m.ss);
                
        });

        //what happens when the user clicks customers
        customersButton.setOnAction(e -> {
            //goes to the scene/screen owner-customers-screen
            OwnerCustomersScreen ownerCustomersScreen = new OwnerCustomersScreen();
            ownerCustomersScreen.doAction(m.ss);
          
        });

        //what happens when the user clicks logout
        logoutButton.setOnAction(e -> {
            //goes to the scene/screen owner-start-screen
            LoginScreen login = new LoginScreen();
            login.doAction(m.ss);
            
        });
    }
}

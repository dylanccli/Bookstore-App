/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class CustomerStartScreen implements State {
    
   
    Manager m = new Manager(); //can use this to call methods in the Manager class
    Utils u = new Utils(); //can use this to call methods in the Utils class
    LoginScreen loginScreen = new LoginScreen();
    
    Stage stage = m.getStage(); //returns the current stage
    
    GridPane customerStartScreenGridPane = new GridPane(); //creates a new grid pane for the customer-start-screen gui scene/screen
    Scene customerStartScreenScene = new Scene(customerStartScreenGridPane); //creates a new scene/screen for the customer-start-screen gui with gridpane customerStartScreenGridPane

    //state design pattern
    public void doAction(SetState ss) {
        System.out.println("  -> user is in the customer-start-screen state");
        ss.setState(this);	
    }
    
    
    //this method handles the customer-start-screen           
    public void cStart(String username) {

        String uName = ""; //creating a new string "uName" 
        int uPoints = 0; //creating a new int "uPoints"
        for (int i = 0; i < m.getCustomers().size(); i++) { //loop through all customers in customers list
            //if a customers username in the customers list at index "i" is equal to the String "username"
            if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(username)) { 
                uName = m.getCustomers().get(i).getUsername(); //set "uName" to the customers username in the customers list at index "i"
                uPoints = m.getCustomers().get(i).getPoints(); //set "uPoints" to the customers username in the customers list at index "i"
            }
        }
        
        //customizing a new gui
        u.customizeGUI(customerStartScreenGridPane, "-fx-background-color: LAVENDER;", 500, 250, 15, 15, Pos.CENTER);
        
        //creating a new text line
        Text welcomeText = u.createTextLine("Welcome " +uName + ". You have " + uPoints + " Points. Your status is " + u.getStatus(uPoints), "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text emptyCart = u.createTextLine("Please select a book/books to purchase first ... ", "-fx-font: normal 15px 'serif' ", Color.ORANGERED);    
        //creating a new button
        Button buyButton = u.createButton("Buy","-fx-background-color: white; -fx-text-fill: black;",100,15);
        //creating a new button
        Button redeemPointsAndBuyButton = u.createButton("Redeem Points and Buy","-fx-background-color: white; -fx-text-fill: black;",100,15);
        //creating a new button
        Button logoutButton = u.createButton("Logout","-fx-background-color: white; -fx-text-fill: black;",100,15);
        //creating a new table that stores books
        TableView<Book> table = new TableView<>();
        
        //creating a new table column
        TableColumn<Book, String> bookColumn = new TableColumn<>("Name");
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //creating a new table column
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //creating a new table column
        TableColumn<Book, CheckBox> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        //adding the created table columns to the table
        table.getColumns().add(bookColumn);
        //adding the created table columns to the table
        table.getColumns().add(priceColumn);
        //adding the created table columns to the table
        table.getColumns().add(selectColumn);
        
        //stop users from resizing table columns
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //setting the minimum width and maximum height of the table
        table.setMinWidth(300);
        table.setMaxHeight(200);
        
        
        for (int i = 0; i < m.getBooks().size(); i++) { //loop through the list books
            //add all books in the books list to the table
            table.getItems().add(new Book(m.getBooks().get(i).getName(), m.getBooks().get(i).getPrice()));
        }
        
        
     
        //putting the created table, text lines, fields, and buttons in the customerStartScreenGridPane
        customerStartScreenGridPane.add(welcomeText, 0, 0);
        customerStartScreenGridPane.add(table, 0, 1);
        customerStartScreenGridPane.add(buyButton, 0,5);
        customerStartScreenGridPane.add(redeemPointsAndBuyButton, 0, 8);
        customerStartScreenGridPane.add(logoutButton, 3, 12);
        
        stage.setScene(this.customerStartScreenScene);
        
        //what happens when the user clicks buy
        buyButton.setOnAction((ActionEvent e) -> {
            double price = 0; //create double "price"
            for (int i = 0; i < table.getItems().size(); i++) { //loop through the entire table one row at a time
                //creating a new Book "b" to denote each row in the table "table"
                Book b = table.getItems().get(i);
                //creating a new CheckBox "c" to denote the check box at row b
                CheckBox c = b.getSelect();
                //if c is selected, add the price of book b to the double "price"
                if (c.isSelected()) {
                    price = price + b.getPrice();
                }
            }
            
            if (price > 0) { //if the total price of the purchase is greater than $0.00

                int customerID = 0; //creating a new int "customerID"
                int points = 0; //creating a new int "points"

                for (int i = 0; i < m.getCustomers().size(); i++) { //loop through all customers in customers list
                    //if a customers username in the customers list at index "i" is equal to the String "username"
                    if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(username)) {
                    
                    points = m.getCustomers().get(i).getPoints();//set "points" to the customers points in the customers list at index "i"
                    customerID = i;//set "customerID" to index "i"
                    }
                }
        
                double a = price * 10; //create a double "a" that is 10 times larger than price
                int j = (int) Math.round(a); //create an int "j" that rounds the double "a" (have to do this since points is an int
                points = points + j; //set the customers new points total to itself plus "j"
                
                CustomerBuyScreen b = new CustomerBuyScreen();
                b.doAction(m.ss);
                b.buyScreen(customerID, username, points, price);
                
                
                
            } else { //if the user has not selected a book to purchase and clicks the buy button
                 if (!customerStartScreenGridPane.getChildren().contains(emptyCart)) //if the screen is not displaying the empty cart message
                     customerStartScreenGridPane.add(emptyCart, 0, 12); //display the empty cart message
            }
        });
        
        //what happens when the user clicks redeem points and buy
        redeemPointsAndBuyButton.setOnAction(e -> {
            double price = 0; //create double "price"
            for (int i = 0; i < table.getItems().size(); i++) { //loop through the entire table one row at a time
                //creating a new Book "b" to denote each row in the table "table"
                Book b = table.getItems().get(i);
                //creating a new CheckBox "c" to denote the check box at row b
                CheckBox c = b.getSelect();
                //if c is selected, add the price of book b to the double "price"
                if (c.isSelected()) {
                    price = price + b.getPrice();
                }
            }
            if (price > 0) { //if the total price of the purchase is greater than $0.00
                
                int customerID = 0; //creating a new int "customerID"
                int points = 0; //creating a new int "points" 
        
                for (int i = 0; i < m.getCustomers().size(); i++) { //loop through all customers in customers list
                //if a customers username in the customers list at index "i" is equal to the String "username"
                if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(username)) { 
                    
                    points = m.getCustomers().get(i).getPoints();//set "points" to the customers points in the customers list at index "i"
                    customerID = i;//set "customerID" to index "i"
                }
            }
        
            //as long as the total price is larger than $0.00 and the customers points are 100 or more
            while (price > 0 && points >= 100) {
                price = price - 1; //subtract $1.00 from the price
                points = points - 100; //subtract 100 points from the customers point total
            }
            //if the price is larger than $0.00 and the customers has no points
            if (price > 0) { // && points == 0) {
                double a = price * 10; //create a double "a" that is 10 times larger than price
                int j = (int) Math.round(a); //create an int "j" that rounds the double "a" (have to do this since points is an int
                points = points + j; //set the customers new points total to itself plus "j"
            }
        
            CustomerBuyScreen b = new CustomerBuyScreen();
                b.doAction(m.ss);
                b.buyScreen(customerID, username, points, price);
        
            
                
            }else { //if the user has not selected a book to purchase and clicks redeem points and buy button
                 if (!customerStartScreenGridPane.getChildren().contains(emptyCart)) //if the screen is not displaying the empty cart message
                     customerStartScreenGridPane.add(emptyCart, 0, 12); //display the empty cart message
            }
        });
        
        //what happens when the user clicks logout
        logoutButton.setOnAction(e -> {
            //goes to the scene/screen login-screen   
            LoginScreen login = new LoginScreen();
            login.doAction(m.ss);           
            
        });
        
    }
    
}

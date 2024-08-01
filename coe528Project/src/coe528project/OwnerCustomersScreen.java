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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dylan
 */

/*
    this screen is accessed after an admin selects the customers option on the owner-start-screen
 */
public class OwnerCustomersScreen implements State{

    Manager m = new Manager(); //allows me to call methods in the Manager class
    Utils u = new Utils(); //allows me to call methods in the Utils class
    TextFiles textFiles = new TextFiles(); //allows me to call methods in the TextFiles

    Stage stage = m.getStage(); //returns the current stage
    GridPane ownerCustomersScreenGridPane = new GridPane(); //creates a new grid pane for this gui scene/screen
    //creates a new scene/screen for this gui with gridpane ownerCustomersScreenGridPane
    Scene ownerCustomersScreenScene = new Scene(ownerCustomersScreenGridPane);

    public void doAction(SetState ss) {
        System.out.println("    -> user is in the owner-customers-screen state");
        ss.setState(this);	
        ownerCustomersScreen();
        m.getStage().setScene(ownerCustomersScreenScene);
    }
    
    //method is called when I need to reset two text fields [(username and password) or (bookname and bookprice)]
    public void resetTexts(TextField a, TextField b) {
        //remove the inputs from the text fields 
        a.setText("");
        b.setText("");
    }

    public void ownerCustomersScreen() {

        //customizing a new gui
        u.customizeGUI(ownerCustomersScreenGridPane, "-fx-background-color: LAVENDER;", 500, 500, 15, 15, Pos.CENTER);

        //creating a new text field
        TextField usernameInput = new TextField();
        //creating a new text field
        TextField passwordInput = new TextField();

        //creating a new text line
        Text usernameText = u.createTextLine("Username:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text passwordText = u.createTextLine("Password:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text customersText = u.createTextLine("Current\nCustomers:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text addUserErrorText = u.createTextLine("please enter a valid username and password\nplease try again ... ", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text addUserDuplicateText = u.createTextLine("this username already exists\nplease try again ... ", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text addUserSuccessText = u.createTextLine("successfully added a new user", "-fx-font: normal bold 12px 'serif' ", Color.GREEN);
        //creating a new text line
        Text addUserSelectErrorText = u.createTextLine("select a user to delete first ...", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text deleteUserSuccessText = u.createTextLine("successfully deleted user", "-fx-font: normal bold 12px 'serif' ", Color.GREEN);

        //creating a new button
        Button addButton = u.createButton("Add", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);
        //creating a new button
        Button deleteButton = u.createButton("Delete", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);
        //creating a new button
        Button backButton = u.createButton("Back", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);

        //creating a new table that stores customers
        TableView<Customer> table = new TableView<>();

        //creating a new table column
        TableColumn<Customer, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        //creating a new table column
        TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        //creating a new table column
        TableColumn<Customer, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        //adding the created table columns to the table
        table.getColumns().add(usernameColumn);
        table.getColumns().add(passwordColumn);
        table.getColumns().add(pointsColumn);

        //stop users from resizing table columns
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //setting the minimum width and maximum height of the table
        table.setMinWidth(300);
        table.setMaxHeight(200);

        for (int i = 0; i < m.getCustomers().size(); i++) { //loop through the list customers
            //add all customers in the customers list to the table
            table.getItems().add(new Customer(m.getCustomers().get(i).getUsername(), m.getCustomers().get(i).getPassword(), m.getCustomers().get(i).getPoints()));
        }

        //putting the created table, text lines, fields, and buttons in the ownerCustomersScreenGridPane
        ownerCustomersScreenGridPane.add(customersText, 0, 0);
        ownerCustomersScreenGridPane.add(table, 1, 0);
        ownerCustomersScreenGridPane.add(usernameText, 0, 3);
        ownerCustomersScreenGridPane.add(passwordText, 0, 4);
        ownerCustomersScreenGridPane.add(usernameInput, 1, 3);
        ownerCustomersScreenGridPane.add(passwordInput, 1, 4);
        ownerCustomersScreenGridPane.add(addButton, 1, 5);
        ownerCustomersScreenGridPane.add(deleteButton, 1, 10);
        ownerCustomersScreenGridPane.add(backButton, 2, 10);

        //what happens when the user clicks add
        addButton.setOnAction((ActionEvent e) -> {

            //boolean to check if the usernameInput field contains any spaces
            boolean usernameHasSpaces = u.containsSpaces(usernameInput.getText());
            //boolean to check if the passwordInput field contains any spaces
            boolean passwordHasSpaces = u.containsSpaces(passwordInput.getText());

            if (ownerCustomersScreenGridPane.getChildren().size() > 9) {
                ownerCustomersScreenGridPane.getChildren().remove(9);
            }

            /*
                Check if the usernameInput is empty, contains spaces, or is equal to "admin"
                And
                Check if the passwordInput is empty or contains spaces
            
                A valid customer input 
                Has a usernameInput that is not empty, does not contains spaces, and does not equal "admin"
                And
                has a passwordInput that is not empty or contain spaces
             */
            
            //checks for valid customer input
            if (usernameInput.getText() == null || usernameInput.getText().isEmpty() || usernameHasSpaces || usernameInput.getText().equals("admin")
                    || passwordInput.getText() == null || passwordInput.getText().isEmpty() || passwordHasSpaces) {

                //customer input is invalid so add the invalid customer entry error message
                ownerCustomersScreenGridPane.add(addUserErrorText, 1, 6);
                //resets the text field usernameInput and passwordInput
                resetTexts(usernameInput, passwordInput);
            } else { //the user entered a valid customer input
                for (int i = 0; i < m.getCustomers().size(); i++) { //loop through the customers list
                    if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(usernameInput.getText())) { //if the username exists
                        //usernameInput already exists so add the add duplicate customer message
                        ownerCustomersScreenGridPane.add(addUserDuplicateText, 1, 6);
                        //resets the text field usernameInput and passwordInput
                        resetTexts(usernameInput, passwordInput);
                        return; //needed so that the code below doesnt add the duplicate customer to the list and table
                    }
                }

                //usernameInput is valid so add the success add customer message
                ownerCustomersScreenGridPane.add(addUserSuccessText, 1, 6);
                //adding the new customer to the table   
                table.getItems().add(new Customer(usernameInput.getText(), passwordInput.getText(), 0));
                //adding the new customer to the customers list 
                m.getCustomers().add(new Customer(usernameInput.getText(), passwordInput.getText(), 0));
                //resets the text field usernameInput and passwordInput
                resetTexts(usernameInput, passwordInput);
            }
        });

        //what happens when the user clicks delete
        deleteButton.setOnAction(e -> {
            /*
                By default ownerBooksScreenGridPane.getChildren().size() should return 8
                unless an error or success message was added to the gui screen.
                If ownerBooksScreenGridPane.getChildren().size() > 9 an error or success message
                is on the screen and must be removed before we potentially add another error or success message
             */
            if (ownerCustomersScreenGridPane.getChildren().size() > 9) {
                ownerCustomersScreenGridPane.getChildren().remove(9);
            }
            //get the selected customer in the table
            Customer selectedItem = table.getSelectionModel().getSelectedItem();

            //if the user has not yet selected a customer in the table
            if (selectedItem == null) {
                //customer selection is invalid so add the customer select error message
                ownerCustomersScreenGridPane.add(addUserSelectErrorText, 1, 6);
                return; //needed so that the code below does not throw a NullPointerException
            }

            //remove the customer from the table
            table.getItems().remove(selectedItem);

            //remove from arraylist
            for (int i = 0; i < m.getCustomers().size(); i++) { //loop through the customers list
                //loop through the customers list until we find the selected customer
                if (m.getCustomers().get(i).getUsername().equalsIgnoreCase(selectedItem.getUsername())) {
                    //remove the customer from the customers list
                    m.getCustomers().remove(i);
                }
            }

            //customer selection is valid so add the delete success message
            ownerCustomersScreenGridPane.add(deleteUserSuccessText, 1, 6);
        });

        //what happens when the user clicks back
        backButton.setOnAction(e -> {
            //goes to the scene/screen owner-start-screen
            
            OwnerStartScreen oStart = new OwnerStartScreen();
            oStart.doAction(m.ss);
            
        });

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import java.text.DecimalFormat;
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
    this screen is accessed after an admin selects the books option on the owner-start-screen
*/
public class OwnerBooksScreen implements State {
   
    Manager m = new Manager(); //allows me to call methods in the Manager class
    Utils u = new Utils(); //allows me to call methods in the Utils class
    TextFiles textFiles = new TextFiles(); //allows me to call methods in the TextFiles class
    
    Stage stage = m.getStage(); //returns the current stage
    GridPane ownerBooksScreenGridPane = new GridPane(); //creates a new grid pane for this gui scene/screen
    //creates a new scene/screen for this gui with gridpane ownerBooksScreenGridPane
    Scene ownerBooksScreenScene = new Scene(ownerBooksScreenGridPane);

    public void doAction(SetState ss) {
        System.out.println("    -> user is in the owner-books-screen state");
        ss.setState(this);
        ownerBooksScreen();
        m.getStage().setScene(ownerBooksScreenScene);
    }
    
    public void resetTexts(TextField a, TextField b) {
        //remove the inputs from the text fields 
        a.setText("");
        b.setText("");
    }
    
    public void ownerBooksScreen() { //creating and customizing the owner-books-screen
        
        //customizing a new gui
        u.customizeGUI(ownerBooksScreenGridPane, "-fx-background-color: LAVENDER;", 500, 500, 15, 15, Pos.CENTER);
        
        //creating a new text field
        TextField bookNameInput = new TextField();
        //creating a new text field
        TextField bookPriceInput = new TextField();

        //creating a new text line
        Text BooksText = u.createTextLine("Current\nLibrary:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text nameText = u.createTextLine("Name:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text priceText = u.createTextLine("Price:", "-fx-font: normal 15px 'serif' ", Color.DARKORCHID);
        //creating a new text line
        Text addBookErrorText = u.createTextLine("please enter a valid book name and book price\nplease try again ... ", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text addBookDuplicateText = u.createTextLine("this book name already exists\nplease try again ... ", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text addBookSuccessText = u.createTextLine("successfully added a new book", "-fx-font: normal bold 12px 'serif' ", Color.GREEN);
        //creating a new text line
        Text addBookSelectErrorText = u.createTextLine("please select a book to delete first ...", "-fx-font: normal bold 12px 'serif' ", Color.ORANGERED);
        //creating a new text line
        Text deleteBookSuccessText = u.createTextLine("successfully deleted the book", "-fx-font: normal bold 12px 'serif' ", Color.GREEN);
        //creating a new button
        Button addButton = u.createButton("Add", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);
        //creating a new button
        Button deleteButton = u.createButton("Delete", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);
        //creating a new button
        Button backButton = u.createButton("Back", "-fx-background-color: white; -fx-text-fill: black;", 0, 0);
        
       
        /*
            www.youtube.com/watch?v=vego72w5kPU
            www.tutorialspoint.com/how-to-create-a-tableview-in-javafx
            The above youtube video and webpage helped me learn how to create a table in JavaFX
        */
        
        //creating a new table that stores books
        TableView<Book> table = new TableView<>();
        
        //creating a new table column
        TableColumn<Book, String> bookColumn = new TableColumn<>("Name");
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //creating a new table column
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //adding the created table columns to the table
        table.getColumns().add(bookColumn);
        table.getColumns().add(priceColumn);
        
        //stop users from resizing table columns
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //setting the minimum width and maximum height of the table
        table.setMinWidth(300);
        table.setMaxHeight(200);
        
        for (int i = 0; i < m.getBooks().size(); i++) { //loop through the list books
            //add all books in the books list to the table
            table.getItems().add(new Book(m.getBooks().get(i).getName(), m.getBooks().get(i).getPrice()));
        }
        
        //putting the created table, text lines, fields, and buttons in the ownerBooksScreenGridPane
        ownerBooksScreenGridPane.add(BooksText, 0, 0);
        ownerBooksScreenGridPane.add(table, 1, 0);
        ownerBooksScreenGridPane.add(nameText, 0, 3);
        ownerBooksScreenGridPane.add(priceText, 0, 4);
        ownerBooksScreenGridPane.add(bookNameInput, 1, 3);
        ownerBooksScreenGridPane.add(bookPriceInput, 1, 4);
        ownerBooksScreenGridPane.add(addButton, 1, 5);
        ownerBooksScreenGridPane.add(deleteButton, 1, 10);
        ownerBooksScreenGridPane.add(backButton, 2, 10); 
       
        
        //what happens when the user clicks add
        addButton.setOnAction((ActionEvent e) -> {
            
            //boolean to check if the bookPriceInput field contains any spaces
            boolean priceHasSpaces = u.containsSpaces(bookPriceInput.getText());
            //boolean to check if the bookPriceInput field contains a valid number
            boolean priceOnlyDigit = u.onlyDigits(bookPriceInput.getText());
            
            /*
                By default ownerBooksScreenGridPane.getChildren().size() should return 8
                unless an error or success message was added to the gui screen.
                If ownerBooksScreenGridPane.getChildren().size() > 9 an error or success message
                is on the screen and must be removed before we potentially add another error or success message
            */
            if (ownerBooksScreenGridPane.getChildren().size() > 9) {
                ownerBooksScreenGridPane.getChildren().remove(9);
            }
            
            /*
                Check if the bookNameInput is empty
                And
                Check if the bookPriceInput is empty, or has spaces, contains digits other than 1-9 (invalid number), and contains more than 1 decimal
            
                A valid book input 
                Has a bookNameInput that is not empty
                And
                has a bookPriceInput that is not empty, has no spaces, contains only digits 1-9, with only maximum 1 decimal
            */
            //checks for valid book input
            if (bookNameInput.getText() == null || bookNameInput.getText().isEmpty()
                    || bookPriceInput.getText() == null || bookPriceInput.getText().isEmpty() || priceHasSpaces || !priceOnlyDigit || u.hasMoreThanOneDot(bookPriceInput.getText())) {
                
                //book input is invalid so add the invalid book entry error message
                ownerBooksScreenGridPane.add(addBookErrorText, 1, 6);
                //resets the text fields bookNameInput and bookPriceInput
                resetTexts(bookNameInput, bookPriceInput);
            } else { //the user entered a valid book input
                for (int i = 0; i < m.getBooks().size(); i++) { //loop through the books list
                    if (m.getBooks().get(i).getName().equalsIgnoreCase(bookNameInput.getText())) { //if the book name exists
                        //bookNameInput already exists so add the book duplicate book message
                        ownerBooksScreenGridPane.add(addBookDuplicateText, 1, 6);
                        //resets the text fields bookNameInput and bookPriceInput
                        resetTexts(bookNameInput, bookPriceInput);
                        return; //needed so that the code below doesnt add the duplicate book to the list and table
                    }
                }
                
                //book input is valid so add the success add book message
                ownerBooksScreenGridPane.add(addBookSuccessText, 1, 6); 
                //creating a string "p" to store the text in the text field bookPriceInput
                String p = bookPriceInput.getText();
                
                /*
                    https://mkyong.com/java/java-display-double-in-2-decimal-points/
                    used the above link to make sure only 1 decimal is entered and there are only numbers up to the hundredths decimal
                */
                //need to use decimal formal in case a user enters "15.2345678"
                //we only want to store "15.23" as the price
                
                //creates a new decimal format "d"
                DecimalFormat d = new DecimalFormat("0.00");
               
                //creates a new double dd from the inputted string
                double dd = Double.parseDouble(p); 
                //formats the new double to the correct format
                String s = d.format(dd); 
                //creating the final double price from the string "s"
                double price = Double.parseDouble(s); 
                //adding the new book to the table
                
                
                table.getItems().add(new Book(bookNameInput.getText(), price));
                //adding the new book to the books list
                m.getBooks().add(new Book(bookNameInput.getText(), price));
                //resets the text fields bookNameInput and bookPriceInput
                resetTexts(bookNameInput, bookPriceInput);
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
            if (ownerBooksScreenGridPane.getChildren().size() > 9) {
                ownerBooksScreenGridPane.getChildren().remove(9);
            }
            
            //get the selected book in the table
            Book selectedItem = table.getSelectionModel().getSelectedItem();
            
            //if the user has not yet selected a book in the table
            if (selectedItem == null) {
                //book selection is invalid so add the book select error message
                ownerBooksScreenGridPane.add(addBookSelectErrorText, 1, 6);
                return; //needed so that the code below does not throw a NullPointerException
            }
            
            //remove the book from the table
            table.getItems().remove(selectedItem);
            
            for (int i = 0; i < m.getBooks().size(); i++) { //loop through the books list
                //loop through the books list until we find the selected book
                if (m.getBooks().get(i).getName().equalsIgnoreCase(selectedItem.getName())) {
                    //remove the book from the books list
                    m.getBooks().remove(i);
                }
            }
            
            //book selection is valid so add the delete success message
            ownerBooksScreenGridPane.add(deleteBookSuccessText, 1, 6);
        });
        
        
        //what happens when the user clicks back
        backButton.setOnAction(e -> {
            //goes to the scene/screen owner-start-screen
            OwnerStartScreen oStart = new OwnerStartScreen();
            oStart.doAction(m.ss);
            
            
        });
        
        }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Dylan
 */
public class Utils {

    /*
    public void replace(String fileName, String original, String remove) {
         try {
                PrintWriter writer = new PrintWriter(fileName); 
                String newText = original.replace(remove, "");
                writer.print("");
                writer.write(newText);
                writer.close();

            } catch (IOException e) {
                System.out.println("An error occurred");
            }
    }    */
 /*
        https://stackoverflow.com/questions/4067809/how-can-i-find-whitespace-in-a-string
     */
    //can use this method to know if a string contains any spaces (usernames, passwords, and prices cant have spaces)
    public boolean containsSpaces(final String str) {
        if (str != null) { //if the input is not null
            for (int i = 0; i < str.length(); i++) { //loop through every char of the input
                //if any char of the input is a white space we know there is a space
                if (Character.isWhitespace(str.charAt(i))) {
                    return true; //return true since a space was entered
                }
            }
        }
        return false; //returns false since a space was not 
    }

    /*
        https://www.geeksforgeeks.org/how-to-check-if-string-contains-only-digits-in-java/
        used the above link to know how to make sure only digits are in the entered string
     */
    //can use this method to know if a string contains any only digits (prices must be only digits)
    public boolean onlyDigits(String str) {
        if (str != null) { //if the input is not null
            for (int i = 0; i < str.length(); i++) //loop through every char of the input
            //if any char of the input is a non-digit we know that the input is not a digit
            {
                return Character.isDigit(str.charAt(i));
            }
        }

        return false;
    }

    //can use this method to know if a string contains more than one decimal place (prices can only have maximum 1 decimal)
    public boolean hasMoreThanOneDot(String str) {
        int n = 0; //create a counter integer "n"
        for (int i = 0; i < str.length(); i++) { //loop through all elements of the inputted string "str"
            if (str.charAt(i) == '.') {  //if the char is ".", we add to the counter
                n++;
            }
        }
        if (n > 1) { //if the counter counted more than one decimals return true
            return true;
        } else if (n <= 1) { //if the counter counted less than 2 decimals return false
            return false;
        }
        return false;
    }

    //can use this method to get the status of a customer given the amount of points
    public String getStatus(int points) {
        String status; //creates a string "status"
        if (points >= 1000) { //if the user has 1000 points or more return "Gold" status
            status = "Gold";
        } else {
            status = "Silver"; //if the user has less than 1000 points return "Silver" status
        }
        return status;
    }

    //can use this method to create a button
    public Button createButton(String buttonText, String buttonStyle, double minWidth, double minHeight) {
        Button newButton = new Button(buttonText); //create a new button with the name "newButton"
        newButton.setStyle(buttonStyle); //set the buttons style to "buttonStyle"
        newButton.setMinWidth(minWidth); //set the buttons minimum width to "minWidth"
        newButton.setMinHeight(minHeight); //set the buttons minimum height to "minHeight"

        return newButton; //returns the created button "newButton"
    }

    //can use this method to create a button
    public Text createTextLine(String text, String textStyle, Color colour) {

        Text newText = new Text(text); //creates a new text line with the message "newText"
        newText.setStyle(textStyle); //set the text lines style to "textStyle"
        newText.setFill(colour); //set the text lines colour to "colour"

        return newText;
    }

    //can use this method to customize a new gui / gridpane
    public void customizeGUI(GridPane gridPane, String gridStyle, double minWidth, double minHeight, double verticalGap, double horizontalGap, Pos position) {
        gridPane.setStyle(gridStyle); //customizing the background of our login screen
        gridPane.setMinWidth(minWidth); //setting the window size
        gridPane.setMinHeight(minHeight); //setting the window size
        gridPane.setPadding(new Insets(15, 15, 15, 15)); //setting the padding  
        gridPane.setVgap(verticalGap); //setting the vertical gap between the columns 
        gridPane.setHgap(horizontalGap); //setting the horizontal gap between the columns
        gridPane.setAlignment(position); //setting the alignment of the grid pane "gridPane"
    }

}

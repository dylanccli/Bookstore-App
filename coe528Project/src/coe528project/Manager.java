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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.stage.Stage;

public class Manager extends Application {
    
    private static ArrayList<Customer> customers = new ArrayList<Customer>(); //list of customer' info
    private static ArrayList<Book> books = new ArrayList<Book>(); //list of book info
    
    private static Stage stage; //creating new stage
    
    public String customerDataFile = "customers.txt"; //path to customer.txt file
    public String bookDataFile = "books.txt"; //path to books.txt file

    
    public Stage getStage() { //returns the stage
        return stage; 
    }
    
    public ArrayList<Customer> getCustomers() { //returns the customers list
       return customers;
    }
    
    public ArrayList<Book> getBooks() { //returns the books list
       return books;
    }
    
    
    SetState ss = new SetState();
    
    private ArrayList<String> username = new ArrayList<>(); //used to copy customer usernames to customers list
    private ArrayList<String> password = new ArrayList<>(); //used to copy customer passwords to customers list
    private ArrayList<Integer> points = new ArrayList<>(); //used to copy customer points to customers list
    
    private ArrayList<String> bookname = new ArrayList<>(); //used to copy book names to books list
    private ArrayList<Double> bookprice = new ArrayList<>(); //used to copy book prices to books list
    
    /*
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    SINCE BOOK NAMES CAN HAVE SPACES
    CHECKING FOR "BOOKNAME: "
    AND
    CHECKING FOR "BOOKPRICE:"
    CHECKS ARE NOT GOOD ANYMORE SINCE IF A BOOK IS NAMED "BookName: BookName:"
    IT WILL REPLACE THE ENTIRE BOOK'S NAME WITH "" INSTEAD OF THE PROPER "BookName: BookName:"
    EVEN THOUGH THAT SCENARIO IS RARE IT SHOULD BE CONSIDERED?
    
    HAVE TO FIND A NEW WAY OF DOING THIS (READING DATA IN books.txt TO books LIST) ^^
    
    
    
    
    
    
    CUSTOMER USERNAMES AND PASSWORDS CANT HAVE A SPACE SO THE void readFileToCustomerList 
    DOESNT NEED TO BE CHANGED
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    */
    
    
    public void readFileToBookList() { //used to copy data in the books.txt file to the books list
        try {
            // Write the code here
            Scanner scan = new Scanner(new File(bookDataFile)); 
            String str = ""; 
            while (scan.hasNext()) { //if a next line in books.txt exists
                    str = scan.nextLine();//set str to the next line in books.txt
                    if (str.contains("BookName: ")) { //if the next line contains "BookName: "
                        //replace the "BookName: " text with an empty string and the resulting string
                        //will be the books name
                        str = str.replace("BookName: ", ""); 
                        //add the book name to the list bookname
                        bookname.add(str);
                    }
                    if (str.contains("BookPrice")) { //if the next line contains 
                        str = str.replaceAll("\t-> BookPrice: ", "");
                        if (!str.contains(".")) {
                            str = str + ".00";
                        }
                        double p = Double.parseDouble(str);
                        bookprice.add(p);
                    }
                
            }
            for (int i = 0; i < bookname.size(); i++) 
                books.add(new Book(bookname.get(i), bookprice.get(i)));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    
    
    public void readFileToCustomerList() { //used to copy data in the customers.txt file to the customers list
        try {
            // Write the code here
            Scanner scan = new Scanner(new File(customerDataFile)); 
            String str = ""; 
            while (scan.hasNext()) {
               
                    str = scan.nextLine();
                    if (str.contains("Username: ")) {
                        str = str.replaceAll("Username: ", "");
                        username.add(str);
                        
                    }
                    if (str.contains("Password: ")) {
                        str = str.replaceAll("\t-> Password: ", "");
                        password.add(str);
                    }
                    if (str.contains("Points")) {
                        str = str.replaceAll("\t\t-> Points: ", "");
                        int p = Integer.parseInt(str);
                        points.add(p);
                    }
                
            }
            for (int i = 0; i < username.size(); i++) 
                customers.add(new Customer(username.get(i), password.get(i), points.get(i)));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    
    @Override
    public void start(Stage primaryStage) { //called on launch(args) in main void 
        
        stage = primaryStage;
        stage.setTitle("Bookstore App"); //sets the application title
        stage.setResizable(false); //does not allow users to resize the window
        stage.setAlwaysOnTop(true); //keeps this window/program on top of any other open windows/programs
        readFileToCustomerList(); //reads the customer.txt file and stores all customers' info in a list
        readFileToBookList(); //reads the books.txt file and stores all book info in a list
        
        LoginScreen login = new LoginScreen(); //go to the login screen scene/state      
        login.doAction(ss);
        
    }
    
    

    @Override
    public void stop() { //called when the user clicks the [x] button
        
          try {
                //creates a writer for both text files customers.txt and books.txt
                PrintWriter customerWriter = new PrintWriter(this.customerDataFile);
                PrintWriter bookWriter = new PrintWriter(this.bookDataFile);
                //empties the text files for both customers.txt and books.txt
                customerWriter.print("");
                bookWriter.print("");
                
                for (int i = 0; i < customers.size(); i++) { //looping through each customer in the customers list
                    
                    String customerUsername = customers.get(i).getUsername(); //get current customers username
                    String customerPassword = customers.get(i).getPassword(); //get current customers password
                    int customerPoints = customers.get(i).getPoints(); //get current customers points
                    String toWrite = "";
                    if (i == 0) { //we dont want to have "\n" before the first entry
                        //writes the first customer in the list customers to the string toWrite
                        toWrite = toWrite + "Username: " + customerUsername
                                        + "\n\t-> Password: " + customerPassword
                                        + "\n\t\t-> Points: " + customerPoints;
                    } else {
                        //writes every subsequent customer in the list customers to the string toWrite
                        toWrite = toWrite +  "\nUsername: " + customerUsername
                                        + "\n\t-> Password: " + customerPassword
                                        + "\n\t\t-> Points: " + customerPoints;
                    }
                    //finally, toWrite has all of the customer data and is printed to the file customers.txt
                    customerWriter.write(toWrite); 
                }
                for (int i = 0; i < books.size(); i++) { //loops through each book in the books list
                    
                    String bookName = books.get(i).getName(); //get current book name
                    double bookPrice = books.get(i).getPrice(); //get current book price
                    String toWrite = "";
                    if (i == 0) { //we dont want to have "\n" before the first entry
                        //writes the first book in the list books to the string toWrite
                        toWrite = toWrite + "BookName: " + bookName 
                                        + "\n\t-> BookPrice: " + bookPrice;
                    } else {
                        //writes every subsequent book in the list books to the string toWrite
                        toWrite = toWrite +  "\nBookName: " + bookName
                                        + "\n\t-> BookPrice: " + bookPrice;
                    }
                    //finall, toWrite has all of the book data and is printed to the file books.txt
                    bookWriter.write(toWrite); 
                }
                //close both print writers for customers.txt and books.txt
                customerWriter.close();
                bookWriter.close();
                //the data in both the customers and books list has been successfully stored
                //in the files customers.txt and books.txt
            } catch (IOException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }  
    }
    
    public static void main(String args[]) {
        TextFiles t = new TextFiles();
        t.createFile("customers.txt"); //creates file customers.txt
        t.createFile("books.txt"); //creates file books.txt
        System.out.println();
        launch(args); //start
    } 

}
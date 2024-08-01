/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528project;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dylan
 */
public class TextFiles {

    Manager m = new Manager(); //allows me to call methods in the Manager class
    // File cFile = new File(m.customerDataFile); 
    // File bFile = new File(m.bookDataFile);

    /*
        code used from lab 3
     */
    //can use this method to create a file
    public void createFile(String fileName) {

        try {
            File file = new File(fileName); //creates new file
            if (file.createNewFile()) { //if the file does not exist
                System.out.println("File created: " + file.getName());
            } else { //if the file already exists
                System.out.println(file.getName() + " loaded");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /*
        code used from lab 3
     */
    //can use this method to read a file
    public String read(String fileName) {
        String string = ""; //creates a new String "string"
        try {
            Scanner scan = new Scanner(new File(fileName)); //creating a new Scanner "scan" to read through our created file
            String str = ""; //creating a new String "str"
            while (scan.hasNext()) {  //while a next line exists to read in our created file: 
                str = str + scan.nextLine() + "\n"; //set str to itself plus the next line
            }
            string = str; //set "str" to "string"
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return string; //returns the entire file as a string
    }

    /*
        code used from lab 3
     */
    //can use this method to write to a file
    public void write(String fileName, String msg) {
        try {
            FileWriter writer = new FileWriter(new File(fileName), true); //creating a new fileWriter "writer" to write to our created file
            writer.write(msg); //writing the String "msg" to our created file
            writer.close(); //closing the fileWriter "writer"
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

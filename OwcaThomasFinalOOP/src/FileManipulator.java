/*
 * Author: 		Thomas Owca
 * Date: 		4/24/2019
 * Class: 		OOP
 * Instructor: 	Ray Klump
 * Assignment: 	Final Project
 * File:		FileManipulator.java
 * Project:		OwcaThomasFinalOOP
 * Info:		All work is by me. The purpose of the project
 * 				is to have a useful CRUD (Create, Read, Update, Delete) utility
 * 				management system for a fictional store that has guitars for sale.
 * 				The management application allows the user to keep track of inventory 
 * 				and operate CRUD functionality. 
 * 				
 * 				Uses:
 * 				1. The Table can be sorted by specified column. This is done simply by clicking on 
 * 				one of the several column headers for the table.
 * 		
 * 				2. File menu contains within it: New - creates a fresh table.
 * 				Open Inventory - allows the user to navigate to an existing storage file
 * 				containing Guitar objects in the .xml or .bin format. Otherwise the full directory
 * 				and file path can be typed out as well (ensuring .xml or .bin is specified after filename).
 * 				Save As - simply saves a new file. You must ensure .xml or .bin is specified after the filename.
 * 				It works for overwriting files as well.
 * 				Exit - simply terminates the application, gracefully.
 * 				
 * 				3. Help menu contains within it: Help - which in simpler terms describes some of what is being
 * 				described in this lengthy comment. About - states more about the project and myself.
 * 
 * 				4. You can toggle through the functionality of the application by toggling the radio buttons
 * 				at the northern section of the window. This hides the labels and text fields as required for the functionality.
 * 					
 */
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManipulator {

	private static String theFileName;
	public static void SaveData(String filename, String extension, ArrayList<Guitar> guitars) {
		if (extension.equals(".bin")) {
			// Set the the stream equal to null;
			ObjectOutputStream oos = null;
			
			// Try to save the file. Else it will catch the exception(s) thrown.
			try {
				oos = new ObjectOutputStream(new FileOutputStream(filename));
				oos.writeObject(guitars);
				oos.close();
			}
			catch (Exception ex) { 
				System.out.println("Error saving the file: " + filename);
			}
		}
		// Handle the saving in XML format. Similar logic to the above block.
		else if (extension.equals(".xml")) {
			try {
				XMLEncoder xmlenc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
				xmlenc.writeObject(guitars);
				xmlenc.close();
			}
			catch (Exception ex) {
				System.out.println("Error saving the file: " + filename);
			}
		}
	}
	
	
	public static ArrayList<Guitar> OpenAndLoadData(String filename, String extension) {
		// Set the ArrayList to null.
		ArrayList<Guitar> readObject = null;
		
		// If the extension is for a binary file. Open the binary file.
		if (extension.equals(".bin")) {
			// Set the the stream equal to null;
			ObjectInputStream ois = null;
			
			// Try/catch block while using the streams. It catches any exceptions thrown.
			try {
				ois = new ObjectInputStream(new FileInputStream(filename));
				readObject = (ArrayList<Guitar>)ois.readObject();
				ois.close();
			}
			catch (Exception e) { 
				System.out.println("Error opening the file: " + filename);
			}
		}
		// Else if the file is XML, try to decode it. Similar logic to the above block.
		else if (extension.equals(".xml")) {
			try {
				XMLDecoder xmldec = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
				readObject = (ArrayList<Guitar>)xmldec.readObject();
				xmldec.close();
			}
			catch (Exception ex) {
				
			}
		}
		return readObject;
	}
}

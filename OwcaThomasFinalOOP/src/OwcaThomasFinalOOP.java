/*
 * Author:		Thomas Owca
 * Instructor:	Ray Klump
 * Date:		4/24/2019
 * Assignment:	Final Assignment
 * Program:		OwcaThomasFinalOOP.java
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

import java.util.ArrayList;

public class OwcaThomasFinalOOP {
	
	public static void main(String [] args) {
		
		// Make this frame be visible to the user.
		ArrayList<Guitar> results = GuitarGenerator.GenerateGuitars(500);
		AppFrame appFrame = new AppFrame(results);
		appFrame.setVisible(true);
	}
}

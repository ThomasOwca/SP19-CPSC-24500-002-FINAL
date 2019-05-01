/*
 * Author: 		Thomas Owca
 * Date: 		4/24/2019
 * Class: 		OOP
 * Instructor: 	Ray Klump
 * Assignment: 	Final Project
 * File:		GuitarGenerator.java
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
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is responsible for create random Guitar objects that populate the table on initial
 * application launch/run.
 * @author Thomas
 *
 */
public class GuitarGenerator {
	public static ArrayList<Guitar> GenerateGuitars(int number) {
		ArrayList<Guitar> result = new ArrayList<Guitar>();
		Random random = new Random();
		int brand;
		int model;
		int finish;
		int price;
		int year;
		int id;
		
		String [] brands = { "Fender", "Gibson", "PRS" };
		String [] modelsFender = { "Stratocaster", "Telecaster", "Mustang" };
		String [] modelsGibson = { "Les Paul", "SG", "ES-335" };
		String [] modelsPRS = { "SC245", "McCarty", "Starla" };
		String [] finishFender = { "Black", "White", "Butterscotch Blonde" };
		String [] finishGibson = { "Cherry Sunburst", "Goldtop", "Honeyburst" };
		String [] finishPRS = { "Vintage Sunburst", "Black", "Goldtop" };
		
		for (int i = 0; i < number; i++) {
			Guitar guitar = new Guitar();
			id = i + 1000;
			brand = random.nextInt(3);
			model = random.nextInt(3);
			price = random.nextInt(3000 - 1000  + 1) + 1000;
			year = random.nextInt(2019 - 1996 + 1) + 1996;
			finish = random.nextInt(3);
			
			guitar.setId(Integer.toString(id));
			guitar.setYear(year);
			guitar.setPrice(price);
			guitar.setStatus("In-Stock");
			
			// 0 for Fenders.
			if (brand == 0) {
				guitar.setBrand(brands[brand]);
				guitar.setModel(modelsFender[model]);
				guitar.setFinish(finishFender[finish]);
			}
			// 1 for Gibsons
			else if (brand == 1) {
				guitar.setBrand(brands[brand]);
				guitar.setModel(modelsGibson[model]);
				guitar.setFinish(finishGibson[finish]);
			}
			// 2 for PRSi
			else if (brand == 2) {
				guitar.setBrand(brands[brand]);
				guitar.setModel(modelsPRS[model]);
				guitar.setFinish(finishPRS[finish]);
			}
			result.add(guitar);
		}
		return result;
	}
}
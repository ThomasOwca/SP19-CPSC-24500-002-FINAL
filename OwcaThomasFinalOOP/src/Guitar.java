/*
 * Author: 		Thomas Owca
 * Date: 		4/24/2019
 * Class: 		OOP
 * Instructor: 	Ray Klump
 * Assignment: 	Final Project
 * File:		Guitar.java
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
import java.io.Serializable;

/**
 * This is the model class that implements the Serializable interface to be compatible with the XML file format.
 * This class represents a guitar and its characteristics..
 * @author Thomas
 *
 */
public class Guitar implements Serializable {
	// Private data members are below.
	private static final long serialVersionUID = 1L;
	private String id;
	private String brand;
	private String model;
	private String finish;
	private int year;
	private String status;
	private double price;
	
	// Default constructor.
	public Guitar() {
		id = "0";
		brand = "Not Available";
		model = "Not Available";
		year = 2010;
		status = "Unknown";
		price = 0.00;
	}
	
	// Parameterized constructor.
	public Guitar(String id, String brand, String model, int year, String status, double price) {
		setId(id);
		setBrand(brand);
		setModel(model);
		setYear(year);
		setStatus(status);
		setPrice(price);
	}
	
	// Getters/Setters | Accessors/Mutators are below.
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}
	
	@Override
	public String toString() {
		return String.format(("ID: " + id + ", Brand: " + brand + ", Model: " + model + ", Finish: " + finish + 
				", Year: %d" + ", Price: %.2f" + ", Status: " + status), year, price);
	}
}
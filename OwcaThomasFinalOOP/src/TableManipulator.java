/*
 * Author: 		Thomas Owca
 * Date: 		4/24/2019
 * Class: 		OOP
 * Instructor: 	Ray Klump
 * Assignment: 	Final Project
 * File:		TableManipulator.java
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
 * 
 */
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * This is a controller class that handles the creation of a guitar.
 * It works as the bridge between the model and the view following the MVC Pattern.
 * @author Thomas
 *
 */
public class TableManipulator {
	// Private members are declared below.
	private ArrayList<Guitar> guitars;
	private DefaultTableModel tableModel;
	private JTable table;
	
	private JTextField txtID;
	private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtFinish;
    private JTextField txtYear;
    private JTextField txtStatus;
    private JTextField txtPrice;
	
    // Parameterized constructor.
	public TableManipulator(ArrayList<Guitar> guitars, ArrayList<JTextField> fields, JTable table, DefaultTableModel tableModel) {
		this.guitars = guitars;
		this.tableModel = tableModel;
		this.table = table;
		
		// Set the private properties in the order that the ArrayList stores the ones that are passed in.
		txtID = fields.get(0);
		txtBrand = fields.get(1);
		txtModel = fields.get(2);
		txtFinish = fields.get(3);
		txtYear = fields.get(4);
		txtStatus = fields.get(5);
		txtPrice = fields.get(6);
		
		// Create the guitar.
		createGuitar();
	}

	public void createGuitar() {
		Guitar guitar = new Guitar();
		Object [] data = new Object[7];

		// Creates the guitar with the help of a private helper method in this class.
		// The Array of Objects is assigned these various guitar model's properties and then added to the
		// table model as a row.
		try {
    		guitar = guitarMaker();

    		data[0] = guitar.getId();
        	data[1] = guitar.getBrand();
        	data[2] = guitar.getModel();
        	data[3] = guitar.getFinish();
        	data[4] = String.valueOf(guitar.getYear());
        	data[5] = String.format("$%.2f", guitar.getPrice());
        	data[6] = guitar.getStatus();
        	
        	// Add the newly created guitar to the ArrayList of Guitar objects.
        	guitars.add(guitar);
        	
        	// Add the row to the table's model.
        	tableModel.addRow(data);
        	
        	// Set the modified model as the model for the current table.
        	table.setModel(tableModel);	
		}
		catch (Exception ex) {
			System.out.println("Error when adding guitar!");
			System.out.println(ex.toString());
		}
		finally {
			// Reset all the fields to empty.
			txtID.setText("");
			txtBrand.setText("");
			txtModel.setText("");
			txtFinish.setText("");
			txtPrice.setText("");
			txtStatus.setText("");
			txtYear.setText("");
		}
	}
	
	// Private helper method that implements the logic for creating the Guitar object from the user's input.
	private Guitar guitarMaker() {
		String lastID;
    	Guitar guitar = new Guitar();
    	
    	// If there is at least one row in the table, assign lastID as the last row's ID #. Else, initialize with 999.
    	if (tableModel.getRowCount() != 0)
    		lastID = (String)tableModel.getValueAt(tableModel.getRowCount() - 1, 0);
    	else
    		lastID = "999";
    	
    	// Add one to get the newest ID for the newest guitar that will be added to the table.
		int newID = Integer.parseInt(lastID) + 1;
    	
    	guitar.setId(String.valueOf(newID));
		guitar.setBrand(txtBrand.getText());
		guitar.setModel(txtModel.getText());
		guitar.setFinish(txtFinish.getText());
		guitar.setStatus(txtStatus.getText());
		guitar.setPrice(Double.parseDouble(txtPrice.getText()));
		guitar.setYear(Integer.parseInt(txtYear.getText()));
		
		return guitar;
    }
	
}
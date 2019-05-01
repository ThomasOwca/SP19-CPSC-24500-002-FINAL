/*
 * Author: 		Thomas Owca
 * Date: 		4/24/2019
 * Class: 		OOP
 * Instructor: 	Ray Klump
 * Assignment: 	Final Project
 * File:		AppFrame.java
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * The AppFrame class is part of the view for this application. 
 * It is responsible for housing the various components that make up this CRUD application.
 * @author Thomas
 *
 */
public class AppFrame extends JFrame {
	// Private members are declared below.
	private JButton submitBtn;
	private JRadioButton radioBtnSearch;
	private JRadioButton radioBtnCreate;
	private JRadioButton radioBtnDelete;
	private JRadioButton radioBtnUpdate;
	
	private JTextField txtID;
	private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtFinish;
    private JTextField txtYear;
    private JTextField txtStatus;
    private JTextField txtPrice;
    
    private JLabel lblID;
    private JLabel lblBrand;
    private JLabel lblModel;
    private JLabel lblFinish;
    private JLabel lblYear;
    private JLabel lblStatus;
    private JLabel lblPrice;
    private JLabel lblCurrentFile;
    private JLabel lblCurrentFileName;
    
    // This array of Strings holds the required column header names.
    private String[] columnNames = {"ID", "Brand", "Model", "Finish", "Year", "Price", "Status"};
    
    // This object is what actually holds and represents the model data in a format that works with a JTable.
    // It is instantiated to contain the column header names.
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    
    // This is the table that makes up the majority of the view.
    private JTable table;
    
    // The guitars object reference variable will hold the the model objects that will be viewed and manipulated.
    private ArrayList<Guitar> guitars;
    
    // Used to hold some of the JTextField objects that will be passed on to a controller.
    private ArrayList<JTextField> fields;
    
    // Instantiating one of the controllers. 
    // This TableManipulator object has a variety of useful methods for manipulating the raw data.
    private TableManipulator rowCreator;
	
    // Parameterized constructor.
    public AppFrame(ArrayList<Guitar> guitars) {
    	this.guitars = guitars;
    	
    	// This few lines for the creation of a new JTable ensure that the cells are not immediately available to the user for editing.
    	table = new JTable() {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
    	
    	// Next several lines are just instantiating classes/creating objects.
    	fields = new ArrayList<JTextField>();
    	
    	txtID = new JTextField();
        txtBrand = new JTextField();
        txtModel = new JTextField();
        txtFinish = new JTextField();
        txtYear = new JTextField();
        txtStatus = new JTextField();
        txtPrice = new JTextField();
    	
        // Adding the necessary textfields to the ArrayList. This will come in handy when it will be passed to a controller.
    	fields.add(txtID);
    	fields.add(txtBrand);
    	fields.add(txtModel);
    	fields.add(txtFinish);
    	fields.add(txtYear);
    	fields.add(txtStatus);
    	fields.add(txtPrice);
  
    	// Initial setup of the UI, menu, and event-handling behaviors.
    	setupUI();
    	setupMenu();
    	setupEventHandlers();
    }
    
    // Default constructor. The table is instantiated and set to not allow direct editing of table's raw data.
    public AppFrame() {
    	table = new JTable() {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
    	
    	// Sets the model that the JTable uses to display its records with.
    	table.setModel(tableModel);
    	
    	// More instantiation.
    	guitars = new ArrayList<Guitar>();
    	fields = new ArrayList<JTextField>();
    	
    	// Even more instantiation.
    	txtID = new JTextField();
        txtBrand = new JTextField();
        txtModel = new JTextField();
        txtFinish = new JTextField();
        txtYear = new JTextField();
        txtStatus = new JTextField();
        txtPrice = new JTextField();
    	
    	fields.add(txtID);
    	fields.add(txtBrand);
    	fields.add(txtModel);
    	fields.add(txtFinish);
    	fields.add(txtYear);
    	fields.add(txtStatus);
    	fields.add(txtPrice);
    	
    	setupUI();
    	setupMenu();
    	setupEventHandlers();
    }
    
    /**
     * This helper method sets up the majority of the user interface/view.
     */
    public void setupUI() {
    	// Set some generic operations for the frame.
    	setTitle("Once Upon A Guitar (Management System) - Created By: Thomas Owca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300,140,1400,800);
        
        // Create a Container and set the layout of it.
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        // This array of Objects will represent the raw model data that will be placed into the table's model.
        Object [] data = null;
        
        // Create the data by getting the data from the ArrayList containing the model data. Add this raw data to the table model
        // one row at a time.
        try {
	        for (int i = 0; i < guitars.size(); i++) {
	        	data = new Object[7];
	        	
	        	data[0] = guitars.get(i).getId();
	        	data[1] = guitars.get(i).getBrand();
	        	data[2] = guitars.get(i).getModel();
	        	data[3] = guitars.get(i).getFinish();
	        	data[4] = String.valueOf(guitars.get(i).getYear());
	        	data[5] = "$" + String.valueOf(guitars.get(i).getPrice()) + "0";
	        	data[6] = guitars.get(i).getStatus();
	        	
	        	// Add raw data, one row at a time into the table's model.
	        	tableModel.addRow(data);
	    	}
	        
	        // Set the table's model.
	        table.setModel(tableModel);
	        
	        // Set the highlighting color when a row is clicked on or searched for.
	        table.setSelectionBackground(Color.CYAN);
        }
        catch (Exception ex) {
        	System.out.println("No default file was read in.");
        }
        
        // More initialization of table visuals. 
        table.setAutoCreateRowSorter(true);
    	table.setFont(new Font("Arial", Font.BOLD, 24));
    	table.setRowHeight(table.getRowHeight()+ 10);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.RED);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        
        // Set layout for the panel housing the table.
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Creating the JLabel objects and setting their visual orientation in the view.
        lblID = new JLabel("ID#: ", SwingConstants.RIGHT);
        lblBrand = new JLabel("Brand: ", SwingConstants.RIGHT);
        lblModel = new JLabel("Model: ", SwingConstants.RIGHT);
        lblFinish = new JLabel("Finish: ", SwingConstants.RIGHT);
        lblYear = new JLabel("Year: ", SwingConstants.RIGHT);
        lblStatus = new JLabel("Status: ", SwingConstants.RIGHT);
        lblPrice = new JLabel("Price: ", SwingConstants.RIGHT);
        lblCurrentFile = new JLabel("Current File:", SwingConstants.RIGHT);
        lblCurrentFileName = new JLabel(" Demo Template", SwingConstants.LEFT);
        
        // More color setting.
        lblID.setForeground(Color.decode("#fefbd8"));
        lblBrand.setForeground(Color.decode("#fefbd8"));
        lblModel.setForeground(Color.decode("#fefbd8"));
        lblFinish.setForeground(Color.decode("#fefbd8"));
        lblYear.setForeground(Color.decode("#fefbd8"));
        lblStatus.setForeground(Color.decode("#fefbd8"));
        lblPrice.setForeground(Color.decode("#fefbd8"));
        lblCurrentFile.setForeground(Color.decode("#fefbd8"));
        lblCurrentFileName.setForeground(Color.decode("#fefbd8"));
        
        // Hide the text field for the ID on initial load.
        txtID.setEnabled(false);
        
        // Instantiate the submit button. Set the color.
        submitBtn = new JButton("Submit");
        submitBtn.setBackground(Color.RED);
        submitBtn.setForeground(Color.WHITE);
        
        // Create the panel that will house the labels and text fields.
        JPanel panelRight = new JPanel(new GridLayout(11, 2));
        panelRight.setBackground(Color.decode("#4040a1"));
        
        // Add the components into the panel.
        panelRight.add(lblCurrentFile);
        panelRight.add(lblCurrentFileName);
        panelRight.add(new JLabel(""));
        panelRight.add(submitBtn);
        panelRight.add(lblID);
        panelRight.add(txtID);
        panelRight.add(lblBrand);
        panelRight.add(txtBrand);
        panelRight.add(lblModel);
        panelRight.add(txtModel);
        panelRight.add(lblFinish);
        panelRight.add(txtFinish);
        panelRight.add(lblYear);
        panelRight.add(txtYear);
        panelRight.add(lblStatus);
        panelRight.add(txtStatus);
        panelRight.add(lblPrice);
        panelRight.add(txtPrice);
        
        // Create the radio buttons for toggling the various CRUD features.
        radioBtnCreate = new JRadioButton("Create");
        radioBtnUpdate = new JRadioButton("Update");
        radioBtnDelete = new JRadioButton("Delete");
        radioBtnSearch = new JRadioButton("Search");
        
        // Set the visual appearances for the radio buttons.
        radioBtnCreate.setBackground(Color.decode("#4040a1"));
        radioBtnCreate.setForeground(Color.decode("#fefbd8"));
        radioBtnCreate.setFont(new Font("Arial", Font.BOLD, 18));
        
        radioBtnUpdate.setBackground(Color.decode("#4040a1"));
        radioBtnUpdate.setForeground(Color.decode("#fefbd8"));
        radioBtnUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        
        radioBtnDelete.setBackground(Color.decode("#4040a1"));
        radioBtnDelete.setForeground(Color.decode("#fefbd8"));
        radioBtnDelete.setFont(new Font("Arial", Font.BOLD, 18));
        
        radioBtnSearch.setBackground(Color.decode("#4040a1"));
        radioBtnSearch.setForeground(Color.decode("#fefbd8"));
        radioBtnSearch.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Turn off focus.
        radioBtnCreate.setFocusable(false);
        radioBtnUpdate.setFocusable(false);
        radioBtnDelete.setFocusable(false);
        radioBtnSearch.setFocusable(false);
        
        // Create a group and add the radio buttons to this group.
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtnCreate);
        btnGroup.add(radioBtnUpdate);
        btnGroup.add(radioBtnDelete);
        btnGroup.add(radioBtnSearch);
        
        // Set the default selected radio button.
        radioBtnSearch.setSelected(true);
        
        // Create the northern panel. Set other initializing properties.
        JPanel panelNorth = new JPanel();
        panelNorth.setForeground(Color.WHITE);
        panelNorth.setBackground(Color.decode("#4040a1"));
        
        // Add the radio buttons to this northern panel.
        panelNorth.add(radioBtnSearch);
        panelNorth.add(radioBtnCreate);
        panelNorth.add(radioBtnUpdate);
        panelNorth.add(radioBtnDelete);
        
        // Appropriately add the various panels to the container.
        c.add(panelNorth, BorderLayout.NORTH);
        c.add(panelCenter, BorderLayout.CENTER);
        c.add(panelRight, BorderLayout.SOUTH);
        
        // Set the initial visibility for initial application launch.
        txtID.setEnabled(true);
		txtBrand.setVisible(false);
		txtModel.setVisible(false);
		txtFinish.setVisible(false);
		txtYear.setVisible(false);
		txtPrice.setVisible(false);
		txtStatus.setVisible(false);
		lblBrand.setVisible(false);
		lblModel.setVisible(false);
		lblFinish.setVisible(false);
		lblYear.setVisible(false);
		lblPrice.setVisible(false);
		lblStatus.setVisible(false);
		submitBtn.setVisible(true);
    }
    
    /**
     * Sets up the menu for the app.
     */
    public void setupMenu() {
    	// Create the various components of the menu.
    	JMenuBar menuBar = new JMenuBar();
    	JMenu file = new JMenu("File");
    	JMenu help = new JMenu("Help");
    	
    	JMenuItem itemNew = new JMenuItem("New");
    	JMenuItem itemOpenInventory = new JMenuItem("Open Inventory");
    	JMenuItem itemSave = new JMenuItem("Save As");
    	JMenuItem itemExit = new JMenuItem("Exit");
    	
    	JMenuItem itemHelp = new JMenuItem("Help");
    	JMenuItem itemAbout = new JMenuItem("About");
    	
    	// Add the created menu items to the file menu.
    	file.add(itemNew);
    	file.add(itemOpenInventory);
    	file.add(itemSave);
    	file.add(itemExit);
    	
    	// Add the created menu items to the help menu.
    	help.add(itemHelp);
    	help.add(itemAbout);
    	
    	// Add the menu(s) to the menu bar.
    	menuBar.add(file);
    	menuBar.add(help);
    	
    	// Event handler for clicking on "File -> New".
    	// Gives a blank "canvas" file to create new Guitars for storage.
    	itemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guitars.clear();
				lblCurrentFileName.setText(" New File (Unsaved Changes)");
				updateTable(guitars);
			}	
    	});
    	
    	// Event handler for clicking on "File -> Save As".
    	// Saves the file in a compatible format. *MUST* add .bin or .xml after the filename.
    	itemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				
				// Once user inputs the filename, go ahead and process the file and attempt to save it.
				if (jfc.showSaveDialog(AppFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					String filename = file.getAbsolutePath();
					
					if (filename.endsWith(".bin")) {
						// Save the binary file.
						FileManipulator.SaveData(filename, ".bin", guitars);
						System.out.println("Saved! " + filename);
						lblCurrentFileName.setText(" " + filename);
					}
					else if (filename.endsWith(".xml")) {
						//  Save the xml file.
						FileManipulator.SaveData(filename, ".xml", guitars);
						System.out.println("Saved! " + filename);
						lblCurrentFileName.setText(" " + filename);
						
					}
					else
						System.out.println("File was not found or extension was not compatible.");
				}
			}
		});
    	
    	// Event handler for clicking "File -> Open Inventory".
    	// Opens up a saved guitar inventory in the .bin or .xml file types.
    	itemOpenInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				ArrayList<Guitar> readObjects = null;
				
				// Open the dialog window for opening a file.
				if (jfc.showOpenDialog(AppFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					String filename = file.getAbsolutePath();
					
					// If the file is a binary serialized file format. Go ahead and try to process it.
					if (filename.endsWith(".bin")) {
						// Open the binary file.
						readObjects = FileManipulator.OpenAndLoadData(filename, ".bin");
						
						if (guitars != null)
							guitars.clear();
						
						try {
							for (Guitar g : readObjects) {
								guitars.add(g);
							}
							
							// Set the visual display of the current filename and path to the user.
							lblCurrentFileName.setText(" " + filename);
						}
						catch (Exception ex) {
							System.out.println("Error loading in data from file.");
						}
						
						// Update the table.
						updateTable(guitars);
					}
					// Similar logic to above block. This one is essentially the same, except it processes a XML serialized file.
					else if (filename.endsWith(".xml")) {
						// Open the binary file.
						readObjects = FileManipulator.OpenAndLoadData(filename, ".xml");
						
						if (guitars != null)
							guitars.clear();
						
						// Again, this is wrapped in a try/catch block in case readObjects is pointing to null.
						try {
							for (Guitar g : readObjects) {
								guitars.add(g);
							}
							
							// Set the visual display of the current filename and path to the user.
							lblCurrentFileName.setText(" " + filename);
						}
						catch (Exception ex) {
							System.out.println("Error loading in data from file.");
						}
						
						// Update the table.
						updateTable(guitars);
					}
					else
						System.out.println("File was not found or extension was not compatible.");
				}
			}
		});
    	
    	// Event handler for clicking "File -> Exit".
    	// Simply exits the program.
    	itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Exit and terminate the application gracefully with an exit code of 0.
				System.exit(0);
			}
    	});
    	
    	// Event handler for clicking "Help -> Help".
    	// Displays help messsage.
    	itemHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "This is a CRUD application for a NoSQL database storing guitars." +
						"\n\nGuitars can be stored in either a .bin or .xml file. You have to type this extension after the filename in "
						+ "order to save the file of this acceptable format."
						+ "\n\nClick on the table's column headers to enable column sorting." +
						"\n\nSelect the appropriate toggle button for Search, Create, Update, or Delete to manipulate the data.");
			}
    	});
    	
    	itemAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Created by Thomas Owca in Spring 2019 as final project for Object Oriented Programming class.\n"
						+ "This is a CRUD application for a NoSQL storage of guitars.");
			}	
    	});
    	
    	this.setJMenuBar(menuBar);
    }
    
    /**
     * Sets up the "other" event handlers dealing with the radio buttons.
     */
    public void setupEventHandlers() {
    	
    	radioBtnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setVisible(false);
				txtBrand.setVisible(true);
				txtModel.setVisible(true);
				txtFinish.setVisible(true);
				txtYear.setVisible(true);
				txtPrice.setVisible(true);
				txtStatus.setVisible(true);
				submitBtn.setVisible(true);
				
				lblBrand.setVisible(true);
				lblFinish.setVisible(true);
				lblModel.setVisible(true);
				lblPrice.setVisible(true);
				lblStatus.setVisible(true);
				lblYear.setVisible(true);
				repaint();
			}
    	});
    	
    	radioBtnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setVisible(true);
				txtBrand.setVisible(false);
				txtModel.setVisible(false);
				txtFinish.setVisible(false);
				txtYear.setVisible(false);
				txtPrice.setVisible(false);
				txtStatus.setVisible(false);
				submitBtn.setVisible(true);
				
				lblBrand.setVisible(false);
				lblFinish.setVisible(false);
				lblModel.setVisible(false);
				lblPrice.setVisible(false);
				lblStatus.setVisible(false);
				lblYear.setVisible(false);
				repaint();
			}
    	});
    	
    	radioBtnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setVisible(true);
				txtBrand.setVisible(false);
				txtModel.setVisible(false);
				txtFinish.setVisible(false);
				txtYear.setVisible(false);
				txtPrice.setVisible(false);
				txtStatus.setVisible(false);
				submitBtn.setVisible(true);
				
				lblBrand.setVisible(false);
				lblFinish.setVisible(false);
				lblModel.setVisible(false);
				lblPrice.setVisible(false);
				lblStatus.setVisible(false);
				lblYear.setVisible(false);
				repaint();
			}
    	});
    	
    	radioBtnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setVisible(true);
				txtBrand.setVisible(false);
				txtModel.setVisible(false);
				txtFinish.setVisible(false);
				txtYear.setVisible(false);
				txtPrice.setVisible(false);
				txtStatus.setVisible(false);
				submitBtn.setVisible(true);
				
				lblBrand.setVisible(false);
				lblFinish.setVisible(false);
				lblModel.setVisible(false);
				lblPrice.setVisible(false);
				lblStatus.setVisible(false);
				lblYear.setVisible(false);
				repaint();
			}
    	});
    	
    	// This is the most sophisticated event-handler as it deals with the submit button click.
    	// Based on which radio button is currently selected the behavior of handling the event will drastically change.
    	submitBtn.addActionListener(new ActionListener() {
    		boolean found = false;
    		int row = 0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (radioBtnCreate.isSelected()) {
					// Pass the vital data to the controller. This constructor itself will create the new guitar.
					rowCreator = new TableManipulator(guitars, fields, table, tableModel);
		    	}
				else if (radioBtnSearch.isSelected()) {
					/**
					 * The following logic below will search the tableModel for the ID number that the user provides.
					 * If found, the search result will be highlighted for the user to see in the table.
					 */
					found = false;
					if (txtID.getText() != null) { 
						
						for (int i = 0; i < tableModel.getRowCount(); i++) {
							
							if (tableModel.getValueAt(i,0).equals(txtID.getText())) {
								row = i;
								found = true;
								break;
							}
						}
						
						if (found) {
							// Resets the sort order to the default if a sort is enabled by one of the columns.
							table.setRowSorter(new TableRowSorter<>(table.getModel()));
							
							// This line of code highlights the row that is found.
							table.getSelectionModel().setSelectionInterval(row, row);
						
							// Scroll to the highlighted/selected row. Make this row visible to the user.
							table.scrollRectToVisible(new Rectangle(table.getCellRect(row, 0, true)));
							System.out.println(tableModel.getValueAt(row, 0) + " " + tableModel.getValueAt(row, 1) + " " + tableModel.getValueAt(row, 2) + 
									" " + tableModel.getValueAt(row, 3) + " " + tableModel.getValueAt(row, 4) + " " + tableModel.getValueAt(row, 5)
									+ " " + tableModel.getValueAt(row, 6));
						}
					}
				}
				else if (radioBtnDelete.isSelected()) {
					/**
					 * If the specified ID is found, ask the user to confirm their intention to delete the item.
					 */
					found = false;
					if (txtID.getText() != null) {
						
						for (int i = 0; i < guitars.size(); i++) {
							if (guitars.get(i).getId().equals(txtID.getText())) {
								found = true;
							}
							
							if (found) {
								
							    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ID: #" + txtID.getText() + "?", "Confirm",
							        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							    
							    // Yes is clicked, delete the specified row.
							    if (response == JOptionPane.YES_OPTION) {
							    	guitars.remove(i);
							    	System.out.println("Guitar ID: " + txtID.getText() + " deleted!");
							    	break;
							    } 
							    else
							    	break;
							}
						}
					}
						
					if (found) {
						updateTable(guitars);
					}
					else {
						System.out.println("Guitar ID: " + txtID.getText() + " not found/deleted!");
					}
				}
				else if (radioBtnUpdate.isSelected()) {
					/**
					 * Update the table if the user 
					 */
					boolean found = false;
					int row = 0;
					
					// Attempting to find the ID in the records.
					if (txtID.getText() != null) {
					
						for (int i = 0; i < guitars.size(); i++) {
							row = i;
							
							if (guitars.get(i).getId().equals(txtID.getText())) {
								found = true;
								break;
							}
						}
					}
					
					// If found, prompt the user to update the fields as necessary.
					if (found) {
						JTextField model = new JTextField();
						JTextField brand = new JTextField();
						JTextField finish = new JTextField();
						JTextField year = new JTextField();
						JTextField status = new JTextField();
						JTextField price = new JTextField();
						
						Object[] message = {
							"Update the fields. Leave field(s) empty if to remain unchanged!",
						    "Brand:", brand,
						    "Model:", model,
						    "Finish:", finish,
						    "Year:", year,
						    "Status:", status,
						    "Price:", price
						};
	
						int option = JOptionPane.showConfirmDialog(null, message, "Change Guitar ID#:" + guitars.get(row).getId(), JOptionPane.OK_CANCEL_OPTION);
						
						if (option == JOptionPane.OK_OPTION) {
							try {
								// If these text boxes are not empty. Update them to whatever the user sets them as.
								if (!((JTextField)message[2]).getText().equals(""))
									guitars.get(row).setBrand(((JTextField)message[2]).getText());
								if (!((JTextField)message[4]).getText().equals(""))
									guitars.get(row).setModel(((JTextField)message[4]).getText());
								if (!((JTextField)message[6]).getText().equals(""))
									guitars.get(row).setFinish(((JTextField)message[6]).getText());
								if (!((JTextField)message[8]).getText().equals(""))
									guitars.get(row).setYear(Integer.parseInt(((JTextField)message[8]).getText()));
								if (!((JTextField)message[10]).getText().equals(""))
									guitars.get(row).setStatus(((JTextField)message[10]).getText());
								if (!((JTextField)message[12]).getText().equals(""))
									guitars.get(row).setPrice(Double.parseDouble(((JTextField)message[12]).getText()));
							}
							catch (Exception ex) {}
							finally {
								updateTable(guitars);
								txtID.setText("");
							}
						}
					}
				}
			}
    	});
    }
    
    // This method simple updates the table's model and subsequently the visual table displayed to the user.    
    public void updateTable(ArrayList<Guitar> results) {
    	
    	Object [] data = new Object[7];
    	
    	int rowCount = tableModel.getRowCount();
    	
    	// Make guitars equal the passed in argument, results.
    	guitars = results;
    	
    	// Remove all the rows in the tableModel.
    	for (int i = rowCount - 1; i >= 0; i--) {
    		tableModel.removeRow(i);
    	}
    	
    	try {
	    	// Add the new rows into the tableModel.
	    	for (int i = 0; i < results.size(); i++) {
		    		data[0] = results.get(i).getId();
		        	data[1] = results.get(i).getBrand();
		        	data[2] = results.get(i).getModel();
		        	data[3] = results.get(i).getFinish();
		        	data[4] = String.valueOf(results.get(i).getYear());
		        	data[5] = String.format("$%.2f", results.get(i).getPrice());
		        	data[6] = results.get(i).getStatus();
		        	
		        	tableModel.addRow(data);
	    	}
	    	table.setModel(tableModel);
    	}
    	catch (Exception ex) {
    		System.out.println("Table couldn't be updated.");
    	}
    	finally {
    		repaint();
    		System.out.println("Table updated!");
    	}
    }
}
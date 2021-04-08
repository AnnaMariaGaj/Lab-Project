import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UiMain {

	private JFrame frmStorageItem;
	private JTable itemsTable;
	private JTable historyTable;
	private JTable teachersTable;
	private ItemTableModel itemTableModel;
	private TeacherTableModel teacherTableModel;
	private HistoryTableModel historyTableModel;
	
	private List<Item> items = Arrays.asList(
//			new Item("Beaker", 15),
//			new Item("Flask", 15),
//			new Item("Microscope", 10),
//			new Item("Magnet", 0)
			);
//	private List<Item> items = new ArrayList<Item>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiMain window = new UiMain();
					window.frmStorageItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStorageItem = new JFrame();
		frmStorageItem.setTitle("Storage Items Tracker");
		frmStorageItem.setBounds(100, 100, 617, 367);
		frmStorageItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStorageItem.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 605, 327);
		frmStorageItem.getContentPane().add(tabbedPane);
		
		// History Panel
		JPanel historyPanel = new JPanel();
		historyPanel.setLayout(null);
		tabbedPane.addTab("History", null, historyPanel, null);
		
		JScrollPane historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(6, 6, 572, 216);
		historyPanel.add(historyScrollPane);
		
		JScrollPane teachersScrollPane_1 = new JScrollPane();
		teachersScrollPane_1.setBounds(6, 6, 572, 211);
		historyPanel.add(teachersScrollPane_1);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(442, 234, 136, 29);
		historyPanel.add(btnReturn);
		
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Return button clicked");
				int rowIndex = historyTable.getSelectedRow();
				if (rowIndex != -1) {
					Date itemReturnDate = historyTableModel.getItemReturnDate(rowIndex);
					if (itemReturnDate == null) {
						String itemName = historyTableModel.getItemName(rowIndex);
						int quantity = historyTableModel.getItemQuantity(rowIndex);
						historyTableModel.returnItem(rowIndex);
						itemTableModel.returnItem(itemName, quantity);
						System.out.println("Returning item " + itemName);
					}
				}
			}
		});
		// History Table
		historyTable = new JTable();
		historyTable.setAutoCreateRowSorter(true);
		historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyTable.setCellSelectionEnabled(true);
		historyTableModel = new HistoryTableModel();
		historyTable.setModel(historyTableModel);
		historyScrollPane.setViewportView(historyTable);

		
		// Items Panel
		JPanel itemsPanel = new JPanel();
		tabbedPane.addTab("Items", null, itemsPanel, null);
		itemsPanel.setLayout(null);
		
		JScrollPane itemsScrollPane = new JScrollPane();
		itemsScrollPane.setBounds(6, 6, 572, 216);
		itemsPanel.add(itemsScrollPane);
		
		// Items Table
		itemsTable = new JTable();
		itemsTable.setAutoCreateRowSorter(true);
		itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsTable.setCellSelectionEnabled(true);
		itemTableModel = new ItemTableModel();
		itemsTable.setModel(itemTableModel);
		itemsScrollPane.setViewportView(itemsTable);

		
		// Teachers Panel 
		JPanel teachersPanel = new JPanel();
		tabbedPane.addTab("Teachers", null, teachersPanel, null);
		teachersPanel.setLayout(null);
		
		JScrollPane teachersScrollPane = new JScrollPane();
		teachersScrollPane.setBounds(6, 6, 572, 211);
		teachersPanel.add(teachersScrollPane);
		
		// Teachers Table
		teachersTable = new JTable();
		teachersTable.setAutoCreateRowSorter(true);
		teacherTableModel = new TeacherTableModel();
		teachersTable.setModel(teacherTableModel);
		teachersScrollPane.setViewportView(teachersTable);
		
		//  Teacher Button
		JButton btnNewTeacherButton = new JButton("Add new teacher");
		btnNewTeacherButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Add new Teacher button clicked");
				JTextField name = new JTextField();
				JTextField password = new JPasswordField();
				Object[] message = {
				    "Name: ", name
				};

				int option = JOptionPane.showConfirmDialog(frmStorageItem, message, "New Teacher", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
				    System.out.println("Adding new teacher: " + name.getText());
				    teacherTableModel.newRow(name.getText());
				} else {
				    System.out.println("Adding canceled");
				}
			}
		});
		btnNewTeacherButton.setBounds(442, 234, 136, 29);
		teachersPanel.add(btnNewTeacherButton);
		
		JButton btnRemoveTeacherButton = new JButton("Remove teacher");
		btnRemoveTeacherButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Remove teacher button clicked");
				int rowIndex = teachersTable.getSelectedRow();
				if (rowIndex != -1) {
					teacherTableModel.removeRow(rowIndex);
					System.out.println("Removing teacher " + rowIndex);
				}
				
			}
		});
		btnRemoveTeacherButton.setBackground(Color.RED);
		btnRemoveTeacherButton.setBounds(6, 234, 136, 29);
		teachersPanel.add(btnRemoveTeacherButton);
		
		//Items Button 
		JButton btnNewItemButton = new JButton("Add new item");
		btnNewItemButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Add new item button clicked");
				JTextField name = new JTextField();
				JTextField quantity = new JTextField();
				Object[] message = {
				    "Name: ", name,
				    "Quantity: ", quantity
				};

				int option = JOptionPane.showConfirmDialog(frmStorageItem, message, "New Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					if (name.getText().equals("") || quantity.getText().equals("")) {
						System.out.println("Error empty fields");
						JOptionPane.showMessageDialog(null, "Please fill all information");
					} else {
						System.out.println("Adding new item: " + name.getText() + ", quantity: " + quantity.getText());
					    itemTableModel.newRow(name.getText(), Integer.parseInt(quantity.getText()));
					}
				    
				} else {
				    System.out.println("Adding canceled");
				}
			}
		});
		btnNewItemButton.setBounds(442, 234, 136, 29);
		itemsPanel.add(btnNewItemButton);
		
		JButton borrowButton = new JButton("Borrow");
		borrowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = itemsTable.getSelectedRow();
				System.out.println("Borrow item button clicked " + rowIndex);
				if (rowIndex != -1) {
					String itemName = itemTableModel.getItemName(itemsTable.getSelectedRow());
					
					String[] teachersStrings = teacherTableModel.getAllTeachers();
					JComboBox teachersList = new JComboBox(teachersStrings);
					JTextField quantity = new JTextField();
					Object[] message = {
					    "Teacher: ", teachersList,
					    "Quantity: ", quantity
					};
					
	
					int option = JOptionPane.showConfirmDialog(frmStorageItem, message, "Borrow item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (option == JOptionPane.OK_OPTION) {
						if (teachersList.getSelectedItem().equals("") || quantity.getText().equals("")) {
							System.out.println("Error empty fields");
							JOptionPane.showMessageDialog(null, "Please fill all information");
						} else if(Integer.parseInt(quantity.getText()) <= itemTableModel.getAvailableQuantity(rowIndex)) {
							System.out.println("Borrowing item: " + teachersList.getSelectedItem());
							itemTableModel.borrowItem(rowIndex, Integer.parseInt(quantity.getText()));
						    historyTableModel.newRow(teachersList.getSelectedItem().toString(), itemName, Integer.parseInt(quantity.getText()));
						} else {
							// the number is too big
							JOptionPane.showMessageDialog(frmStorageItem, "Not enough available items", "Error", JOptionPane.ERROR_MESSAGE);
							System.out.println("Not enough available items");
						}
					    
					} else {
					    System.out.println("Borrowing canceled");
					}
				}
			}
		});
		borrowButton.setBounds(6, 234, 117, 29);
		itemsPanel.add(borrowButton);
		
		JButton btnRemoveItemButton = new JButton("Remove item");
		btnRemoveItemButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = itemsTable.getSelectedRow();
				System.out.println("Remove item button clicked " + rowIndex);
				if (rowIndex != -1) {
					int option = JOptionPane.showConfirmDialog(frmStorageItem, "Are you sure you want to remove this item?", "Remove item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (option == JOptionPane.OK_OPTION) {
						itemTableModel.removeRow(rowIndex);
						System.out.println("Removing item " + rowIndex);
					}
					
				}
			}
		});
		btnRemoveItemButton.setBounds(135, 234, 117, 29);
		itemsPanel.add(btnRemoveItemButton);
		
		
		// Add initial test values
		teacherTableModel.newRow("Anna");
		itemTableModel.newRow("Microscope", 14);
	}
}

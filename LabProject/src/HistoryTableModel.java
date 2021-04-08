
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class HistoryTableModel extends DefaultTableModel  {
	private String[] columnNames = { "Teacher", "Item", "Quantity", "Status", "Pickup", "Return"};
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	@Override
    public Class getColumnClass(int col) {
      if  (col == 2)
          return Integer.class;
      else if (col == 4 || col == 5)
          return Date.class;
      else return String.class;
      
  }
	
	public void newRow(String teacherName,String itemName, int quantity) {
		History newHistory = new History(teacherName, itemName, quantity);
		
		Object[] row = new Object[6];
		row[0] = newHistory.getTeacher();
		row[1] = newHistory.getItem();
		row[2]= newHistory.getQuantity();
		row[3]= newHistory.getStatus();
		row[4]= newHistory.getPickupDate();
		row[5]= newHistory.getReturnDate();
		addRow(row);
	}
	
	public void returnItem(int rowIndex) {
		setValueAt(new Date(), rowIndex, 5);
		setValueAt("Returned", rowIndex, 3);
	}

	public String getItemName(int rowIndex) {
		return (String) getValueAt(rowIndex, 1);
	}

	public int getItemQuantity(int rowIndex) {
		return (int) getValueAt(rowIndex, 2);
	}

	public Date getItemReturnDate(int rowIndex) {
		return (Date) getValueAt(rowIndex, 5);
	}
	
	
	
}

import javax.swing.table.DefaultTableModel;

public class ItemTableModel extends DefaultTableModel  {
	private String[] columnNames = { "Name", "Available Quantity", "Total Quantity"};
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return true;
	}
	
	@Override
    public Class getColumnClass(int col) {
      if (col == 1 || col == 2)
          return Integer.class;
      else return String.class;
  }
	
	public void newRow(String name, int quantity) {
		Item newItem = new Item(name, quantity);
		
		Object[] row = new Object[3];
		row[0] = newItem.getName();
		row[1] = newItem.getQuantity();
		row[2]= newItem.getQuantity();
		addRow(row);
	}
	
	public String getItemName(int rowIndex) {
		return (String) getValueAt(rowIndex, 0);
	}
	
	public void borrowItem(int rowIndex, int quantity) {
		int availableQuantity = (int) getValueAt(rowIndex, 1);
		setValueAt(availableQuantity - quantity, rowIndex, 1);
	}

	public void returnItem(String itemName, int quantity) {
		int rowIndex = getRowByItemName(itemName);
		if (rowIndex != -1) {
			int availableQuantity = (int) getValueAt(rowIndex, 1);
			setValueAt(availableQuantity + quantity, rowIndex, 1);
		}
	}
	
	public int getAvailableQuantity(int rowIndex) {
		return (int) getValueAt(rowIndex, 1);
	}
	
	public int getRowByItemName(String itemName) {
	    for (int i = getRowCount() - 1; i >= 0; --i) {
            if (getValueAt(i, 0).equals(itemName)) {
                return i;
            }
	    }
		return -1;
	}
	
}
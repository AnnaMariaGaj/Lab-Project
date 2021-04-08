
import javax.swing.table.DefaultTableModel;

public class TeacherTableModel extends DefaultTableModel  {
	private String[] columnNames = { "Name"};
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	};
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return true;
	}

	public void newRow(String name) {
		Teacher newTeacher = new Teacher(name);
		
		Object[] row = new Object[1];
		row[0] = newTeacher.getName();
		addRow(row);
	}
	
	public String[] getAllTeachers() {
		String[] teachers = new String[this.getRowCount()];
		for(int i = 0; i < this.getRowCount(); i++) {
			teachers[i] = (String) this.getValueAt(i, 0);
		}
		return teachers;
	}
	

}

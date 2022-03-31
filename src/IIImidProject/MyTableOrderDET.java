package IIImidProject;

import javax.swing.table.AbstractTableModel;

public class MyTableOrderDET extends AbstractTableModel {
    private boolean DEBUG = false;
    public static String[] columnNames = {"OrderID",
            "Last Name",
            "OrderID",
            "OrderID",
            "OrderID"};
    private Object[][] data = TableDataDBImp.getOrderDET();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}

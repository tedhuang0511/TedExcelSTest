package IIImidProject;

import javax.swing.table.AbstractTableModel;

public class MyTableCustomers extends AbstractTableModel {
    private boolean DEBUG = false;
    public static String[] columnNames = {"CustomerID",
            "CompanyName",
            "ContactName",
            "ContactTitle",
            "Address",
            "City",
            "Region",
            "PostalCode",
            "Country",
            "Phone",
            "Fax"};
    private Object[][] data = TableDataDBImp.getCustomers();

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
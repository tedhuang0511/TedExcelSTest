package IIImidProject;

import javax.swing.table.AbstractTableModel;

public class MyTableEmployees extends AbstractTableModel {
    private boolean DEBUG = false;
    public static String[] columnNames = {"EmployeeID",
            "LastName",
            "FirstName",
            "Title",
            "TitleOfCourtesy",
            "BirthDate",
            "HireDate",
            "Address",
            "City",
            "Region",
            "PostalCode",
            "Country",
            "HomePhone",
            "Extension",
            "Notes",
            "ReportsTo",
            "PhotoPath",
            "Salary"};
    private Object[][] data = TableDataDBImp.getEmployees();

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
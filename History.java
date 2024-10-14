import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class History extends JPanel {
    Fun appLogic;
    private JTable historyTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public History(Fun appLogic) {
        this.appLogic = appLogic;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Add title
        titleLabel = new JLabel("History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Create table model with column names
        String[] columnNames = {"ID", "Name", "Plate Number", "Current Price", "Init Weight", "Final Weight", "Grade", "Buy Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create JTable with the model
        historyTable = new JTable(model);
        historyTable.setFillsViewportHeight(true);

        // Add some sample data (replace this with actual database fetching later)
        addSampleData(model);

        // Create scroll pane and add table to it
        scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        applyStyles();
    }

    private void addSampleData(DefaultTableModel model) {
        // Add some sample rows (replace with actual data from database later)
        model.addRow(new Object[]{"1", "John Doe", "ABC123", "1000", "500", "450", "A", "4500"});
        model.addRow(new Object[]{"2", "Jane Smith", "XYZ789", "1000", "600", "580", "B", "5800"});
        model.addRow(new Object[]{"3", "Bob Johnson", "DEF456", "1000", "550", "520", "A", "5200"});
    }

    private void applyStyles() {
        // Style the table
        historyTable.setBackground(Color.WHITE);
        historyTable.setForeground(Color.BLACK);
        historyTable.setGridColor(new Color(200, 200, 200));
        historyTable.setRowHeight(25);

        // Style the header
        historyTable.getTableHeader().setBackground(new Color(220, 220, 220));
        historyTable.getTableHeader().setForeground(Color.BLACK);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Style the scroll pane
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Style the title
        titleLabel.setForeground(Color.BLACK);
    }

    // Method to update table data (call this when you fetch new data from the database)
    public void updateTableData(Object[][] newData) {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : newData) {
            model.addRow(row);
        }
    }
}

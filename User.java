import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class User extends JPanel {
    private Fun appLogic;
    private JTable userTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JButton deleteButton, updateButton;

    public User(Fun appLogic) {
        this.appLogic = appLogic;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Add title
        titleLabel = new JLabel("User Table", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Create table model with column names
        String[] columnNames = {"ID", "Name", "Plate Number", "Phone Number"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create JTable with the model
        userTable = new JTable(model);
        userTable.setFillsViewportHeight(true);

        // Add some sample data (replace this with actual database fetching later)
        addSampleData(model);

        // Create scroll pane and add table to it
        scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to refresh the table
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshUserTable());
        buttonPanel.add(refreshButton);

        applyStyles();
    }

    private void addSampleData(DefaultTableModel model) {
        // Add some sample rows (replace with actual data from database later)
        model.addRow(new Object[]{"1", "Isma", "asasa3323", "2324234"});
        model.addRow(new Object[]{"2", "Jane Smith", "XYZ789", "9876543210"});
        model.addRow(new Object[]{"3", "Bob Johnson", "DEF456", "5555555555"});
    }

    private void applyStyles() {
        // Style the table
        userTable.setBackground(Color.WHITE);
        userTable.setForeground(Color.BLACK);
        userTable.setGridColor(new Color(200, 200, 200));
        userTable.setRowHeight(25);

        // Style the header
        userTable.getTableHeader().setBackground(new Color(220, 220, 220));
        userTable.getTableHeader().setForeground(Color.BLACK);
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Style the scroll pane
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Style the title
        titleLabel.setForeground(Color.BLACK);

        // Style the buttons
        deleteButton.setBackground(new Color(220, 53, 69)); // Red color
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));

        updateButton.setBackground(new Color(40, 167, 69)); // Green color
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 12));
    }

    // Method to update table data (call this when you fetch new data from the database)
    public void updateTableData(Object[][] newData) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Object[] row : newData) {
            model.addRow(row);
        }
    }

    private void refreshUserTable() {
        List<Fun.User> users = appLogic.getUsers();
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear existing data
        for (Fun.User user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getName(),
                user.getPlateNumber(),
                user.getPhoneNumber()
            });
        }
    }
}

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Calculator extends JPanel {
    private Fun appLogic;
    JTextField currentPriceField, idField, initialWeightField, finalWeightField;
    JComboBox<String> gradeComboBox;
    JButton generateButton;
    JLabel imageLabel;
    JPanel contentPanel, inputPanel;

    public Calculator(Fun appLogic) {
        this.appLogic = appLogic;
        setLayout(new BorderLayout());

        // Create main content panel
        contentPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add title
        JLabel titleLabel = new JLabel("OilPalmPay", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for input fields and image
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.CENTER);

        // Add input fields
        addLabelAndField(inputPanel, gbc, "Current price (RM)", currentPriceField = new JTextField(20));
        addLabelAndField(inputPanel, gbc, "ID", idField = new JTextField(20));
        addLabelAndField(inputPanel, gbc, "Initial Weight (kg in vehicle)", initialWeightField = new JTextField(20));
        addLabelAndField(inputPanel, gbc, "Final Weight (kg in vehicle)", finalWeightField = new JTextField(20));

        currentPriceField.setText("600"); // set default val here

        // Add grade dropdown
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Grade"), gbc);
        gbc.gridx = 1;
        String[] grades = {"A", "B", "C"};
        gradeComboBox = new JComboBox<>(grades);
        inputPanel.add(gradeComboBox, gbc);

        // Add generate button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        generateButton = new JButton("Generate");
        inputPanel.add(generateButton, gbc);

        // Add action listener to the generate button
        generateButton.addActionListener(e -> generatePayment());

        // Add image on the right
        try {
            File imageFile = new File("./asset/sawit.png");
            if (imageFile.exists()) {
                Image img = ImageIO.read(imageFile);
                Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaledImg));
                imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Add some padding
                centerPanel.add(imageLabel, BorderLayout.EAST);
            } else {
                System.out.println("Image file not found: " + imageFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        contentPanel.add(centerPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        applyStyles();
    }

    private void generatePayment() {
        try {
            int userId = Integer.parseInt(idField.getText());
            double currentPrice = Double.parseDouble(currentPriceField.getText());
            double initialWeight = Double.parseDouble(initialWeightField.getText());
            double finalWeight = Double.parseDouble(finalWeightField.getText());
            String grade = (String) gradeComboBox.getSelectedItem();

            double netWeight = Math.abs(finalWeight - initialWeight);
            double payment = appLogic.calculatePayment(currentPrice, initialWeight, finalWeight, grade);
            appLogic.addTransaction(userId, currentPrice, initialWeight, finalWeight, grade);

            JOptionPane.showMessageDialog(this, 
                String.format("Net Weight: %.2f kg\nPayment calculated: RM %.2f", netWeight, payment),
                "Payment Result", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for all fields.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(field, gbc);
    }

    private void applyStyles() {
        setBackground(Color.WHITE);
        contentPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        
        Component[] components = inputPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JTextField) {
                JTextField field = (JTextField) comp;
                field.setBackground(Color.WHITE);
                field.setForeground(Color.BLACK);
                field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            } else if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(Color.BLACK);
            }
        }
        
        gradeComboBox.setBackground(Color.WHITE);
        gradeComboBox.setForeground(Color.BLACK);
        
        generateButton.setBackground(new Color(0, 122, 255));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

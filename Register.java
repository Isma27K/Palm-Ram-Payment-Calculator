import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Register extends JPanel {
    JTextField nameField, plateNumberField, phoneNumberField;
    JButton registerButton;
    JPanel contentPanel, inputPanel;
    JLabel imageLabel;

    public Register() {
        setLayout(new BorderLayout());

        // Create main content panel
        contentPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add title
        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for input fields and image
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.CENTER);

        // Add input fields
        addLabelAndField(inputPanel, gbc, "Name", nameField = new JTextField(20));
        addLabelAndField(inputPanel, gbc, "Plate Number", plateNumberField = new JTextField(20));
        addLabelAndField(inputPanel, gbc, "Phone Number", phoneNumberField = new JTextField(20));

        // Add register button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("Register");
        inputPanel.add(registerButton, gbc);

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
        
        registerButton.setBackground(new Color(0, 122, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

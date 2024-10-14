import javax.swing.*;
//import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Palm Oil Ram Factory System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Create the JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create Calculator
        Calculator calculator = new Calculator();

        // Create Register
        Register register = new Register();

        // Create History
        History history = new History();

        // Create User
        User user = new User();

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Calculator", calculator);
        tabbedPane.addTab("Register", register);
        tabbedPane.addTab("History", history);
        tabbedPane.addTab("User", user);

        // Add the tabbed pane to the frame (this)
        add(tabbedPane);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

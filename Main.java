import javax.swing.*;
//import java.awt.*;

public class Main extends JFrame {
    public Fun appLogic;

    public Main() {
        appLogic = new Fun();
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
        Calculator calculator = new Calculator(appLogic);

        // Create Register
        Register register = new Register(appLogic);

        // Create History
        History history = new History(appLogic);

        // Create User
        User user = new User(appLogic);

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

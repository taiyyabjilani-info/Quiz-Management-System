
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame {

    // Constructor
    private QuizManager manager;

    public HomeFrame(QuizManager manager) {
        this.manager = manager;

        // Window settings
        setTitle("Quiz Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 245, 255));
        mainPanel.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 60, 110));
        headerPanel.setPreferredSize(new Dimension(700, 120));
        headerPanel.setLayout(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel(
                "QUIZ MANAGEMENT SYSTEM",
                JLabel.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 27));
        titleLabel.setForeground(Color.WHITE);

        JLabel welcomeLabel = new JLabel(
                "Welcome! Test Your Knowledge",
                JLabel.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        headerPanel.add(welcomeLabel);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(240, 245, 255));
        centerPanel.setLayout(new GridLayout(4, 1, 10, 15));

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(40, 180, 40, 180));

        // Buttons
        JButton startButton = new JButton("Start Quiz");
        JButton adminButton = new JButton("Admin Panel");
        JButton instructionButton = new JButton("Instructions");
        JButton exitButton = new JButton("Exit");

        // Button styling
        JButton[] buttons = {
                startButton,
                adminButton,
                instructionButton,
                exitButton
        };

        for (JButton button : buttons) {

            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(45, 90, 160));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
        }

        // Add buttons
        centerPanel.add(startButton);
        centerPanel.add(adminButton);
        centerPanel.add(instructionButton);
        centerPanel.add(exitButton);

        // Footer
        JLabel footerLabel = new JLabel(
                "Developed using Java Swing",
                JLabel.CENTER);

        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(
                BorderFactory.createEmptyBorder(10, 0, 15, 0));

        // Button actions
        startButton.addActionListener(e -> {
            if (manager.getTotalQuestions() == 0) {
                JOptionPane.showMessageDialog(this, "No questions are available. Add questions in the Admin Panel first.");
            } else new QuizFrame(manager);
        });

        adminButton.addActionListener(e -> {
            new AdminFrame(manager);
        });

        instructionButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "1. Click Start Quiz to begin.\n" +
                            "2. Select the correct option.\n" +
                            "3. Answer all questions.\n" +
                            "4. Submit your quiz to see the result.",
                    "Quiz Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Application",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // Display window
        setVisible(true);
    }
}

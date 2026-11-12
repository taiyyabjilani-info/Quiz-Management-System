
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuizFrame extends JFrame {

    private QuizManager manager;
    private int currentQuestion = 0;
    private int[] answers;

    private JLabel questionLabel;
    private JLabel categoryLabel;
    private JLabel progressLabel;

    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;

    private ButtonGroup buttonGroup;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    public QuizFrame(QuizManager manager) {

        this.manager = manager;

        if (manager.getTotalQuestions() == 0) {
            JOptionPane.showMessageDialog(null, "No quiz questions are available.");
            dispose();
            return;
        }

        answers = new int[manager.getTotalQuestions()];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = -1;
        }

        setTitle("Quiz - Quiz Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 245, 255));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 60, 110));
        headerPanel.setPreferredSize(new Dimension(700, 80));
        headerPanel.setLayout(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel("KNOWLEDGE TEST", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        progressLabel = new JLabel("", JLabel.CENTER);
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        headerPanel.add(titleLabel);
        headerPanel.add(progressLabel);

        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setLayout(new GridLayout(6, 1, 10, 10));
        questionPanel.setBorder(
            BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );

        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 14));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        JRadioButton[] options = {
            option1, option2, option3, option4
        };

        buttonGroup = new ButtonGroup();

        for (JRadioButton option : options) {
            option.setFont(new Font("Arial", Font.PLAIN, 16));
            option.setBackground(Color.WHITE);
            buttonGroup.add(option);
        }

        questionPanel.add(categoryLabel);
        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);

        // Bottom Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 245, 255));

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit Quiz");

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        previousButton.addActionListener(e -> {
            saveAnswer();
            if (currentQuestion > 0) {
                currentQuestion--;
                showQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveAnswer();
            if (currentQuestion < answers.length - 1) {
                currentQuestion++;
                showQuestion();
            }
        });

        submitButton.addActionListener(e -> submitQuiz());

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        showQuestion();

        setVisible(true);
    }

    private void showQuestion() {

        ArrayList<MCQQuestion> questions = manager.getQuestions();

        if (questions.isEmpty()) return;
        MCQQuestion question = questions.get(currentQuestion);

        categoryLabel.setText("Category: " + question.getCategory());

        questionLabel.setText(
            "Q" + (currentQuestion + 1) + ". " +
            question.getQuestionText()
        );

        ArrayList<String> options = question.getOptions();

        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));

        buttonGroup.clearSelection();

        if (answers[currentQuestion] != -1) {
            int selected = answers[currentQuestion];

            if (selected == 0) option1.setSelected(true);
            if (selected == 1) option2.setSelected(true);
            if (selected == 2) option3.setSelected(true);
            if (selected == 3) option4.setSelected(true);
        }

        progressLabel.setText(
            "Question " + (currentQuestion + 1) +
            " of " + answers.length
        );

        previousButton.setEnabled(currentQuestion > 0);

        nextButton.setEnabled(
            currentQuestion < answers.length - 1
        );
    }

    private void saveAnswer() {

        if (option1.isSelected()) answers[currentQuestion] = 0;
        else if (option2.isSelected()) answers[currentQuestion] = 1;
        else if (option3.isSelected()) answers[currentQuestion] = 2;
        else if (option4.isSelected()) answers[currentQuestion] = 3;
    }

    private void submitQuiz() {

        saveAnswer();

        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to submit the quiz?",
            "Confirm Submission",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            int correctAnswers = 0;

            ArrayList<MCQQuestion> questions = manager.getQuestions();

            for (int i = 0; i < questions.size(); i++) {

                if (answers[i] + 1 ==
                    questions.get(i).getCorrectAnswer()) {

                    correctAnswers++;
                }
            }

            int totalQuestions = questions.size();
            int wrongAnswers = totalQuestions - correctAnswers;
            Result result = new Result(totalQuestions, correctAnswers);

            JOptionPane.showMessageDialog(
                this,
                "QUIZ COMPLETED!\n\n" +
                "Total Questions: " + totalQuestions + "\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Wrong Answers: " + wrongAnswers + "\n" +
                "Percentage: " + String.format("%.2f", result.calculatePercentage()) + "%",
                "Quiz Result",
                JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        }
    }
}

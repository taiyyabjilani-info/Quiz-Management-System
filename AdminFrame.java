import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame {
    private final QuizManager manager;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> questionList = new JList<>(listModel);
    private final JTextField questionField = new JTextField();
    private final JTextField[] optionFields = { new JTextField(), new JTextField(), new JTextField(),
            new JTextField() };
    private final JComboBox<String> categoryField = new JComboBox<>(
            new String[] { "Programming", "Computer Basics", "Web Development", "General Knowledge" });
    private final JComboBox<String> correctChoice = new JComboBox<>(
            new String[] { "Option 1", "Option 2", "Option 3", "Option 4" });

    public AdminFrame(QuizManager manager) {
        this.manager = manager;
        setTitle("Admin Panel - Quiz Management System");
        setSize(850, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));
        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Add a question"));
        categoryField.setEditable(true);
        form.add(new JLabel("Question:"));
        form.add(questionField);
        form.add(new JLabel("Category:"));
        form.add(categoryField);
        for (int i = 0; i < 4; i++) {
            form.add(new JLabel("Option " + (i + 1) + ":"));
            form.add(optionFields[i]);
        }
        form.add(new JLabel("Correct answer:"));
        form.add(correctChoice);
        JButton addButton = new JButton("Add Question");
        form.add(addButton);
        form.add(new JLabel("All fields are required."));
        JPanel right = new JPanel(new BorderLayout(6, 6));
        right.setBorder(BorderFactory.createTitledBorder("Existing questions"));
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        right.add(new JScrollPane(questionList), BorderLayout.CENTER);
        JPanel actions = new JPanel();
        JButton deleteButton = new JButton("Delete Selected");
        JButton closeButton = new JButton("Close");
        actions.add(deleteButton);
        actions.add(closeButton);
        right.add(actions, BorderLayout.SOUTH);
        add(form, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
        addButton.addActionListener(e -> addQuestion());
        deleteButton.addActionListener(e -> deleteQuestion());
        closeButton.addActionListener(e -> dispose());
        refreshList();
        setVisible(true);
    }

    private void addQuestion() {
        String text = questionField.getText().trim(), category = categoryField.getEditor().getItem().toString().trim();
        if (text.isEmpty() || category.isEmpty()) {
            showError("Enter both the question and category.");
            return;
        }
        ArrayList<String> options = new ArrayList<>();
        for (JTextField field : optionFields) {
            String option = field.getText().trim();
            if (option.isEmpty()) {
                showError("Enter all four options.");
                return;
            }
            options.add(option);
        }
        for (int i = 0; i < options.size(); i++)
            for (int j = i + 1; j < options.size(); j++)
                if (options.get(i).equalsIgnoreCase(options.get(j))) {
                    showError("Options must be different.");
                    return;
                }
        try {
            manager.addQuestion(new MCQQuestion(text, options, correctChoice.getSelectedIndex() + 1, category));
            questionField.setText("");
            categoryField.setSelectedIndex(0);
            for (JTextField field : optionFields)
                field.setText("");
            refreshList();
            JOptionPane.showMessageDialog(this, "Question added and saved.");
        } catch (RuntimeException ex) {
            showError("Could not add question: " + ex.getMessage());
        }
    }

    private void deleteQuestion() {
        int index = questionList.getSelectedIndex();
        if (index < 0) {
            showError("Select a question to delete.");
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this, "Delete the selected question?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            manager.deleteQuestion(index);
            refreshList();
        }
    }

    private void refreshList() {
        listModel.clear();
        for (MCQQuestion question : manager.getQuestions())
            listModel.addElement("[" + question.getCategory() + "] " + question.getQuestionText());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

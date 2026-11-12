
import java.util.ArrayList;

public class Question {

    private String questionText;
    private ArrayList<String> options;
    private int correctAnswer;

    // Constructor
    public Question(String questionText, ArrayList<String> options, int correctAnswer) {

        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getter methods
    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    // Method to check answer
    public boolean checkAnswer(int answer) {

        return answer == correctAnswer;
    }

    // Display question
    public void displayQuestion() {

        System.out.println(questionText);

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

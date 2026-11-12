
import java.util.ArrayList;

public class MCQQuestion extends Question {

    private String category;

    // Constructor
    public MCQQuestion(String questionText, ArrayList<String> options,
                       int correctAnswer, String category) {

        super(questionText, options, correctAnswer);

        this.category = category;
    }

    // Getter method
    public String getCategory() {
        return category;
    }

    // Method overriding
    @Override
    public void displayQuestion() {

        System.out.println("Category: " + category);

        super.displayQuestion();
    }
}

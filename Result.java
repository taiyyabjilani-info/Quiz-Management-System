
public class Result {

    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;

    // Constructor
    public Result(int totalQuestions, int correctAnswers) {

        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;

        this.wrongAnswers = totalQuestions - correctAnswers;
    }

    // Calculate percentage
    public double calculatePercentage() {
        if (totalQuestions == 0) {
            return 0.0;
        }
        return (correctAnswers * 100.0) / totalQuestions;
    }

    // Display result
    public void displayResult() {

        System.out.println("\n========== QUIZ RESULT ==========");

        System.out.println("Total Questions: " + totalQuestions);

        System.out.println("Correct Answers: " + correctAnswers);

        System.out.println("Wrong Answers: " + wrongAnswers);

        System.out.println("Percentage: " + calculatePercentage() + "%");

        System.out.println("================================");
    }
}

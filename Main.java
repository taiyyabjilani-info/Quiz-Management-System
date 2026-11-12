
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            QuizManager manager = new QuizManager();

            new HomeFrame(manager);

        });
    }
}

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class QuizManager {
    private final ArrayList<MCQQuestion> questions = new ArrayList<>();
    private final Path dataFile = Paths.get("questions.txt");

    public QuizManager() { loadQuestions(); }

    public void addQuestion(MCQQuestion question) {
        if (question == null) throw new IllegalArgumentException("Question cannot be empty.");
        questions.add(question);
        saveQuestions();
    }

    public void deleteQuestion(int index) {
        if (index < 0 || index >= questions.size()) throw new IndexOutOfBoundsException("Select a valid question.");
        questions.remove(index);
        saveQuestions();
    }

    public ArrayList<MCQQuestion> getQuestions() { return new ArrayList<>(questions); }
    public int getTotalQuestions() { return questions.size(); }

    private void loadQuestions() {
        if (!Files.exists(dataFile)) { addSampleQuestions(); saveQuestions(); return; }
        try (BufferedReader reader = Files.newBufferedReader(dataFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\t", -1);
                if (fields.length != 7) continue;
                try {
                    ArrayList<String> options = new ArrayList<>();
                    for (int i = 2; i <= 5; i++) options.add(decode(fields[i]));
                    questions.add(new MCQQuestion(decode(fields[0]), options,
                            Integer.parseInt(fields[6]), decode(fields[1])));
                } catch (RuntimeException ex) { System.err.println("Skipped invalid question record: " + ex.getMessage()); }
            }
        } catch (IOException ex) { System.err.println("Could not read questions.txt: " + ex.getMessage()); }
        if (questions.isEmpty()) { addSampleQuestions(); saveQuestions(); }
    }

    private void saveQuestions() {
        try (BufferedWriter writer = Files.newBufferedWriter(dataFile, StandardCharsets.UTF_8)) {
            for (MCQQuestion q : questions) {
                writer.write(encode(q.getQuestionText()) + "\t" + encode(q.getCategory()));
                for (String option : q.getOptions()) writer.write("\t" + encode(option));
                writer.write("\t" + q.getCorrectAnswer());
                writer.newLine();
            }
        } catch (IOException ex) { System.err.println("Could not save questions.txt: " + ex.getMessage()); }
    }

    private String encode(String value) { return java.util.Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)); }
    private String decode(String value) { return new String(java.util.Base64.getDecoder().decode(value), StandardCharsets.UTF_8); }

    private void addSampleQuestions() {
        add("Which language is used for Android development?", "Programming", "Java", "HTML", "CSS", "SQL", 1);
        add("Who developed Java?", "Programming", "Microsoft", "Google", "James Gosling", "Amazon", 3);
        add("Which memory is volatile?", "Computer Basics", "RAM", "ROM", "Hard Disk", "Pen Drive", 1);
        add("What is the full form of HTML?", "Web Development", "Hyper Text Markup Language", "High Text Machine Language", "Hyper Transfer Markup Language", "None of these", 1);
        add("Which is an output device?", "Computer Basics", "Scanner", "Printer", "Keyboard", "Mouse", 2);
    }
    private void add(String text, String category, String a, String b, String c, String d, int answer) {
        ArrayList<String> options = new ArrayList<>();
        options.add(a); options.add(b); options.add(c); options.add(d);
        questions.add(new MCQQuestion(text, options, answer, category));
    }
}

# 🎯 Quiz Management System

A desktop-based **Quiz Management System** developed using Java and Java Swing. The application allows users to participate in multiple-choice quizzes, view their results, and manage quiz questions through an Admin Panel.

This project was developed as a college mini-project to demonstrate practical implementation of Object-Oriented Programming, GUI development, event handling, collections, and file handling in Java.

## ✨ Features

### 🧑‍🎓 Quiz Module
- Interactive multiple-choice quiz.
- Four options for every question.
- Next and Previous question navigation.
- Retains selected answers while navigating.
- Submit quiz functionality.
- Automatic score calculation.
- Displays correct answers, wrong answers, and percentage.

### 🔐 Admin Panel
- Add new quiz questions.
- Select existing categories.
- Create custom categories.
- Add four multiple-choice options.
- Select the correct answer.
- View existing questions.
- Delete questions.
- Input validation.

### 💾 File Management
- Stores questions in `questions.txt`.
- Creates starter questions on the first launch.
- Saves questions between application sessions.
- Uses Java file handling.
- No database required.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Java Swing | Graphical User Interface |
| Java AWT | GUI components and layouts |
| ArrayList | Managing questions and options |
| File Handling | Saving and loading questions |
| VS Code | Development environment |

## 📂 Project Structure

```text
QuizManagementSystem/
│
├── .vscode/
│   └── launch.json
│
├── src/
│   ├── Main.java
│   ├── HomeFrame.java
│   ├── QuizFrame.java
│   ├── AdminFrame.java
│   ├── Question.java
│   ├── MCQQuestion.java
│   ├── QuizManager.java
│   └── Result.java
│
├── out/                 # Compiled files (not committed)
│
├── questions.txt        # Quiz question storage
├── .gitignore
└── README.md
```

## ⚙️ Requirements

Before running this project, install:

- Java Development Kit (JDK).
- Visual Studio Code.
- Java Extension Pack for VS Code (recommended).

Check your Java installation:

```bash
java -version
javac -version
```

## 🚀 How to Run the Project

### 1. Clone the repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Open the project folder

```bash
cd QuizManagementSystem
```

### 3. Compile the Java files

For Windows PowerShell:

```powershell
javac -d out src\*.java
```

### 4. Run the application

```powershell
java -cp out Main
```

The Quiz Management System home screen will open.

Alternatively, use the **Run Quiz Management System** launch configuration in VS Code.

## 📸 Application Screenshots

Add screenshots of your running application to showcase its interface.

### 🏠 Home Screen

<!-- Add home screen screenshot here -->

### 📝 Quiz Interface

<!-- Add quiz interface screenshot here -->

### 🔐 Admin Panel

<!-- Add admin panel screenshot here -->

### 📊 Result Screen

<!-- Add result screen screenshot here -->

## 🧠 OOP Concepts Implemented

This project demonstrates the following Java concepts:

- **Encapsulation:** Using private data members and public getter methods.
- **Inheritance:** `MCQQuestion` inherits from the `Question` class.
- **Polymorphism:** Method overriding using `displayQuestion()`.
- **Classes and Objects:** Separate classes for quiz management, questions, results, and GUI screens.
- **Exception Handling:** Handling errors during file operations and input validation.
- **Collections:** Using `ArrayList` to manage questions and options.

## 🎯 Project Objectives

- Develop an interactive quiz application using Java Swing.
- Understand practical implementation of OOP concepts.
- Learn GUI development and event handling.
- Implement file handling for persistent question storage.
- Build a simple question management system without a database.

## 🔮 Future Enhancements

- Countdown timer for quizzes.
- User login and registration.
- Different difficulty levels.
- Leaderboard and ranking system.
- Detailed performance reports.
- More quiz categories.

## 👨‍💻 Author

**Developed as a Java Mini Project**

## 📜 License

This project is created for educational and learning purposes.

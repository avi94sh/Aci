import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

 class OnlineExamination {

    // User data storage
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static int timeLeft = 300; // 5 minutes for the exam
    private static Timer timer = new Timer();
    private static boolean examInProgress = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. Start Exam");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    updateProfile(scanner);
                    break;
                case 3:
                    changePassword(scanner);
                    break;
                case 4:
                    startExam(scanner);
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password1", "User One"));
        users.put("user2", new User("user2", "password2", "User Two"));
    }

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateProfile(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter new name: ");
        String newName = scanner.next();
        currentUser.setName(newName);
        System.out.println("Profile updated successfully.");
    }

    private static void changePassword(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter current password: ");
        String currentPassword = scanner.next();
        if (!currentUser.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect password.");
            return;
        }
        System.out.print("Enter new password: ");
        String newPassword = scanner.next();
        currentUser.setPassword(newPassword);
        System.out.println("Password changed successfully.");
    }

    private static void startExam(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        if (examInProgress) {
            System.out.println("Exam already in progress.");
            return;
        }
        examInProgress = true;
        timeLeft = 300; // 5 minutes for the exam
        System.out.println("Exam started. You have 5 minutes.");

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    System.out.println("Time left: " + timeLeft + " seconds");
                    timeLeft--;
                } else {
                    autoSubmitExam();
                }
            }
        }, 0, 1000);

        String[] questions = {
            "Q1: What is 2 + 2? \n1. 3 \n2. 4 \n3. 5",
            "Q2: What is the capital of France? \n1. Berlin \n2. Paris \n3. Madrid"
        };

        int[] answers = new int[questions.length];

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Select answer (1-3): ");
            answers[i] = scanner.nextInt();
        }

        submitExam(answers);
    }

    private static void autoSubmitExam() {
        System.out.println("Time's up! Auto-submitting exam...");
        timer.cancel();
        examInProgress = false;
        // Handle auto submission logic here
        logout();
    }

    private static void submitExam(int[] answers) {
        timer.cancel();
        examInProgress = false;
        System.out.println("Exam submitted successfully. Your answers:");
        for (int i = 0; i < answers.length; i++) {
            System.out.println("Q" + (i + 1) + ": " + answers[i]);
        }
        logout();
    }

    private static void logout() {
        if (currentUser != null) {
            System.out.println("Goodbye, " + currentUser.getName() + "!");
            currentUser = null;
        } else {
            System.out.println("You are not logged in.");
        }
    }
}

class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}

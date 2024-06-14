import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class TrainReservationSystem {
    private static final int MIN_PNR = 1000;
    private static final int MAX_PNR = 9999;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/vasu";

    public static class User {
        private String username;
        private String password;
        private Scanner scanner = new Scanner(System.in);

        public User() {}

        public String getUsername() {
            System.out.println("Enter Username:");
            username = scanner.nextLine();
            return username;
        }

        public String getPassword() {
            System.out.println("Enter Password:");
            password = scanner.nextLine();
            return password;
        }
    }

    public static class PnrRecord {
        private int pnrNumber;
        private String passengerName;
        private String trainNumber;
        private String classType;
        private String journeyDate;
        private String from;
        private String to;
        private Scanner scanner = new Scanner(System.in);

        public int generatePnrNumber() {
            Random random = new Random();
            pnrNumber = random.nextInt(MAX_PNR - MIN_PNR + 1) + MIN_PNR;
            return pnrNumber;
        }

        public String getPassengerName() {
            System.out.println("Enter passenger name:");
            passengerName = scanner.nextLine();
            return passengerName;
        }

        public String getTrainNumber() {
            System.out.println("Enter train number:");
            trainNumber = scanner.nextLine();
            return trainNumber;
        }

        public String getClassType() {
            System.out.println("Enter class type:");
            classType = scanner.nextLine();
            return classType;
        }

        public String getJourneyDate() {
            System.out.println("Enter journey date (YYYY-MM-DD):");
            journeyDate = scanner.nextLine();
            return journeyDate;
        }

        public String getFrom() {
            System.out.println("Enter starting place:");
            from = scanner.nextLine();
            return from;
        }

        public String getTo() {
            System.out.println("Enter destination place:");
            to = scanner.nextLine();
            return to;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DATABASE_URL, username, password)) {
                System.out.println("User Connection Granted.\n");

                while (true) {
                    System.out.println("Enter your choice:");
                    System.out.println("1. Insert Record");
                    System.out.println("2. Delete Record");
                    System.out.println("3. Show All Records");
                    System.out.println("4. Exit");
                    int choice = scanner.nextInt();

                    // Implement the functionality based on the choice
                }
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC driver: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

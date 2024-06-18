import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a Book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

// Class to represent a User
class User {
    private String name;
    private String email;
    private boolean isAdmin;

    public User(String name, String email, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

// Main Library Management System
public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Admin functionalities
    public void addBook(String title, String author, String isbn) {
        books.add(new Book(title, author, isbn));
        System.out.println("Book added successfully.");
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book removed successfully.");
    }

    public void addUser(String name, String email, boolean isAdmin) {
        users.add(new User(name, email, isAdmin));
        System.out.println("User added successfully.");
    }

    public void removeUser(String email) {
        users.removeIf(user -> user.getEmail().equals(email));
        System.out.println("User removed successfully.");
    }

    // User functionalities
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with the title: " + title);
        }
    }

    public void issueBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isIssued()) {
                    book.setIssued(true);
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("No book found with the ISBN: " + isbn);
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isIssued()) {
                    book.setIssued(false);
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("No book found with the ISBN: " + isbn);
    }

    // Main method to run the system
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        User admin = new User("Admin", "admin@library.com", true);

        // Adding some initial data
        library.addBook("Java Programming", "James Gosling", "1111");
        library.addBook("Effective Java", "Joshua Bloch", "2222");
        library.addUser("John Doe", "john@doe.com", false);

        while (true) {
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter admin email: ");
                String email = scanner.next();
                if (email.equals(admin.getEmail())) {
                    while (true) {
                        System.out.println("Admin Menu");
                        System.out.println("1. Add Book");
                        System.out.println("2. Remove Book");
                        System.out.println("3. Add User");
                        System.out.println("4. Remove User");
                        System.out.println("5. Logout");
                        System.out.print("Enter your choice: ");
                        int adminChoice = scanner.nextInt();

                        if (adminChoice == 1) {
                            System.out.print("Enter book title: ");
                            String title = scanner.next();
                            System.out.print("Enter book author: ");
                            String author = scanner.next();
                            System.out.print("Enter book ISBN: ");
                            String isbn = scanner.next();
                            library.addBook(title, author, isbn);
                        } else if (adminChoice == 2) {
                            System.out.print("Enter book ISBN to remove: ");
                            String isbn = scanner.next();
                            library.removeBook(isbn);
                        } else if (adminChoice == 3) {
                            System.out.print("Enter user name: ");
                            String name = scanner.next();
                            System.out.print("Enter user email: ");
                            String userEmail = scanner.next();
                            System.out.print("Is admin (true/false): ");
                            boolean isAdmin = scanner.nextBoolean();
                            library.addUser(name, userEmail, isAdmin);
                        } else if (adminChoice == 4) {
                            System.out.print("Enter user email to remove: ");
                            String userEmail = scanner.next();
                            library.removeUser(userEmail);
                        } else if (adminChoice == 5) {
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid admin email.");
                }
            } else if (choice == 2) {
                System.out.print("Enter user email: ");
                String email = scanner.next();
                boolean userFound = false;
                for (User user : library.users) {
                    if (user.getEmail().equals(email) && !user.isAdmin()) {
                        userFound = true;
                        while (true) {
                            System.out.println("User Menu");
                            System.out.println("1. View Books");
                            System.out.println("2. Search Book");
                            System.out.println("3. Issue Book");
                            System.out.println("4. Return Book");
                            System.out.println("5. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();

                            if (userChoice == 1) {
                                library.viewBooks();
                            } else if (userChoice == 2) {
                                System.out.print("Enter book title to search: ");
                                String title = scanner.next();
                                library.searchBook(title);
                            } else if (userChoice == 3) {
                                System.out.print("Enter book ISBN to issue: ");
                                String isbn = scanner.next();
                                library.issueBook(isbn);
                            } else if (userChoice == 4) {
                                System.out.print("Enter book ISBN to return: ");
                                String isbn = scanner.next();
                                library.returnBook(isbn);
                            } else if (userChoice == 5) {
                                break;
                            }
                        }
                    }
                }
                if (!userFound) {
                    System.out.println("User not found or invalid email.");
                }
            } else if (choice == 3) {
                break;
            }
        }
        scanner.close();
    }
}

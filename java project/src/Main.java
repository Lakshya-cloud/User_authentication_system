
import service.UserService;
import java.util.Scanner;

public class Main {
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_SECONDS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {
            System.out.println("\n--- Shopping Platform ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPass = scanner.nextLine();
                    userService.register(regUser, regPass);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    int attempts = 0;

                    while (attempts < MAX_ATTEMPTS) {
                        System.out.print("Enter password: ");
                        String loginPass = scanner.nextLine();
                        if (userService.login(loginUser, loginPass)) {
                            // Add shopping options here
                            return;
                        } else {
                            attempts++;
                            System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
                        }
                    }

                    System.out.println("Too many failed attempts. Locked for " + LOCK_TIME_SECONDS + " seconds.");
                    try {
                        Thread.sleep(LOCK_TIME_SECONDS * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("You can try again.");
                    break;

                case 3:
                    System.out.println("Thank you for visiting!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}


package service;

import model.User;
import storage.UserStorage;

public class UserService {
    public boolean register(String username, String password) {
        if (UserStorage.userExists(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        User user = new User(username, password);
        UserStorage.addUser(user);
        System.out.println("Registration successful.");
        return true;
    }

    public boolean login(String username, String password) {
        if (!UserStorage.userExists(username)) {
            System.out.println("User not found.");
            return false;
        }

        User user = UserStorage.getUser(username);
        if (user.checkPassword(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }
}

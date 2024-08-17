import java.io.*;
import java.util.ArrayList;

public class Bank {

    private ArrayList<User> users = new ArrayList<>();
    
    public Bank() {
        users = new ArrayList<>();
    }

    
    public void addUser(User user) {
        users.add(user);
    }

    
    public boolean containsUser(User user) {
        return users.contains(user);
    }

    // Load users from a file
    public void loadUsersFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    User user = (User) in.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break; 
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // Save users to a file
    public void saveUsersToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (User user : users) {
                out.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if a username exists
    public boolean searchUserName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    // Check if a user with username and password exists
    public boolean searchUserExist(String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Get a user by username and password
    public User getUser(String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Get the list of users
    public ArrayList<User> getUsers() {
        return users;
    }
}

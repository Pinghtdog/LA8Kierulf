package com.daangit.la8kierulf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import javafx.scene.input.KeyEvent;
import java.io.*;
import java.sql.*;

public class HelloController {
    public TextField tfUsername;
    public TextField tfpassword;
    public Label errorText;
    public Button btnLogin;
    @FXML
    private Label welcomeText;

    private static final String LOGIN_FILE = "loggedUser.txt";


    private static final String url = "jdbc:mysql://localhost:3306/users";
    private static final String user = "root";
    private static final String pass = "";

    public void initialize() {
        errorText.setVisible(false);

        tfUsername.addEventHandler(KeyEvent.KEY_TYPED, e -> errorText.setVisible(false));
        tfpassword.addEventHandler(KeyEvent.KEY_TYPED, e -> errorText.setVisible(false));

        setupDatabase();
        autoLogin();
    }

    public void onLogin(ActionEvent actionEvent) {
        String username = tfUsername.getText().trim();
        String password = tfpassword.getText().trim();

        if (authenticate(username, password)) {
            saveLoginToFile(username, password);
            System.out.println("Login successful!");
            // Proceed to dashboard
        } else {
            errorText.setText("Invalid username or password.");
//            errorText.setFill(Color.RED);
            errorText.setVisible(true);
        }
    }

    private void setupDatabase() {
        String rootUrl = "jdbc:mysql://localhost:3306/";
        String dbName = "users";
        String fullUrl = rootUrl + dbName;
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(rootUrl, user, pass);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);

            try (Connection usersConn = DriverManager.getConnection(fullUrl, user, pass);
                 Statement usersStmt = usersConn.createStatement()) {
                usersStmt.executeUpdate("""
                            CREATE TABLE IF NOT EXISTS users (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                username VARCHAR(50) UNIQUE NOT NULL,
                                password VARCHAR(50) NOT NULL
                            )
                        """);

                ResultSet adminRS = usersStmt.executeQuery("SELECT * FROM users WHERE username = 'admin'");
                if (!adminRS.next()) {
                    usersStmt.executeUpdate("INSERT INTO users (username, password) VALUES ('admin', 'admin123')");
                    System.out.println("admin account created");
                }

                ResultSet studentRS = usersStmt.executeQuery("SELECT * FROM users WHERE username = 'student'");
                if (!studentRS.next()) {
                    usersStmt.executeUpdate("INSERT INTO users (username, password) VALUES ('student', 'student123')");
                    System.out.println("student account created");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void saveLoginToFile(String username, String password) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOGIN_FILE))) {
            oos.writeObject(new User(username, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Valid login if found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void autoLogin() {
        File file = new File(LOGIN_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                User user = (User) ois.readObject(); // ✅ READS OBJECT from .txt
                if (authenticate(user.getUsername(), user.getPassword())) {
                    System.out.println("Auto-login successful for user: " + user.getUsername());
                    // ✅ Proceed to dashboard
                } else {
                    System.out.println("Auto-login failed. Invalid credentials in file.");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
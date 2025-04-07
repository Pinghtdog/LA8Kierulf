//package com.daangit.la8kierulf;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//import java.io.IOException;
//
//public class StudentDashboardController implements HelloController.UserAwareController {
//    @FXML private TextField tfName;
//    @FXML private TableView<CourseGrade> gradesTable;
//    @FXML private TableColumn<CourseGrade, String> codeColumn;
//    @FXML private TableColumn<CourseGrade, String> nameColumn;
//    @FXML private TableColumn<CourseGrade, Double> gradeColumn;
//
//    private LoginController.User currentUser;
//    private String firstName = "Jean";
//    private String lastName = "Gunnhildr";
//
//    @Override
//    public void setUser(LoginController.User user) {
//        this.currentUser = user;
//        initializeData();
//    }
//
//    @FXML
//    public void initialize() {
//        // Set up table columns
//        codeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
//        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
//
//        // Format grade column to show 1 decimal place
//        gradeColumn.setCellFactory(tc -> new TableCell<>() {
//            @Override
//            protected void updateItem(Double grade, boolean empty) {
//                super.updateItem(grade, empty);
//                if (empty || grade == null) {
//                    setText(null);
//                } else {
//                    setText(String.format("%.1f", grade));
//                }
//            }
//        });
//    }
//
//    private void initializeData() {
//        // Set student name
//        tfName.setText(lastName + ", " + firstName);
//
//        // Sample data - in a real app, this would come from a database
//        gradesTable.getItems().addAll(
//                new CourseGrade("CS244", "Design and Analysis of Algorithms", 4.2),
//                new CourseGrade("CS1728", "Object-Oriented Programming 2", 4.8)
//        );
//    }
//
//    @FXML
//    private void handleLogOut() {
//        try {
//            // Delete the serialized user file
//            new java.io.File(LoginController.USER_DATA_FILE).delete();
//
//            // Load login screen
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
//            Parent root = loader.load();
//
//            Stage stage = (Stage) tfName.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            showAlert("Error", "Failed to log out: " + e.getMessage());
//        }
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    // Model class for table data
//    public static class CourseGrade {
//        private final String courseCode;
//        private final String courseName;
//        private final double grade;
//
//        public CourseGrade(String courseCode, String courseName, double grade) {
//            this.courseCode = courseCode;
//            this.courseName = courseName;
//            this.grade = grade;
//        }
//
//        public String getCourseCode() { return courseCode; }
//        public String getCourseName() { return courseName; }
//        public double getGrade() { return grade; }
//    }
//}

package OOP_Architeccture;

import DB_Connect.DBConnection; // Import DB Connection class
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine_Calculation extends Application {
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        connection = DBConnection.getConnection(); // Get database connection

        // Create a pane to hold UI elements
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setResizable(false); // Disable window resizing
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true); // Keep the window on top
        primaryStage.setTitle("Library Fine Calculator"); // Set window title
        primaryStage.setScene(scene);
        primaryStage.show(); // Show the stage
        
        pane.setStyle("-fx-background-color: #ADD8E6;"); // Set background color
        
        // Title label
        Label lblTitle = new Label("Fine Calculation");
        lblTitle.setFont(new Font("Times New Roman", 32));
        lblTitle.relocate(80, 10);
        
        // Student ID label and text field
        Label lblStudent = new Label("Student ID:");
        lblStudent.relocate(30, 80);
        lblStudent.setFont(new Font ("Times New Roman",15));
        TextField txtStudent = new TextField();
        txtStudent.relocate(150, 75);

        // Return date label and date picker
        Label lblReturnDate = new Label("Return Date:");
        lblReturnDate.relocate(30, 130);
        lblReturnDate.setFont(new Font ("Times New Roman",15));
        DatePicker dpReturnDate = new DatePicker();
        dpReturnDate.relocate(150, 125);

        // Button to calculate fine
        Button btnCalculate = new Button("Calculate Fine");
        btnCalculate.relocate(150, 200);

        // Label to display the result
        Label lblResult = new Label();
        lblResult.relocate(30, 250);

        // Button Action
        btnCalculate.setOnAction(e -> {
            int studentId;
            try {
                studentId = Integer.parseInt(txtStudent.getText()); // Parse student ID
            } catch (NumberFormatException ex) {
                lblResult.setText("Invalid Student ID.");
                return;
            }

            LocalDate returnDate = dpReturnDate.getValue(); // Get selected return date
            if (returnDate == null) {
                lblResult.setText("Please select a return date.");
                return;
            }

            LocalDate borrowDate = fetchBorrowDate(studentId); // Get borrow date from DB
            if (borrowDate == null) {
                lblResult.setText("No borrow record found.");
                return;
            }

            // Calculate fine based on late days and fixed rate
            double fineAmount = calculateFine(borrowDate, returnDate, 5.0);
            lblResult.setText("Fine Amount: $" + fineAmount);
        });

        // Add components to the pane
        pane.getChildren().addAll(lblTitle, lblStudent, txtStudent, lblReturnDate, dpReturnDate, btnCalculate, lblResult);
    }

    // Method to calculate fine based on borrow and return dates
    private double calculateFine(LocalDate borrowDate, LocalDate returnDate, double finePerDay) {
        long daysLate = ChronoUnit.DAYS.between(borrowDate, returnDate) - 14; // 14-day borrowing period
        return Math.max(0, daysLate * finePerDay); // If not late, fine is 0
    }

    // Method to fetch the borrow date of a student from the database
    private LocalDate fetchBorrowDate(int studentId) {
        String sql = "SELECT borrowed_date FROM borrow_book WHERE student_id = ? ORDER BY borrowed_date DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection(); // Get DB connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getDate("borrowed_date").toLocalDate() : null; // Return borrow date
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

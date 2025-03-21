package OOP_Architeccture;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Update extends Application {

    // MySQL connection variables
    private static final String URL = "jdbc:mysql://localhost:3306/menu"; // Update with your database URL
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = "bhularun7"; // Update with your MySQL password

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Paint paint = Paint.valueOf("Green");
        scene.setFill(paint);
        primaryStage.setScene(scene);
    
        primaryStage.setTitle("Update Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);
        
        // Set Background Color on Stage
        pane.setStyle("-fx-background-color: #ADD8E6;");
        
        Label lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblMessage;
        TextField txtBook_ID, txtTitle, txtAuthor, txtQuantity;
        Button btnUpdate, btnSearch, btnClose;
        
        lblUpdate_Book = new Label("Update Book");
        lblUpdate_Book.relocate(110, 0);
        lblUpdate_Book.setFont(new Font("Arial", 30));
        
        lblBook_ID = new Label("Book_ID:");
        lblBook_ID.relocate(30, 55);
        lblBook_ID.setFont(new Font("Arial", 15)); 
        txtBook_ID = new TextField();
        txtBook_ID.relocate(110, 50);
        
        btnSearch = new Button("Search");
        btnSearch.relocate(280, 50);
        
        lblTitle = new Label("Title:");
        lblTitle.relocate(30, 105);
        lblTitle.setFont(new Font("Arial", 15));
        txtTitle = new TextField();
        txtTitle.relocate(110, 100);
        
        lblAuthor = new Label("Author:");
        lblAuthor.relocate(30, 155);
        lblAuthor.setFont(new Font("Arial", 15));
        txtAuthor = new TextField();
        txtAuthor.relocate(110, 150);
        
        lblQuantity = new Label("Quantity:");
        lblQuantity.relocate(30, 205);
        lblQuantity.setFont(new Font("Arial", 15));
        txtQuantity = new TextField();
        txtQuantity.relocate(110, 200);
        
        btnClose = new Button("Close");
        btnClose.relocate(200, 250);

        // Event handler for the Close button
        btnClose.setOnAction(event -> goBackToDashboard(primaryStage));
        
        btnUpdate = new Button("Update");
        btnUpdate.relocate(120, 250);
        
        // Message Label for Errors/Success
        lblMessage = new Label();
        lblMessage.relocate(30, 280);
        lblMessage.setFont(new Font("Arial", 12));

        // Event handler for the Search button
        btnSearch.setOnAction(event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtQuantity, lblMessage));

        // Event handler for the Update button
        btnUpdate.setOnAction(event -> updateBook(txtBook_ID.getText(), txtTitle.getText(), txtAuthor.getText(), txtQuantity.getText(), lblMessage));

        primaryStage.show();
        
        pane.getChildren().addAll(lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, txtBook_ID, txtTitle, txtAuthor, txtQuantity, btnUpdate, btnSearch, btnClose, lblMessage);
    }

    private Object goBackToDashboard(Stage primaryStage) {
        // TODO Auto-generated method stub
        return null;
    }

    // Method to search the book from MySQL database
    private void searchBook(String bookID, TextField titleField, TextField authorField, TextField quantityField, Label lblMessage) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the MySQL database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to search for a book by Book_ID
            String sql = "SELECT * FROM books WHERE book_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookID); // Set the Book_ID in the query

            // Execute query
            rs = stmt.executeQuery();

            if (rs.next()) {
                // If book is found, populate the TextFields
                titleField.setText(rs.getString("title"));
                authorField.setText(rs.getString("author"));
                quantityField.setText(rs.getString("quantity"));
                lblMessage.setText("Book found. You can update now.");
                lblMessage.setStyle("-fx-text-fill: green;");
            } else {
                // If no book is found, clear the TextFields and show message
                titleField.clear();
                authorField.clear();
                quantityField.clear();
                lblMessage.setText("No book found with that ID.");
                lblMessage.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            lblMessage.setText("Error: " + e.getMessage());
            lblMessage.setStyle("-fx-text-fill: red;");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                lblMessage.setText("Error: " + e.getMessage());
                lblMessage.setStyle("-fx-text-fill: red;");
            }
        }
    }

    // Method to update the book in the MySQL database
    private void updateBook(String bookID, String title, String author, String quantity, Label lblMessage) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish connection to the MySQL database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to update book details
            String sql = "UPDATE books SET title = ?, author = ?, quantity = ? WHERE book_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, title); // Set the Title
            stmt.setString(2, author); // Set the Author
            stmt.setInt(3, Integer.parseInt(quantity)); // Set the Quantity
            stmt.setString(4, bookID); // Set the Book_ID

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                lblMessage.setText("Book updated successfully!");
                lblMessage.setStyle("-fx-text-fill: green;");
            } else {
                lblMessage.setText("Failed to update the book.");
                lblMessage.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            lblMessage.setText("Error: " + e.getMessage());
            lblMessage.setStyle("-fx-text-fill: red;");
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                lblMessage.setText("Error: " + e.getMessage());
                lblMessage.setStyle("-fx-text-fill: red;");
            }
        }
    }

    public static void main(String[] args) {
        launch(args); // Call start method
    }
}
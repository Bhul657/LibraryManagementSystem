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

public class Add extends Application {

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
    
        primaryStage.setTitle("Add Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);
        
        // Set Background Color on Stage
        pane.setStyle("-fx-background-color: #ADD8E6;");
        
        Label lblAdd_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblMessage;
        TextField txtBook_ID, txtTitle, txtAuthor, txtQuantity;
        Button btnAdd, btnClose;
        
        lblAdd_Book = new Label("Add Book");
        lblAdd_Book.relocate(125, 0);
        lblAdd_Book.setFont(new Font("Arial", 30));
        
        lblBook_ID = new Label("Book_ID:");
        lblBook_ID.relocate(30, 55);
        lblBook_ID.setFont(new Font("Arial", 15)); 
        txtBook_ID = new TextField();
        txtBook_ID.relocate(110, 50);
        
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
        
        btnAdd = new Button("Add");
        btnAdd.relocate(120, 250);
        
         // Message Label for Errors/Success
        lblMessage = new Label();
        lblMessage.relocate(30, 280);
        lblMessage.setFont(new Font("Arial", 12));

        // Event handler for the Add button
        btnAdd.setOnAction(event -> addBook(txtBook_ID.getText(), txtTitle.getText(), txtAuthor.getText(), txtQuantity.getText(), lblMessage));
        
        primaryStage.show();
        
        pane.getChildren().addAll(lblAdd_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, txtBook_ID, txtTitle, txtAuthor, txtQuantity, btnAdd, btnClose, lblMessage);
    }

    // Method to add a book to the database
    private void addBook(String bookID, String title, String author, String quantity, Label lblMessage) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish connection to the MySQL database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to insert a new book
            String sql = "INSERT INTO books (book_id, title, author, quantity) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookID);   // Set the Book_ID
            stmt.setString(2, title);    // Set the Title
            stmt.setString(3, author);   // Set the Author
            stmt.setInt(4, Integer.parseInt(quantity)); // Set the Quantity

            // Execute the query to insert the book into the database
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful and update the message label
            if (rowsAffected > 0) {
                lblMessage.setText("Book added successfully!");
                lblMessage.setStyle("-fx-text-fill: green;");
            } else {
                lblMessage.setText("Failed to add the book.");
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

    // Method to go back to the User Dashboard
    private void goBackToDashboard(Stage primaryStage) {
        // Create an instance of the User_Dashboard class
        User_Dashboard userDashboard = new User_Dashboard();
        try {
            // Switch to the User Dashboard scene
            userDashboard.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Call start method
    }
}

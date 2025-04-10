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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DB_Connect.DBConnection; 

public class Search extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false); 
        Pane pane = new Pane(); 
        Scene scene = new Scene(pane); 
        
        // Set background color to green
        Paint paint = Paint.valueOf("Green");
        scene.setFill(paint);
        primaryStage.setScene(scene);

        // Set window title and dimensions
        primaryStage.setTitle("Search Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(420);

        // Set background color for the pane
        pane.setStyle("-fx-background-color: #ADD8E6;"); // Light blue background

        // Declare UI components
        Label lblSearch_Book, lblBook_ID, lblTitle, lblAuthor, lblEdition, lblQuantity;
        TextField txtBook_ID, txtTitle, txtAuthor, txtEdition, txtQuantity;
        Button btnSearch;

        // Create and position labels
        lblSearch_Book = new Label("Search Book");
        lblSearch_Book.relocate(125, 0);
        lblSearch_Book.setFont(new Font("Arial", 30));

        lblBook_ID = new Label("Book ID:");
        lblBook_ID.relocate(30, 55);
        lblBook_ID.setFont(new Font("Arial", 15));

        lblTitle = new Label("Title:");
        lblTitle.relocate(30, 105);
        lblTitle.setFont(new Font("Arial", 15));

        lblAuthor = new Label("Author:");
        lblAuthor.relocate(30, 155);
        lblAuthor.setFont(new Font("Arial", 15));

        lblEdition = new Label("Edition:");
        lblEdition.relocate(30, 205);
        lblEdition.setFont(new Font("Arial", 15));

        lblQuantity = new Label("Quantity:");
        lblQuantity.relocate(30, 255);
        lblQuantity.setFont(new Font("Arial", 15));

        // Create and position text fields
        txtBook_ID = new TextField();
        txtBook_ID.relocate(110, 50);

        txtTitle = new TextField();
        txtTitle.relocate(110, 100);
        txtTitle.setEditable(false); // Make it read-only since it will be fetched from the database

        txtAuthor = new TextField();
        txtAuthor.relocate(110, 150);
        txtAuthor.setEditable(false);

        txtEdition = new TextField();
        txtEdition.relocate(110, 200);
        txtEdition.setEditable(false);

        txtQuantity = new TextField();
        txtQuantity.relocate(110, 250);
        txtQuantity.setEditable(false);

        // Create and position the search button
        btnSearch = new Button("Search");
        btnSearch.relocate(155, 300);

        // Event handler for the Search button
        btnSearch.setOnAction(event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtEdition, txtQuantity));

        primaryStage.show();

        // Add all elements to the pane
        pane.getChildren().addAll(lblSearch_Book, lblBook_ID, lblTitle, lblAuthor, lblEdition, lblQuantity, 
                                  txtBook_ID, txtTitle, txtAuthor, txtEdition, txtQuantity, btnSearch);
    }

    // Method to search the book from the MySQL database
    private void searchBook(String bookID, TextField titleField, TextField authorField, TextField editionField, TextField quantityField) {
        Connection conn = DBConnection.getConnection(); // Get database connection
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            if (conn != null) { // Check if connection is successful
                String sql = "SELECT * FROM book WHERE book_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, bookID); // Set the Book ID in the query

                rs = stmt.executeQuery(); // Execute the query

                if (rs.next()) { // If book is found, display details in the text fields
                    titleField.setText(rs.getString("title"));
                    authorField.setText(rs.getString("author"));
                    editionField.setText(rs.getString("edition"));
                    quantityField.setText(rs.getString("quantity"));
                } else { // If book is not found, clear all fields
                    titleField.clear();
                    authorField.clear();
                    editionField.clear();
                    quantityField.clear();
                    System.out.println("No book found with that ID.");
                }
            } else {
                System.out.println("Database connection failed.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors that occur
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args); 
    }
}

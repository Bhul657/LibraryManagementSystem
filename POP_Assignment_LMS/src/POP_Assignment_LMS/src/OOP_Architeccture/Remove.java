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

public class Remove extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        scene.setFill(Paint.valueOf("Green")); // Setting background color
        primaryStage.setScene(scene);

        primaryStage.setTitle("Delete Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(420);

        pane.setStyle("-fx-background-color: #ADD8E6;"); // Setting background color

        // Title label
        Label lblDelete_Book = new Label("Remove Book");
        lblDelete_Book.relocate(100, 0);
        lblDelete_Book.setFont(new Font("Arial", 30));

        // Book ID input
        Label lblBook_ID = new Label("Book ID:");
        lblBook_ID.relocate(30, 55);
        lblBook_ID.setFont(new Font("Arial", 15));
        TextField txtBook_ID = new TextField();
        txtBook_ID.relocate(110, 50);
        
        Button btnSearch = new Button("Search");
        btnSearch.relocate(280, 50);

        // Labels and fields for book details
        Label lblTitle = new Label("Title:");
        lblTitle.relocate(30, 105);
        lblTitle.setFont(new Font("Arial", 15));
        TextField txtTitle = new TextField();
        txtTitle.relocate(110, 100);
        txtTitle.setEditable(false); // Make it non-editable

        Label lblAuthor = new Label("Author:");
        lblAuthor.relocate(30, 155);
        lblAuthor.setFont(new Font("Arial", 15));
        TextField txtAuthor = new TextField();
        txtAuthor.relocate(110, 150);
        txtAuthor.setEditable(false);

        Label lblEdition = new Label("Edition:");
        lblEdition.relocate(30, 205);
        lblEdition.setFont(new Font("Arial", 15));
        TextField txtEdition = new TextField();
        txtEdition.relocate(110, 200);
        txtEdition.setEditable(false);

        Label lblQuantity = new Label("Quantity:");
        lblQuantity.relocate(30, 255);
        lblQuantity.setFont(new Font("Arial", 15));
        TextField txtQuantity = new TextField();
        txtQuantity.relocate(110, 250);
        txtQuantity.setEditable(false);

        // Remove button
        Button btnRemove = new Button("Remove");
        btnRemove.relocate(155, 300);

        // Event handlers for the buttons
        btnSearch.setOnAction(event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtEdition, txtQuantity));
        btnRemove.setOnAction(event -> deleteBook(txtBook_ID.getText()));

        primaryStage.show();

        // Add all UI components to the pane
        pane.getChildren().addAll(lblDelete_Book, lblBook_ID, txtBook_ID, lblTitle, txtTitle, lblAuthor, txtAuthor,
                lblEdition, txtEdition, lblQuantity, txtQuantity, btnRemove, btnSearch);
    }

    // Method to search for a book in the database
    private void searchBook(String bookID, TextField titleField, TextField authorField, TextField editionField, TextField quantityField) {
        try (Connection conn = DBConnection.getConnection(); // Establish database connection
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE book_id = ?")) {

            stmt.setString(1, bookID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // If book is found, display details
                    titleField.setText(rs.getString("title"));
                    authorField.setText(rs.getString("author"));
                    editionField.setText(rs.getString("edition"));
                    quantityField.setText(rs.getString("quantity"));
                } else { // If no book is found, clear fields
                    titleField.clear();
                    authorField.clear();
                    editionField.clear();
                    quantityField.clear();
                    System.out.println("No book found with that ID.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a book from the database
    private void deleteBook(String bookID) {
        try (Connection conn = DBConnection.getConnection(); // Establish database connection
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE book_id = ?")) {

            stmt.setString(1, bookID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Failed to delete the book.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

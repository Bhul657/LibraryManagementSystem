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

import DB_Connect.DBConnection; // Import the DBConnection class to establish the connection

public class Add extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        scene.setFill(Paint.valueOf("Green"));
        primaryStage.setScene(scene);

        primaryStage.setTitle("Add Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(420);
        primaryStage.setAlwaysOnTop(true);

        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblAdd_Book = new Label("Add Book");
        lblAdd_Book.relocate(125, 0);
        lblAdd_Book.setFont(new Font("Arial", 30));

        Label lblBook_ID = new Label("Book ID:");
        lblBook_ID.relocate(30, 50);
        lblBook_ID.setFont(new Font("Arial", 15));
        TextField txtBook_ID = new TextField();
        txtBook_ID.relocate(110, 45);

        Label lblTitle = new Label("Title:");
        lblTitle.relocate(30, 100);
        lblTitle.setFont(new Font("Arial", 15));
        TextField txtTitle = new TextField();
        txtTitle.relocate(110, 95);

        Label lblAuthor = new Label("Author:");
        lblAuthor.relocate(30, 150);
        lblAuthor.setFont(new Font("Arial", 15));
        TextField txtAuthor = new TextField();
        txtAuthor.relocate(110, 145);

        Label lblEdition = new Label("Edition:");
        lblEdition.relocate(30, 200);
        lblEdition.setFont(new Font("Arial", 15));
        TextField txtEdition = new TextField();
        txtEdition.relocate(110, 195);

        Label lblQuantity = new Label("Quantity:");
        lblQuantity.relocate(30, 250);
        lblQuantity.setFont(new Font("Arial", 15));
        TextField txtQuantity = new TextField();
        txtQuantity.relocate(110, 245);

        Button btnClose = new Button("Close");
        btnClose.relocate(200, 300);
        btnClose.setOnAction(event -> goBackToDashboard(primaryStage));

        Button btnAdd = new Button("Add");
        btnAdd.relocate(125, 300);

        Label lblMessage = new Label();
        lblMessage.relocate(30, 330);
        lblMessage.setFont(new Font("Arial", 12));

        // Event handler for the Add button
        btnAdd.setOnAction(event -> addBook(txtBook_ID.getText(), txtTitle.getText(), txtAuthor.getText(), txtEdition.getText(), txtQuantity.getText(), lblMessage));

        // Add all components to the pane
        pane.getChildren().addAll(lblAdd_Book, lblBook_ID, txtBook_ID, lblTitle, txtTitle, lblAuthor, txtAuthor,
                lblEdition, txtEdition, lblQuantity, txtQuantity, btnAdd, btnClose, lblMessage);

        primaryStage.show();
    }

    // Method to add a book to the database using the DBConnection class
    private void addBook(String bookID, String title, String author, String edition, String quantity, Label lblMessage) {
        try (Connection conn = DBConnection.getConnection(); // Get connection from DBConnection
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (book_id, title, author, edition, quantity) VALUES (?, ?, ?, ?, ?)");) {

            stmt.setString(1, bookID);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, edition);
            stmt.setInt(5, Integer.parseInt(quantity));

            int rowsAffected = stmt.executeUpdate();

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
        }
    }

    private void goBackToDashboard(Stage primaryStage) {
        Librarian_Dashboard userDashboard = new Librarian_Dashboard();
        try {
            userDashboard.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

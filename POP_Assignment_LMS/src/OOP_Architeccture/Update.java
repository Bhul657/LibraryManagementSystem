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

public class Update extends Application {

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
        primaryStage.setHeight(460);
        primaryStage.setAlwaysOnTop(true);

        // Set Background Color on Stage
        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblEdition, lblMessage;
        TextField txtBook_ID, txtTitle, txtAuthor, txtQuantity, txtEdition;
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

        lblEdition = new Label("Edition:");
        lblEdition.relocate(30, 255);
        lblEdition.setFont(new Font("Arial", 15));
        txtEdition = new TextField();
        txtEdition.relocate(110, 250);

        btnClose = new Button("Close");
        btnClose.relocate(200, 300);

        // Event handler for the Close button
        btnClose.setOnAction(event -> primaryStage.close());

        btnUpdate = new Button("Update");
        btnUpdate.relocate(120, 300);

        // Message Label for Errors/Success
        lblMessage = new Label();
        lblMessage.relocate(30, 330);
        lblMessage.setFont(new Font("Arial", 12));

        // Event handler for the Search button
        btnSearch.setOnAction(event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtQuantity, txtEdition, lblMessage));

        // Event handler for the Update button
        btnUpdate.setOnAction(event -> updateBook(txtBook_ID.getText(), txtTitle.getText(), txtAuthor.getText(), txtQuantity.getText(), txtEdition.getText(), lblMessage));

        primaryStage.show();

        pane.getChildren().addAll(lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblEdition, txtBook_ID, txtTitle, txtAuthor, txtQuantity, txtEdition, btnUpdate, btnSearch, btnClose, lblMessage);
    }

    // Method to search for a book in the database
    private void searchBook(String bookID, TextField titleField, TextField authorField, TextField quantityField, TextField editionField, Label lblMessage) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            if (conn != null) {
                String sql = "SELECT * FROM books WHERE book_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, bookID);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    titleField.setText(rs.getString("title"));
                    authorField.setText(rs.getString("author"));
                    quantityField.setText(rs.getString("quantity"));
                    editionField.setText(rs.getString("edition"));
                    lblMessage.setText("Book found. You can update now.");
                    lblMessage.setStyle("-fx-text-fill: green;");
                } else {
                    titleField.clear();
                    authorField.clear();
                    quantityField.clear();
                    editionField.clear();
                    lblMessage.setText("No book found with that ID.");
                    lblMessage.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            lblMessage.setText("Error: " + e.getMessage());
            lblMessage.setStyle("-fx-text-fill: red;");
        }
    }

    // Method to update book details in the database
    private void updateBook(String bookID, String title, String author, String quantity, String edition, Label lblMessage) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            if (conn != null) {
                String sql = "UPDATE books SET title = ?, author = ?, quantity = ?, edition = ? WHERE book_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.setInt(3, Integer.parseInt(quantity));
                stmt.setString(4, edition);
                stmt.setString(5, bookID);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    lblMessage.setText("Book updated successfully!");
                    lblMessage.setStyle("-fx-text-fill: green;");
                } else {
                    lblMessage.setText("Failed to update the book.");
                    lblMessage.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            lblMessage.setText("Error: " + e.getMessage());
            lblMessage.setStyle("-fx-text-fill: red;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

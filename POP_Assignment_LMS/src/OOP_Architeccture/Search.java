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

public class Search extends Application {

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

        primaryStage.setTitle("Search Book");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);

        // Set Background Color on Stage
        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblSearch_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity;
        TextField txtBook_ID, txtTitle, txtAuthor, txtQuantity;
        Button btnSearch, btnClose;

        lblSearch_Book = new Label("Search Book");
        lblSearch_Book.relocate(125, 0);
        lblSearch_Book.setFont(new Font("Arial", 30));

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

        btnSearch = new Button("Search");
        btnSearch.relocate(155, 250);

        // Event handler for the Search button
        btnSearch.setOnAction(event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtQuantity));

        primaryStage.show();

        pane.getChildren().addAll(lblSearch_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, txtBook_ID, txtTitle, txtAuthor, txtQuantity, btnSearch);
    }

    // Method to search the book from MySQL database
    private void searchBook(String bookID, TextField titleField, TextField authorField, TextField quantityField) {
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
            } else {
                // If no book is found, clear the TextFields
                titleField.clear();
                authorField.clear();
                quantityField.clear();
                System.out.println("No book found with that ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
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
        launch(args); // Call start method
    }
}

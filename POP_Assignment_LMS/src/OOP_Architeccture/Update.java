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

		// Set window title and size
		primaryStage.setTitle("Update Book");
		primaryStage.setWidth(400);
		primaryStage.setHeight(460);

		// Set background color of the pane
		pane.setStyle("-fx-background-color: #ADD8E6;"); // Light blue background

		// Declare UI elements
		Label lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblEdition, lblMessage;
		TextField txtBook_ID, txtTitle, txtAuthor, txtQuantity, txtEdition;
		Button btnUpdate, btnSearch, btnClose;

		// Title label
		lblUpdate_Book = new Label("Update Book");
		lblUpdate_Book.relocate(110, 0);
		lblUpdate_Book.setFont(new Font("Arial", 30));

		// Book ID label and text field
		lblBook_ID = new Label("Book ID:");
		lblBook_ID.relocate(30, 55);
		lblBook_ID.setFont(new Font("Arial", 15));
		txtBook_ID = new TextField();
		txtBook_ID.relocate(110, 50);

		// Search button
		btnSearch = new Button("Search");
		btnSearch.relocate(280, 50);

		// Title label and text field
		lblTitle = new Label("Title:");
		lblTitle.relocate(30, 105);
		lblTitle.setFont(new Font("Arial", 15));
		txtTitle = new TextField();
		txtTitle.relocate(110, 100);

		// Author label and text field
		lblAuthor = new Label("Author:");
		lblAuthor.relocate(30, 155);
		lblAuthor.setFont(new Font("Arial", 15));
		txtAuthor = new TextField();
		txtAuthor.relocate(110, 150);

		// Quantity label and text field
		lblQuantity = new Label("Quantity:");
		lblQuantity.relocate(30, 205);
		lblQuantity.setFont(new Font("Arial", 15));
		txtQuantity = new TextField();
		txtQuantity.relocate(110, 200);

		// Edition label and text field
		lblEdition = new Label("Edition:");
		lblEdition.relocate(30, 255);
		lblEdition.setFont(new Font("Arial", 15));
		txtEdition = new TextField();
		txtEdition.relocate(110, 250);

		// Close button to exit the window
		btnClose = new Button("Close");
		btnClose.relocate(200, 300);
		btnClose.setOnAction(event -> primaryStage.close()); // Close window when clicked

		// Update button
		btnUpdate = new Button("Update");
		btnUpdate.relocate(125, 300);

		// Message label to display success/error messages
		lblMessage = new Label();
		lblMessage.relocate(30, 330);
		lblMessage.setFont(new Font("Arial", 12));

		// Search button action - Fetch book details
		btnSearch.setOnAction(
				event -> searchBook(txtBook_ID.getText(), txtTitle, txtAuthor, txtQuantity, txtEdition, lblMessage));

		// Update button action - Update book details in the database
		btnUpdate.setOnAction(event -> updateBook(txtBook_ID.getText(), txtTitle.getText(), txtAuthor.getText(),
				txtQuantity.getText(), txtEdition.getText(), lblMessage));

		primaryStage.show();

		// Add all UI components to the pane
		pane.getChildren().addAll(lblUpdate_Book, lblBook_ID, lblTitle, lblAuthor, lblQuantity, lblEdition, txtBook_ID,
				txtTitle, txtAuthor, txtQuantity, txtEdition, btnUpdate, btnSearch, btnClose, lblMessage);
	}

	/**
	 * Searches for a book in the database based on the given Book ID. If found, the
	 * book's details are displayed in the respective fields.
	 */
	private void searchBook(String bookID, TextField titleField, TextField authorField, TextField quantityField,
			TextField editionField, Label lblMessage) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			if (conn != null) {
				String sql = "SELECT * FROM book WHERE book_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bookID);
				rs = stmt.executeQuery();

				if (rs.next()) { // If book is found, populate fields
					titleField.setText(rs.getString("title"));
					authorField.setText(rs.getString("author"));
					quantityField.setText(rs.getString("quantity"));
					editionField.setText(rs.getString("edition"));
					lblMessage.setText("Book found. You can update now.");
					lblMessage.setStyle("-fx-text-fill: green;");
				} else { // If no book is found, clear fields
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

	/**
	 * Updates the book details in the database based on the provided information.
	 */
	private void updateBook(String bookID, String title, String author, String quantity, String edition,
			Label lblMessage) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;

		try {
			if (conn != null) {
				String sql = "UPDATE books SET title = ?, author = ?, quantity = ?, edition = ? WHERE book_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, title);
				stmt.setString(2, author);
				stmt.setInt(3, Integer.parseInt(quantity)); // Convert quantity to integer
				stmt.setString(4, edition);
				stmt.setString(5, bookID);

				int rowsAffected = stmt.executeUpdate();

				if (rowsAffected > 0) { // If update is successful
					lblMessage.setText("Book updated successfully!");
					lblMessage.setStyle("-fx-text-fill: green;");
				} else { // If no rows were updated
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

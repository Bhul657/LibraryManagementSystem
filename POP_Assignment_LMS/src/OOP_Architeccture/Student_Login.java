package OOP_Architeccture;

import DB_Connect.DBConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student_Login extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);

		// Set window title and dimensions
		primaryStage.setTitle("Student Login");
		primaryStage.setWidth(400);
		primaryStage.setHeight(350);

		// Load background image
		Image image = new Image(getClass().getResourceAsStream("Librarian1.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(400);
		imageView.setFitWidth(400);
		imageView.relocate(0, 0);
		pane.getChildren().add(imageView);

		// Set background color (in case image does not load)
		pane.setStyle("-fx-background-color: #ADD8E6;"); // Light blue

		// Create and position UI components
		Label lblTitle = new Label("Student Login ");
		lblTitle.setFont(new Font("Times New Roman", 38));
		lblTitle.relocate(70, 30);

		Label lblUser = new Label("Username:");
		lblUser.setFont(new Font("Times New Roman", 18));
		lblUser.relocate(30, 120);

		TextField txtUser = new TextField(); // Input field for username
		txtUser.relocate(120, 115);

		Label lblPassword = new Label("Password:");
		lblPassword.setFont(new Font("Times New Roman", 18));
		lblPassword.relocate(30, 170);

		PasswordField txtPassword = new PasswordField(); // Input field for password
		txtPassword.relocate(120, 165);

		Button btnLogin = new Button("Login"); // Login button
		btnLogin.relocate(125, 220);

		Button btnClose = new Button("Close"); // Close button
		btnClose.relocate(225, 220);

		Label lblMessage = new Label(); // Label for login messages
		lblMessage.setFont(new Font("Arial", 16));
		lblMessage.relocate(50, 260);

		// Handle login button click
		btnLogin.setOnAction(e -> {
			String username = txtUser.getText();
			String password = txtPassword.getText();

			if (validateLogin(username, password)) { // Check if credentials are valid
				lblMessage.setText("Login Successful!");
				lblMessage.setStyle("-fx-text-fill: green;");

				// Open Search window after successful login
				Search search = new Search();
				Stage searchStage = new Stage();
				try {
					search.start(searchStage);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				primaryStage.close(); // Close login window
			} else {
				lblMessage.setText("Invalid username or password.");
				lblMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
			}
		});

		// Handle close button click
		btnClose.setOnAction(e -> primaryStage.close());

		// Apply styles to labels for better visibility
		lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
		lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
		lblPassword.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

		// Add all components to the pane
		pane.getChildren().addAll(lblTitle, lblUser, txtUser, lblPassword, txtPassword, btnLogin, btnClose, lblMessage);

		primaryStage.show(); // Display the login window
	}

	// Method to validate login credentials from the database
	private boolean validateLogin(String username, String password) {
		String sql = "SELECT * FROM student_login WHERE user_name = ? AND password = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, username); // Set user_name parameter
			stmt.setString(2, password); // Set password parameter
			ResultSet rs = stmt.executeQuery(); // Execute query

			return rs.next(); // If user exists, return true
		} catch (Exception e) {
			e.printStackTrace();
			return false; // If any error occurs, return false
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

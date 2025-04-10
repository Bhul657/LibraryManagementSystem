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

public class Librarian_Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Librarian Login"); 
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);

        // Set background image
        Image image = new Image(getClass().getResourceAsStream("Librarian1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.relocate(0, 0);
        pane.getChildren().add(imageView);

        // Title Label
        Label lblTitle = new Label("Librarian Login");
        lblTitle.setFont(new Font("Times New Roman", 38));
        lblTitle.relocate(70, 30);

        // Username Label & Text Field
        Label lblUser = new Label("User:");
        lblUser.setFont(new Font("Times New Roman", 18));
        lblUser.relocate(30, 120);
        TextField txtUser = new TextField();
        txtUser.relocate(120, 115);

        // Password Label & Password Field
        Label lblPassword = new Label("Password:");
        lblPassword.setFont(new Font("Times New Roman", 18));
        lblPassword.relocate(30, 170);
        PasswordField txtPassword = new PasswordField();
        txtPassword.relocate(120, 165);

        // Login & Close Buttons
        Button btnLogin = new Button("Login");
        btnLogin.relocate(125, 220);
        Button btnClose = new Button("Close");
        btnClose.relocate(225, 220);

        // Message Label for displaying login status
        Label lblMessage = new Label();
        lblMessage.setFont(new Font("Arial", 16));
        lblMessage.relocate(50, 260);

        // Login Button Action
        btnLogin.setOnAction(e -> {
            String username = txtUser.getText();
            String password = txtPassword.getText();

            if (validateLogin(username, password)) {
                lblMessage.setText("Login Successful!");
                lblMessage.setStyle("-fx-text-fill: green;");

                // Open Librarian Dashboard
                Librarian_Dashboard dashboard = new Librarian_Dashboard();
                Stage dashboardStage = new Stage();
                try {
                    dashboard.start(dashboardStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                primaryStage.close(); // Close login window
            } else {
                lblMessage.setText("Invalid username or password.");
                lblMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: red;" );
            }
        });

        // Close Button Action
        btnClose.setOnAction(e -> primaryStage.close());

        // Styling labels
        lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        lblPassword.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        // Adding elements to pane
        pane.getChildren().addAll(lblTitle, lblUser, txtUser, lblPassword, txtPassword, btnLogin, btnClose, lblMessage);

        primaryStage.show();
    }

    // Method to validate login credentials by checking the database
    private boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM librarian_login WHERE user_name = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If a matching record is found, return true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

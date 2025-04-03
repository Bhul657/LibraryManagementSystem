package OOP_Architeccture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB_Connect.DBConnection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Student_Registration extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Student Registration");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);

        // Background Image
        Image image = new Image(getClass().getResourceAsStream("register.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.relocate(0, 0);
        pane.getChildren().add(imageView);

        // Background Color
        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblTitle = new Label("Student Registration");
        lblTitle.setFont(new Font("Arial", 32));
        lblTitle.relocate(50, 30);

        Label lblUser = new Label("Username:");
        lblUser.setFont(new Font("Times New Roman", 18));
        lblUser.relocate(30, 100);

        TextField txtUser = new TextField();
        txtUser.relocate(150, 95);

        Label lblPassword = new Label("Password:");
        lblPassword.setFont(new Font("Times New Roman", 18));
        lblPassword.relocate(30, 150);

        PasswordField txtPassword = new PasswordField();
        txtPassword.relocate(150, 145);

        Button btnRegister = new Button("Register");
        btnRegister.relocate(150, 200);

        Button btnBack = new Button("Back");
        btnBack.relocate(250, 200);
        
        Label lblMessage = new Label();
        lblMessage.setFont(new Font("Arial", 14));
        lblMessage.relocate(30, 250);

        // Registration Button Action (Connects to MySQL)
        btnRegister.setOnAction((ActionEvent event) -> {
            String username = txtUser.getText();
            String password = txtPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                lblMessage.setText("Please fill all fields!");
                lblMessage.setStyle("-fx-text-fill: red;");
                return;
            }

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO student_login (user_name, password) VALUES (?, ?)")) {

                stmt.setString(1, username);
                stmt.setString(2, password);
                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    lblMessage.setText("Registration Successful!");
                    lblMessage.setStyle("-fx-text-fill: white;");
                } else {
                    lblMessage.setText("Registration Failed!");
                    lblMessage.setStyle("-fx-text-fill: white;");
                }

            } catch (SQLException e) {
                lblMessage.setText("Error: " + e.getMessage());
                lblMessage.setStyle("-fx-text-fill: red;");
            }
        });

      //Login Button Action (Redirects to User_Dashboard)
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {

                // Open User User_Dashboard
        		library_management_system back = new library_management_system();
                Stage backStage = new Stage();
                try {
                    back.start(backStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        lblPassword.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        

        // Adding elements to pane
        pane.getChildren().addAll(lblTitle, lblUser, txtUser, lblPassword, txtPassword, btnRegister, btnBack, lblMessage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

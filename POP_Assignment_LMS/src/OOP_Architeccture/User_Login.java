package OOP_Architeccture;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class User_Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("User Login");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);

        // Set Background Color
        pane.setStyle("-fx-background-color: #ADD8E6;");

        // Labels
        Label lblTitle = new Label("User Login Window");
        lblTitle.relocate(30, 0);
        lblTitle.setFont(new Font("Arial", 24));

        Label lblUser = new Label("Username:");
        lblUser.relocate(30, 55);
        lblUser.setFont(new Font("Constantia", 15));

        Label lblPassword = new Label("Password:");
        lblPassword.relocate(30, 105);
        lblPassword.setFont(new Font("Constantia", 15));

        Label lblMessage = new Label(); // Message Label for Errors/Success
        lblMessage.relocate(30, 230);
        lblMessage.setFont(new Font("Arial", 15));
        lblMessage.setVisible(false); // Initially hidden

        // Input Fields
        TextField txtUser = new TextField();
        txtUser.relocate(30, 78);
        txtUser.setPrefWidth(200);

        PasswordField txtPassword = new PasswordField();
        txtPassword.relocate(30, 128);
        txtPassword.setPrefWidth(200);

        // Buttons
        Button btnLogin = new Button("Login");
        btnLogin.relocate(30, 180);

        Button btnClose = new Button("Close");
        btnClose.relocate(130, 180);

        // **Login Button Action (Redirects to Dashboard)**
        btnLogin.setOnAction(e -> {
            if (txtUser.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                lblMessage.setText("Username and Password cannot be empty!");
                lblMessage.setTextFill(Paint.valueOf("red"));
                lblMessage.setVisible(true);
            } else {
                lblMessage.setText("Login successful!");
                lblMessage.setTextFill(Paint.valueOf("green"));
                lblMessage.setVisible(true);

                // Close login window
                primaryStage.close();

                // Open User Dashboard
                User_Dashboard dashboard = new User_Dashboard();
                Stage dashboardStage = new Stage();
                try {
                    dashboard.start(dashboardStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // **Close Button Action**
        btnClose.setOnAction(e -> primaryStage.close());

        // Add elements to pane
        pane.getChildren().addAll(lblTitle, lblUser, txtUser, lblPassword, txtPassword, btnLogin, btnClose, lblMessage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Calls start method
    }
}

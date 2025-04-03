package OOP_Architeccture;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Librarian_Login extends Application {

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
        
        Image image = new Image(getClass().getResourceAsStream("Librarian1.jpg"));
	    ImageView imageView = new ImageView(image);
	    imageView.setFitHeight(400);
	    imageView.setFitWidth(400);
	    imageView.relocate(0, 0);
	    pane.getChildren().add(imageView);

        // Background Color
        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblTitle = new Label("Librarian Login ");
        lblTitle.setFont(new Font("Times New Roman", 38));
        lblTitle.relocate(70, 30);

        Label lblUser = new Label("User:");
        lblUser.setFont(new Font("Times New Roman", 18));
        lblUser.relocate(30, 120);

        TextField txtUser = new TextField();
        txtUser.relocate(120, 115);

        Label lblPassword = new Label("Password:");
        lblPassword.setFont(new Font("Times New Roman", 18));
        lblPassword.relocate(30, 170);

        PasswordField txtPassword = new PasswordField();
        txtPassword.relocate(120, 165);

        Button btnLogin = new Button("Login");
        btnLogin.relocate(125, 220);

        Button btnClose = new Button("Close");
        btnClose.relocate(225, 220);
        
        Label lblMessage = new Label();
        lblMessage.setFont(new Font("Arial", 12));
        lblMessage.relocate(30, 260);

        //Login Button Action (Redirects to User_Dashboard)
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {

                // Open User User_Dashboard
                Librarian_Dashboard dashboard = new Librarian_Dashboard();
                Stage dashboardStage = new Stage();
                try {
                    dashboard.start(dashboardStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                // Close login window
                primaryStage.close();
            }
        });

        lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white;"); // Bold & White text
        lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");   // Bold & White text
        lblPassword.setStyle("-fx-font-weight: bold; -fx-text-fill: white;"); // Bold & White text
        
        
        // Adding elements to pane
        pane.getChildren().addAll(lblTitle, lblUser, txtUser, lblPassword, txtPassword, btnLogin, btnClose,lblMessage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Calling start method
    }
}

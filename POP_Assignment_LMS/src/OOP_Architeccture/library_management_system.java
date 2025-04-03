package OOP_Architeccture;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class library_management_system extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Library Management System");
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        primaryStage.setAlwaysOnTop(true);
        
        Image image = new Image(getClass().getResourceAsStream("welcome.jpg"));
	    ImageView imageView = new ImageView(image);
	    imageView.setFitHeight(400);
	    imageView.setFitWidth(400);
	    imageView.relocate(0, 0);
	    pane.getChildren().add(imageView);

        // Background Color
        pane.setStyle("-fx-background-color: #ADD8E6;");

        Label lblTitle = new Label("Welcome to Library");
        lblTitle.setFont(new Font("Times New Roman", 35));
        lblTitle.relocate(48, 60);
        
        Label lblRegister = new Label("Not Registered? Click Below");
        lblRegister.setFont(new Font("Time New Roman",16));
        lblRegister.relocate(100, 220);
        
        Button btnStudent = new Button("Student");
        btnStudent.relocate(125, 150);

        Button btnLibrarian = new Button("Librarian");
        btnLibrarian.relocate(225, 150);
        
        Button btnRegister = new Button ("Registration Form");
        btnRegister.relocate(145, 250);    
        
        Label lblMessage = new Label();
        lblMessage.setFont(new Font("Arial", 12));
        lblMessage.relocate(30, 260);
        
      

        //Login Button Action (Redirects to User_Dashboard)
        btnLibrarian.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {

                // Open User User_Dashboard
                Librarian_Login login = new Librarian_Login();
                Stage loginStage = new Stage();
                try {
                    login.start(loginStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
      //Button to redirects Student_Registration 
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent arg0) {

                // Open User User_Dashboard
                Student_Registration register = new Student_Registration();
                Stage registerStage = new Stage();
                try {
                    register.start(registerStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        
        
        lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: White;"); // Bold & White text
        lblRegister.setStyle("-fx-font-weight: bold; -fx-text-fill: White;"); // Bold & White text
        
        // Adding elements to pane
        pane.getChildren().addAll(lblTitle, lblRegister, btnStudent, btnLibrarian, btnRegister, lblMessage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Calling start method
    }
}

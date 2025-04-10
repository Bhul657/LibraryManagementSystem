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
        primaryStage.setResizable(false); // Disable window resizing
        Pane pane = new Pane(); // Creating a pane to hold UI elements
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Library Management System"); // Set window title
        primaryStage.setWidth(400);
        primaryStage.setHeight(350);
        
        // Adding a background image
        Image image = new Image(getClass().getResourceAsStream("welcome.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.relocate(0, 0);
        pane.getChildren().add(imageView);

        // Setting background color
        pane.setStyle("-fx-background-color: #ADD8E6;");

        // Creating a welcome label
        Label lblTitle = new Label("Welcome to Library");
        lblTitle.setFont(new Font("Times New Roman", 35));
        lblTitle.relocate(48, 60);
        
        // Creating buttons for user roles
        Button btnStudent = new Button("Student");
        btnStudent.relocate(125, 150);

        Button btnLibrarian = new Button("Librarian");
        btnLibrarian.relocate(225, 150);
        
        Label lblMessage = new Label(); // Label for displaying messages
        lblMessage.setFont(new Font("Arial", 12));
        lblMessage.relocate(30, 260);
        
        // Event handler for Librarian login button
        btnLibrarian.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Librarian_Login login = new Librarian_Login();
                Stage loginStage = new Stage();
                try {
                    login.start(loginStage); // Open the librarian login window
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        // Event handler for Student login button
        btnStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Student_Login login = new Student_Login();
                Stage loginStage = new Stage();
                try {
                    login.start(loginStage); // Open the student login window
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Styling labels for better visibility
        lblTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: White;"); // Bold & White text
        
        // Adding all UI elements to the pane
        pane.getChildren().addAll(lblTitle, btnStudent, btnLibrarian, lblMessage);

        primaryStage.show(); // Displaying the main window
    }

    public static void main(String[] args) {
        launch(args); 
    }
}

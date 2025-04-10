package OOP_Architeccture;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Librarian_Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 520); // width: 900, height: 520
        scene.setFill(Color.LIGHTBLUE); // light blue background
        primaryStage.setScene(scene);

        // LEFT SIDEBAR 
        VBox sidebar = new VBox(20); // Vertical box with 20px spacing between buttons
        sidebar.setPadding(new Insets(20)); // Padding inside the sidebar
        sidebar.setStyle("-fx-background-color: #ffffff;"); // White background
        sidebar.setPrefWidth(200); // Sidebar width

        // Creating all the sidebar buttons
        Button btnAdd = new Button("Add");
        Button btnSearch = new Button("Search");
        Button btnUpdate = new Button("Update");
        Button btnRemove = new Button("Remove");
        Button btnCalculateFine = new Button("Calculate Fine");
        Button btnSignOut = new Button("Sign Out");

        // Set a uniform width for all buttons
        double buttonWidth = 120;
        btnAdd.setPrefWidth(buttonWidth);
        btnSearch.setPrefWidth(buttonWidth);
        btnUpdate.setPrefWidth(buttonWidth);
        btnRemove.setPrefWidth(buttonWidth);
        btnCalculateFine.setPrefWidth(buttonWidth);
        btnSignOut.setPrefWidth(buttonWidth);

        // Add all buttons to the sidebar
        sidebar.getChildren().addAll(btnAdd, btnSearch, btnUpdate, btnRemove, btnCalculateFine, btnSignOut);

        // ========== MAIN CONTENT AREA ==========
        VBox mainContent = new VBox(); // Vertical layout for the center content
        mainContent.setPadding(new Insets(30)); // Padding around the text area
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom right, #e6f0ff, #ffffff);");

        // Creating a read-only text area for displaying info
        TextArea textArea = new TextArea();
        textArea.setWrapText(true); // Text will wrap if it's too long
        textArea.setEditable(false); // Users can't edit it
        textArea.setPrefWidth(400); // Fixed width
        textArea.setPrefHeight(420); // Fixed height

        // Add the text area directly into the center
        mainContent.getChildren().add(textArea);

        // TOP MENU BAR 
        MenuBar menuBar = new MenuBar(); // Horizontal menu bar at the top

        // File menu and its items
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
            new MenuItem("New"),
            new MenuItem("Open"),
            new MenuItem("Close"),
            new MenuItem("Exit"),
            new MenuItem("Save"),
            new SeparatorMenuItem() // Adds a line separator
        );

        // Edit menu and its items
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(
            new MenuItem("Copy"),
            new MenuItem("Cut"),
            new MenuItem("Paste"),
            new MenuItem("Raw Paste"),
            new SeparatorMenuItem()
        );

        // Resources menu
        Menu resourceMenu = new Menu("Resources");
        resourceMenu.getItems().addAll(
            new MenuItem("Library"),
            new MenuItem("Reading Lounge"),
            new MenuItem("Conference Lounge"),
            new SeparatorMenuItem()
        );

        // About/help menu
        Menu aboutMenu = new Menu("About");
        aboutMenu.getItems().addAll(
            new MenuItem("Help"),
            new MenuItem("Search"),
            new MenuItem("About Us"),
            new SeparatorMenuItem()
        );

        // Add all menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu, resourceMenu, aboutMenu);

        // BOTTOM FOOTER 
        HBox footer = new HBox(); // Horizontal box for the footer
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER); // Center the content

        // Footer label
        Label footerLabel = new Label("A & N Library");
        footerLabel.setFont(Font.font("Times New Roman", 16)); // Font style and size
        footer.getChildren().add(footerLabel);

        // PLACING EVERYTHING IN LAYOUT 
        root.setTop(menuBar);       // Menu bar at the top
        root.setLeft(sidebar);      // Sidebar on the left
        root.setCenter(mainContent); // Main area in the center
        root.setBottom(footer);     // Footer at the bottom

        // BUTTON ACTIONS 
        // Each button opens a new window (new Stage)
        btnAdd.setOnAction(e -> {
            try {
                new Add().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnSearch.setOnAction(e -> {
            try {
                new Search().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnUpdate.setOnAction(e -> {
            try {
                new Update().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnRemove.setOnAction(e -> {
            try {
                new Remove().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnCalculateFine.setOnAction(e -> {
            try {
                new Fine_Calculation().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Sign out button closes current window and opens login
        btnSignOut.setOnAction(e -> {
            try {
                new Librarian_Login().start(new Stage());
                primaryStage.close(); // Close the current dashboard
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Window title and showing the stage
        primaryStage.setTitle("Librarian Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}

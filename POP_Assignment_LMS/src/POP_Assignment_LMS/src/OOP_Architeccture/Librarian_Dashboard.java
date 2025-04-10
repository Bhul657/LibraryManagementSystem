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
        // ✅ Increased scene size to fit full layout including 400x420 TextArea
        Scene scene = new Scene(root, 900, 520);
        scene.setFill(Color.LIGHTBLUE);
        primaryStage.setScene(scene);

        // ===== Sidebar =====
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #ffffff;");
        sidebar.setPrefWidth(200);

        // Buttons
        Button btnAdd = new Button("Add");
        Button btnSearch = new Button("Search");
        Button btnUpdate = new Button("Update");
        Button btnRemove = new Button("Remove");
        Button btnCalculateFine = new Button("Calculate Fine");
        Button btnSignOut = new Button("Sign Out");

        double buttonWidth = 120;
        btnAdd.setPrefWidth(buttonWidth);
        btnSearch.setPrefWidth(buttonWidth);
        btnUpdate.setPrefWidth(buttonWidth);
        btnRemove.setPrefWidth(buttonWidth);
        btnCalculateFine.setPrefWidth(buttonWidth);
        btnSignOut.setPrefWidth(buttonWidth);

        sidebar.getChildren().addAll(btnAdd, btnSearch, btnUpdate, btnRemove, btnCalculateFine, btnSignOut);

        // ===== Main Content =====
        VBox mainContent = new VBox();
        mainContent.setPadding(new Insets(30));
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom right, #e6f0ff, #ffffff);");

        // ✅ TextArea without ScrollPane
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefWidth(400);   // Fixed width
        textArea.setPrefHeight(420);  // Fixed height

        mainContent.getChildren().add(textArea);

        // ===== Menu Bar =====
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
            new MenuItem("New"),
            new MenuItem("Open"),
            new MenuItem("Close"),
            new MenuItem("Exit"),
            new MenuItem("Save"),
            new SeparatorMenuItem()
        );

        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(
            new MenuItem("Copy"),
            new MenuItem("Cut"),
            new MenuItem("Paste"),
            new MenuItem("Raw Paste"),
            new SeparatorMenuItem()
        );

        Menu resourceMenu = new Menu("Resources");
        resourceMenu.getItems().addAll(
            new MenuItem("Library"),
            new MenuItem("Reading Lounge"),
            new MenuItem("Conference Lounge"),
            new SeparatorMenuItem()
        );

        Menu aboutMenu = new Menu("About");
        aboutMenu.getItems().addAll(
            new MenuItem("Help"),
            new MenuItem("Search"),
            new MenuItem("About Us"),
            new SeparatorMenuItem()
        );

        menuBar.getMenus().addAll(fileMenu, editMenu, resourceMenu, aboutMenu);

        // ===== Footer =====
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setAlignment(Pos.CENTER);
        Label footerLabel = new Label("A & N Library");
        footerLabel.setFont(Font.font("Times New Roman", 16));
        footer.getChildren().add(footerLabel);

        // ===== Layout Assignments =====
        root.setTop(menuBar);
        root.setLeft(sidebar);
        root.setCenter(mainContent);
        root.setBottom(footer);

        // ===== Button Actions =====
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

        btnSignOut.setOnAction(e -> {
            try {
                new Librarian_Login().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.setTitle("Librarian Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package OOP_Architeccture;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Student_Dashboard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		Pane pane1 = new Pane();
		Scene scene1 = new Scene(pane1);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 450);
		scene.setFill(Color.LIGHTBLUE);
		primaryStage.setScene(scene);

		// Sidebar
		VBox sidebar = new VBox(20);
		sidebar.setPadding(new Insets(20));
		sidebar.setStyle("-fx-background-color: #ffffff;");
		sidebar.setPrefWidth(200);

		// Button
		Button btnAdd, btnSearch, btnUpdate, btnRemove, btnCalculateFine, btnSignOut;
		btnAdd = new Button("Add");
		btnAdd.relocate(30, 20);
		pane1.getChildren().add(btnAdd);

		btnSearch = new Button("Search");
		btnSearch.relocate(30, 80);
		pane1.getChildren().add(btnSearch);

		btnUpdate = new Button("Update");
		btnUpdate.relocate(30, 140);
		pane1.getChildren().add(btnUpdate);

		btnRemove = new Button("Remove");
		btnRemove.relocate(30, 200);
		pane1.getChildren().add(btnRemove);

		btnCalculateFine = new Button("Calculate Fine");
		btnCalculateFine.relocate(30, 260);
		pane1.getChildren().add(btnCalculateFine);

		btnSignOut = new Button("Sign Out");
		btnSignOut.relocate(30, 260);
		pane1.getChildren().add(btnSignOut);
		;

		// For Same Size Button
		double buttonWidth = 120;
		btnAdd.setPrefWidth(buttonWidth);
		btnSearch.setPrefWidth(buttonWidth);
		btnUpdate.setPrefWidth(buttonWidth);
		btnRemove.setPrefWidth(buttonWidth);
		btnCalculateFine.setPrefWidth(buttonWidth);
		btnSignOut.setPrefWidth(buttonWidth);

		sidebar.getChildren().addAll(btnAdd, btnSearch, btnUpdate, btnRemove, btnCalculateFine, btnSignOut);

		// Main Content
		VBox mainContent = new VBox(20);
		mainContent.setPadding(new Insets(30));
		mainContent.setStyle("-fx-background-color: linear-gradient(to bottom right, #e6f0ff, #ffffff);");

		// ScrollPane with Text
		TextArea textArea = new TextArea();
		textArea.setText("Rules and Regulations of Librarian\n 1.\n 2. \n 3.\n 4.\n 5. \n 6.\n 7.\n \n"
				+ " Resources\n 1.\n 2. \n 3.\n 4.\n 5. \n \n " + "Intresting Facts \n 1.\n 2. \n 3.\n 4.\n 5."); // Scrolling
		textArea.setWrapText(true);
		textArea.setEditable(false); // Disable editing if it's only for viewing text
		textArea.setPrefSize(600, 400); // Adjust size based on your content

		ScrollPane scrollPane1 = new ScrollPane();
		scrollPane1.setFitToWidth(true); // Make the content width fit the ScrollPane width
		scrollPane1.setFitToHeight(true); // Make the content height fit the ScrollPane height
		scrollPane1.setContent(textArea); // Set content of ScrollPane to Rectangle

		mainContent.getChildren().add(scrollPane1); // Add ScrollPane with Rectangle to mainContent

		// Menu Bar File
		MenuBar menuBar1 = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem menuItem1 = new MenuItem("New");
		MenuItem menuItem2 = new MenuItem("Open");
		MenuItem menuItem3 = new MenuItem("Close");
		MenuItem menuItem4 = new MenuItem("Exit");
		MenuItem menuItem5 = new MenuItem("Save");
		SeparatorMenuItem separator = new SeparatorMenuItem();

		menuFile.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, separator);
		menuBar1.getMenus().add(menuFile);

		// Menu Bar Edit
		MenuBar menuBar2 = new MenuBar();
		Menu menuEdit = new Menu("Edit");
		MenuItem menuItemEdit1 = new MenuItem("Copy");
		MenuItem menuItemEdit2 = new MenuItem("Cut");
		MenuItem menuItemEdit3 = new MenuItem("Paste");
		MenuItem menuItemEdit4 = new MenuItem("Raw Paste");
		SeparatorMenuItem separator1 = new SeparatorMenuItem();

		menuEdit.getItems().addAll(menuItemEdit1, menuItemEdit2, menuItemEdit3, menuItemEdit4, separator1);
		menuBar2.getMenus().add(menuEdit);

		// Menu Bar Resources
		MenuBar menuBar3 = new MenuBar();
		Menu menuRes = new Menu("Resources");
		MenuItem menuRes1 = new MenuItem("Library");
		MenuItem menuRes2 = new MenuItem("Reading Lounge");
		MenuItem menuRes3 = new MenuItem("Conference Lounge");
		SeparatorMenuItem separator2 = new SeparatorMenuItem();

		menuRes.getItems().addAll(menuRes1, menuRes2, menuRes3, separator2);
		menuBar3.getMenus().add(menuRes);

		// Menu Bar About
		MenuBar menuBar4 = new MenuBar();
		Menu menuAbo = new Menu("About");
		MenuItem menuAbo1 = new MenuItem("Help");
		MenuItem menuAbo2 = new MenuItem("Search");
		MenuItem menuAbo3 = new MenuItem("About US");
		MenuItem menuAbo4 = new MenuItem("Check Update");
		SeparatorMenuItem separator3 = new SeparatorMenuItem();

		menuAbo.getItems().addAll(menuAbo1, menuAbo2, menuAbo3, separator3);
		menuBar4.getMenus().add(menuAbo);

		// Combine all menu bars in one HBox
		HBox topContainer = new HBox();
		topContainer.getChildren().addAll(menuBar1, menuBar2, menuBar3, menuBar4); // ✅ fixed
		root.setTop(topContainer);

		// Navbar Footer
		HBox footer = new HBox();
		footer.setPadding(new Insets(15));
		footer.setAlignment(Pos.CENTER);

		Label footerLabel = new Label("A & N Library");
		footerLabel.setFont(Font.font("Times New Roman", 16));
		footer.getChildren().add(footerLabel);
		root.setBottom(footer);

		// Layout Setup
		root.setTop(topContainer); // Set combined menu bars at the top
		root.setLeft(sidebar);
		root.setCenter(mainContent);
		root.setBottom(footer);

		// Event Handling for the Add Button
		btnAdd.setOnAction(e -> {
			// Open the Add window
			Add add = new Add();
			try {
				add.start(new Stage()); // Open a new stage for the Menu
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnSearch.setOnAction(e -> {
			// Open the Search window
			Search search = new Search();
			try {
				search.start(new Stage()); // Open a new stage for the Menu
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnUpdate.setOnAction(e -> {
			// Open the Update window
			Update update = new Update();
			try {
				update.start(new Stage()); // Open a new stage for the Menu
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnRemove.setOnAction(e -> {
			// Open the Remove window
			Remove remove = new Remove();
			try {
				remove.start(new Stage()); // Open a new stage for the Menu
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnCalculateFine.setOnAction(e -> {
			// Open the Calculate Fine Window
			// Remove CalculateFine = new CalculateFine();
			try {
				new Fine_test().start(new Stage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnSignOut.setOnAction(e -> {
			// Create a new Stage for the Library Login page
			Librarian_Login loginPage = new Librarian_Login();
			try {
				// Start the Login page
				loginPage.start(new Stage());

				// Close the current dashboard window
				primaryStage.close(); // Close the current Librarian Dashboard window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		primaryStage.setTitle("Librarian Dashboard");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args); // Call start method
	}
}

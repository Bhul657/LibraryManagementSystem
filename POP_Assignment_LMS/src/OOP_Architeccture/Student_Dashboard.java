package OOP_Architeccture;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Student_Dashboard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		Paint paint = Paint.valueOf("Green");
		scene.setFill(paint);
		primaryStage.setScene(scene);

		Image image = new Image(getClass().getResourceAsStream("images.jpg"));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(400);
		imageView.setFitWidth(400);
		imageView.relocate(0, 0);
		pane.getChildren().add(imageView);

		primaryStage.setTitle("Librarian Dashboard");
		primaryStage.setWidth(400);
		primaryStage.setHeight(350);
		primaryStage.setAlwaysOnTop(true);

		// Set Background Color on Stage
		pane.setStyle("-fx-background-color: #ADD8E6;");
		Button btnAdd, btnSearch, btnUpdate, btnRemove, btnCalculateFine;

		btnAdd = new Button("Add");
		btnAdd.relocate(30, 20);
		pane.getChildren().add(btnAdd);

		btnSearch = new Button("Search");
		btnSearch.relocate(30, 80);
		pane.getChildren().add(btnSearch);

		btnUpdate = new Button("Update");
		btnUpdate.relocate(30, 140);
		pane.getChildren().add(btnUpdate);

		btnRemove = new Button("Remove");
		btnRemove.relocate(30, 200);
		pane.getChildren().add(btnRemove);

		btnCalculateFine = new Button("Calculate Fine");
		btnCalculateFine.relocate(30, 260);
		pane.getChildren().add(btnCalculateFine);
		;

		// Event Handling for the Add Button
		btnCalculateFine.setOnAction(e -> {
			Fine_Calculation CalculateFine = new Fine_Calculation();
			try {
				CalculateFine.start(new Stage()); // Open a new stage for the Menu
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

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

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args); // Call start method
	}
}
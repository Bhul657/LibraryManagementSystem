package OOP_Architeccture;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine_test extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label dueDateLabel = new Label("Select Due Date:");
        DatePicker dueDatePicker = new DatePicker();

        Label returnDateLabel = new Label("Select Return Date:");
        DatePicker returnDatePicker = new DatePicker();

        Label fineLabel = new Label("Fine Amount: $0.00");
        Button calculateButton = new Button("Calculate Fine");

        calculateButton.setOnAction(e -> {
            LocalDate dueDate = dueDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();
            double finePerDay = 5.0; // Fixed fine per day

            if (dueDate != null && returnDate != null) {
                double fineAmount = calculateFine(dueDate, returnDate, finePerDay);
                fineLabel.setText("Fine Amount: $" + fineAmount);
            } else {
                fineLabel.setText("Please select both dates.");
            }
        });

        VBox layout = new VBox(10, dueDateLabel, dueDatePicker, returnDateLabel, returnDatePicker, calculateButton, fineLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fine Calculator");
        primaryStage.show();
    }

    private double calculateFine(LocalDate dueDate, LocalDate returnDate, double finePerDay) {
        if (returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
            return 0; // No fine if returned on or before due date
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate * finePerDay;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

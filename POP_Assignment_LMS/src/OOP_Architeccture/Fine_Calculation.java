package OOP_Architeccture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import DB_Connect.DBConnection;
import javafx.stage.Stage;

public class Fine_Calculation {

	// Method to calculate fine and store it in the database
	public void calculateFineAndStore(int userID, int bookID) {
		try (Connection conn = DBConnection.getConnection()) {

			// 1. Get the due date of the book
			String selectQuery = "SELECT due_date FROM borrow_records WHERE user_id = ? AND book_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
				stmt.setInt(1, userID);
				stmt.setInt(2, bookID);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					Date dueDate = rs.getDate("due_date");
					Date currentDate = new Date(System.currentTimeMillis()); // Get today's date

					// 2. Check if the book is overdue
					if (currentDate.after(dueDate)) {
						long overdueDays = (currentDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
						double fineAmount = overdueDays * 1.0; // Assuming $1 fine per day.

						// 3. Insert the fine into the fines table
						String insertQuery = "INSERT INTO fines (user_id, book_id, fine_amount, fine_date) VALUES (?, ?, ?, ?)";
						try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
							insertStmt.setInt(1, userID);
							insertStmt.setInt(2, bookID);
							insertStmt.setDouble(3, fineAmount);
							insertStmt.setDate(4, currentDate); // Set today's date as fine calculation date.

							int rowsAffected = insertStmt.executeUpdate();
							if (rowsAffected > 0) {
								System.out.println("Fine of $" + fineAmount + " has been calculated and stored.");
							} else {
								System.out.println("Failed to store the fine.");
							}
						}
					} else {
						System.out.println("No fine is due. The book is returned on time.");
					}
				} else {
					System.out.println("No borrow record found for the given user and book.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start(Stage stage) {
		Fine_Calculation fine_Calculation = new Fine_Calculation();
		fine_Calculation.calculateFineAndStore(1, 101); // Example userID = 1, bookID = 101

	}
}

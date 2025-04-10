package OOP_Architeccture;

import java.time.LocalDate;

public interface LibraryOperations {

	// Add a new book
	void addBook(String bookId, String title, String author, int quantity, String edition);

	// Remove a book by book ID
	void removeBook(String bookId);

	// Search for a book by book ID
	void searchBook(String bookId);

	// Update existing book information
	void updateBook(String bookId, String title, String author, int quantity, String edition);

	// Calculate fine based on borrow and return date
	double calculateFine(int studentId, LocalDate returnDate, double finePerDay);
}

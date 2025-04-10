package DB_Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// Database connection details
	private static final String URL = "jdbc:mysql://localhost:3306/library_db";
	private static final String USER = "root";
	private static final String PASSWORD = "bhularun7";

	// Method to establish a database connection
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// Establish the connection to MySQL database
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Database connected successfully!");
		} catch (SQLException e) {
			// Print an error message if connection fails
			System.out.println("Database connection failed: " + e.getMessage());
		}
		return conn; // Return the established connection
	}

	// Method to close the database connection
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close(); // Closing the connection
				System.out.println("Connection closed successfully!");
			} catch (SQLException e) {
				// Print an error message if closing fails
				System.out.println("Failed to close the connection: " + e.getMessage());
			}
		}
	}
}

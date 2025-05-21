package javafxplanner;
import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:planner.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS app_users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT UNIQUE NOT NULL);";

        String createEntriesTable = "CREATE TABLE IF NOT EXISTS journal_entries (" +
                "entry_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "author_id INTEGER," +
                "entry_title TEXT," +
                "entry_content TEXT," +
                "creation_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(author_id) REFERENCES app_users(user_id));";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createUsersTable);
            stmt.execute(createEntriesTable);

            stmt.executeUpdate("INSERT OR IGNORE INTO app_users(username) VALUES('default_author')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
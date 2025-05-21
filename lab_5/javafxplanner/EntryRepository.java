package javafxplanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    private static final int DEFAULT_AUTHOR_ID = 1;

    public List<JournalEntry> searchEntries(String keyword) {
        List<JournalEntry> entries = new ArrayList<>();
        String query = "SELECT * FROM journal_entries " +
                "WHERE author_id = ? AND entry_title LIKE ? " +
                "ORDER BY creation_date DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, DEFAULT_AUTHOR_ID);
            stmt.setString(2, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                entries.add(new JournalEntry(
                        rs.getInt("entry_id"),
                        rs.getInt("author_id"),
                        rs.getString("entry_title"),
                        rs.getString("entry_content"),
                        rs.getString("creation_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public void saveEntry(JournalEntry entry) {
        String query = entry.getEntryId() == 0 ?
                "INSERT INTO journal_entries(author_id, entry_title, entry_content) VALUES(?,?,?)" :
                "UPDATE journal_entries SET entry_title = ?, entry_content = ? WHERE entry_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (entry.getEntryId() == 0) {
                stmt.setInt(1, DEFAULT_AUTHOR_ID);
                stmt.setString(2, entry.getTitle());
                stmt.setString(3, entry.getContent());
            } else {
                stmt.setString(1, entry.getTitle());
                stmt.setString(2, entry.getContent());
                stmt.setInt(3, entry.getEntryId());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(int entryId) {
        String query = "DELETE FROM journal_entries WHERE entry_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, entryId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
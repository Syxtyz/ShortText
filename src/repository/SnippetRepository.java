package repository;

import db.DatabaseManager;
import model.Snippet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SnippetRepository {

    public List<Snippet> findAll() {
        List<Snippet> list = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM snippets")) {

            while (rs.next()) {
                list.add(new Snippet(
                        rs.getInt("id"),
                        rs.getString("trigger"),
                        rs.getString("content"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(String trigger, String content, String description) {
        String sql = "INSERT INTO snippets(trigger, content, description) VALUES(?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trigger);
            ps.setString(2, content);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String trigger, String content, String description) {
        String sql = "UPDATE snippets SET trigger=?, content=?, description=? WHERE id=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trigger);
            ps.setString(2, content);
            ps.setString(3, description);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement("DELETE FROM snippets WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

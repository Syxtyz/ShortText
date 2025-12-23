/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.HashMap;
import java.util.Map;
import db.DatabaseManager;
/**
 *
 * @author Admin
 */
public class SnippetRepository {
    public Map<String, String> loadAll() {
        Map<String, String> map = new HashMap<>();
        try (var conn = DatabaseManager.getConnection(); var stmt = conn.createStatement(); var rs = stmt.executeQuery("SELECT trigger, content FROM snippets")) {
            while (rs.next()) {
                map.put(rs.getString("trigger"), rs.getString("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}

package shorttext;

import system.SystemTray;
import ui.ApplicationGUI;
import db.SchemaInitializer;

public class ShortText {
    public static void main(String[] args) {
        SchemaInitializer.init();
//        try (var conn = db.DatabaseManager.getConnection(); var stmt = conn.createStatement(); var rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='snippets';")) {
//            if (rs.next()) {
//                System.out.println("Table 'snippets' exists!");
//            } else {
//                System.out.println("Table 'snippets' does NOT exist.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        ApplicationGUI window = new ApplicationGUI();
        new SystemTray(window);
    }
}
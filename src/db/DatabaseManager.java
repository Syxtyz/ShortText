/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DatabaseManager {
    private static final Path APP_DIR = Path.of(System.getProperty("user.home"), ".shorttext");
    private static final String DB_URL = "jdbc:sqlite:" + APP_DIR.resolve("snippets.db");
    
    static {
        try {
            Files.createDirectories(APP_DIR);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create app directory", e);
        }
    }
    
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }
}

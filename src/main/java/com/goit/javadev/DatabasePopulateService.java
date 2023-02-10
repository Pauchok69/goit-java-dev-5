package com.goit.javadev;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    private static final String POPULATE_DB_FILE = "sql/populate_db.sql";

    public static void main(String[] args) throws IOException {
        try (Connection connection = Database.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                Path path = Paths.get(POPULATE_DB_FILE);
                statement.execute(Files.readString(path, StandardCharsets.UTF_8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

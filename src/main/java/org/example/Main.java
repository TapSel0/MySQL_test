package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String name_of_table = "products";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Выполнение запроса
            String query = "SELECT * FROM " + name_of_table;
            ResultSet resultSet = statement.executeQuery(query);

            // Обработка результатов
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

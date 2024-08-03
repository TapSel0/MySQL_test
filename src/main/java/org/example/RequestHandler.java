package org.example;

import java.sql.*;

public class RequestHandler {
    private String table_name;

    public RequestHandler(String table_name){
        this.table_name = table_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }


    //Вывод всей таблицы (id (int), name (string), price(double))
    public void selectAll(){
        // Выполнение запроса
        String query = "SELECT * FROM " + table_name;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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

    //INSERT
    public void insert(int new_id, String new_product, double price){
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()){
            String query = "insert into " + table_name + " (id, name, price) values ("
                    + new_id + ", '" + new_product + "', " + price + ")";
            //Важно чтобы название было в одинарных ковычках, а числа с плавающей точкой именно с точкой были записаны
            //insert into products (id, name, price) values (2, 'grape', 200.0)
            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Rows affected: " + rowsAffected);
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("This key already exist");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //UPDATE
    public void update(int id, String new_product, double new_price){
        try (Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement()){
            String query = "update " + table_name +
                    " set name = '" + new_product +
                    "', price = " + new_price +
                    " where id = " + id;
            if (statement.executeUpdate(query) != 0){
                System.out.println("Element updated");
            }
            else{
                System.out.println("Element doesn't updated");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //UPDATE overload
    public void update(int id, double new_price){
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()){
            String query = "update " + table_name +
                    " set price = " + new_price +
                    " where id = " + id;
            if (statement.executeUpdate(query) != 0){
                System.out.println("Element updated");
            }
            else{
                System.out.println("Element doesn't updated");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    //UPDATE overload
    public void update(int id, String new_product){
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()){
            String query = "update " + table_name +
                    " set name = '" + new_product +
                    "' where id = " + id;
            if (statement.executeUpdate(query) != 0){
                System.out.println("Element updated");
            }
            else{
                System.out.println("Element doesn't updated");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //DELETE
    public void delete(int id){
        try (Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement()){
            String query = "delete from " + table_name
                    + " where id = " + id;
            if (statement.executeUpdate(query) != 0){
                System.out.println("Element deleted");
            }
            else{
                System.out.println("Element doesn't deleted");
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

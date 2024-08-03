package org.example;

public class Main {
    public static void main(String[] args) {
        String table_name = "products";
        RequestHandler requestHandler = new RequestHandler(table_name);
        requestHandler.selectAll();

    }
}

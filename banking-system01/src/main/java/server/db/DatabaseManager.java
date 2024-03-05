package server.db;

import client.ClientData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:C:\\Users\\mega_\\Desktop\\Univer\\Dominik\\banking-system01\\src\\main\\java\\server\\db\\clients";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Create (INSERT)
    public void addClient(ClientData client) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO clients (name, lastname, pesel, accountNumber, balance) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getPesel());
            stmt.setString(4, client.getAccountNumber());
            stmt.setDouble(5, client.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    // Read (SELECT)
    public ClientData getClient(String pesel) {
        ClientData client = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients WHERE pesel = ?")) {
            stmt.setString(1, pesel);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new ClientData(
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("pesel"),
                        rs.getString("accountNumber"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting client: " + e.getMessage());
        }
        return client;
    }

    // Update (UPDATE)
    public void updateClient(ClientData client) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE clients SET name = ?, lastname = ?, accountNumber = ?, balance = ? WHERE pesel = ?")) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getAccountNumber());
            stmt.setDouble(4, client.getBalance());
            stmt.setString(5, client.getPesel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    // Delete (DELETE)
    public void deleteClient(String pesel) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM clients WHERE pesel = ?")) {
            stmt.setString(1, pesel);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

    public void showAllClients(){
        DatabaseManager db = new DatabaseManager();
        System.out.println("All clients:");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-10s%-18s%-13s%-18s%-15s\n", "Name", "Lastname", "PESEL", "Account Number", "Balance");
        System.out.println("----------------------------------------------------------------------");

        // Получение данных о клиентах из базы данных
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients");
            ResultSet rs = stmt.executeQuery();

            // Вывод данных о клиентах в форме таблицы
            while (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String pesel = rs.getString("pesel");
                String accountNumber = rs.getString("accountNumber");
                double balance = rs.getDouble("balance");

                System.out.printf("%-10s%-13s%-18s%-18s%-15.2f\n",
                        name, lastname, pesel, accountNumber, balance);
            }

            // Закрытие ресурсов
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("----------------------------------------------------------------------");
    }





//    public static void main(String[] args) {
//        // Создание объекта для работы с базой данных
//        DatabaseManager db = new DatabaseManager();
//
//        ClientData newClient = new ClientData("Alina", "Narolska", "02229994440000", "222200002222", 12.00);
//
//        db.addClient(newClient);
//        // Вывод данных о клиентах в форме таблицы
//        System.out.println("All clients:");
//        System.out.println("----------------------------------------------------------------------");
//        System.out.printf("%-10s%-18s%-13s%-18s%-15s\n", "Name", "Lastname", "PESEL", "Account Number", "Balance");
//        System.out.println("----------------------------------------------------------------------");
//
//        // Получение данных о клиентах из базы данных
//        try {
//            Connection conn = db.getConnection();
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clients");
//            ResultSet rs = stmt.executeQuery();
//
//            // Вывод данных о клиентах в форме таблицы
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String lastname = rs.getString("lastname");
//                String pesel = rs.getString("pesel");
//                String accountNumber = rs.getString("accountNumber");
//                double balance = rs.getDouble("balance");
//
//                System.out.printf("%-10s%-13s%-18s%-18s%-15.2f\n",
//                        name, lastname, pesel, accountNumber, balance);
//            }
//
//            // Закрытие ресурсов
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        System.out.println("----------------------------------------------------------------------");
//    }
}


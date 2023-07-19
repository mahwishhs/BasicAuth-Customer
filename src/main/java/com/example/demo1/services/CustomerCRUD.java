package com.example.demo1.services;

import com.example.demo1.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerCRUD {
    public static void saveCustomer(Customer customer) throws SQLException {
        try (Connection connection = DBConn.getConnection()) {
            String query = "INSERT INTO customers (id, name, email, address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getPhone());
            statement.executeUpdate();
            System.out.println("Customer saved successfully.");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(int id, Customer customer) {
        try (Connection connection = DBConn.getConnection()) {
            String query = "UPDATE customers SET name = ?, email = ?, address = ?, phone = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int id) {
        try (Connection connection = DBConn.getConnection()) {
            String query = "DELETE FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConn.getConnection()) {
            String query = "SELECT * FROM customers";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                Customer customer = new Customer(id, name, email, address, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static Customer getCustomerById(int id) {
        Customer customer = null;
        try (Connection connection = DBConn.getConnection()) {
            String query = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                customer = new Customer(customerId, name, email, address, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static List<Customer> getCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConn.getConnection()) {
            String query = "SELECT * FROM customers WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                Customer customer = new Customer(id, customerName, email, address, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

}


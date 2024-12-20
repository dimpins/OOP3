package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class LocalServer {

    private static final Connection con = getConnectionDataBase();

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Server listening on port " + 8080);
            while (true) {
                try (Socket socket = server.accept()) {
                    System.out.println("Client connected");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isLogin(String login){
        ArrayList<String> allLogins = new ArrayList<>();

        try(Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT login FROM users")) {

            while (rs.next()) {
                allLogins.add(rs.getString("login"));
            }

            return allLogins.contains(login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isPassword(String login, String password){
        return isMatheElement(login, password,
                "SELECT password FROM users WHERE login = '" + login + "'", "password");
    }

    public static Boolean isManager(String login){
        return isMatheElement(login, "Y",
                "SELECT isManager FROM users WHERE login = '" + login + "'", "isManager");
    }

    public static void addAccount(String login, String password){
        addElement(login, password, "INSERT INTO users (login, password) VALUES (?,?)");
    }

    public static void addBook(String author, String nameBook){
        addElement(author, nameBook, "INSERT INTO books (authors, nameBooks) VALUES (?,?)");
    }

    private static void addElement(String data, String data1, String query){
        try(PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, data);
            pst.setString(2, data1);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean isMatheElement(String login, String data, String query, String columnLabel){
        try(Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            rs.next();
            return Objects.equals(rs.getString(columnLabel), data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnectionDataBase() {
        String userNameSql = "root";
        String passwordSql = "12345678";
        String connectionUrl = "jdbc:mysql://localhost:3306/accounts";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionUrl, userNameSql, passwordSql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

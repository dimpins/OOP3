package Server;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Objects;

public class LocalServer {     //класс сервера, который запускается локально и где происходит вся работа с бд

    private static final Connection con = getConnectionDataBase();     //соединение с бд

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8080)) {        //открываем сервер на порте 8080
            System.out.println("Server listening on port " + 8080);
            while (true) {                                 //ждём пока подключатся пользователи
                try (Socket socket = server.accept()) {
                    System.out.println("Client connected");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isLogin(String login) {        //проверка наличия логина в бд
        return isInDatabase(login, "SELECT EXISTS(SELECT 1 FROM users WHERE login = ?)");
    }

    public static Boolean isBook(String nameBook) {      //проверка наличия названия книги в бд
        return isInDatabase(nameBook, "SELECT EXISTS(SELECT 1 FROM books WHERE LOWER(nameBooks) = LOWER(?))");
    }

    public static Boolean isAuthor(String nameBook, String author) {       //проверка совпадения автора с названием книги
        return isMatheElement(author.replaceAll(" ", "").toLowerCase(),
                "SELECT LOWER(REPLACE(authors, ' ', '')) FROM books WHERE LOWER(nameBooks) = LOWER(?)", nameBook, "LOWER(REPLACE(authors, ' ', ''))");
    }

    private static Boolean isInDatabase(String data, String query){      //проверка наличия данных в бд
        try(PreparedStatement pst = con.prepareStatement(query)) {     //отправляем запрос (query)
            pst.setString(1, data);                      //ставим на место "?" данные для проверки
            ResultSet rs = pst.executeQuery();                         //создаём объект для сохранения в нём данных
            rs.next();                                               //сохраняем данные

            return rs.getBoolean(1);            //поверка наличия объекта

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Boolean isPassword(String login, String password){        //проверка соответствия пароля логину
        return isMatheElement(password,
                "SELECT password FROM users WHERE login = (?)", login, "password");
    }

    public static Boolean isManager(String login){        //проверка менеджер или нет
        return isMatheElement("Y",
                "SELECT isManager FROM users WHERE login = (?)", login, "isManager");
    }

    public static void addAccount(String login, String password){       //добавление аккаунта
        addElement(login, password, "INSERT INTO users (login, password) VALUES (?,?)");
    }

    public static void addBook(String author, String nameBook){     //добавление книги
        addElement(author, nameBook, "INSERT INTO books (authors, nameBooks) VALUES (?,?)");
    }

    public static void dellBook(String author, String nameBook){       //удаление книги
        try(PreparedStatement pst =                 //запрос SQL для бд, чтобы найти книгу по названия и автору по нижнему регистру и без пробелов в значении автора
                    con.prepareStatement("DELETE FROM books WHERE LOWER(REPLACE(authors, ' ', '')) = LOWER(?) AND LOWER(nameBooks) = LOWER(?)")) {
            pst.setString(1, author.replaceAll(" ", ""));     //ставим на первый ? значение автора без пробелов
            pst.setString(2, nameBook);            //ставим на второй ? название книги
            pst.executeUpdate();              //обновляем бд
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DefaultTableModel addAllBooksToTableModel(){      //добавляем все данные из таблицы books в модель таблицы
        DefaultTableModel model = new DefaultTableModel(new String[]{"Авторы", "Книги"}, 0);       //заголовки таблицы
        try(Statement st = con.createStatement();            //запрос SQL для доступа к значениям таблицы books
        ResultSet rs = st.executeQuery("SELECT authors, nameBooks FROM books")){
            while(rs.next()){       //пока элементы есть, добавляем в модель таблицы
                model.addRow(new Object[]{rs.getString("authors"),rs.getString("nameBooks")});
            }
            return model;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static DefaultTableModel addListOfAccountsToTableModel(){          //добавляем в модель список аккаунтов и их ролей
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Менеджер", "Аккаунты"}, 0) { //заголовки таблицы

            @Override             //переобределённый метод, чтобы первому столбцу задать класс boolean
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class;
            }

            @Override             //переопределённый метод, чтобы разрешить редактировать первый столбец
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0;
            }
        };
        tableModel.addTableModelListener(e ->{         //добавляем действие при изменении значения в первом столбце
            int row = e.getFirstRow();                   //считываем строку
            int column = e.getColumn();                 //считываем столбец
            if(column == 0){            //если первый
                Boolean isChecked = (Boolean) tableModel.getValueAt(row, column);      //узнаём значение ячейки
                String login = (String) tableModel.getValueAt(row, column + 1);      //узнаём логин напротив галочки
                changeIsManager(login, isChecked);          //изменяем роль в бд
            }
        });

        try(Statement st = con.createStatement();            //SQL запрос для доступа к данным из таблицы users
        ResultSet rs = st.executeQuery("SELECT isManager, login FROM users")){
            while(rs.next()){
                tableModel.addRow(new Object[]{Objects.equals(rs.getString("isManager"), "Y"),      //добавляем значение роли
                        rs.getString("login")});            //добавляем логин
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return tableModel;
    }

    private static void changeIsManager(String login, Boolean isManager){     //изменяем значение роли менеджера у логина
        try(PreparedStatement pst =            //запрос для изменения поля isManager где login равен переданному параметру
                    con.prepareStatement("UPDATE users SET isManager = ? WHERE login = ?")){
            pst.setString(1, isManager? "Y": "N");         //устанавливаем на первый ? значение в зависимости от параметра
            pst.setString(2, login);                        //устанавливаем логин
            pst.executeUpdate();                      //обновляем таблицу
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void addElement(String data, String data1, String query){ //добавление какого либо элемента
        try(PreparedStatement pst = con.prepareStatement(query)) {        //передаём SQL запрос из параметра
            pst.setString(1, data);            //устанавливаем первое значение
            pst.setString(2, data1);           //второе
            pst.executeUpdate();                            //обновляем бд
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean isMatheElement(String data, String query, String dataToMathe, String columnLabel){ //проверка совпадения
        try(PreparedStatement pst = con.prepareStatement(query)) {      //передаём sql запрос
            pst.setString(1, dataToMathe);            //ставим значение
            ResultSet rs = pst.executeQuery();
            rs.next();                                   //берём значение
            return Objects.equals(rs.getString(columnLabel), data);    //проверяем совпадение
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnectionDataBase() {          //подключение к базе данных
        String userNameSql = "root";            //логин бд
        String passwordSql = "12345678";          //пароль бд
        String connectionUrl = "jdbc:mysql://localhost:3306/accounts";       //расположение бд (нужно изменить, если будет в другом месте)

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");         //подключаем драйвер обработки MySQL бд (находится в директории drivers и подключён в зависимостях проекта)
            return DriverManager.getConnection(connectionUrl, userNameSql, passwordSql);      //возвращаем подключение
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package DB;

import java.sql.*;

public class DBHomeWork {
    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement psInsert;


//    Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java приложения.
//    id - порядковый номер записи, первичный ключ
//    prodid - уникальный номер товара
//    title - название товара
//    cost - стоимость
// При запуске приложения очистить таблицу и заполнить 10.000 товаров вида:
//    id_товара 1 товар1 10
//    id_товара 2 товар2 20
//    id_товара 3 товар3 30
//            ...
//    id_товара 10000 товар10000 100000

    public static void main(String[] args) {
        try {
            connect();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    prodid INTEGER UNIQUE,\n" +
                    "    title  TEXT,\n" +
                    "    cost   REAL\n" +
                    ");\n");

            stmt.executeUpdate("DELETE FROM products");
            conn.setAutoCommit(false);
            psInsert = conn.prepareStatement("INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?)");
            for (int i = 0; i < 10000; i++){
                psInsert.setInt(1,i+1);
                psInsert.setString(2, "product" + (i + 1));
                psInsert.setInt(3, (i + 1) * 10);
                psInsert.addBatch();
            }
            psInsert.executeBatch();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private static void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:HW2db.db");
        stmt = conn.createStatement();
    }

    private static void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

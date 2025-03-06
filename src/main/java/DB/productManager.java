package DB;

import java.sql.*;
import java.util.Scanner;

public class productManager {
    private static Connection conn;
    private static Statement stmt;
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isNotExit = true;

    public static void main(String[] args) {
        try {
            connect();
            while (isNotExit){
                String[] line = scanner.nextLine().split(" ");
                boolean isFound = false;
                ResultSet resultSet;
                switch (line[0]){
                    case "/цена":
                        String query = "SELECT cost FROM products WHERE title LIKE '" + line[1] + "';";
                        resultSet = stmt.executeQuery(query);
                        while (resultSet.next()){
                            System.out.println(line[1] + " цена " + resultSet.getFloat(1));
                            isFound = true;
                        }
                        if (!isFound){
                            System.out.println("товара " + line[1] + " не существует" );
                        }
                        break;
                    case "/сменитьцену":
                        stmt.executeUpdate("UPDATE products SET cost = " + line[2] + " WHERE title LIKE '" + line[1] + "';");
                        break;
                    case "/товарыпоцене":
                        resultSet = stmt.executeQuery("SELECT title FROM products WHERE cost BETWEEN + " + line[1] + " AND " + line[2] + ";");
                        while (resultSet.next()){
                            System.out.println(resultSet.getString(1));
                        }
                        break;
                    case "/выход":
                        isNotExit = false;
                        break;
                    default:
                        System.out.println("неизвестная командаавыфа");
                }
            }

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

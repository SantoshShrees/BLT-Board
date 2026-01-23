package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    // url for the oracle
    // private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";

    // url for the MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/bbs11?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";

    // private static final String USER = "root";
    // private static final String PASSWORD = "Bbs@11";
    private static final String USER = "bbsuser";
    private static final String PASSWORD = "Bbs@1122!";

    public static Connection getConnection() throws SQLException {
        try {
            // my sql jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
